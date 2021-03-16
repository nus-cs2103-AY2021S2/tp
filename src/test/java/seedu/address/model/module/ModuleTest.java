package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.ModuleBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalRemindMe.CS2101;
import static seedu.address.testutil.TypicalRemindMe.CS2103;

public class ModuleTest {

    @Test
    public void isSameModule() {
        // same instance -> returns true
        assertTrue(CS2103.isSameModule(CS2103));

        // null -> returns false
        assertFalse(CS2103.isSameModule(null));

        // same title, all other attributes are different -> returns true
        Module moduleCompared = new ModuleBuilder(CS2103)
                .withAssignments(VALID_ASSIGNMENTS_CS2103)
                .withExams(VALID_EXAMS_CS2103).build();
        assertTrue(CS2103.isSameModule(moduleCompared));

        // different title, but all other attributes are similar -> returns false
        moduleCompared = new ModuleBuilder(CS2103).withTitle(VALID_TITLE_CS2101).build();
        assertFalse(CS2103.isSameModule(moduleCompared));

        // title is lowered case, all other attributes are the same -> returns false
        moduleCompared = new ModuleBuilder(CS2103)
                .withTitle(VALID_TITLE_CS2103.toLowerCase()).build();
        assertFalse(CS2103.isSameModule(moduleCompared));

        // title has whitespaces trailing, all other attributes are the same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_CS2103 + "    ";
        moduleCompared = new ModuleBuilder(CS2103).withTitle(titleWithTrailingSpaces).build();
        assertFalse(CS2103.isSameModule(moduleCompared));
    }

    @Test
    public void equals() {
        // same instance -> returns true
        assertTrue(CS2103.equals(CS2103));

        // same attributes -> returns true
        Module moduleCompared = new ModuleBuilder(CS2103).build();
        assertTrue(CS2103.equals(moduleCompared));

        // null -> returns false
        assertFalse(CS2103.equals(null));

        // different class -> returns false
        assertFalse(CS2103.equals("HI"));

        // different person -> returns false
        assertFalse(CS2103.equals(CS2101));

        // different names -> returns false
        moduleCompared = new ModuleBuilder(CS2103).withTitle(VALID_TITLE_CS2101).build();
        assertFalse(CS2103.equals(moduleCompared));

        // different assignments -> return false
        moduleCompared = new ModuleBuilder(CS2103)
                .withAssignments(VALID_ASSIGNMENTS_CS2103).build();
        assertFalse(CS2103.equals(moduleCompared));

        // different exams -> return false
        moduleCompared = new ModuleBuilder(CS2103)
                .withExams(VALID_EXAMS_CS2103).build();
        assertFalse(CS2103.equals(moduleCompared));
    }
}
