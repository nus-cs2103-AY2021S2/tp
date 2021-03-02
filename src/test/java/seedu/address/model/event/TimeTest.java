package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import static seedu.address.testutil.Assert.assertThrows;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }
}
