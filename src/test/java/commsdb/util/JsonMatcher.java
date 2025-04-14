package commsdb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.function.Function;


public class JsonMatcher<T> extends BaseMatcher {
    ObjectMapper mapper = new ObjectMapper();
    Function<T, Boolean> f = t -> true;
    Class<T> klazz;
    public T value;
    public JsonMatcher(Class<T> klazz ) {
      this.klazz = klazz;
    }

    public JsonMatcher(Class<T> klazz, Function<T, Boolean> f) {
        this(klazz);
        this.f = f;
    }

    @Override
    public boolean matches(Object o) {
        try {
            value = mapper.readValue(o.toString(), klazz);
        } catch(Exception e){
            e.printStackTrace();
            Log.errorv("%s is not an instance of %s", o, klazz.getSimpleName());
            return false;
        }
        return f.apply(value);
    }

    @Override
    public void describeTo(Description description) {
        System.out.println(description);
    }
}