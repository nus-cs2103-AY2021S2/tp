package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals_success() {
        Name n = new Name("bobby");
        assertEquals(n, n);

        assertEquals(new Name("peter"), new Name("peter"));
        assertEquals(new Name("Peter"), new Name("peter"));
        assertEquals(new Name("Peter"), new Name("PeTeR"));

        assertEquals(new Name("peter ong zhi yuan"), new Name("peter ong zhi yuan"));
        assertEquals(new Name("Peter Ong Zhi Yuan"), new Name("peter ong zhi yuan"));
        assertEquals(new Name("Peter Ong Zhi Yuan"), new Name("PeTEr Ong zhI yuAn"));
    }

    @Test
    public void equals_failure() {
        Object o = new Object();
        assertNotEquals(new Name("bob"), o);

        assertNotEquals(new Name("peter"), new Name("bob"));
        assertNotEquals(new Name("peter ong"), new Name("peter  ong")); // two spaces
    }
}
