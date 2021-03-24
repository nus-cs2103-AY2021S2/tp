package seedu.us.among.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Endpoint}.
 */
class JsonAdaptedEndpoint {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Endpoint's %s field is missing!";

    private final String method;
    private final String address;
    private final String data;
    private final List<JsonAdaptedHeader> headers = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private JsonAdaptedResponse response;

    /**
     * Constructs a {@code JsonAdaptedEndpoint} with the given endpoint details.
     */
    @JsonCreator
    public JsonAdaptedEndpoint(@JsonProperty("method") String method,
                               @JsonProperty("address") String address,
                               @JsonProperty("data") String data,
                               @JsonProperty("headers") List<JsonAdaptedHeader> headers,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("response") JsonAdaptedResponse response) {
        this.method = method;
        this.address = address;
        this.data = data;

        if (headers != null) {
            this.headers.addAll(headers);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged); // to-do
        }
        if (response != null) {
            this.response = response;
        }
    }

    /**
     * Converts a given {@code Endpoint} into this class for Jackson use.
     */
    public JsonAdaptedEndpoint(Endpoint source) {
        method = source.getMethod().methodName;
        address = source.getAddress().value;
        data = source.getData().value;
        headers.addAll(source.getHeaders().stream()
                .map(JsonAdaptedHeader::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        Response sourceResponse = source.getResponse();
        response = new JsonAdaptedResponse(sourceResponse.getProtocolVersion(),
                sourceResponse.getStatusCode(),
                sourceResponse.getReasonPhrase(),
                sourceResponse.getStatusLine(),
                sourceResponse.getResponseEntity(),
                sourceResponse.getResponseTime());
    }

    /**
     * Converts this Jackson-friendly adapted endpoint object into the model's
     * {@code Endpoint} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted endpoint.
     */
    public Endpoint toModelType() throws IllegalValueException {
        final List<Tag> endpointTags = new ArrayList<>();
        final List<Header> endpointHeaders = new ArrayList<>();
        for (JsonAdaptedHeader header: headers) {
            endpointHeaders.add(header.toModelType());
        }

        for (JsonAdaptedTag tag : tagged) {
            endpointTags.add(tag.toModelType());
        }

        if (method == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Method.class.getSimpleName()));
        }
        if (!Method.isValidMethod(method)) {
            throw new IllegalValueException(Method.MESSAGE_CONSTRAINTS);
        }
        final Method modelName = new Method(method);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Data modelData;
        if (data == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Data.class.getSimpleName()));
        }
        if (data.equals("")) {
            modelData = new Data();
        } else if (!Data.isValidData(data)) {
            throw new IllegalValueException(Data.MESSAGE_CONSTRAINTS);
        } else {
            modelData = new Data(data);
        }

        final Response modelResponse;
        if (response == null) {
            modelResponse = new Response();
        } else {
            Response newModelResponse = response.toModelType();
            modelResponse = new Response(newModelResponse.getProtocolVersion(),
                    newModelResponse.getStatusCode(), newModelResponse.getReasonPhrase(),
                    newModelResponse.getStatusLine(), newModelResponse.getResponseEntity(),
                    newModelResponse.getResponseTime());
        }

        final Set<Header> modelHeaders = new HashSet<>(endpointHeaders);
        final Set<Tag> modelTags = new HashSet<>(endpointTags);
        return new Endpoint(modelName, modelAddress, modelData, modelHeaders, modelTags, modelResponse);
    }

}
