package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.common.Date;
import seedu.address.model.event.Event;
import seedu.address.model.task.Task;


public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public Path getSocheduleFilePath() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setSocheduleFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void addTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void addEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setSochedule(ReadOnlySochedule newData) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void sortTasks(String comparingVar) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortTasksDefault() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getNumCompletedTask() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getNumOverdueTask() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getNumIncompleteTask() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortEvents(String comparingVar) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getNumIncomingEvents() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ArrayList<String> getFreeTimeSlots(Date date) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlySochedule getSochedule() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean hasTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean hasEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean isTaskListEmpty() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean isEventListEmpty() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void deleteTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void deleteEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void doneTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void pinTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void unpinTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setEvent(Event target, Event editedEvent) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void clearExpiredTasks() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void clearCompletedTasks() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void clearExpiredEvents() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
