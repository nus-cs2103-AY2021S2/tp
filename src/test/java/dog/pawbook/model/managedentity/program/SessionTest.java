package dog.pawbook.model.managedentity.program;

import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_APRIL_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_FEB_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATEOFBIRTH_SEP_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_APRIL_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_FEB_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_SEP_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_TIME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_TIME_MINUTES_DESC;
import static dog.pawbook.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.dog.DateOfBirth;

public class SessionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session((String) null));
        assertThrows(NullPointerException.class, () -> new Session((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidSession_throwsIllegalArgumentException() {
        // date of birth contains letters
        assertThrows(IllegalArgumentException.class, () -> new Session(INVALID_SESSION_DESC));

        // date is 31-04-2020, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_SESSION_APRIL_DESC));

        // date is 29-02-2021, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_SESSION_FEB_DESC));

        // date is 31-09-2020, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_SESSION_SEP_DESC));

        // time is 24:30, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_SESSION_TIME_DESC));

        // time is 22:60, does not exist
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(INVALID_SESSION_TIME_MINUTES_DESC));
    }
}
