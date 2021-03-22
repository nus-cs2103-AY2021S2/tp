package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending put requests.
 */
public class PutRequest extends Request {

    /**
     * Constructor for PutRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public PutRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with a put request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpPut(super.getAddress());

        request = super.setHeaders(request, super.getHeaders());
        request = super.setData(request, super.getData());

        return super.execute(request);
    }
}
