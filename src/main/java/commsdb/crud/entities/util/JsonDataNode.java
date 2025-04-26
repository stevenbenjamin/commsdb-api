package commsdb.crud.entities.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class JsonDataNode {

    public JsonDataNode(Map<String, Object> m) {
    }

    public JsonDataNode(JsonNode node) {
    }

    public JsonDataNode(Collection<Object> c) {
    }

    public Optional<JsonNode> get(String name){ return null;}



}
