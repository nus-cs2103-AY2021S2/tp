package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending delete requests.
 */
public class DeleteRequest extends Request {

    /**
     * Constructor for DeleteRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public DeleteRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with a delete request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpDelete(super.getAddress());
        request = super.setHeaders(request, super.getHeaders());
        return super.execute(request);
    }
}
