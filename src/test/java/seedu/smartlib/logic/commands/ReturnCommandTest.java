package seedu.smartlib.logic.commands;

import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReturnCommandTest {

    @Test
    public void constructor_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReturnCommand(null));
    }


}

