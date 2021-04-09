package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_DESC;
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
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_DATEOFBIRTH_DESC));
    }
}
