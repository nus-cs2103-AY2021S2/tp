package seedu.us.among.logic.endpoint;

import java.io.IOException;

import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for making API calls.
 */
public class EndpointCaller {
    private final Endpoint endpointToSend;

    /**
     * Constructor for Endpointcaller.
     *
     * @param endpointToSend endpoint to make API call on
     */
    public EndpointCaller(Endpoint endpointToSend) {
        this.endpointToSend = endpointToSend;
    }

    /**
     * Sends the appropriate request based on attributes provided in EndpointCaller.
     *
     * @return response of API call
     */
    public Response callEndpoint() throws IOException {

        Response response = new Response();
        MethodType requestMethod = this.endpointToSend.getMethod().getMethodType();

        switch (requestMethod) {
        case GET:
            response = new GetRequest(endpointToSend).send();
            break;
        case POST:
            //to-do
            break;
        case PUT:
            //to-do
            break;
        case DELETE:
            //to-do
            break;
        case HEAD:
            //to-do
            break;
        case OPTIONS:
            //to-do
            break;
        case PATCH:
            //to-do
        default:
            break;
        }

        //mimics delay in API call, comment out if necessary //to-do Tan Jin remove after testing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            //try-catch to allow thread to sleep
        }

        return response;
    }
}
