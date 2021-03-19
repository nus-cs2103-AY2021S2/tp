package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.MARATHON;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.addTask(HOMEWORK);
        assertTrue(taskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        taskList.addTask(HOMEWORK);
        Task editedHomework = new TaskBuilder(HOMEWORK).build();
        assertTrue(taskList.contains(editedHomework));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, HOMEWORK));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(HOMEWORK, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(HOMEWORK, HOMEWORK));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.addTask(HOMEWORK);
        taskList.setTask(HOMEWORK, HOMEWORK);
        TaskList expectedUniqueTaskList = new TaskList();
        expectedUniqueTaskList.addTask(HOMEWORK);
        assertEquals(expectedUniqueTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        taskList.addTask(HOMEWORK);
        Task editedHomework = new TaskBuilder(HOMEWORK).build();
        taskList.setTask(HOMEWORK, editedHomework);
        TaskList expectedUniqueTaskList = new TaskList();
        expectedUniqueTaskList.addTask(editedHomework);
        assertEquals(expectedUniqueTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.addTask(HOMEWORK);
        taskList.setTask(HOMEWORK, MARATHON);
        TaskList expectedUniqueTaskList = new TaskList();
        expectedUniqueTaskList.addTask(MARATHON);
        assertEquals(expectedUniqueTaskList, taskList);
    }


    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(HOMEWORK));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.addTask(HOMEWORK);
        taskList.remove(HOMEWORK);
        TaskList expectedUniqueTaskList = new TaskList();
        assertEquals(expectedUniqueTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedTaskList() {
        taskList.addTask(HOMEWORK);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(MARATHON);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.addTask(HOMEWORK);
        List<Task> taskList = Collections.singletonList(MARATHON);
        this.taskList.setTasks(taskList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addTask(MARATHON);
        assertEquals(expectedTaskList, this.taskList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskList.asUnmodifiableObservableList()
                .remove(0));
    }
}
