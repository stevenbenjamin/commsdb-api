package commsdb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;


public class JsonMatcher<T> extends BaseMatcher {
    private static final Logger log = LoggerFactory.getLogger(JsonMatcher.class);
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
            log.error("{} is not an instance of {}", o, klazz.getSimpleName());
            return false;
        }
        return f.apply(value);
    }

    @Override
    public void describeTo(Description description) {
        System.out.println(description);
    }
}