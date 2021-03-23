package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithMatricNum;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_PERSON;
import static seedu.address.testutil.TypicalMatricNumbers.MATRIC_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validMatricNumUnfilteredList_success() {
        List<Person> personListTest = model.getFilteredPersonList();
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_PERSON);
        Person personToDelete = DeleteCommand.getPerson(personListTest, matricNumberToDelete);
        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMatricNumUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_PERSON));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    }

    @Test
    public void execute_validMatricNumFilteredList_success() {
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_PERSON);
        showPersonWithMatricNum(model, matricNumberToDelete);

        List<Person> personListTest = model.getFilteredPersonList();
        Person personToDelete = DeleteCommand.getPerson(personListTest, matricNumberToDelete);
        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);


        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }



    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FIRST_PERSON);
    //        // showPersonWithMatricNum(model, matricNumberToDelete);
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(matricNumberToDelete);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    //    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_PERSON));
        DeleteCommand deleteSecondCommand = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_SECOND_PERSON));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new MatriculationNumber(MATRIC_NUMBER_FIRST_PERSON));
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
