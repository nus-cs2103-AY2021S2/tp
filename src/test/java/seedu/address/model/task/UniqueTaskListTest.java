package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_PROJECT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(ASSIGNMENT));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(ASSIGNMENT);
        assertTrue(uniqueTaskList.contains(ASSIGNMENT));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(ASSIGNMENT);
        Task editedAssignment = new TaskBuilder(ASSIGNMENT).withDeadline(VALID_DEADLINE_TASKONE)
                .withCategories(VALID_CATEGORY_PROJECT).build();
        assertTrue(uniqueTaskList.contains(editedAssignment));
    }


    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(ASSIGNMENT));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, ASSIGNMENT));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, ASSIGNMENT));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.setTask(ASSIGNMENT, ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ASSIGNMENT);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }


    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(ASSIGNMENT);
        Task editedAssignment = new TaskBuilder(ASSIGNMENT).withDeadline(VALID_DEADLINE_TASKONE)
                .withCategories(VALID_CATEGORY_PROJECT).build();
        uniqueTaskList.setTask(ASSIGNMENT, editedAssignment);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedAssignment);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }


    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.setTask(ASSIGNMENT, LAB);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(LAB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.add(LAB);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, LAB));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(ASSIGNMENT));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.remove(ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(LAB);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(ASSIGNMENT);
        List<Task> taskList = Collections.singletonList(LAB);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(LAB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(ASSIGNMENT, ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTaskList.asUnmodifiableObservableList()
                .remove(0));
    }
}
