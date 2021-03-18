package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103;
import static seedu.address.testutil.TypicalRemindMe.DATE_1;
import static seedu.address.testutil.TypicalRemindMe.DATE_2;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;
import static seedu.address.testutil.TypicalRemindMe.MOD_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class ModuleTest {
    private Assignment assignment1 = new Assignment(new Description(VALID_ASSIGNMENT_DESCRIPTION_1),
            DATE_1, new Tag("MOD1"));
    private Assignment assignment2 = new Assignment(new Description(VALID_ASSIGNMENT_DESCRIPTION_2),
            DATE_2, new Tag("MOD2"));
    private Exam exam1 = new Exam(DATE_1, new Tag("MOD1"));
    private Exam exam2 = new Exam(DATE_2, new Tag("MOD2"));

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

    @Test
    public void editTitle() {
        // same title -> returns true
        assertTrue(MOD_1.equals(MOD_1));

        // different title -> returns false
        Module moduleCompared = new ModuleBuilder(MOD_1).build();
        moduleCompared.editTitle(new Title("MOD 2"));
        assertFalse(MOD_1.equals(moduleCompared));

        // same title after changing -> returns true
        assertTrue(MOD_2.equals(moduleCompared));
    }

    @Test
    public void getAssignment() {
        Module copyMod1 = new ModuleBuilder(MOD_1).build();
        copyMod1.addAssignment(assignment1);
        copyMod1.addAssignment(assignment2);

        // assignment at index 0 -> returns true
        assertTrue(copyMod1.getAssignment(0).equals(assignment1));

        // assignment at index 1 -> returns true
        assertTrue(copyMod1.getAssignment(1).equals(assignment2));
    }

    @Test
    public void getExamAt() {
        Module copyMod2 = new ModuleBuilder(MOD_2).build();
        copyMod2.addExam(exam1);
        copyMod2.addExam(exam2);

        // exam at index 0 -> returns true
        assertTrue(copyMod2.getExam(0).equals(exam1));

        // exam at index 1 -> returns true
        assertTrue(copyMod2.getExam(1).equals(exam2));
    }

    @Test
    public void hasAssignment() {
        Module copyMod2 = new ModuleBuilder(MOD_2).build();
        // empty list
        assertFalse(copyMod2.hasAssignment(assignment1));

        // has assignment -> returns true
        copyMod2.addAssignment(assignment1);
        assertTrue(copyMod2.hasAssignment(assignment1));

        // does not have the assignment -> returns false
        assertFalse(copyMod2.hasAssignment(assignment2));
    }

    @Test
    public void hasExam() {
        Module copyMod2 = new ModuleBuilder(MOD_2).emptyBuild();
        // empty list
        assertFalse(copyMod2.hasExam(exam1));

        // has exam -> returns true
        copyMod2.addExam(exam2);
        assertTrue(copyMod2.hasExam(exam2));

        // does not have exam -> returns false
        assertFalse(copyMod2.hasExam(exam1));
    }

    @Test
    public void deleteAssignment() {
        Module mod1Copy = new ModuleBuilder(MOD_1).emptyBuild();
        mod1Copy.addAssignment(assignment1);
        mod1Copy.addAssignment(assignment2);

        // delete assignment at index 0 -> returns true
        mod1Copy.deleteAssignment(assignment1);
        assertTrue(mod1Copy.getAssignment(0).equals(assignment2));

        // delete assignment at index 1 -> returns true
        mod1Copy.addAssignment(assignment1);
        mod1Copy.deleteAssignment(assignment1);
        assertTrue(mod1Copy.getAssignment(0).equals(assignment2));

        // delete using index
        mod1Copy = new ModuleBuilder(MOD_1).emptyBuild();
        Module mod2Copy = new ModuleBuilder(MOD_1).emptyBuild();
        mod1Copy.addAssignment(assignment2);
        mod1Copy.addAssignment(assignment1);
        mod2Copy.addAssignment(assignment2);
        mod2Copy.addAssignment(assignment1);

        // delete at index 1 -> returns true
        mod1Copy.deleteAssignment(1);
        mod2Copy.deleteAssignment(assignment1);
        assertTrue(mod1Copy.equals(mod2Copy));

        // delete at index 0 -> returns true
        mod1Copy.deleteAssignment(assignment2);
        mod2Copy.deleteAssignment(0);
        assertTrue(mod1Copy.equals(mod2Copy));
    }

    @Test
    public void deleteExam() {
        Module mod2Copy1 = new ModuleBuilder(MOD_2).emptyBuild();
        Module mod2Copy2 = new ModuleBuilder(MOD_2).emptyBuild();
        mod2Copy1.addExam(exam1);
        mod2Copy1.addExam(exam2);
        mod2Copy2.addExam(exam1);
        mod2Copy2.addExam(exam2);

        // delete exam2 -> returns true
        mod2Copy1.deleteExam(exam2);
        mod2Copy2.deleteExam(exam2);
        assertTrue(mod2Copy1.equals(mod2Copy2));

        // delete exam1 -> returns true
        mod2Copy1.deleteExam(exam1);
        mod2Copy2.deleteExam(exam1);
        assertTrue(mod2Copy1.equals(mod2Copy2));

        // delete using index
        mod2Copy1.addExam(exam2);
        mod2Copy1.addExam(exam1);
        mod2Copy2.addExam(exam2);
        mod2Copy2.addExam(exam1);

        // delete at index 1 -> returns true
        mod2Copy1.deleteExam(1);
        mod2Copy2.deleteExam(1);
        assertTrue(mod2Copy1.equals(mod2Copy2));

        // delete at index 0 -> returns true
        mod2Copy1.deleteExam(0);
        mod2Copy2.deleteExam(0);
        assertTrue(mod2Copy1.equals(mod2Copy2));
        assertTrue(mod2Copy1.getExams().equals(new ExamList()));
        assertTrue(mod2Copy2.getExams().equals(new ExamList()));
    }

    @Test
    public void compareTo() {
        // same module -> returns 0
        assertEquals(0, MOD_1.compareTo(MOD_1));

        // different titles -> return -1
        assertTrue(MOD_1.compareTo(MOD_2) == -1);

        // different titles -> return 1
        assertTrue(MOD_2.compareTo(MOD_1) == 1);
    }
}
