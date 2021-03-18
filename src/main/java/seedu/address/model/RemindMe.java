package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class RemindMe implements ReadOnlyRemindMe {

    private final UniqueModuleList modules;
    private final UniquePersonList persons;

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
    }

    public RemindMe() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public RemindMe(ReadOnlyRemindMe toBeCopied) {
        this();
        resetData(toBeCopied);
    }

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
     * Resets the existing data of this {@code RemindMe} with {@code newData}.
     */
    public void resetData(ReadOnlyRemindMe newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
    }

    public void resetModules() {
        setModules(new ArrayList<Module>());
    }

    public void resetPersons() {
        setPersons(new ArrayList<Person>());
    }

    //// module-level operations

    /**
     * Returns true if a module with the same title as {@code module} exists in the module planner.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if the {@code index} is a valid index within the range of the module planner.
     */
    public boolean hasModule(int index) {
        return index > 0 && index <= modules.size();
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
     * Returns true if {@code module} has an exam with the same date as {@code exam}.
     */
    public boolean hasExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        Module mod = modules.getModule(module);
        return mod.hasExam(exam);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
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
     * Removes {@code key} from {@code module} in the module planner.
     * {@code module} must exist in the module planner.
     * {@code key} must exist in {@code module}.
     */
    public void removeAssignment(Module module, Assignment key) {
        Module editedModule = modules.getModule(module);
        editedModule.deleteAssignment(key);
        setModule(module, editedModule);
    }

    /**
     * Removes {@code key} from {@code module} in the module planner.
     * {@code module} must exist in the module planner.
     * {@code key} must exist in {@code module}.
     */
    public void removeExam(Module module, Exam key) {
        Module editedModule = modules.getModule(module);
        editedModule.deleteExam(key);
        setModule(module, editedModule);
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
