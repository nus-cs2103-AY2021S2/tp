package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.logic.comparators.DateTimeComparator;
import seedu.address.logic.comparators.ModuleCodeComparator;
import seedu.address.logic.comparators.PriorityTagComparator;
import seedu.address.logic.comparators.TaskNameComparator;
import seedu.address.logic.comparators.WeightageComparator;
import seedu.address.logic.util.SortingFlag;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());
    private SortingFlag dateTimeFlag = new SortingFlag("dateTime");
    private SortingFlag moduleCodeFlag = new SortingFlag("moduleCode");
    private SortingFlag priorityTagFlag = new SortingFlag("priorityTag");
    private SortingFlag taskNameFlag = new SortingFlag("taskName");
    private SortingFlag weightageFlag = new SortingFlag("weightage");

    private DateTimeComparator dateTimeComparator = new DateTimeComparator();
    private ModuleCodeComparator moduleCodeComparator = new ModuleCodeComparator();
    private PriorityTagComparator priorityTagComparator = new PriorityTagComparator();
    private TaskNameComparator taskNameComparator = new TaskNameComparator();
    private WeightageComparator weightageComparator = new WeightageComparator();

    @Test
    public void equals() {


        SortCommand sortCommandDateTime = new SortCommand(dateTimeFlag);
        SortCommand sortCommandModuleCode = new SortCommand(moduleCodeFlag);
        SortCommand sortCommandPriorityTag = new SortCommand(priorityTagFlag);
        SortCommand sortCommandTaskName = new SortCommand(taskNameFlag);
        SortCommand sortCommandWeightage = new SortCommand(weightageFlag);

        // same object -> returns true
        assertTrue(sortCommandDateTime.equals(sortCommandDateTime));
        assertTrue(sortCommandModuleCode.equals(sortCommandModuleCode));
        assertTrue(sortCommandPriorityTag.equals(sortCommandPriorityTag));
        assertTrue(sortCommandTaskName.equals(sortCommandTaskName));
        assertTrue(sortCommandWeightage.equals(sortCommandWeightage));

        // same values -> returns true
        SortCommand sortCommandDateTimeCopy = new SortCommand(dateTimeFlag);
        SortCommand sortCommandModuleCodeCopy = new SortCommand(moduleCodeFlag);
        SortCommand sortCommandPriorityTagCopy = new SortCommand(priorityTagFlag);
        SortCommand sortCommandTaskNameCopy = new SortCommand(taskNameFlag);
        SortCommand sortCommandWeightageCopy = new SortCommand(weightageFlag);

        assertTrue(sortCommandDateTime.equals(sortCommandDateTimeCopy));
        assertTrue(sortCommandModuleCode.equals(sortCommandModuleCodeCopy));
        assertTrue(sortCommandPriorityTag.equals(sortCommandPriorityTagCopy));
        assertTrue(sortCommandTaskName.equals(sortCommandTaskNameCopy));
        assertTrue(sortCommandWeightage.equals(sortCommandWeightageCopy));

        // different types -> returns false
        assertFalse(sortCommandDateTime.equals(1));

        // null -> returns false
        assertFalse(sortCommandDateTime.equals(null));

        // different sorting flags -> returns false
        assertFalse(sortCommandDateTime.equals(sortCommandModuleCode));
    }

    @Test
    public void execute_validSortByDateTime_success() {
        SortCommand sortCommandDateTime = new SortCommand(dateTimeFlag);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS + dateTimeFlag.toString();
        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.sortTasks(dateTimeComparator);

        assertCommandSuccess(sortCommandDateTime, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortByModuleCode_success() {
        SortCommand sortCommandModuleCode = new SortCommand(moduleCodeFlag);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS + moduleCodeFlag.toString();
        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.sortTasks(moduleCodeComparator);

        assertCommandSuccess(sortCommandModuleCode, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortByPriorityTag_success() {
        SortCommand sortCommandPriorityTag = new SortCommand(priorityTagFlag);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS + priorityTagFlag.toString();
        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.sortTasks(priorityTagComparator);

        assertCommandSuccess(sortCommandPriorityTag, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortByTaskName_success() {
        SortCommand sortCommandTaskName = new SortCommand(taskNameFlag);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS + taskNameFlag.toString();
        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.sortTasks(taskNameComparator);

        assertCommandSuccess(sortCommandTaskName, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortByWeightage_success() {
        SortCommand sortCommandWeightage = new SortCommand(weightageFlag);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS + weightageFlag.toString();
        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.sortTasks(weightageComparator);

        assertCommandSuccess(sortCommandWeightage, model, expectedMessage, expectedModel);
    }

}
