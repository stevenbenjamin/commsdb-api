package commsdb.rules;

import commsdb.enums.RuleApplicationResultType;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RuleApplicationResult {

    public RuleApplicationResultType responseType;
    public String reason;
}