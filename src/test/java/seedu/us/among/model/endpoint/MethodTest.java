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
        assertFalse(Method.isValidMethod("peter*")); // contains non-alphanumeric characters

        // valid Method
        assertTrue(Method.isValidMethod("GET")); // alphabets only
        assertTrue(Method.isValidMethod("POST")); // numbers only
        assertTrue(Method.isValidMethod("PUT")); // alphanumeric characters
        assertTrue(Method.isValidMethod("DELETE")); // with capital letters
        assertTrue(Method.isValidMethod("David Roger Jackson Ray Jr 2nd")); // long Methods //to-do
    }
}
