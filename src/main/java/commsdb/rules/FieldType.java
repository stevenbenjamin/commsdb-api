package commsdb.rules;

import com.fasterxml.jackson.databind.JsonNode;
import commsdb.crud.entities.util.EnumAttributeConverter;
import commsdb.util.DateUtil;
import io.quarkus.logging.Log;
import io.vavr.control.Try;

import java.time.LocalDate;
import java.util.Optional;
public  enum FieldType {

    String(String.class) {
        @Override
        protected String tryCast(Object o) {
            return o.toString();
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asText());
        }
    },
    Integer(Long.class) {
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
    Floating(Double.class) {
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
    Date(LocalDate.class) {

        @Override
        protected LocalDate tryCast(Object o)  {
            return DateUtil.parse( o.toString()).orElseThrow(() -> new RuntimeException("Can't parse "+o+" as a date"));
        }

        @Override
        public Optional<Object> extractValue(JsonNode node) {
           return DateUtil.parse(node.asText()).map(d -> (Object)d);
        }
    },
    Boolean(Boolean.class) {
        @Override
        protected java.lang.Boolean tryCast(Object o) {
            return java.lang.Boolean.parseBoolean(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asBoolean());
        }
    };
    protected   Class<?> fieldClass = null;
    FieldType(Class<?> fieldClass){this.fieldClass=fieldClass;}

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

    public static  class Converter extends EnumAttributeConverter<FieldType> {
        public Converter(){super(FieldType.class);}
    }
}