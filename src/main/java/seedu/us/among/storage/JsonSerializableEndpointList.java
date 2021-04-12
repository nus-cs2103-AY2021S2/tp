package seedu.us.among.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * An Immutable EndpointList that is serializable to JSON format.
 */
@JsonRootName(value = "API endpoint list")
class JsonSerializableEndpointList {

    public static final String MESSAGE_DUPLICATE_ENDPOINT = "API endpoint list contains duplicate endpoint(s).";

    private final List<JsonAdaptedEndpoint> endpoints = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEndpointList} with the given endpoints.
     */
    @JsonCreator
    public JsonSerializableEndpointList(@JsonProperty("endpoints") List<JsonAdaptedEndpoint> endpoints) {
        this.endpoints.addAll(endpoints);
    }

    /**
     * Converts a given {@code ReadOnlyEndpointList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEndpointList}.
     */
    public JsonSerializableEndpointList(ReadOnlyEndpointList source) {
        endpoints.addAll(source.getEndpointList().stream().map(JsonAdaptedEndpoint::new).collect(Collectors.toList()));
    }

    /**
     * Converts this API endpoint list into the model's {@code EndpointList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EndpointList toModelType() throws IllegalValueException {
        EndpointList endpointList = new EndpointList();
        for (JsonAdaptedEndpoint jsonAdaptedEndpoint : endpoints) {
            Endpoint endpoint = jsonAdaptedEndpoint.toModelType();
            if (endpointList.hasEndpoint(endpoint)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENDPOINT);
            }
            endpointList.addEndpoint(endpoint);
        }
        return endpointList;
    }

}
