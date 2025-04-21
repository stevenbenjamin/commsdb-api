package commsdb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.NullNode;
import io.quarkus.logging.Log;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonUtil {


    public static JsonNode EMPTY_JSON = NullNode.getInstance();
    public static Optional<JsonNode> toJsonNode(String s)   {

        try {
            return Optional.of(new ObjectMapper().readTree(s));
        }
        catch( JsonProcessingException e){
            Log.warnf("Can't convert to json: %s", s);
            return Optional.empty();
        }
    }

    public static <T> T fromString(String s, Class<T> klazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(s, klazz);
    }

    public static String toJsonString(Object o )  {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
        }
        catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

//     private static Object toNumber (JsonNode node){
//        return node.isInt()?  node.asInt():node.asDouble();
//     }
//    public static Optional<Object> fromJsonNode(JsonNode node) {
//        if (node == null) return Optional.empty();
//        var i= Optional.of(
//                switch (node.getNodeType()) {
//            case JsonNodeType.BOOLEAN -> node.asBoolean();
//            case JsonNodeType.NUMBER  -> toNumber(node);
//            case JsonNodeType.STRING -> node.asText();
//            case JsonNodeType.NULL -> null;
//            case JsonNodeType.ARRAY -> IterationUtil.mapOver(node.elements(), JsonUtil::fromJsonNode);
//            case JsonNodeType.OBJECT -> IterationUtil.mapOver(node.fields(), e -> e)
//                    .stream()
//                    .collect(Collectors.toMap(Map.Entry::getKey, e -> fromJsonNode(e.getValue())));
//
//            default -> null;
//        }
//                );
//
//        System.out.println("I = "+i+ " "+i.get().getClass());
//        return i;

    //}



}

