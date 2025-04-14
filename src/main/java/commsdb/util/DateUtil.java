package commsdb.util;

import io.quarkus.logging.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtil {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    /** Expect a string of the form "2011-12-03"
     */
    public static Optional<LocalDate> parse(String s){
        try {
            return Optional.of(LocalDate.parse(s, formatter));
        }
        catch (Exception e){
            Log.warnf("Could not parse \"%s\" as a date", s);
            return Optional.empty();
        }
    }

}
