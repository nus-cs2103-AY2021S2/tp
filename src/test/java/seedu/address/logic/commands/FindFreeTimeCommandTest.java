package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EXAMPLE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.FREE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.NO_FREE_TIME_DATE;
import static seedu.address.testutil.TypicalEvents.getTypicalSochedule;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.common.Date;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class FindFreeTimeCommandTest {

    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_validDateNoFreeTime_success() {
        Date exampleDate = new Date(NO_FREE_TIME_DATE);
        FindFreeTimeCommand findFreeTimeCommand = new FindFreeTimeCommand(exampleDate);
        String expectedMessage = findFreeTimeCommand.MESSAGE_NO_FREE_TIME;
        try {
            CommandResult result = findFreeTimeCommand.execute(model);
            assertEquals(result, new CommandResult(expectedMessage));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void execute_validDateAllDayFree_success() {
        Date exampleDate = new Date(FREE_DATE);
        ArrayList<String> actual = model.getFreeTimeSlots(exampleDate);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The entire day is free!");
        assertEquals(actual, expected);
    }

    @Test
    public void equals() {
        Date exampleDateOne = new Date(FREE_DATE);
        Date exampleDateTwo = new Date(EXAMPLE_DATE);
        FindFreeTimeCommand findFreeTimeCommandOne = new FindFreeTimeCommand(exampleDateOne);
        FindFreeTimeCommand findFreeTimeCommandTwo = new FindFreeTimeCommand(exampleDateTwo);

        // same object -> returns true
        assertTrue(findFreeTimeCommandOne.equals(findFreeTimeCommandOne));


        // different types -> returns false
        assertFalse(findFreeTimeCommandOne.equals(1));

        // null -> returns false
        assertFalse(findFreeTimeCommandOne.equals(null));

        // different task -> returns false
        assertFalse(findFreeTimeCommandOne.equals(findFreeTimeCommandTwo));
    }
}
