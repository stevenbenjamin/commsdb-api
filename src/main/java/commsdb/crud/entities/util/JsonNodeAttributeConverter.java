package commsdb.crud.entities.util;

import com.fasterxml.jackson.databind.JsonNode;
import commsdb.util.JsonUtil;
import jakarta.persistence.AttributeConverter;

import java.util.Map;

public class JsonNodeAttributeConverter implements AttributeConverter<JsonNode, String> {

    @Override
    public String convertToDatabaseColumn(JsonNode node){
        return node==null?"{}": node.toPrettyString();
    }

    @Override
    public JsonNode convertToEntityAttribute(String s) {
        return JsonUtil.toJsonNode(s).orElse(JsonUtil.EMPTY_JSON);
    }
}
