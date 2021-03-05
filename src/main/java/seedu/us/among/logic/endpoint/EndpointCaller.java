package seedu.us.among.logic.endpoint;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

public class EndpointCaller {

    //to-do - using test endpoint as placeholder for now
    private final Endpoint endpointToSend;

    public EndpointCaller(Endpoint endpointToSend) {
        this.endpointToSend = endpointToSend;
    }

    /**
     * Calls the API endpoint with the attributes provided in EndpointCaller.
     *
     * @return response of API call, currently an int status code as placeholder
     */
    public Response callEndpoint() throws IOException {

        //solution adapted from https://mkyong.com/java/apache-httpclient-examples/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;

        try {

            //test url
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/todo/1");

            //test header
            request.addHeader("test-header", "test-header-value");

            response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion()); // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode()); // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return new Response(response.getProtocolVersion().toString(),
                String.valueOf(response.getStatusLine().getStatusCode()),
                response.getStatusLine().getReasonPhrase(),
                response.getStatusLine().toString());
    }
}
