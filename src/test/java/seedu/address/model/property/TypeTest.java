package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
    }

    @Test
    public void isValidType() {
        // null type
        assertThrows(NullPointerException.class, () -> Type.isValidType(null));

        // invalid type
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only
        assertFalse(Type.isValidType(" hdb")); // contains extraneous leading spaces
        assertFalse(Type.isValidType("hdb ")); // contains extraneous trailing spaces
        assertFalse(Type.isValidType("1hdb")); // contains extraneous leading characters
        assertFalse(Type.isValidType("hdb1")); // contains extraneous trailing characters
        assertFalse(Type.isValidType("apartment")); // not one of hdb, condo, landed

        // valid type
        assertTrue(Type.isValidType("hdb")); // hdb type
        assertTrue(Type.isValidType("condo")); // condo only
        assertTrue(Type.isValidType("landed")); // landed type
        assertTrue(Type.isValidType("Hdb")); // hdb in title case
        assertTrue(Type.isValidType("Condo")); // condo in title case
        assertTrue(Type.isValidType("Landed")); // landed in title case
        assertTrue(Type.isValidType("HDB")); // hdb in uppercase
        assertTrue(Type.isValidType("CONDO")); // condo in uppercase
        assertTrue(Type.isValidType("LANDED")); // landed in uppercase
    }
}
