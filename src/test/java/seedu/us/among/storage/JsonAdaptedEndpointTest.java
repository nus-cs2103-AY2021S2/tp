package seedu.us.among.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.us.among.storage.JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;

public class JsonAdaptedEndpointTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATA = "{invaliddata";
    private static final String INVALID_HEADER = "\"invalidheader";

    private static final String VALID_NAME = POST.getMethod().toString();
    private static final String VALID_ADDRESS = POST.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = POST.getTags().stream().map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_DATA = POST.getData().toString();
    private static final List<JsonAdaptedHeader> VALID_HEADERS = POST.getHeaders().stream().map(JsonAdaptedHeader::new)
            .collect(Collectors.toList());
    private static final JsonAdaptedResponse EMPTY_RESPONSE = new JsonAdaptedResponse(new Response());

    @Test
    public void toModelType_validEndpointDetails_returnsEndpoint() throws Exception {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(POST);
        assertEquals(POST, endpoint.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(INVALID_NAME, VALID_ADDRESS, VALID_DATA,
                VALID_HEADERS, VALID_TAGS, EMPTY_RESPONSE);
        String expectedMessage = Method.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(null, VALID_ADDRESS, VALID_DATA,
                VALID_HEADERS, VALID_TAGS,
                EMPTY_RESPONSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Method.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, INVALID_ADDRESS, VALID_DATA,
                VALID_HEADERS, VALID_TAGS,
                EMPTY_RESPONSE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, null, VALID_DATA,
                VALID_HEADERS, VALID_TAGS,
                EMPTY_RESPONSE);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, VALID_ADDRESS, VALID_DATA,
                VALID_HEADERS, invalidTags,
                EMPTY_RESPONSE);
        assertThrows(IllegalValueException.class, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidData_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(VALID_NAME,
                        VALID_ADDRESS,
                        INVALID_DATA,
                        VALID_HEADERS,
                        VALID_TAGS,
                        EMPTY_RESPONSE);
        String expectedMessage = Data.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullData_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, VALID_ADDRESS, null, VALID_HEADERS,
                VALID_TAGS, EMPTY_RESPONSE);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT,
                Data.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }


    @Test
    public void toModelType_invalidHeaders_throwsIllegalValueException() {
        List<JsonAdaptedHeader> invalidHeaders = new ArrayList<>(VALID_HEADERS);
        invalidHeaders.add(new JsonAdaptedHeader(INVALID_HEADER));
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, VALID_ADDRESS, VALID_DATA,
                invalidHeaders, VALID_TAGS,
                EMPTY_RESPONSE);
        assertThrows(IllegalValueException.class, endpoint::toModelType);
    }
}
