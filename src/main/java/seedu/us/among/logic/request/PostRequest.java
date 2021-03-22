package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending post requests.
 */
public class PostRequest extends Request {

    /**
     * Constructor for PostRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public PostRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with a post request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpPost(super.getAddress());

        request = super.setHeaders(request, super.getHeaders());
        request = super.setData(request, super.getData());

        return super.execute(request);
    }
}
