package seedu.address.testutil;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RemindMe;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Person;

/**
 * A default model stub that have all of the methods failing.
 */
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
    public Path getRemindMeFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRemindMeFilePath(Path remindMeFilePath) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModule(Module module) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void deleteExam(Module module, Exam exam) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void deleteAssignment(Module module, Assignment assignment) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void deleteGeneralEvent(GeneralEvent generalEvent) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public Module getModule(Module module) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public Module getModule(int index) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void setModule(Module target, Module editedMod) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public boolean hasAssignment(Module module, Assignment assignment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAssignment(Module module, int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editAssignment(Module module, int index, Description edit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editAssignment(Module module, int index, LocalDateTime edit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void toggleDoneStatusForAssignment(Module module, int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAssignment(Module module, Assignment assignment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExam(Module module, Exam exam) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExam(Module module, int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExam(Module module, Exam exam) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editExam(Module module, int index, LocalDateTime edit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRemindMe(RemindMe remindMe) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetModules() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetPersons() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetEvents() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editModule(int index, Title title) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyRemindMe getRemindMe() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(GeneralEvent event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(GeneralEvent event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editEvent(int index, Description edit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editEvent(int index, LocalDateTime edit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(GeneralEvent event) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public GeneralEvent getEvent(int index) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void setEvent(GeneralEvent target, GeneralEvent editedEvent) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEventList(Predicate<GeneralEvent> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<GeneralEvent> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }
}
