package seedu.us.among.model.header;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeaderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Header(null));
    }

    @Test
    public void constructor_invalidHeaderName_throwsIllegalArgumentException() {
        String invalidHeaderName = "";
        assertThrows(IllegalArgumentException.class, () -> new Header(invalidHeaderName));
    }

    @Test
    public void isValidHeaderName() {
        // null header name
        assertThrows(NullPointerException.class, () -> Header.isValidHeaderName(null));

        // invalid header
        assertFalse(Header.isValidHeaderName("*abc"));
        assertFalse(Header.isValidHeaderName("key: value"));
        assertFalse(Header.isValidHeaderName("'key: value'"));
        assertFalse(Header.isValidHeaderName("")); //
        assertFalse(Header.isValidHeaderName(" ")); //

        // valid header
        assertTrue(Header.isValidHeaderName("\"key\": \"value\""));
        assertTrue(Header.isValidHeaderName("\"key\":\"value\""));
    }

}
