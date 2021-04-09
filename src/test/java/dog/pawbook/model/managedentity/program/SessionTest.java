package dog.pawbook.model.managedentity.program;

import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_DESC;
import static dog.pawbook.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session((String) null));
        assertThrows(NullPointerException.class, () -> new Session((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidSession_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Session(INVALID_SESSION_DESC));
    }
}
