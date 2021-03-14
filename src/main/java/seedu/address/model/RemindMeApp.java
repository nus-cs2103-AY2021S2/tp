package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

public class RemindMeApp implements ReadOnlyRemindMeApp {

    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
    }

    public RemindMeApp() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public RemindMeApp(ReadOnlyRemindMeApp toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRemindMeApp newData) {
        requireNonNull(newData);
        setModules(newData.getModuleList());
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
     * Removes {@code key} from this {@code ModulePlanner}.
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindMeApp // instanceof handles nulls
                && modules.equals(((RemindMeApp) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
