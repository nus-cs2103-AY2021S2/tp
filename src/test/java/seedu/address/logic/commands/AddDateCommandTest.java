package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_BEFORE_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalSpecialDates.DATE_ONE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.SpecialDate;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SpecialDateBuilder;

public class AddDateCommandTest {

    private static final SpecialDate VALID_DATE = DATE_ONE;
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputsUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();

        String expectedMessage = String.format(AddDateCommand.MESSAGE_ADD_DATE_SUCCESS, editedPerson.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        AddDateCommand addDateCommand = new AddDateCommand(INDEX_FIRST_PERSON, VALID_DATE);

        assertCommandSuccess(addDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputsFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDates(VALID_DATE).build();

        String expectedMessage = String.format(AddDateCommand.MESSAGE_ADD_DATE_SUCCESS, editedPerson.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        AddDateCommand addDateCommand = new AddDateCommand(INDEX_FIRST_PERSON, VALID_DATE);

        assertCommandSuccess(addDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddDateCommand addDateCommand = new AddDateCommand(outOfBoundIndex, VALID_DATE);

        assertCommandFailure(addDateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddDateCommand addDateCommand = new AddDateCommand(outOfBoundIndex, VALID_DATE);
        assertCommandFailure(addDateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSpecialDates_success() {
        // this person needs to match the person used in #testValidSpecialDate
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDate birthDate = firstPerson.getBirthday().getDate();

        // Acceptable range of values is from date of birthday to today
        testValidSpecialDate(new SpecialDateBuilder().withDate(birthDate).build());
        testValidSpecialDate(new SpecialDateBuilder().withDate(birthDate.plusDays(1)).build());
    }

    public void testValidSpecialDate(SpecialDate specialDate) {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDates(specialDate).build();

        AddDateCommand cmd = new AddDateCommand(INDEX_FIRST_PERSON, specialDate);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(AddDateCommand.MESSAGE_ADD_DATE_SUCCESS, editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);

        // reset model
        model.setPerson(editedPerson, firstPerson);
    }

    @Test
    public void execute_invalidSpecialDates_failure() {
        // this person needs to match the person used in #testInvalidSpecialDate
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDate birthDate = firstPerson.getBirthday().getDate();
        SpecialDate specialDateBeforeBirthdate = new SpecialDateBuilder()
                .withDate(birthDate.minusDays(1)).build();

        testInvalidSpecialDate(specialDateBeforeBirthdate, String.format(
                MESSAGE_DATE_BEFORE_BIRTHDAY, DateUtil.toErrorMessage(specialDateBeforeBirthdate.getDate())));
    }

    public void testInvalidSpecialDate(SpecialDate specialDate, String errorMessage) {
        AddDateCommand cmd = new AddDateCommand(INDEX_FIRST_PERSON, specialDate);
        assertCommandFailure(cmd, model, errorMessage);
    }
}
