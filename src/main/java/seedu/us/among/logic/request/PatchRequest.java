package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending patch requests.
 */
public class PatchRequest extends Request {

    /**
     * Constructor for PatchRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public PatchRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with a patch request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpPatch(super.getAddress());
        request = super.setHeaders(request, super.getHeaders());
        return super.execute(request);
    }
}
