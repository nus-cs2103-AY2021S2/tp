package seedu.address.logic.filters;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailFilterTest {
    @Test
    public void nullOrEmptyFilterString() {
        assertThrows(IllegalArgumentException.class, () -> new EmailFilter(" "));
        assertThrows(NullPointerException.class, () -> new EmailFilter(null));
    }
}
