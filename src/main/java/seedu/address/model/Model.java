package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;
import seedu.address.model.util.History;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Plan> PREDICATE_SHOW_ALL_PLANS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getPlansFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPlansFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setPlans(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getPlans();

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    boolean hasPlan(Plan plan);

    /**
     * Deletes the given plan.
     * The plan must exist in the address book.
     */
    void deletePlan(Plan target);

    /**
     * Adds the given plan.
     * {@code plan} must not already exist in the address book.
     */
    void addPlan(Plan plan);

    /**
     * Replaces the given plan {@code target} with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    void setPlan(Plan target, Plan editedPlan);

    /** Returns an unmodifiable view of the filtered plan list */
    ObservableList<Plan> getFilteredPlanList();

    /**
     * Updates the filter of the filtered plan list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPlanList(Predicate<Plan> predicate);

    /**
     * Validates all plans based on masterPlan
     * @param masterPlan the already set masterPlan
     * @param currentSemester the already set currentSemester
     */
    void validate(Plan masterPlan, Semester currentSemester);

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    boolean hasSemester(int planNumber, Semester semester);

    /**
     * Deletes the given plan.
     * The plan must exist in the address book.
     */
    void deleteSemester(Plan plan, Semester target);

    /**
     * Adds the given plan.
     * {@code plan} must not already exist in the address book.
     */
    void addSemester(int planNumber, Semester semester);

    boolean hasModule(int planNumber, int semNumber, Module module) throws CommandException;

    void addModule(int planNumber, int semNumber, Module module);

    History getHistory() throws CommandException;

    Plan getMasterPlan() throws CommandException;

    Semester getCurrentSemester() throws CommandException;

    void setCurrentSemester(Integer currentSemesterNumber) throws CommandException;

    void setMasterPlan(Plan plan) throws CommandException;

    StringProperty getCurrentCommand();

    void setCurrentCommand(String command);

    void deleteModule(Plan plan, Semester semester, Module module);

    boolean isValidGrade(String grade);
}
