package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.module.logic.commands.CommandTestUtil.VALID_RECURRENCE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_RECURRENCE_MIDTERM;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.Recurrence;
import seedu.module.model.task.Task;
import seedu.module.testutil.TaskBuilder;

public class RecurCommandTest {
    private String invalidRecurrenceString = "invalid recurrence";
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecurCommand(null));
    }

    @Test
    public void setRecurrence_nullOptionalRecurrence_throwsAssertionErro() {
        RecurCommand command = new RecurCommand(INDEX_FIRST_TASK);
        //EP: recurrence is null
        assertThrows(AssertionError.class, () -> command.setRecurrence(null));
    }
    @Test
    public void setRecurrence_invalidRecurrenceString_throwsIllegalArgumentException() {
        RecurCommand command = new RecurCommand(INDEX_FIRST_TASK);
        //EP: recurrence is invalid
        assertThrows(IllegalArgumentException.class, () -> command.setRecurrence(
                new OptionalField<>(new Recurrence(invalidRecurrenceString))));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        RecurCommand recurCommandWithInvalidIndex = new RecurCommand(invalidIndex);
        assertCommandFailure(recurCommandWithInvalidIndex, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_sameRecurrenceForTask_throwsCommandException() {
        OptionalField<Recurrence> recurrenceOptional = new OptionalField<>(new Recurrence(VALID_RECURRENCE_MIDTERM));

        RecurCommand recurCommand = new RecurCommand(INDEX_SECOND_TASK); //midterm
        recurCommand.setRecurrence(recurrenceOptional);
        String expectedMessage = String.format(RecurCommand.MESSAGE_DUPLICATE_RECURRENCE, VALID_RECURRENCE_MIDTERM);
        assertCommandFailure(recurCommand, model, expectedMessage);
    }

    @Test
    public void execute_recurNonRecurringTask_throwsCommandException() {
        RecurCommand recurCommand = new RecurCommand(INDEX_FIRST_TASK);
        recurCommand.setRecurrence(new OptionalField<>(null));
        String expectedMessage = RecurCommand.MESSAGE_REMOVE_RECURRENCE_UNSUCCESSFUL;
        assertCommandFailure(recurCommand, model, expectedMessage);
    }

    @Test
    public void execute_addValidRecurrence_success() {
        Model expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        RecurCommand recurCommand = new RecurCommand(INDEX_SECOND_TASK);
        //EP: change recurrence of recurring task
        Task task = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task newTask = new TaskBuilder(task).withRecurrence(VALID_RECURRENCE_LAB).build();
        expectedModel.setTask(task, newTask);

        OptionalField<Recurrence> recurrenceOptional = new OptionalField<>(new Recurrence(VALID_RECURRENCE_LAB));
        recurCommand.setRecurrence(recurrenceOptional);
        String expectedMessage = String.format(RecurCommand.MESSAGE_ADD_RECURRENCE_SUCCESS, newTask.toString());

        assertCommandSuccess(recurCommand, model, expectedMessage, expectedModel);

        //EP: add recurrence to non-recurring task
        Task nonRecurringTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task newRecurringTask = new TaskBuilder(nonRecurringTask).withRecurrence(VALID_RECURRENCE_LAB).build();
        expectedModel.setTask(nonRecurringTask, newRecurringTask);

        RecurCommand recurCommandForNonRecurringTask = new RecurCommand(INDEX_FIRST_TASK);
        recurCommandForNonRecurringTask.setRecurrence(recurrenceOptional);
        String expMessage = String.format(RecurCommand.MESSAGE_ADD_RECURRENCE_SUCCESS, newRecurringTask.toString());

        assertCommandSuccess(recurCommandForNonRecurringTask, model, expMessage, expectedModel);
    }
    @Test
    public void execute_removeRecurrence_success() {
        Model expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        Task task = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());

        //EP: remove recurrence of a task
        OptionalField<Recurrence> emptyRecurrence = new OptionalField<>(null);
        Task newNonRecurringTask = new Task(task.getName(), task.getStartTimeWrapper(), task.getDeadline(),
                task.getModule(), task.getDescription(), task.getWorkload(),
                task.getDoneStatus(), emptyRecurrence, task.getTags());

        expectedModel.setTask(task, newNonRecurringTask);

        RecurCommand recurCommandToRemoveRecurrence = new RecurCommand(INDEX_SECOND_TASK);
        recurCommandToRemoveRecurrence.setRecurrence(new OptionalField<>(null));
        String expectedMessageRemoveSuccess = RecurCommand.MESSAGE_REMOVE_RECURRENCE_SUCCESS;

        assertCommandSuccess(recurCommandToRemoveRecurrence, model, expectedMessageRemoveSuccess, expectedModel);
    }
}
