package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalModuleBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class ModuleBookTest {

    private final ModuleBook addressBook = new ModuleBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleBook_replacesData() {
        ModuleBook newData = getTypicalModuleBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        ModuleBookStub newData = new ModuleBookStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInModuleBook_returnsFalse() {
        assertFalse(addressBook.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInModuleBook_returnsTrue() {
        addressBook.addTask(ALICE);
        assertTrue(addressBook.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInModuleBook_returnsTrue() {
        addressBook.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0));
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
