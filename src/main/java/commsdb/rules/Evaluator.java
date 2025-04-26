package commsdb.rules;

import commsdb.crud.entities.*;
import commsdb.enums.RuleApplicationResultType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static commsdb.util.JsonUtil.toJson;

public class Evaluator {

    record EvalRule(RuleData rd, Optional<Rule> rule) {
    }

    record EvalData(RuleData rd, RuleApplicationResult result) {
    }
    /*
     * RuleData
     * -> ( Form.getFieldData())
     * -> (Rule, RuleData)
     * -> (RuleApplicationResponse, RuleData)
     * -> (RuleApplicationResponse, (Actions)
     *
     *
     * OnRuleAction: Represents the action to be taken on the application of a rule
     * Action: Represents the action that WAS taken
     * Representation: Way to save recommended actions (needed? )
     */
    static List<Action> evaluate(Submission s, Form f) {
        Stream<EvalRule> evalRuleStream = f.rules.stream().map(rd -> //
                new EvalRule(rd, f.getFieldType(rd.name).map(ft -> RuleParser.parse(rd, ft))));

        //eval
        Stream<EvalData>  evalDataStream = evalRuleStream.map(ed -> new EvalData(ed.rd,//
                ed.rule.isEmpty() ?//
                        new RuleApplicationResult(RuleApplicationResultType.NotApplicable, "No form field for " + ed.rd.name)//
                        : ed.rule.get().apply(s, f)));

       return evalDataStream.flatMap(Evaluator::applyRule).toList();
    }



    static Stream<Action> applyRule(EvalData ed) {
        /*
        switch (ed.result.responseType) {
            case Accept -> {

            }

            case Reject -> case BadData -> case NoData -> case NotApplicable ->
        }
*/
    }

    static Action applyOnRuleAction(EvalData ed, OnRuleAction ora){
       return switch (ora.actionType) {
           case Forward -> {
                return   Action.builder()
                        .description(ora.description)
                        .extraData(
                        toJson(Map.of("forwad_to_id", ora.extraData. )
           }
           case Approve -> {
           };
           case Deny -> {
           };
           case None -> {
           };
           case SetPriority -> {
           };
       }
    }


}

