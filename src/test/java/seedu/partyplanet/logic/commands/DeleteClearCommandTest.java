package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteClearCommand}.
 */
public class DeleteClearCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validDeleteClear_success() {
        DeleteCommand deleteCommand = new DeleteClearCommand();

        List<Person> personsDeleted = model.getFilteredPersonList();

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                displayPersons(personsDeleted));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsDeleted) {
            expectedModel.deletePerson(p);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteClearEmptyList_success() {
        DeleteCommand deleteCommand = new DeleteClearCommand();

        model.updateFilteredPersonList(p -> false);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, "None!");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> false);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteClearCommand();
        DeleteCommand deleteSecondCommand = new DeleteClearCommand();

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        assertTrue(deleteFirstCommand.equals(deleteSecondCommand));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));
    }

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> deletedPersons) {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("None!");
    }
}
