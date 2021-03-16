package seedu.us.among.logic.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.util.SampleDataUtil;
import seedu.us.among.testutil.TypicalEndpoints;

public class HeadRequestTest {

    private final Endpoint sampleValidEndpoint = TypicalEndpoints.HEAD;
    private final Endpoint sampleHtmlResponseEndpoint = SampleDataUtil.getSampleHtmlResponseEndpoint("HEAD");
    private final Endpoint sampleInvalidUrlEndpoint = SampleDataUtil.getSampleInvalidUrlEndpoint("HEAD");
    private final HeadRequest jsonRequest = new HeadRequest(sampleValidEndpoint);
    private final HeadRequest htmlRequest = new HeadRequest(sampleHtmlResponseEndpoint);
    private final HeadRequest invalidUrlRequest = new HeadRequest(sampleInvalidUrlEndpoint);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HeadRequest(null));
    }

    @Test
    public void execute_invalidUrlEndpoint_throwsUnknownHostException() {
        assertThrows(UnknownHostException.class, invalidUrlRequest::send);
    }

    @Test
    public void execute_validEndpoint_returnsResponse() throws Exception {
        assertNotEquals(null, jsonRequest.send());
    }

    @Test
    public void execute_validEndpoint_returnsNoBodyData() throws Exception {
        assertEquals("NO DATA", jsonRequest.send().getResponseEntity().toUpperCase());
    }

    @Test
    public void execute_validEndpoint_returnsNonJsonResponseEntity() {
        assertThrows(JsonParseException.class, () -> JsonUtil.toPrettyPrintJsonString(
                htmlRequest.send().getResponseEntity()));
    }
}
