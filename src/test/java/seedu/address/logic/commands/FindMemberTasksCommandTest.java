package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = FindMemberTasksCommand.MESSAGE_SUCCESS;
        TaskContainsAssigneePredicate predicate = preparePredicate(" ");
        FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
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

    //    @Test
    //    public void execute_singleKeyword_existingMember_tasksFound() {
    //        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
    //
    //        //Exists in list
    //        TaskContainsAssigneePredicate predicate = preparePredicate(ALICE.getName().fullName);
    //        FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
    //        expectedModel.updateFilteredTaskList(predicate);
    //
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    //    }

    //        @Test
    //        public void execute_multipleKeywords_existingMember_tasksFound() {
    //            String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
    //
    //            // Does not exist in list
    //            TaskContainsAssigneePredicate predicate = preparePredicate(ALICE.getName().fullName
    //                    + BENSON.getName().fullName);
    //            FindMemberTasksCommand command = new FindMemberTasksCommand(predicate);
    //            expectedModel.updateFilteredTaskList(predicate);
    //
    //            assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //            assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    //        }


    /**
     * Parses {@code userInput} into a {@code DetailsContainsKeywordsPredicate}.
     */
    private TaskContainsAssigneePredicate preparePredicate(String userInput) {
        return new TaskContainsAssigneePredicate(userInput);
    }
}
