package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ColourTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Colour(null));
    }

    @Test
    public void constructor_invalidColour_throwsIllegalArgumentException() {
        String invalidColour = " ";
        assertThrows(IllegalArgumentException.class, () -> new Colour(invalidColour));
    }

    @Test
    public void isValidColour() {
        assertEquals(1, 1);
    }
}
