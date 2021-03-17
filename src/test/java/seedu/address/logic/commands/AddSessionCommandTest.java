package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddSessionCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null, null));
        // replace with session builder
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null, null));
    }
}
