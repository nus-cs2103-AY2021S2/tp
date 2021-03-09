package seedu.us.among.logic.endpoint;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;

import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending post requests.
 */
public class PostRequest extends Request {

    private final Endpoint endpoint;

    /**
     * Constructor for PostRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public PostRequest(Endpoint endpoint) {
        super(endpoint.getMethod().getMethodType(), endpoint.getAddress().value);
        this.endpoint = endpoint;
    }

    /**
     * Executes the API call with a post request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException {
        HttpPost request = new HttpPost(this.getAddress());

        request = (HttpPost) super.setHeaders(request, this.endpoint.getHeaders());
        request = (HttpPost) super.setData(request, this.endpoint.getData());

        return super.execute(request);
    }
}
