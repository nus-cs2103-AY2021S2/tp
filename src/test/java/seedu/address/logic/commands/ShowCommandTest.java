package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.CS1010E;
import static seedu.address.testutil.TypicalTasks.CS2030;
import static seedu.address.testutil.TypicalTasks.CS2040;
import static seedu.address.testutil.TypicalTasks.CS2100;
import static seedu.address.testutil.TypicalTasks.CS2103;
import static seedu.address.testutil.TypicalTasks.CS3243;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineDateInRangePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class ShowCommandTest {
    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void equals() {
        Optional<DeadlineDate> emptyDeadline = Optional.empty();
        Optional<DeadlineDate> firstDeadline = Optional.of(new DeadlineDate("09-09-2020"));
        DeadlineDateInRangePredicate firstPredicate =
                new DeadlineDateInRangePredicate(emptyDeadline, emptyDeadline);
        DeadlineDateInRangePredicate secondPredicate =
                new DeadlineDateInRangePredicate(firstDeadline, emptyDeadline);
        DeadlineDateInRangePredicate thirdPredicate =
                new DeadlineDateInRangePredicate(emptyDeadline, firstDeadline);

        ShowCommand showFirstCommand = new ShowCommand(firstPredicate);
        ShowCommand showSecondCommand = new ShowCommand(secondPredicate);
        ShowCommand showThirdCommand = new ShowCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));
        assertTrue(showSecondCommand.equals(showSecondCommand));
        assertTrue(showThirdCommand.equals(showThirdCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(firstPredicate);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));
        ShowCommand showSecondCommandCopy = new ShowCommand(secondPredicate);
        assertTrue(showSecondCommand.equals(showSecondCommandCopy));
        ShowCommand showThirdCommandCopy = new ShowCommand(thirdPredicate);
        assertTrue(showThirdCommand.equals(showThirdCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1)); //integer
        assertFalse(showSecondCommand.equals(1));
        assertFalse(showThirdCommand.equals(1));
        assertFalse(showFirstCommand.equals("aiue")); //string
        assertFalse(showSecondCommand.equals("aiue"));
        assertFalse(showThirdCommand.equals("aiue"));
        assertFalse(showFirstCommand.equals(new Object())); //object
        assertFalse(showSecondCommand.equals(new Object()));
        assertFalse(showThirdCommand.equals(new Object()));
        assertFalse(showFirstCommand.equals(new DeadlineDate("10-11-2022"))); //DeadlineDate
        assertFalse(showSecondCommand.equals(new DeadlineDate("10-11-2022")));
        assertFalse(showThirdCommand.equals(new DeadlineDate("10-11-2022")));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));
        assertFalse(showSecondCommand.equals(null));
        assertFalse(showThirdCommand.equals(null));

        // different task -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
        assertFalse(showFirstCommand.equals(showThirdCommand));
        assertFalse(showSecondCommand.equals(showThirdCommand));
    }

    @Test
    public void execute_zeroDateGiven_noTaskFound() {
        Optional<DeadlineDate> emptyDeadline = Optional.empty();
        DeadlineDateInRangePredicate predicate =
                new DeadlineDateInRangePredicate(emptyDeadline, emptyDeadline);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        ShowCommand command = new ShowCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_oneDateGiven_multipleTasksFound() {
        Optional<DeadlineDate> emptyDeadline = Optional.empty();
        Optional<DeadlineDate> firstDeadline = Optional.of(new DeadlineDate("09-10-2020"));
        Optional<DeadlineDate> secondDeadline = Optional.of(new DeadlineDate("10-10-2020"));
        DeadlineDateInRangePredicate firstPredicate =
                new DeadlineDateInRangePredicate(firstDeadline, emptyDeadline);
        DeadlineDateInRangePredicate secondPredicate =
                new DeadlineDateInRangePredicate(emptyDeadline, secondDeadline);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 5);
        ShowCommand command = new ShowCommand(firstPredicate);
        expectedModel.updateFilteredTaskList(firstPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103, CS2040, CS1010E, CS2030, CS2100), model.getFilteredTaskList());
        command = new ShowCommand(secondPredicate);
        expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 5);
        expectedModel.updateFilteredTaskList(secondPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103, CS2040, CS1010E, CS2030, CS3243), model.getFilteredTaskList());
    }


    @Test
    public void execute_twoDateGiven_multipleTasksFound() {
        Optional<DeadlineDate> firstDeadline = Optional.of(new DeadlineDate("09-10-2020"));
        Optional<DeadlineDate> secondDeadline = Optional.of(new DeadlineDate("10-10-2020"));
        DeadlineDateInRangePredicate predicate =
                new DeadlineDateInRangePredicate(firstDeadline, secondDeadline);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        ShowCommand command = new ShowCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103, CS2040, CS1010E, CS2030), model.getFilteredTaskList());
    }
}
