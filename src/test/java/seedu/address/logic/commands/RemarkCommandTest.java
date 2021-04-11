package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_JANE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemarkCommand}.
 */
public class RemarkCommandTest {

    private static final Remark JANE_REMARK = new Remark(VALID_REMARK_JANE);
    private static final Remark NO_REMARK = new Remark(VALID_REMARK_BOB);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAddRemarkUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK_JANE).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, JANE_REMARK);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_CHANGE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexRemoveRemarkUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK_BOB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_EIGHTH_PERSON, NO_REMARK);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_CHANGE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexAddRemarkFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK_JANE).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, JANE_REMARK);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_CHANGE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexRemoveRemarkFilteredList_success() {
        showPersonAtIndex(model, INDEX_EIGHTH_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK_BOB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, NO_REMARK);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_CHANGE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAddRemark_failure() {
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_NINTH_PERSON, JANE_REMARK);

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
