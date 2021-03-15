package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSessionCommand}.
 */
class DeleteSessionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //Session sessionToDelete = model.getFilteredStudentList().

        //assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }
}
