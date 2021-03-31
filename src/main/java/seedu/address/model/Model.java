package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;
    Predicate<GeneralEvent> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' remind me file path.
     */
    Path getRemindMeFilePath();

    /**
     * Sets the user prefs' RemindMe file path.
     */
    void setRemindMeFilePath(Path remindMeFilePath);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the RemindMe.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the RemindMe.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the RemindMe.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the RemindMe.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the RemindMe.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a module with the same title, exams, and assignments as {@code module}
     * exists in the RemindMe.
     */
    boolean hasModule(Module module);

    /**
     * Returns true if there exists a module in the module list at {@code index}
     */
    boolean hasModule(int index);

    /**
     * Adds the given module.
     * {@code module} must not already exists in RemindMe.
     */
    void addModule(Module module);

    /**
     * Deletes the given module.
     * The Module must exist in the RemindMe.
     */
    void deleteModule(Module target);

    /**
     * Gets the module with the same title as {@code module}
     */
    Module getModule(Module module);

    /**
     * Gets the module at {@code index}
     */
    Module getModule(int index);

    /**
     * Replaces the given person {@code target} with {@code editedMod}.
     * {@code target} must exist in the RemindMe.
     * The person identity of {@code editedMod} must not be the same as another existing person in the RemindMe.
     */
    void setModule(Module target, Module editedMod);

    /**
     * Returns true if an assignment that has the same description and deadline
     * as {@code assignment} exists in the same module.
     */
    boolean hasAssignment(Module module, Assignment assignment);

    /**
     * Returns true if the {@code index} is within the size of the
     * assignment list in {@code module}
     */
    boolean hasAssignment(Module module, int index);

    /**
     * Edits the given module at index
     */
    void editModule(int index, Title target);

    /**
     * Adds the given assignment.
     * {@code assignment} must not already exist in the module it is to be added to.
     */
    void addAssignment(Module module, Assignment assignment);

    /**
     * Edits the description of the assignment at {@code index} in the {@code module} with the {@code edit}.
     * {@code module} must already exist in the remindMe and {@code index} must be a valid index.
     */
    void editAssignment(Module module, int index, Description edit);

    /**
     * Edits the deadline of the assignment at {@code index} in the {@code module} with the {@code edit}.
     * {@code module} must already exist in the remindMe and {@code index} must be a valid index.
     */
    void editAssignment(Module module, int index, LocalDateTime edit);

    /**
     * Returns true if an exam with the same date and time as {@code module} exists in the
     * RemindMe.
     */
    boolean hasExam(Module module, Exam exam);

    /**
     * Returns true if {@code index} is within the exam list of {@code module}.
     */
    boolean hasExam(Module module, int index);

    /**
     * Edits the date of the exam at {@code index} in the {@code module} with the given {@code edit}
     */
    void editExam(Module module, int index, LocalDateTime edit);

    /**
     * Adds the given exam.
     * {@code exam} must not already exist in the module it is to be added to.
     */
    void addExam(Module module, Exam exam);

    /**
     * Replaces remindMe data with the data in {@code remindMe}.
     */
    void setRemindMe(RemindMe remindMe);

    /**
     * Clear RemindMe's modules.
     */
    void resetModules();

    /**
     * Clear RemindMe's persons.
     */
    void resetPersons();

    /**
     * Clear RemindMe's general events.
     */
    void resetEvents();

    /**
     * Returns the RemindMe
     */
    ReadOnlyRemindMe getRemindMe();

    /**
     * Returns true if an event with the same description and date as {@code event} exists in the
     * RemindMe.
     */
    boolean hasEvent(GeneralEvent event);

    /**
     * Returns true if there exists an event in the event list at {@code index}
     */
    boolean hasEvent(int index);

    /**
     * Adds the given event to RemindMe.
     * {@code event} must not already exist in RemindMe.
     */
    void addEvent(GeneralEvent event);

    /**
     * Edits the given event at {@code index} with the given edit {@code edit}.
     */
    void editEvent(int index, Description edit);

    /**
     * Edits the given event at {@code index} with the given edit {@code edit}.
     */
    void editEvent(int index, LocalDateTime edit);

    /**
     * Removes the given target event from model
     * @param target
     */
    void deleteEvent(GeneralEvent target);

    /**
     * Gets the event at {@code index}.
     */
    GeneralEvent getEvent(int index);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    void updateFilteredEventList(Predicate<GeneralEvent> predicate);

    ObservableList<GeneralEvent> getFilteredEventList();
}
