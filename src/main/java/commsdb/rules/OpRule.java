package commsdb.rules;


import commsdb.crud.entities.Form;
import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.Submission;
import commsdb.util.JsonUtil;
import io.quarkus.logging.Log;

import java.util.Optional;
import java.util.function.BiPredicate;

import static commsdb.rules.RuleApplicationResponse.ResponseType.*;


public class OpRule implements Rule {
    String fieldName;
    Object fieldValue;
    Op op;

    public static enum Op {
        EQ("=", (o, o2) -> applyCompare(o, o2) == 0),
        LT("<",(o, o2) -> applyCompare(o, o2) < 0),
        GT(">",(o, o2) -> applyCompare(o, o2) > 0),
        LTE("<=",(o, o2) -> applyCompare(o, o2) <= 0),
        GTE(">=",(o, o2) -> applyCompare(o, o2) >= 0);

        public final String operator;
        public final BiPredicate<Object, Object> comparator;
         Op(String operator, BiPredicate<Object,Object> f){
            this.operator = operator;
            this.comparator =f;
        }

        /**
         * Return comparator result iff o & o2 are the same type and implement comparable.
         */
         static int applyCompare(Object o, Object o2) throws RuleApplicationException {
            if (o == null || o2 == null) {
                throw new RuleApplicationException("Can't apply rule to empty values");
            }

            var klazz = o.getClass();
            var klazz2 = o2.getClass();
            if (klazz != klazz2) {
                throw new RuleApplicationException(String.format("Can't compare types %s %s", klazz.getSimpleName(), klazz2.getSimpleName()));
            }
            if (!(o instanceof Comparable co)) {
                throw new RuleApplicationException(String.format("Can't compare types %s", klazz.getSimpleName()));
            }

            var c2o = co.getClass().cast(o2);
            return co.compareTo(c2o);
        }

    }
    protected OpRule(Op op, String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.op = op;
    }

    public static OpRule EQ(String fieldName, Object fieldValue) {
        return new OpRule(Op.EQ,fieldName, fieldValue) {
        };
    }

    public static OpRule LT(String fieldName, Object fieldValue) {
        return new OpRule(Op.LT,fieldName, fieldValue) {
        };

    }

    public static OpRule GT(String fieldName, Object fieldValue) {
        return new OpRule(Op.GT,fieldName, fieldValue) {
        };
    }

    public static OpRule LTE(String fieldName, Object fieldValue) {
        return new OpRule(Op.LTE,fieldName, fieldValue) {
        };

    }

    public static OpRule GTE(String fieldName, Object fieldValue) {
        return new OpRule(Op.GTE,fieldName, fieldValue) {
        };
    }



    private Optional<Object> extractSubmissionValue(Submission submission, FieldType fieldType) {
        var json = JsonUtil.toJsonNode(submission.data).map(j -> j.get(fieldName));
        if (json.isEmpty() ) {
            Log.warnf("Error extracting %s:(type=%s) from %s", fieldName, fieldType, submission.data);
            return Optional.empty();
        }
        return json.flatMap(fieldType::extractValue);
    }

    @Override
    public RuleApplicationResponse apply(Submission submission, Form form) {
        var optFieldType = form.getField(fieldName).map(f -> FieldType.fromString(f.fieldType));
        if (optFieldType.isEmpty()){
            Log.warnf("Can't determine field type for %s, using string", fieldName);
        }
        var fieldType = optFieldType.orElse(FieldType.STRING);
        var submittedValue = extractSubmissionValue(submission,fieldType );

        if (submittedValue.isEmpty()) {
            return new RuleApplicationResponse(NO_DATA, fieldName);
        }

        var testSuccess = op.comparator.test( submittedValue.get(), fieldType.cast(fieldValue));
        return  testSuccess?   new RuleApplicationResponse(ACCEPT, fieldName): new RuleApplicationResponse(REJECT, fieldName);
    }

    @Override
    public RuleData toRuleData() {
        return RuleData.builder()
                .name("")
                .ruleType(op.name())
                .expr(String.format("%s %s %s", fieldName, op.operator,fieldValue))
                .description("")
                .fieldName(this.fieldName)
                .build();
    }


}
