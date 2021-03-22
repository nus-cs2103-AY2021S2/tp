package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.event.UniqueGeneralEventList;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class RemindMe implements ReadOnlyRemindMe {

    private final UniqueModuleList modules;
    private final UniquePersonList persons;
    private final UniqueGeneralEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
        persons = new UniquePersonList();
        events = new UniqueGeneralEventList();
    }

    public RemindMe() {}

    /**
     * Creates a RemindMe using the Persons in the {@code toBeCopied}
     */
    public RemindMe(ReadOnlyRemindMe toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<GeneralEvent> events) {
        this.events.setGeneralEvents(events);
    }

    /**
     * Resets the existing data of this {@code RemindMe} with {@code newData}.
     */
    public void resetData(ReadOnlyRemindMe newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
        setEvents(newData.getEventList());
    }

    /**
     * Resets the existing data of the modules in this {@code RemindMe}.
     */
    public void resetModules() {
        setModules(new ArrayList<Module>());
    }

    /**
     * Resets the existing data of the contacts in this {@code RemindMe}.
     */
    public void resetPersons() {
        setPersons(new ArrayList<Person>());
    }

    /**
     * Resets the existing data of the general events in this {@code RemindMe}.
     */
    public void resetEvents() {
        setEvents(new ArrayList<GeneralEvent>());
    }

    //// module-level operations

    /**
     * Returns true if a module with the same title as {@code module} exists in RemindMe.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if the {@code index} is a valid index within the range of RemindMe.
     */
    public boolean hasModule(int index) {
        return index > 0 && index <= modules.size();
    }

    /**
     * Returns true if a general event with the same description as {@code event} exists in
     * RemindMe.
     */
    public boolean hasEvent(GeneralEvent event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Returns true if the {@code index} is a valid index within the range of RemindMe.
     */
    public boolean hasEvent(int index) {
        return index > 0 && index <= events.size();
    }

    /**
     * Returns true if {@code module} has an assignment with the same description and date as {@code assignment}.
     */
    public boolean hasAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        Module mod = modules.getModule(module);
        return mod.hasAssignment(assignment);
    }

    /**
     * Returns true if {@code index} is within the size of the assignment list in {@code module}.
     */
    public boolean hasAssignment(Module module, int index) {
        requireNonNull(module);
        Module mod = modules.getModule(module);
        return mod.hasAssignment(index);
    }

    /**
     * Returns true if {@code module} has an exam with the same date as {@code exam}.
     */
    public boolean hasExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        Module mod = modules.getModule(module);
        return mod.hasExam(exam);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the RemindMe.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the RemindMe.
     * The person must not already exist in the RemindMe.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the RemindMe.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the RemindMe.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code RemindMe}.
     * {@code key} must exist in RemindMe.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    /**
     * Adds a module to the module planner.
     * The module must not already exist in the module planner.
     */
    public void addModule(Module mod) {
        modules.add(mod);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the module planner.
     * The title of {@code editedModule} must not be the same as another existing module in the module planner.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code RemindMe}.
     * {@code key} must exist in the module planner.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Edits module at {@code index} and changes its title to {@code title} in the module planner.
     * {@code index} must be within the bounds of the module planner.
     */
    public void editModule(int index, Title title) {
        Module target = modules.getModule(index);
        Module editedModule = target;
        editedModule.editTitle(title);
        setModule(target, editedModule);
    }

    /**
     * Gets the module with the same title as {@code module}/
     */
    public Module getModule(Module module) {
        return modules.getModule(module);
    }

    /**
     * Adds an assignment to the module in the module planner.
     * The module must already exist in the module planner.
     * The assignment must not already exist in the module.
     */
    public void addAssignment(Module mod, Assignment assignment) {
        Module editedMod = modules.getModule(mod);
        editedMod.addAssignment(assignment);
        modules.setModule(mod, editedMod);
    }

    /**
     * Edits the description of the assignment in {@code module} at {@code index} with the given {@code edit}.
     * {@code module} must already exist in the remindMe and {@code index} must be a valid index.
     */
    public void editAssignment(Module module, int index, Description edit) {
        Module edited = modules.getModule(module);
        edited.editAssignment(index, edit);
        modules.setModule(module, edited);
    }

    /**
     * Edits the description of the assignment in {@code module} at {@code index} with the given {@code edit}.
     * {@code module} must already exist in the remindMe and {@code index} must be a valid index.
     */
    public void editAssignment(Module module, int index, LocalDateTime edit) {
        Module edited = modules.getModule(module);
        edited.editAssignment(index, edit);
        modules.setModule(module, edited);
    }

    /**
     * Removes {@code key} from {@code module} in the module planner.
     * {@code module} must exist in the module planner.
     * {@code key} must exist in {@code module}.
     * todo if not needed, pls delete.
     */
    public void removeAssignment(Module module, Assignment key) {
        Module editedModule = modules.getModule(module);
        editedModule.deleteAssignment(key);
        setModule(module, editedModule);
    }

    /**
     * Adds an exam to the module in the module planner.
     * The module must already exist in the module planner.
     * The exam must not already exist in the module.
     */
    public void addExam(Module mod, Exam exam) {
        Module editedMod = modules.getModule(mod);
        editedMod.addExam(exam);
        modules.setModule(mod, editedMod);
    }

    /**
     * Removes {@code key} from {@code module} in the module planner.
     * {@code module} must exist in the module planner.
     * {@code key} must exist in {@code module}.
     * todo if not needed, pls delete.
     */
    public void removeExam(Module module, Exam key) {
        Module editedModule = modules.getModule(module);
        editedModule.deleteExam(key);
        setModule(module, editedModule);
    }

    public void addEvent(GeneralEvent toAdd) {
        events.add(toAdd);
    }

    /**
     * Replaces the given GeneralEvent {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in RemindMe.
     * The description of {@code editedEvent} must not be the same as another existing GeneralEvent
     * in RemindMe.
     */
    public void setEvent(GeneralEvent target, GeneralEvent editedEvent) {
        requireNonNull(editedEvent);

        events.setGeneralEvent(target, editedEvent);
    }

    /**
     * Removes {@code toRemove} from this RemindMe.
     * {@code toRemove} must exist in RemindMe.
     */
    public void removeEvent(GeneralEvent toRemove) {
        events.remove(toRemove);
    }

    /**
     * Edits event at {@code index} with the given {@code description}.
     */
    public void editEvent(int index, Description description) {
        GeneralEvent target = events.getGeneralEvent(index);
        GeneralEvent edited = target.setDescription(description);
        events.setGeneralEvent(target, edited);
    }

    /**
     * Edits event at {@code index} with the given {@code date}.
     */
    public void editEvent(int index, LocalDateTime date) {
        GeneralEvent target = events.getGeneralEvent(index);
        GeneralEvent edited = target.setDate(date);
        events.setGeneralEvent(target, edited);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<GeneralEvent> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindMe // instanceof handles nulls
                && modules.equals(((RemindMe) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
