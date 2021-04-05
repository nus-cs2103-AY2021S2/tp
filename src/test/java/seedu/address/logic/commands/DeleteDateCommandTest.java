package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSpecialDates.DATE_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.SpecialDate;
import seedu.address.testutil.PersonBuilder;

public class DeleteDateCommandTest {

    private static final SpecialDate VALID_DATE = DATE_ONE;
    private static final Index VALID_INDEX = Index.fromOneBased(1);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputsUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person firstPersonWithDate = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();
        model.setPerson(firstPerson, firstPersonWithDate);
        Person editedPerson = new PersonBuilder(firstPerson).build();

        String expectedMessage = String.format(DeleteDateCommand.MESSAGE_DELETE_DATE_SUCCESS, editedPerson.getName());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPersonWithDate, editedPerson);

        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, VALID_INDEX);

        assertCommandSuccess(deleteDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputsFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person firstPersonWithDate = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();
        model.setPerson(firstPerson, firstPersonWithDate);
        Person editedPerson = new PersonBuilder(firstPerson).build();

        String expectedMessage = String.format(DeleteDateCommand.MESSAGE_DELETE_DATE_SUCCESS, editedPerson.getName());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPersonWithDate, editedPerson);

        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, VALID_INDEX);

        assertCommandSuccess(deleteDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(outOfBoundIndex, VALID_INDEX);

        assertCommandFailure(deleteDateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_SECOND_PERSON, VALID_INDEX);

        assertCommandFailure(deleteDateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDateIndexUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person firstPersonWithDate = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();
        model.setPerson(firstPerson, firstPersonWithDate);

        Index outOfBoundIndex = Index.fromOneBased(firstPersonWithDate.getDates().size() + 1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(deleteDateCommand, model, Messages.MESSAGE_INVALID_INDEX_ARGUMENT);
    }

    @Test
    public void execute_invalidDateIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person firstPersonWithDate = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();
        model.setPerson(firstPerson, firstPersonWithDate);

        Index outOfBoundIndex = Index.fromOneBased(firstPersonWithDate.getDates().size() + 1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(deleteDateCommand, model, Messages.MESSAGE_INVALID_INDEX_ARGUMENT);
    }

    @Test
    public void execute_noDatesUnfilteredList_failure() {
        Person personWithoutDates = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(Messages.MESSAGE_NO_DATES, personWithoutDates.getName());

        Index outOfBoundIndex = Index.fromOneBased(1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(deleteDateCommand, model, expectedMessage);
    }

    @Test
    public void execute_noDatesFilteredList_failure() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person personWithoutDates = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(Messages.MESSAGE_NO_DATES, personWithoutDates.getName());

        Index outOfBoundIndex = Index.fromOneBased(1);
        DeleteDateCommand deleteDateCommand = new DeleteDateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(deleteDateCommand, model, expectedMessage);
    }
}
