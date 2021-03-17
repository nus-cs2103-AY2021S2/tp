package seedu.module.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.QUIZ;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.module.model.task.Task;
import seedu.module.model.task.exceptions.DuplicateTaskException;
import seedu.module.testutil.TaskBuilder;

public class ModuleBookTest {

    private final ModuleBook moduleBook = new ModuleBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleBook_replacesData() {
        ModuleBook newData = getTypicalModuleBook();
        moduleBook.resetData(newData);
        assertEquals(newData, moduleBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        List<Task> newTasks = Arrays.asList(QUIZ, editedAlice);
        ModuleBookStub newData = new ModuleBookStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> moduleBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInModuleBook_returnsFalse() {
        assertFalse(moduleBook.hasTask(QUIZ));
    }

    @Test
    public void hasTask_taskInModuleBook_returnsTrue() {
        moduleBook.addTask(QUIZ);
        assertTrue(moduleBook.hasTask(QUIZ));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInModuleBook_returnsTrue() {
        moduleBook.addTask(QUIZ);
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH)
                .build();
        assertTrue(moduleBook.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleBook whose tasks list can violate interface constraints.
     */
    private static class ModuleBookStub implements ReadOnlyModuleBook {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ModuleBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
