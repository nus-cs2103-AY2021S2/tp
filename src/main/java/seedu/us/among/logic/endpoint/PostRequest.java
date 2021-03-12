package seedu.us.among.logic.endpoint;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;

import seedu.us.among.logic.endpoint.exceptions.RequestException;
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
        HttpPost request = new HttpPost(super.getAddress());

        request = (HttpPost) super.setHeaders(request, super.getHeaders());
        request = (HttpPost) super.setData(request, super.getData());

        return super.execute(request);
    }
}
