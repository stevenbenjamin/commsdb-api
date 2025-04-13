package commsdb.rules;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RuleApplicationResponse {
    public static enum ResponseType {
        ACCEPT,
        REJECT,
        BAD_DATA,
        NO_DATA,
        NOT_APPLICABLE
    }

    public ResponseType responseType;
    public String reason;
}