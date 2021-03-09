package seedu.us.among.logic.endpoint;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;

import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending get requests.
 */
public class GetRequest extends Request {

    /**
     * Constructor for GetRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public GetRequest(Endpoint endpoint) {
        super(endpoint.getMethod().getMethodType(), endpoint.getAddress().value);
    }

    /**
     * Executes the API call with a get request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException {
        HttpGet request = new HttpGet(this.getAddress());
        return super.execute(request);
    }
}
