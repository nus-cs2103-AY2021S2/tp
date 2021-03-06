package seedu.us.among.logic.endpoint;

import java.io.IOException;

import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;

/**
 * Parent class of request sending classes. Contains the two compulsory fields method and address.
 */
public abstract class Request {
    private final MethodType method;
    private final String address;

    /**
     * Constructor for Request.
     *
     * @param method request method
     * @param address request address
     */
    public Request(MethodType method, String address) {
        this.method = method;
        this.address = address;
    }

    public MethodType getMethodType() {
        return this.method;
    }

    public String getAddress() {
        return this.address;
    }

    /**
     * Executes the API call by sending the appropriate request.
     *
     * @return returns the response from the API call
     */
    public abstract Response send() throws IOException;
}
