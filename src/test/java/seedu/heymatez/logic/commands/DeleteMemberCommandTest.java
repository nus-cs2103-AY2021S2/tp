package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showPersonAtName;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;
import seedu.heymatez.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMemberCommand}.
 */
public class DeleteMemberCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() throws ParseException {
        Person personToDelete = null;
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(parsedNameAlice);

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personToDelete = person;
                break;
            }
        }

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        Name invalidName = new Name("John");
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(invalidName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void execute_validNameFilteredList_success() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        showPersonAtName(model, parsedNameAlice);
        Person personToDelete = null;

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personToDelete = person;
                break;
            }
        }

        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(parsedNameAlice);
        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        showPersonAtName(model, parsedNameAlice);

        Name invalidName = TypicalPersons.BENSON.getName();
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(invalidName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void execute_removePersonAsAssigneeFilteredPersonList_onDeleteSuccess() throws ParseException {
        Person personToDelete = null;
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(parsedNameAlice);

        Task dummyTaskWithAssigned = new TaskBuilder().withAssignees(parsedNameAlice.fullName).build();

        model.addTask(dummyTaskWithAssigned);

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personToDelete = person;
                break;
            }
        }

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());

        expectedModel.deletePerson(personToDelete);
        expectedModel.removeAssignee(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() throws ParseException {
        Name parsedNameAlice = TypicalPersons.ALICE.getName();
        Name parsedNameBenson = TypicalPersons.BENSON.getName();

        DeleteMemberCommand deleteFirstCommand = new DeleteMemberCommand(parsedNameAlice);
        DeleteMemberCommand deleteSecondCommand = new DeleteMemberCommand(parsedNameBenson);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMemberCommand deleteFirstCommandCopy = new DeleteMemberCommand(parsedNameAlice);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
