package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalTasks.MIDTERM;
import static seedu.module.testutil.TypicalTasks.PROJECT;
import static seedu.module.testutil.TypicalTasks.QUIZ;
import static seedu.module.testutil.TypicalTasks.TUTORIAL;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.Module;
import seedu.module.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code FindModuleCommand}.
 */
public class FindModuleCommandTest {
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    public void equals() {
        Module firstMod = new Module("CS2105");
        Module secondMod = new Module("CS2106");

        FindModuleCommand findFirstCommand = new FindModuleCommand(firstMod.toString());
        FindModuleCommand findSecondCommand = new FindModuleCommand(secondMod.toString());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindModuleCommand findFirstCommandCopy = new FindModuleCommand(firstMod.toString());
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        Predicate<Task> predicate = preparePredicate(" ");
        FindModuleCommand command = new FindModuleCommand("");
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_invalidModule_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        Predicate<Task> predicate = preparePredicate("CSMoDuLeCoDe");
        FindModuleCommand command = new FindModuleCommand("CSMoDuLeCoDe");
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_existModule_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        Predicate<Task> predicate = preparePredicate("ST2131");
        FindModuleCommand command = new FindModuleCommand("ST2131");
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TUTORIAL), model.getFilteredTaskList());
    }

    @Test
    public void execute_existModule_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        Predicate<Task> predicate = preparePredicate("CS3243");
        FindModuleCommand command = new FindModuleCommand("CS3243");
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(QUIZ, MIDTERM, PROJECT), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate}.
     */
    private Predicate<Task> preparePredicate(String userInput) {
        return (Task x) -> x.getModule().toString().equals(userInput);
    }
}
