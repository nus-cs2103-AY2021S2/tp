package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalRemindMe.MOD_2;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same instance -> returns true
        assertTrue(MOD_1.isSameModule(MOD_1));

        // null -> returns false
        assertFalse(MOD_1.isSameModule(null));

        // same title, all other attributes are different -> returns true
        Module moduleCompared = new ModuleBuilder(MOD_1)
                .withAssignments(VALID_ASSIGNMENT_DESCRIPTION_1, VALID_ASSIGNMENT_DESCRIPTION_2)
                .withExams(VALID_EXAM_DATETIME_1, VALID_EXAM_DATETIME_2).build();
        assertTrue(MOD_1.isSameModule(moduleCompared));

        // different title, but all other attributes are similar -> returns false
        moduleCompared = new ModuleBuilder(MOD_1).withTitle(VALID_TITLE_CS2101).build();
        assertFalse(MOD_1.isSameModule(moduleCompared));

        // title is lowered case, all other attributes are the same -> returns false
        moduleCompared = new ModuleBuilder(MOD_1)
                .withTitle(VALID_TITLE_CS2103.toLowerCase()).build();
        assertFalse(MOD_1.isSameModule(moduleCompared));

        // title has whitespaces trailing, all other attributes are the same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_CS2103 + "    ";
        moduleCompared = new ModuleBuilder(MOD_1).withTitle(titleWithTrailingSpaces).build();
        assertFalse(MOD_1.isSameModule(moduleCompared));
    }

    @Test
    public void equals() {
        // same instance -> returns true
        assertTrue(MOD_1.equals(MOD_1));

        // same attributes -> returns true
        Module moduleCompared = new ModuleBuilder(MOD_1).build();
        assertTrue(MOD_1.equals(moduleCompared));

        // null -> returns false
        assertFalse(MOD_1.equals(null));

        // different class -> returns false
        assertFalse(MOD_1.equals("HI"));

        // different module -> returns false
        assertFalse(MOD_1.equals(MOD_2));

        // different title -> returns false
        moduleCompared = new ModuleBuilder(MOD_1).withTitle(VALID_TITLE_CS2101).build();
        assertFalse(MOD_1.equals(moduleCompared));

        // different assignments -> return false
        moduleCompared = new ModuleBuilder(MOD_1)
                .withAssignments(VALID_ASSIGNMENT_DESCRIPTION_1).build();
        assertFalse(MOD_1.equals(moduleCompared));

        // different exams -> return false
        moduleCompared = new ModuleBuilder(MOD_1)
                .withExams(VALID_EXAM_DATETIME_1).build();
        assertFalse(MOD_1.equals(moduleCompared));
    }
}
