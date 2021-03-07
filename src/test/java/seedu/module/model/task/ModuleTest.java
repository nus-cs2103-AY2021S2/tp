package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // blank module
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only

        // missing parts
        assertFalse(Module.isValidModule("1101")); // Only Module Code
        assertFalse(Module.isValidModule("CS")); // Only Major Name

        // invalid parts
        assertFalse(Module.isValidModule("CS110")); // shorter length of code
        assertFalse(Module.isValidModule("CS11111")); // longer length of code
        assertFalse(Module.isValidModule("Computer Science 1101")); // Invalid Major Name

        // valid module
        assertTrue(Module.isValidModule("CS1101S"));
        assertTrue(Module.isValidModule("CS3243")); // minimal
        assertTrue(Module.isValidModule("DSA1101")); // alphabets only
    }
}
