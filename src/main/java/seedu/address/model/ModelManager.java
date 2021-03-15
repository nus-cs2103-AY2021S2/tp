package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Plan> filteredPlans;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPlans = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPlansFilePath() {
        return userPrefs.getPlansFilePath();
    }

    @Override
    public void setPlansFilePath(Path plansFilePath) {
        requireNonNull(plansFilePath);
        userPrefs.setPlansFilePath(plansFilePath);
    }

    //=========== Plan ================================================================================
    public void setPlans(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getPlans() {
        return addressBook;
    }

    @Override
    public boolean hasPlan(Plan plan) {
        requireNonNull(plan);
        return addressBook.hasPlan(plan);
    }

    @Override
    public void deletePlan(Plan target) {
        addressBook.removePlan(target);
    }

    @Override
    public void addPlan(Plan plan) {
        addressBook.addPlan(plan);
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public void setPlan(Plan target, Plan editedPlan) {
        requireAllNonNull(target, editedPlan);

        addressBook.setPlan(target, editedPlan);
    }

    //=========== Semester ================================================================================

    @Override
    public boolean hasSemester(int planNumber, Semester semester) {
        requireAllNonNull(planNumber, semester);
        Plan plan = addressBook.getPersonList().get(planNumber);
        return plan.getSemesters().stream().anyMatch((currentSemester) ->
            currentSemester.getSemNumber() == semester.getSemNumber()
        );
    }

    @Override
    public void deleteSemester(Plan plan, Semester target) {
        addressBook.setPlan(plan, plan.removeSemester(target));
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public void addSemester(int planNumber, Semester semester) {
        Plan plan = addressBook.getPersonList().get(planNumber);
        addressBook.setPlan(plan, plan.addSemester(semester));
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    //=========== Filtered Plan List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Plan} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Plan> getFilteredPlanList() {
        return filteredPlans;
    }

    @Override
    public void updateFilteredPlanList(Predicate<Plan> predicate) {
        requireNonNull(predicate);
        filteredPlans.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPlans.equals(other.filteredPlans);
    }

    @Override
    public boolean hasModule(int planNumber, int semNumber, Module module) throws CommandException {
        requireAllNonNull(semNumber, module);
        try {
            Plan plan = addressBook.getPersonList().get(planNumber);
            Semester semester = plan.getSemesters().get(semNumber);
            return semester.getModules().stream().anyMatch((currentModule) -> {
                return currentModule.getModuleCode() == module.getModuleCode();
            });
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Plan or Semester index is invalid", e);
        }
    }

    @Override
    public void addModule(int planNumber, int semNumber, Module module) {
        Plan plan = addressBook.getPersonList().get(planNumber);
        Semester semester = plan.getSemesters().get(semNumber);
        semester.addModule(module);
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }
}
