package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.MEETING_1;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Goal.Frequency;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class SetGoalCommandTest {

    private static final Meeting VALID_MEETING = MEETING_1;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_setGoalUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withGoal(new Goal(Frequency.WEEKLY)).build();

        SetGoalCommand cmd = new SetGoalCommand(INDEX_FIRST_PERSON, Frequency.WEEKLY);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(
                SetGoalCommand.MESSAGE_ADD_GOAL_SUCCESS,
                editedPerson.getGoal().toString().toLowerCase(Locale.ROOT),
                editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setGoalFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withGoal(new Goal(Frequency.WEEKLY)).build();

        SetGoalCommand cmd = new SetGoalCommand(INDEX_FIRST_PERSON, Frequency.WEEKLY);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(
                SetGoalCommand.MESSAGE_ADD_GOAL_SUCCESS,
                editedPerson.getGoal().toUi(),
                editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setGoalInvalidIndex_failure() {
        SetGoalCommand setGoalCommand = new SetGoalCommand(Index.fromZeroBased(Integer.MAX_VALUE), Frequency.NONE);
        Exception e = assertThrows(CommandException.class, () -> setGoalCommand.execute(model));
        assertEquals(Messages.MESSAGE_INVALID_INDEX_ARGUMENT, e.getMessage());
    }
}
