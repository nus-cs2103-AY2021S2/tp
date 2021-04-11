package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_APRIL_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_FEB_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_SEP_DESC;
import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth((String) null));
        assertThrows(NullPointerException.class, () -> new DateOfBirth((LocalDate) null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        // date of birth contains letters
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_DATEOFBIRTH_DESC));

        // date of birth is 31-04-2020, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_DATEOFBIRTH_APRIL_DESC));

        // date of birth is 29-02-2021, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_DATEOFBIRTH_FEB_DESC));

        // date of birth is 31-09-2020, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_DATEOFBIRTH_SEP_DESC));
    }

    @Test
    public void equals() {
        DateOfBirth date1 = new DateOfBirth("11-05-1995");
        DateOfBirth date2 = new DateOfBirth("29-07-2005");

        // same object -> returns true
        assertTrue(date1.equals(date1));

        // same values -> returns true
        DateOfBirth date1Copy = new DateOfBirth("11-05-1995");
        assertTrue(date1.equals(date1Copy));

        // different types -> returns false
        assertFalse(date1.equals(1));

        // null -> returns false
        assertFalse(date1.equals(null));

        // different DateOfBirth -> returns false
        assertFalse(date1.equals(date2));
    }
}
