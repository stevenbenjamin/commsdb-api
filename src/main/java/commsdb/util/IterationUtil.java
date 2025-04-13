package commsdb.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public class IterationUtil {

    public static <T, U> Collection<U> mapOver(Iterator<T> iter, Function<T, U> f) {
        var out = new ArrayList<U>();
        while (iter.hasNext()) {
            out.add(f.apply(iter.next()));
        }
        return out;
    }
}
