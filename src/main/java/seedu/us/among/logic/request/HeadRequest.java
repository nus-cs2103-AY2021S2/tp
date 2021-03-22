package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending head requests.
 */
public class HeadRequest extends Request {

    /**
     * Constructor for HeadRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public HeadRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with a head request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpHead(super.getAddress());
        request = super.setHeaders(request, super.getHeaders());
        return super.execute(request);
    }
}
