package seedu.address.model.sort.descriptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PropertySortingKeyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropertySortingKey(null));
    }

    @Test
    public void constructor_invalidPropertySortingKey_throwsIllegalArgumentException() {
        String invalidPropertySortingKey = "";
        assertThrows(IllegalArgumentException.class, () -> new PropertySortingKey(invalidPropertySortingKey));
    }

    @Test
    public void isValidPropertySortingKey() {
        // null propertySortingKey
        assertThrows(NullPointerException.class, () -> PropertySortingKey.isValidPropertySortingKey(null));

        // invalid propertySortingKey
        assertFalse(PropertySortingKey.isValidPropertySortingKey("")); // empty string
        assertFalse(PropertySortingKey.isValidPropertySortingKey(" ")); // spaces only

        // valid propertySortingKey
        assertTrue(PropertySortingKey.isValidPropertySortingKey("deadline"));
        assertTrue(PropertySortingKey.isValidPropertySortingKey("name"));
        assertTrue(PropertySortingKey.isValidPropertySortingKey("postalcode"));
        assertTrue(PropertySortingKey.isValidPropertySortingKey("price"));
    }
}
