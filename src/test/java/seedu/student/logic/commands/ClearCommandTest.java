package seedu.student.logic.commands;

import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.StudentBook;
import seedu.student.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyStudentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentBook_success() {
        Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());
        expectedModel.setStudentBook(new StudentBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
