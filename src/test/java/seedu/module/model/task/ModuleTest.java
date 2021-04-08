package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertThrows(NullPointerException.class, () -> Module.isValidModuleFormat(null));

        // blank module
        assertFalse(Module.isValidModuleFormat("")); // empty string
        assertFalse(Module.isValidModuleFormat(" ")); // spaces only

        // missing parts
        assertFalse(Module.isValidModuleFormat("1101")); // Only Module Code
        assertFalse(Module.isValidModuleFormat("CS")); // Only Major Name

        // invalid parts
        assertFalse(Module.isValidModuleFormat("CS110")); // shorter length of code
        assertFalse(Module.isValidModuleFormat("CS11111")); // longer length of code
        assertFalse(Module.isValidModuleFormat("Computer Science 1101")); // Invalid Major Name

        // valid module
        assertTrue(Module.isValidModuleFormat("CS1101S"));
        assertTrue(Module.isValidModuleFormat("CS3243")); // minimal
        assertTrue(Module.isValidModuleFormat("DSA1101")); // alphabets only
    }

    @Test
    public void equals() {
        Module firstModule = new Module("CS1101S");
        Module secondModule = new Module("CS1101S");
        Module thirdModule = new Module("CS2030");
        String notModule = "CS1101S";

        //EP: Same object
        assertTrue(firstModule.equals(firstModule));

        //EP: Different object, same value
        assertTrue(firstModule.equals(secondModule));

        //EP: Different object, different value
        assertFalse(firstModule.equals(thirdModule));

        //EP: Different type
        assertFalse(firstModule.equals(notModule));
    }

    @Test
    public void compareTo() {
        Module firstModule = new Module("CS1101S");
        Module secondModule = new Module("CS1101S");
        Module thirdModule = new Module("CS2030");

        //EP: Same object
        assertTrue(firstModule.compareTo(firstModule) == 0);

        //EP: Different object, same value
        assertTrue(firstModule.compareTo(secondModule) == 0);

        //EP: Different object, different value
        assertTrue(firstModule.compareTo(thirdModule) > 0);
        assertTrue(thirdModule.compareTo(firstModule) < 0);
    }

    @Test
    public void hashCodeTest() {
        String firstModuleString = "CS1101S";
        Module firstModule = new Module(firstModuleString);
        assertEquals(firstModule.hashCode(), firstModuleString.hashCode());
    }
}
