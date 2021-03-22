package seedu.us.among.logic.request;

import static seedu.us.among.commons.core.Messages.MESSAGE_CALL_CANCELLED;
import static seedu.us.among.commons.core.Messages.MESSAGE_CONNECTION_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_GENERAL_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_JSON;
import static seedu.us.among.commons.core.Messages.MESSAGE_UNKNOWN_HOST;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for making API calls.
 */
public class EndpointCaller {
    private static final Logger logger = LogsCenter.getLogger(EndpointCaller.class);

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
     * Calls the endpoint based on attributes provided in EndpointCaller.
     *
     * @return response of API call
     */
    public Response callEndpoint() throws RequestException, AbortRequestException {

        Response response;

        //to-do Tan Jin verify again all errors caught here due to API calls
        try {
            response = sendRequest();
        } catch (UnknownHostException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_UNKNOWN_HOST);
        } catch (ClientProtocolException | SocketTimeoutException | SocketException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_CONNECTION_ERROR);
        } catch (JsonParseException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_INVALID_JSON);
        } catch (IllegalStateException | SSLException | NoHttpResponseException | InterruptedIOException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new AbortRequestException(MESSAGE_CALL_CANCELLED);
        } catch (IOException e) {
            logger.warning(StringUtil.getDetails(e));
            throw new RequestException(MESSAGE_GENERAL_ERROR);
        }

        return response;
    }

    /**
     * Matches and sends the request based on type of method specified.
     *
     * @return response of API call
     */
    public Response sendRequest() throws IOException, RequestException {
        MethodType requestMethod = this.endpointToSend.getMethodType();
        switch (requestMethod) {
        case GET:
            return new GetRequest(endpointToSend).send();
        case POST:
            return new PostRequest(endpointToSend).send();
        case PUT:
            return new PutRequest(endpointToSend).send();
        case DELETE:
            return new DeleteRequest(endpointToSend).send();
        case HEAD:
            return new HeadRequest(endpointToSend).send();
        case OPTIONS:
            return new OptionsRequest(endpointToSend).send();
        case PATCH:
            return new PatchRequest(endpointToSend).send();
        default:
            return new Response();
        }
    }
}
