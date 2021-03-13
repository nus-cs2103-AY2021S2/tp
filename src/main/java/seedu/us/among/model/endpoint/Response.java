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

    /*
     * //to-do match everything for now
     */
    public static final String VALIDATION_REGEX = ".*";

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
        this.protocolVersion = "";
        this.statusCode = "";
        this.reasonPhrase = "";
        this.statusLine = "";
        this.responseEntity = "";
        this.responseTime = "";
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
        if (this.protocolVersion.equals("")) {
            return "No Data";
        }
        return this.protocolVersion;
    }

    public String getStatusCode() {
        if (this.statusCode.equals("")) {
            return "No Data";
        }
        return this.statusCode;
    }

    public String getReasonPhrase() {
        if (this.reasonPhrase.equals("")) {
            return "No Data";
        }
        return this.reasonPhrase;
    }

    public String getStatusLine() {
        if (this.statusLine.equals("")) {
            return "No Data";
        }
        return this.statusLine;
    }

    public String getResponseEntity() {
        if (this.responseEntity.equals("")) {
            return "No Data";
        }
        try {
            return JsonUtil.toPrettyPrintJsonString(this.responseEntity);
        } catch (IOException ex) {
            return this.responseEntity; // not of JSON format
        }
    }

    public String getResponseTime() {
        if (this.responseTime.equals("")) {
            return "No Data";
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
                + "\nResponse Body:\n"
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
