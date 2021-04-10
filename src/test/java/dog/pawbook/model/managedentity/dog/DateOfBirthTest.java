package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_APRIL_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_FEB_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_SEP_DESC;
import static dog.pawbook.testutil.Assert.assertThrows;

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
}
