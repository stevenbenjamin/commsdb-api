package commsdb.rules;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.logging.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
public  enum FieldType {

    STRING(String.class) {
        @Override
        public String tryCast(Object o) {
            return o.toString();
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asText());
        }
    },
    INTEGER(Long.class) {
        @Override
        public Long tryCast(Object o) {
            if (o instanceof Number) {
                return ((Number) o).longValue();
            }
            return Long.parseLong(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asInt());
        }
    },
    FLOATING(Double.class) {
        @Override
        public Double tryCast(Object o) {
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
    DATE(java.util.Date.class) {
        @Override
        public Date tryCast(Object o)  throws Exception {
            return new SimpleDateFormat().parse(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            try {
                return Optional.of(tryCast(node.asText()));

            }
            catch (Exception e){
                return Optional.empty();
            }
        }
    },
    BOOLEAN(Boolean.class) {
        @Override
        public Boolean tryCast(Object o) {
            return Boolean.parseBoolean(o.toString());
        }
        @Override
        public Optional<Object> extractValue(JsonNode node) {
            return Optional.of(node.asBoolean());
        }
    };
    protected Class<?> fieldClass;
    FieldType(Class<?> fieldClass){this.fieldClass=fieldClass;}

    public static FieldType fromString(String s) {
        return FieldType.valueOf(s.toUpperCase());
    }

    protected abstract Comparable<?> tryCast(Object o) throws Exception;
    public abstract Optional<Object> extractValue(JsonNode node);

    public Optional<Object> cast(Object o) {
        if (fieldClass.isInstance(o)){
            return Optional.of(o);
        }
        try {
            return Optional.of(tryCast(o));
        } catch
        (Exception e) {
            Log.warnv("Can't cast %s to %s", o, this);
            return Optional.empty();
        }
    }

}