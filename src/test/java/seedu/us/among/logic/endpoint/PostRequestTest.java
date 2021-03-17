package seedu.us.among.logic.endpoint;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;

import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.util.SampleDataUtil;
import seedu.us.among.testutil.TypicalEndpoints;

public class PostRequestTest {

    private final Endpoint sampleValidEndpoint = TypicalEndpoints.POST;
    private final Endpoint sampleHtmlResponseEndpoint = SampleDataUtil.getSampleHtmlResponseEndpoint("POST");
    private final Endpoint sampleInvalidUrlEndpoint = SampleDataUtil.getSampleInvalidUrlEndpoint("POST");
    private final PostRequest jsonRequest = new PostRequest(sampleValidEndpoint);
    private final PostRequest htmlRequest = new PostRequest(sampleHtmlResponseEndpoint);
    private final PostRequest invalidUrlRequest = new PostRequest(sampleInvalidUrlEndpoint);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PostRequest(null));
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
    public void execute_validEndpoint_returnsJsonResponseEntity() throws Exception {
        assertNotEquals(null, JsonUtil.toPrettyPrintJsonString(jsonRequest.send().getResponseEntity()));
    }

    @Test
    public void execute_validEndpoint_returnsNonJsonResponseEntity() {
        assertThrows(JsonParseException.class, () -> JsonUtil.toPrettyPrintJsonString(
                htmlRequest.send().getResponseEntity()));
    }

    @Test
    public void execute_validEndpoint_returnsHtmlResponseEntity() throws Exception {
        assertTrue(htmlRequest.send().getResponseEntity().toUpperCase().contains("<!DOCTYPE HTML>"));
    }
}
