package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ResponseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Response(null, null, null, null, null, null));
    }

    @Test
    public void isValidResponse() {
        // null response
        assertThrows(NullPointerException.class, () -> Response.isValidResponse(null, null, null, null));
        assertThrows(NullPointerException.class, () -> Response.isValidResponse(null, "", "", ""));
        assertThrows(NullPointerException.class, () -> Response.isValidResponse("", null, "", ""));
        assertThrows(NullPointerException.class, () -> Response.isValidResponse("", "", null, ""));
        assertThrows(NullPointerException.class, () -> Response.isValidResponse("", "", "", null));

        // valid responses
        assertTrue(Response.isValidResponse("", "", "", "")); //empty response
        assertTrue(Response.isValidResponse("HTTP/1.1", "200", "OK", "HTTP/1.1 200 OK")); //classic response
    }
}
