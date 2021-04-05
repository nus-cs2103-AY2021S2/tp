package seedu.heymatez.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.testutil.Assert.assertThrows;
import static seedu.heymatez.testutil.TypicalTasks.HOMEWORK;
import static seedu.heymatez.testutil.TypicalTasks.MARATHON;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.heymatez.model.task.exceptions.TaskNotFoundException;
import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code UniqueTaskList}.
 */
public class UniqueTaskListTest {
    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.addTask(HOMEWORK);
        assertTrue(uniqueTaskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.addTask(HOMEWORK);
        Task editedHomework = new TaskBuilder(HOMEWORK).build();
        assertTrue(uniqueTaskList.contains(editedHomework));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.addTask(null));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, HOMEWORK));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(HOMEWORK, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(HOMEWORK, HOMEWORK));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.addTask(HOMEWORK);
        uniqueTaskList.setTask(HOMEWORK, HOMEWORK);
        UniqueTaskList expectedUniqueUniqueTaskList = new UniqueTaskList();
        expectedUniqueUniqueTaskList.addTask(HOMEWORK);
        assertEquals(expectedUniqueUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.addTask(HOMEWORK);
        Task editedHomework = new TaskBuilder(HOMEWORK).build();
        uniqueTaskList.setTask(HOMEWORK, editedHomework);
        UniqueTaskList expectedUniqueUniqueTaskList = new UniqueTaskList();
        expectedUniqueUniqueTaskList.addTask(editedHomework);
        assertEquals(expectedUniqueUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.addTask(HOMEWORK);
        uniqueTaskList.setTask(HOMEWORK, MARATHON);
        UniqueTaskList expectedUniqueUniqueTaskList = new UniqueTaskList();
        expectedUniqueUniqueTaskList.addTask(MARATHON);
        assertEquals(expectedUniqueUniqueTaskList, uniqueTaskList);
    }


    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(HOMEWORK));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.addTask(HOMEWORK);
        uniqueTaskList.remove(HOMEWORK);
        UniqueTaskList expectedUniqueUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedTaskList() {
        uniqueTaskList.addTask(HOMEWORK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.addTask(MARATHON);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.addTask(HOMEWORK);
        List<Task> taskList = Collections.singletonList(MARATHON);
        this.uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.addTask(MARATHON);
        assertEquals(expectedUniqueTaskList, this.uniqueTaskList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTaskList.asUnmodifiableObservableList()
                .remove(0));
    }
}
