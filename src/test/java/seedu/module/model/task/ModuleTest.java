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

    /**
     * Constructs a string that is similar to the return message from getWorkloadCount() method in {@code Module}.
     */
    public String getWorkloadCountBuilder(int lowWorkloadCount, int mediumWorkloadCount, int highWorkloadCount) {
        return String.format("Low workload tasks: %d, medium workload tasks: %d, high workload tasks %d\n",
                lowWorkloadCount, mediumWorkloadCount, highWorkloadCount);
    }

    @Test
    public void validIncrementWorkloadTest() {
        Module originalModule = new Module("CS1101S");
        originalModule.incrementWorkload(new Workload("3"));
        String expectedMessage = getWorkloadCountBuilder(0, 0, 1);
        assertEquals(originalModule.getWorkloadCount(), expectedMessage);

        originalModule.incrementWorkload(new Workload("3"));
        expectedMessage = getWorkloadCountBuilder(0, 0, 2);
        assertEquals(originalModule.getWorkloadCount(), expectedMessage);

        originalModule.incrementWorkload(new Workload("2"));
        originalModule.incrementWorkload(new Workload("1"));
        expectedMessage = getWorkloadCountBuilder(1, 1, 2);
        assertEquals(originalModule.getWorkloadCount(), expectedMessage);
    }

    @Test
    public void validDecrementWorkloadTest() {
        Module originalModule = new Module("CS1101S");
        originalModule.incrementWorkload(new Workload("3"));
        originalModule.incrementWorkload(new Workload("2"));
        originalModule.decrementWorkload(new Workload("3"));
        String expectedMessage = getWorkloadCountBuilder(0, 1, 0);
        assertEquals(originalModule.getWorkloadCount(), expectedMessage);
    }

    @Test
    public void invalidIncrementWorkloadTest() {
        Module originalModule = new Module("CS1101S");
        assertThrows(NullPointerException.class, () -> originalModule.incrementWorkload(null));
    }

    @Test
    public void invalidDecrementWorkloadTest() {
        Module originalModule = new Module("CS1101S");
        originalModule.incrementWorkload(new Workload("2"));
        assertThrows(NullPointerException.class, () -> originalModule.decrementWorkload(null));
        assertThrows(AssertionError.class, () -> originalModule.decrementWorkload(new Workload("3")));
    }
}
