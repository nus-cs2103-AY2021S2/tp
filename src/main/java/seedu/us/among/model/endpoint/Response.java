package seedu.us.among.model.endpoint;

import static seedu.us.among.commons.util.AppUtil.checkArgument;
import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.util.Objects;

import seedu.us.among.commons.util.JsonUtil;

/**
 * Represents an Endpoint's response after an API call.
 * Guarantees: immutable; is valid as declared in {@link #isValidResponse(String, String, String, String)}
 */
public class Response {
    public static final String MESSAGE_CONSTRAINTS = "Responses can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = ".*";
    public static final String EMPTY_RESPONSE = "";
    public static final String EMPTY_RESPONSE_STRING = "No Data";

    public final String protocolVersion;
    public final String statusCode;
    public final String reasonPhrase;
    public final String statusLine;
    public final String responseEntity;
    public final String responseTime;

    /**
     * Constructs an empty {@code Response}.
     */
    public Response() {
        this.protocolVersion = EMPTY_RESPONSE;
        this.statusCode = EMPTY_RESPONSE;
        this.reasonPhrase = EMPTY_RESPONSE;
        this.statusLine = EMPTY_RESPONSE;
        this.responseEntity = EMPTY_RESPONSE;
        this.responseTime = EMPTY_RESPONSE;
    }

    /**
     * Constructs an {@code Response}.
     *
     * @param protocolVersion A valid http protocol version.
     * @param statusCode A valid http status code.
     * @param reasonPhrase A valid reason phrase corresponding to status code.
     * @param statusLine Contains protocolVersion, statusCode and reasonPhrase.
     */
    public Response(String protocolVersion, String statusCode, String reasonPhrase, String statusLine,
                String responseEntity, String responseTime) {
        requireAllNonNull(protocolVersion, statusCode, reasonPhrase, statusLine);
        checkArgument(isValidResponse(protocolVersion, statusCode, reasonPhrase, statusLine), MESSAGE_CONSTRAINTS);
        this.protocolVersion = protocolVersion;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.statusLine = statusLine;
        this.responseEntity = responseEntity;
        this.responseTime = responseTime;
    }

    /**
     * Returns true if current statusCode and reasonPhrase are empty.
     * @return a boolean
     */
    public boolean isReasonEmpty() {
        boolean firstCondition = this.statusCode.equals(EMPTY_RESPONSE)
                && this.reasonPhrase.equals(EMPTY_RESPONSE);
        boolean secondCondition = this.statusCode.equals(EMPTY_RESPONSE_STRING)
                && this.reasonPhrase.equals(EMPTY_RESPONSE_STRING);
        return firstCondition || secondCondition;
    }

    /**
     * Returns true if a given string is a valid response.
     */
    public static boolean isValidResponse(String protocolVersion, String statusCode, String reasonPhrase,
            String statusLine) {
        return protocolVersion.matches(VALIDATION_REGEX)
                && statusCode.matches(VALIDATION_REGEX)
                && reasonPhrase.matches(VALIDATION_REGEX)
                && statusLine.matches(VALIDATION_REGEX);
    }

    public String getProtocolVersion() {
        if (this.protocolVersion.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        return this.protocolVersion;
    }

    public String getStatusCode() {
        if (this.statusCode.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        return this.statusCode;
    }

    public String getReasonPhrase() {
        if (this.reasonPhrase.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        return this.reasonPhrase;
    }

    public String getStatusLine() {
        if (this.statusLine.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        return this.statusLine;
    }

    public String getResponseEntity() {
        if (this.responseEntity.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        try {
            return JsonUtil.toPrettyPrintJsonString(this.responseEntity);
        } catch (IOException ex) {
            return this.responseEntity; // not of JSON format
        }
    }

    public String getResponseTime() {
        if (this.responseTime.equals(EMPTY_RESPONSE)) {
            return EMPTY_RESPONSE_STRING;
        }
        return this.responseTime;
    }

    @Override
    public String toString() {
        return "Time Taken: "
                + getResponseTime()
                + " | Protocol Version: "
                + getProtocolVersion()
                + " | Status Code: "
                + getStatusCode()
                + " | Reason Phrase: "
                + getReasonPhrase()
                + "\n\nResponse Body:\n"
                + getResponseEntity();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Response // instanceof handles nulls
                && protocolVersion.equals(((Response) other).protocolVersion)
                && statusCode.equals(((Response) other).statusCode)
                && reasonPhrase.equals(((Response) other).reasonPhrase)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocolVersion, statusCode, reasonPhrase);
    }
}
