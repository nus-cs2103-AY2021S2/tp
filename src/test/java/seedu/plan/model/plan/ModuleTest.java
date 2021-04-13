package seedu.plan.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plan.testutil.Assert.assertThrows;
import static seedu.plan.testutil.TypicalModules.COMPUTER_ORGANIZATION_MODULE;
import static seedu.plan.testutil.TypicalModules.SOFTWARE_ENGINEERING_MODULE;

import org.junit.jupiter.api.Test;

import seedu.plan.model.plan.exceptions.ModuleExceptions;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPinterException() {
        assertThrows(NullPointerException.class, () -> new Module (null, null, 4));
        assertThrows(NullPointerException.class, () -> new Module ("", null, 4));
        assertThrows(NullPointerException.class, () -> new Module (null, "", 4));
    }

    @Test
    public void getModuleTitle() {
        assertEquals(SOFTWARE_ENGINEERING_MODULE.getModuleTitle(), "Software Engineering");
    }

    @Test
    public void getModuleCode() {
        assertEquals(COMPUTER_ORGANIZATION_MODULE.getModuleCode(), "CS2100");
    }

    @Test
    public void getMCs() {
        assertEquals(COMPUTER_ORGANIZATION_MODULE.getMCs(), 4);
    }

    @Test
    public void isDone() throws ModuleExceptions {
        assertEquals(COMPUTER_ORGANIZATION_MODULE.isDone(), false);
        SOFTWARE_ENGINEERING_MODULE.doneModule("A");
        assertEquals(SOFTWARE_ENGINEERING_MODULE.isDone(), true);
    }

    @Test
    public void convertGradeToCao() {
        assertEquals(SOFTWARE_ENGINEERING_MODULE.convertGradeToCap(), 5.0);
    }

    @Test
    public void equals() {
        Module cs2100copy = COMPUTER_ORGANIZATION_MODULE;
        assertTrue(COMPUTER_ORGANIZATION_MODULE.equals(cs2100copy));
        assertTrue(COMPUTER_ORGANIZATION_MODULE.equals(COMPUTER_ORGANIZATION_MODULE));
        assertFalse(COMPUTER_ORGANIZATION_MODULE.equals(null));

        assertFalse(COMPUTER_ORGANIZATION_MODULE.equals(SOFTWARE_ENGINEERING_MODULE));
        assertFalse(COMPUTER_ORGANIZATION_MODULE.equals("CS2100"));
    }
}
