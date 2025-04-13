package commsdb.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import commsdb.crud.entities.RuleData;
import commsdb.util.JsonUtil;
/** Poor man's parser.*/
public class RuleParser {

    /** For the momenet we're going to assume rules are expressed
     *
     * op rule
     * { "name":fieldName, "op", "=", "value":value}
     */
    static Rule parse(RuleData ruleData, FieldType fieldType){
        try {
            var json = JsonUtil.toJsonNode(ruleData.rule).get();
            var fieldName = json.get("name").asText();
            var fieldValue = json.get("value").asText();
            return switch (json.get("op").asText()) {
                case "=" -> OpRule.EQ(fieldName, fieldValue);
                case ">" -> OpRule.LT(fieldName, fieldValue);
                case "<" -> OpRule.GT(fieldName, fieldValue);
                case ">=" -> OpRule.LTE(fieldName, fieldValue);
                case "<=" -> OpRule.GTE(fieldName, fieldValue);
                default -> throw new RuleParsingException("Can't parse "+ruleData.rule);
            };
        } catch (Exception e){
            throw new RuleParsingException("Can't parse as json: "+ruleData.rule);
        }
    }
}
