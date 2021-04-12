package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MethodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Method(null));
    }

    @Test
    public void constructor_invalidMethod_throwsIllegalArgumentException() {
        String invalidMethod = "";
        assertThrows(IllegalArgumentException.class, () -> new Method(invalidMethod));
    }

    @Test
    public void isValidMethod() {
        // null Method
        assertThrows(NullPointerException.class, () -> Method.isValidMethod(null));

        // invalid Method
        assertFalse(Method.isValidMethod("")); // empty string
        assertFalse(Method.isValidMethod(" ")); // spaces only
        assertFalse(Method.isValidMethod("^")); // only non-alphanumeric characters
        assertFalse(Method.isValidMethod("POST*")); // contains non-alphanumeric characters
        assertFalse(Method.isValidMethod("GET GET")); // contains two of the same command
        assertFalse(Method.isValidMethod("aGETb")); // contains other characters
        assertFalse(Method.isValidMethod(" pUt")); // leading whitespace
        assertFalse(Method.isValidMethod("pUt ")); // trailing whitespace
        assertFalse(Method.isValidMethod(" pUt ")); // leading and trailing whitespace

        // valid Method
        assertTrue(Method.isValidMethod("GET")); // GET command
        assertTrue(Method.isValidMethod("POST")); // POST command
        assertTrue(Method.isValidMethod("PUT")); // PUT command
        assertTrue(Method.isValidMethod("DELETE")); // DELETE command
        assertTrue(Method.isValidMethod("HEAD")); // HEAD command
        assertTrue(Method.isValidMethod("OPTIONS")); // OPTIONS command
        assertTrue(Method.isValidMethod("PATCH")); // PATCH command

        assertTrue(Method.isValidMethod("get")); // GET command small
        assertTrue(Method.isValidMethod("post")); // POST command small
        assertTrue(Method.isValidMethod("put")); // PUT command small

        assertTrue(Method.isValidMethod("pOsT")); // POST command weird
        assertTrue(Method.isValidMethod("pUt")); // PUT command weird

    }
}
