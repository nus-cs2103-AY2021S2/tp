package seedu.us.among.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.endpoint.Response;

/**
 * Jackson-friendly version of {@link Response}.
 */
class JsonAdaptedResponse {

    private final String protocolVersion;
    private final String statusCode;
    private final String reasonPhrase;
    private final String statusLine;
    private final String responseEntity;
    private final String responseTime;

    /**
     * Constructs a {@code JsonAdaptedResponse} with the given {@code protocolVersion, @code statusCode,
     * @code reasonPhrase and @code statusLine}.
     */
    @JsonCreator
    public JsonAdaptedResponse(@JsonProperty("protocolVersion") String protocolVersion,
                               @JsonProperty("statusCode") String statusCode,
                               @JsonProperty("reasonPhrase") String reasonPhrase,
                               @JsonProperty("statusLine") String statusLine,
                               @JsonProperty("responseEntity") String responseEntity,
                               @JsonProperty("responseTime") String responseTime) {
        this.protocolVersion = protocolVersion;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.statusLine = statusLine;
        this.responseEntity = responseEntity;
        this.responseTime = responseTime;

    }

    /**
     * Converts a given {@code Response} into this class for Jackson use.
     */
    public JsonAdaptedResponse(Response source) {
        this.protocolVersion = source.getProtocolVersion();
        this.statusCode = source.getStatusCode();
        this.reasonPhrase = source.getReasonPhrase();
        this.statusLine = source.getStatusLine();
        this.responseEntity = source.getResponseEntity();
        this.responseTime = source.getResponseTime();
    }

    /**
     * Converts this Jackson-friendly adapted response object into the model's {@code Response} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted response.
     */
    public Response toModelType() throws IllegalValueException {
        if (!Response.isValidResponse(protocolVersion, statusCode, reasonPhrase, statusLine)) {
            throw new IllegalValueException(Response.MESSAGE_CONSTRAINTS);
        }
        return new Response(protocolVersion, statusCode, reasonPhrase, statusLine, responseEntity, responseTime);
    }

}
