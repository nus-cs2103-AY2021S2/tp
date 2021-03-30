package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNEE_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNEE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalHeyMatez;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsAssigneePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindMemberTasksCommand}.
 */
public class FindMemberTasksCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsAssigneePredicate firstPredicate =
                new TaskContainsAssigneePredicate("first");
        TaskContainsAssigneePredicate secondPredicate =
                new TaskContainsAssigneePredicate("second");

        FindMemberTasksCommand findFirstCommand = new FindMemberTasksCommand(firstPredicate);
        FindMemberTasksCommand findSecondCommand = new FindMemberTasksCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMemberTasksCommand findFirstCommandCopy = new FindMemberTasksCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_illegalArgumentException() {
        TaskContainsAssigneePredicate predicate = preparePredicate(" ");
        assertThrows(IllegalArgumentException.class, () -> expectedModel.updateFilteredTaskList(predicate));
    }

    @Test
    public void execute_singleKeyword_nonExistentMemberSuccess() {
        String expectedMessage = FindMemberTasksCommand.MESSAGE_SUCCESS;

        // Does not exist in list
        TaskContainsAssigneePredicate predicate = preparePredicate("Alex Yeoh");
        FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_singleKeywordExistingMember_tasksFound() {
        String expectedMessage = FindMemberTasksCommand.MESSAGE_SUCCESS;

        //Exists in list
        TaskContainsAssigneePredicate predicate = preparePredicate(VALID_ASSIGNEE_MARATHON);
        FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleKeywordsExistingMember_noTasksFound() {
        String expectedMessage = FindMemberTasksCommand.MESSAGE_SUCCESS;

        // Does not exist in list
        TaskContainsAssigneePredicate predicate = preparePredicate(VALID_ASSIGNEE_MARATHON + " "
                + VALID_ASSIGNEE_MEETING);
        FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code DetailsContainsKeywordsPredicate}.
     */
    private TaskContainsAssigneePredicate preparePredicate(String userInput) {
        return new TaskContainsAssigneePredicate(userInput);
    }
}
