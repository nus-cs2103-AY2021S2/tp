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
        assertFalse(ModuleCode.isValidModuleCode("cs2103")); // lowercase cs
        assertFalse(ModuleCode.isValidModuleCode("CS2103t")); // lowercase cs


        // EP: CS1000-CS6999 valid name
        assertTrue(ModuleCode.isValidModuleCode("CS6999")); // 6999 is the highest code number accepted
        assertTrue(ModuleCode.isValidModuleCode("CS1000")); // 1000 is the highest code number accepted
        assertTrue(ModuleCode.isValidModuleCode("CS2103")); // typical value
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // with an optional uppercase character
    }
}
