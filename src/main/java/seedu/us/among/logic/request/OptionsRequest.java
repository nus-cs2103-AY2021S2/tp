package seedu.us.among.logic.request;

import java.io.IOException;

import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpUriRequest;

import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending options requests.
 */
public class OptionsRequest extends Request {

    /**
     * Constructor for OptionRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public OptionsRequest(Endpoint endpoint) {
        super(endpoint);
    }

    /**
     * Executes the API call with an options request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response send() throws IOException, RequestException {
        HttpUriRequest request = new HttpOptions(super.getAddress());
        request = super.setHeaders(request, super.getHeaders());
        return super.execute(request);
    }
}
