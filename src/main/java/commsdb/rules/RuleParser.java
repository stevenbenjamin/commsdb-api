package commsdb.rules;

import commsdb.crud.entities.RuleData;
import commsdb.util.JsonUtil;
/** Poor man's parser.*/
public class RuleParser {

    /** For the moment we're going to assume rules are expressed
     *ApplyRules
     * op rule
     * { "name":fieldName, "op", "=", "value":value}
     *
     *
     * Add a NOT rule
     *
     * Add AND, OR rules
     *
     * Add a Content contains Rule
     *
     * Add a Date rule
     */
    static Rule parse(RuleData ruleData, FieldType fieldType){
        try {
            var json = JsonUtil.toJsonNode(ruleData.expr).get();
            var fieldName = json.get("name").asText();
            var fieldValue = json.get("value").asText();
            return switch (json.get("op").asText()) {
                case "=" -> OpRule.EQ(fieldName, fieldValue);
                case ">" -> OpRule.LT(fieldName, fieldValue);
                case "<" -> OpRule.GT(fieldName, fieldValue);
                case ">=" -> OpRule.LTE(fieldName, fieldValue);
                case "<=" -> OpRule.GTE(fieldName, fieldValue);
                default -> throw new RuleParsingException("Can't parse "+ruleData.expr);
            };
        } catch (Exception e){
            throw new RuleParsingException("Can't parse as json: "+ruleData.expr);
        }
    }
}
