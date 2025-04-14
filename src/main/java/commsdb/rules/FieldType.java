package commsdb.rules;

import com.fasterxml.jackson.databind.JsonNode;
import commsdb.util.DateUtil;
import io.quarkus.logging.Log;
import io.vavr.control.Try;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Optional;
public  enum FieldType {

    STRING(String.class) {
        @Override
        protected String tryCast(Object o) {
            return o.toString();
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asText());
        }
    },
    INTEGER(Long.class) {
        @Override
        protected Long tryCast(Object o) {
            if (o instanceof Number) {
                return ((Number) o).longValue();
            }
            return Long.parseLong(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asLong());
        }
    },
    FLOATING(Double.class) {
        @Override
        protected Double tryCast(Object o) {
            if (o instanceof Number) {
                return ((Number) o).doubleValue();
            }
            return Double.parseDouble(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asDouble());
        }
    },
    DATE(LocalDate.class) {

        @Override
        protected LocalDate tryCast(Object o)  {
            return DateUtil.parse( o.toString()).orElseThrow(() -> new RuntimeException("Can't parse "+o+" as a date"));
        }

        @Override
        public Optional<Object> extractValue(JsonNode node) {
           return DateUtil.parse(node.asText()).map(d -> (Object)d);
        }
    },
    BOOLEAN(Boolean.class) {
        @Override
        protected Boolean tryCast(Object o) {
            return Boolean.parseBoolean(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asBoolean());
        }
    };
    protected   Class<?> fieldClass = null;
    FieldType(Class<?> fieldClass){this.fieldClass=fieldClass;}

    public static FieldType fromString(String s) {
        return FieldType.valueOf(s.toUpperCase());
    }
    public  Object cast (Object o)  {
        if (fieldClass.isInstance(o)){
            return o;
        }
        return Try.of(() -> tryCast(o)).getOrElse( () -> {
            Log.warnf("Can't cast %s to %s", o, this.fieldClass);
            return null;
        });
    }
    protected abstract Comparable<?> tryCast(Object o)  ;
    public abstract Optional<Object> extractValue(JsonNode node);

}