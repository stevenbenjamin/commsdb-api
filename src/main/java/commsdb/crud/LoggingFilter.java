package commsdb.crud;

import io.quarkus.logging.Log;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
class LoggingFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        Log.info("Request: " + requestContext.getMethod() + " " + requestContext.getUri());
        // Add additional logging as needed
    }
}