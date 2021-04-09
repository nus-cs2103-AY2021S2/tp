package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByDeadline;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByDescription;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByModule;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByName;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByTag;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBookByWorkload;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.Task;

class SortCommandTest {

    private Model model;
    private Model deadlineSortedModel;
    private Model workloadSortedModel;
    private Model moduleSortedModel;
    private Model nameSortedModel;
    private Model descriptionSortedModel;
    private Model tagSortedModel;

    private SortCommand tagSortCommand = new SortCommand(new Task.TagComparator());
    private SortCommand nameSortCommand = new SortCommand(new Task.NameComparator());
    private SortCommand deadlineSortCommand = new SortCommand(new Task.DeadlineComparator());
    private SortCommand descriptionSortCommand = new SortCommand(new Task.DescriptionComparator());
    private SortCommand moduleSortCommand = new SortCommand(new Task.ModuleComparator());
    private SortCommand workloadSortCommand = new SortCommand(new Task.WorkloadComparator());

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
        deadlineSortedModel = new ModelManager(getSortedTypicalModuleBookByDeadline(), new UserPrefs());
        workloadSortedModel = new ModelManager(getSortedTypicalModuleBookByWorkload(), new UserPrefs());
        moduleSortedModel = new ModelManager(getSortedTypicalModuleBookByModule(), new UserPrefs());
        nameSortedModel = new ModelManager(getSortedTypicalModuleBookByName(), new UserPrefs());
        descriptionSortedModel = new ModelManager(getSortedTypicalModuleBookByDescription(), new UserPrefs());
        tagSortedModel = new ModelManager(getSortedTypicalModuleBookByTag(), new UserPrefs());
    }

    @Test
    public void execute_sortedTaskByDeadline_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.DeadlineComparator()), deadlineSortedModel,
                "Sorted all tasks by deadline.", deadlineSortedModel);
    }

    @Test
    public void execute_unsortedTaskByDeadline_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.DeadlineComparator()), model,
                "Sorted all tasks by deadline.", deadlineSortedModel);
    }

    @Test
    public void execute_sortedTaskByWorkload_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.WorkloadComparator()), workloadSortedModel,
            "Sorted all tasks by workload.", workloadSortedModel);
    }

    @Test
    public void execute_unsortedTaskByWorkload_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.WorkloadComparator()), model,
            "Sorted all tasks by workload.", workloadSortedModel);
    }

    @Test
    public void execute_sortedTaskByModule_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.ModuleComparator()), moduleSortedModel,
            "Sorted all tasks by module.", moduleSortedModel);
    }

    @Test
    public void execute_unsortedTaskByModule_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.ModuleComparator()), model,
            "Sorted all tasks by module.", moduleSortedModel);
    }

    @Test
    public void execute_sortedTaskByName_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.NameComparator()), nameSortedModel,
            "Sorted all tasks by task name.", nameSortedModel);
    }

    @Test
    public void execute_unsortedTaskByName_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.NameComparator()), model,
            "Sorted all tasks by task name.", nameSortedModel);
    }

    @Test
    public void execute_sortedTaskByDescription_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.DescriptionComparator()), descriptionSortedModel,
            "Sorted all tasks by length of description.", descriptionSortedModel);
    }

    @Test
    public void execute_sortedTaskByDescription_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.DescriptionComparator()), model,
            "Sorted all tasks by length of description.", descriptionSortedModel);
    }

    @Test
    public void execute_sortedTaskByNumberOfTags_showsSameList() {
        assertCommandSuccess(new SortCommand(new Task.TagComparator()), tagSortedModel,
            "Sorted all tasks by number of tags.", tagSortedModel);
    }

    @Test
    public void execute_sortedTaskByNumberOfTags_showsSortedList() {
        assertCommandSuccess(new SortCommand(new Task.TagComparator()), model,
            "Sorted all tasks by number of tags.", tagSortedModel);
    }

    @Test
    public void equals() {
        assertEquals(tagSortCommand, tagSortCommand);

        assertEquals(tagSortCommand, new SortCommand(new Task.TagComparator()));

        assertEquals(nameSortCommand, new SortCommand(new Task.NameComparator()));

        assertEquals(deadlineSortCommand, new SortCommand(new Task.DeadlineComparator()));

        assertEquals(descriptionSortCommand, new SortCommand(new Task.DescriptionComparator()));

        assertEquals(moduleSortCommand, new SortCommand(new Task.ModuleComparator()));

        assertEquals(workloadSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(tagSortCommand, "Not a SortCommand");

        assertNotEquals(nameSortCommand, new SortCommand(new Task.TagComparator()));

        assertNotEquals(nameSortCommand, new SortCommand(new Task.DeadlineComparator()));

        assertNotEquals(nameSortCommand, new SortCommand(new Task.DescriptionComparator()));

        assertNotEquals(nameSortCommand, new SortCommand(new Task.ModuleComparator()));

        assertNotEquals(nameSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(tagSortCommand, new SortCommand(new Task.DeadlineComparator()));

        assertNotEquals(tagSortCommand, new SortCommand(new Task.DescriptionComparator()));

        assertNotEquals(tagSortCommand, new SortCommand(new Task.ModuleComparator()));

        assertNotEquals(tagSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(deadlineSortCommand, new SortCommand(new Task.DescriptionComparator()));

        assertNotEquals(deadlineSortCommand, new SortCommand(new Task.ModuleComparator()));

        assertNotEquals(deadlineSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(descriptionSortCommand, new SortCommand(new Task.ModuleComparator()));

        assertNotEquals(descriptionSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(moduleSortCommand, new SortCommand(new Task.WorkloadComparator()));

        assertNotEquals(workloadSortCommand, new SortCommand(new Task.TagComparator()));

    }

}
