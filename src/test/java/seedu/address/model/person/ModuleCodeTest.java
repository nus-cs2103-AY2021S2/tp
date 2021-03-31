package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "CT1211";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidCode));
    }

    @Test
    public void isValidModuleCode() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid name
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("peter*")); // contains non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("CT1211")); // Invalid format

        // valid name
        assertTrue(ModuleCode.isValidModuleCode("CS9999")); // 9999 is the highest code number accepted
        assertTrue(ModuleCode.isValidModuleCode("CS0000")); // 0000 is the highest code number accepted
        assertTrue(ModuleCode.isValidModuleCode("CS2103")); // typical value

    }
}
