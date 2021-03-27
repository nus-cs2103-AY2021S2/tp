package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_CHEESE;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid addresses
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation(" ")); // spaces only
        assertFalse(Location.isValidLocation(" kitchen")); // space at the start

        // valid addresses
        assertTrue(Location.isValidLocation("Blk 456, Den Road, #01-355"));
        assertTrue(Location.isValidLocation("kitchen ")); // trailing space
        assertTrue(Location.isValidLocation("-")); // one character
        assertTrue(Location.isValidLocation("123456")); // all numbers
        assertTrue(Location.isValidLocation("Leng Inc; 1 Mark St; San Francis CA 2349879; US")); // long location
    }

    @Test
    public void compare_locations_success() {
        assertTrue(new ItemName(VALID_LOCATION_BANANA).compare(new ItemName(VALID_LOCATION_CHEESE)) < 0);
        assertTrue(new ItemName(VALID_LOCATION_CHEESE).compare(new ItemName(VALID_LOCATION_BANANA)) > 0);
        assertTrue(new ItemName(VALID_LOCATION_BANANA).compare(new ItemName(VALID_LOCATION_BANANA)) == 0);
    }

    @Test
    public void equals() {
        assertFalse(new Location("kitChen").equals(new Location("Kitchen"))); //same location in different cases
        assertFalse(new Location("kitchen").equals(new Location("KITCHEN"))); //same location in different cases
        assertFalse(new Location("kitchenn").equals(new Location("kitchen"))); //different locations

        assertTrue(new Location("kitchen").equals(new Location("kitchen")));
        assertTrue(new Location("x").equals(new Location("x")));

    }
}
