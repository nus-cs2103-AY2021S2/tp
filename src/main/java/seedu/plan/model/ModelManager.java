package seedu.plan.model;

import static java.util.Objects.requireNonNull;
import static seedu.plan.model.plan.Grade.GRADE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.commons.core.LogsCenter;
import seedu.plan.commons.util.CollectionUtil;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.plan.Module;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;
import seedu.plan.model.util.History;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModulePlanner modulePlanner;
    private final UserPrefs userPrefs;
    private final FilteredList<Plan> filteredPlans;

    private Integer currentSemesterNumber; // Semesters are indexed by ID
    private StringProperty currentCommand;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyModulePlanner addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.modulePlanner = new ModulePlanner(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPlans = new FilteredList<>(this.modulePlanner.getPersonList());
        currentSemesterNumber = addressBook.getCurrentSemesterNumber();
        this.currentCommand = new SimpleStringProperty("");
    }

    public ModelManager() {
        this(new ModulePlanner(), new UserPrefs());
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
    public void setPlansFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPlansFilePath(addressBookFilePath);
    }

    //=========== Plan ================================================================================

    @Override
    public void setPlans(ReadOnlyModulePlanner addressBook) {
        this.modulePlanner.resetData(addressBook);
    }

    @Override
    public ReadOnlyModulePlanner getPlans() {
        return modulePlanner;
    }

    @Override
    public boolean hasPlan(Plan plan) {
        requireNonNull(plan);
        return modulePlanner.hasPlan(plan);
    }

    @Override
    public void deletePlan(Plan target) {
        modulePlanner.removePlan(target);
    }

    @Override
    public void addPlan(Plan plan) {
        modulePlanner.addPlan(plan);
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public void setPlan(Plan target, Plan editedPlan) {
        CollectionUtil.requireAllNonNull(target, editedPlan);

        modulePlanner.setPlan(target, editedPlan);
    }

    /**
     * Checks if all taken modules in master also exist in the other plans
     * @param masterPlan the already set masterPlan
     * @param currentSemester the already set currentSemester
     */
    @Override
    public void validate(Plan masterPlan, Semester currentSemester) {
        List<Module> masterModules = new ArrayList<>();

        for (int i = 1; i < currentSemester.getSemNumber(); i++) {
            Semester sem = masterPlan.getSemester(i);
            List<Module> semModules = sem.getModules();
            if (!semModules.isEmpty()) {
                masterModules = Stream.concat(masterModules.stream(), semModules.stream())
                        .collect(Collectors.toList());
            }
        }

        // filteredPlans
        for (Plan p : filteredPlans) {
            if (p.equals(masterPlan)) {
                continue;
            }

            Plan newPlan = p;

            List<Module> modulesInPlan = new ArrayList<>();
            for (int i = 1; i < currentSemester.getSemNumber(); i++) {
                Semester sem = p.getSemester(i);
                if (sem == null) {
                    break;
                }
                modulesInPlan = Stream.concat(modulesInPlan.stream(), sem.getModules().stream())
                        .collect(Collectors.toList());
            }
            if (modulesInPlan.containsAll(masterModules)) {
                newPlan.setIsValid(true);
            } else {
                newPlan.setIsValid(false);
            }
            modulePlanner.setPlan(p, newPlan);
        }

        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    //=========== Semester ================================================================================

    @Override
    public boolean hasSemester(int planNumber, Semester semester) {
        CollectionUtil.requireAllNonNull(planNumber, semester);
        Plan plan = modulePlanner.getPersonList().get(planNumber);
        return plan.getSemesters().stream().anyMatch((currentSemester) ->
            currentSemester.getSemNumber() == semester.getSemNumber()
        );
    }

    @Override
    public void deleteSemester(Plan plan, Semester target) {
        modulePlanner.setPlan(plan, plan.removeSemester(target));
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public void addSemester(int planNumber, Semester semester) {
        Plan plan = modulePlanner.getPersonList().get(planNumber);
        modulePlanner.setPlan(plan, plan.addSemester(semester));
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
    public History getHistory() throws CommandException {
        if (!hasMasterPlan()) {
            throw new CommandException("You must set a master plan first!");
        }
        if (!hasCurrentSemester()) {
            throw new CommandException("You must set a current semester first!");
        }
        return new History(getMasterPlan(), getCurrentSemester());
    }

    private boolean hasCurrentSemester() {
        return currentSemesterNumber != null;
    }


    @Override
    public Plan getMasterPlan() throws CommandException {
        if (!hasMasterPlan()) {
            throw new CommandException("You must set a master plan first!");
        }
        return getFilteredPlanList().stream().filter(p -> p.getIsMasterPlan()).findFirst().get();
    }

    @Override
    public Semester getCurrentSemester() throws CommandException {
        if (!hasCurrentSemester()) {
            throw new CommandException("You must set a current semester first!");
        }

        Semester currentSem = null;
        for (Semester semester : getMasterPlan().getSemesters()) {
            if (currentSemesterNumber.equals(semester.getSemNumber())) {
                currentSem = semester;
                break;
            }
        }

        // If the currentSem is still null it is possible that currentSemesterNumber no longer
        // matches any existing semesters, suggesting it may have been deleted.
        if (currentSem == null) {
            currentSemesterNumber = null;
            throw new CommandException("Set the current semester again, it may have been removed or deleted.");
        }
        return currentSem;
    }

    @Override
    public void setCurrentSemester(Integer currentSemesterNumber) throws CommandException {
        // Null means to remove currentSemester
        if (currentSemesterNumber == null) {
            this.currentSemesterNumber = null;
            modulePlanner.setCurrentSemesterNumber(this.currentSemesterNumber);
            return;
        }

        Plan masterPlan = getMasterPlan();
        Integer maxSemesterNumber = 0;
        for (Semester semester : masterPlan.getSemesters()) {
            if (semester.getSemNumber() > maxSemesterNumber) {
                maxSemesterNumber = semester.getSemNumber();
            }
        }

        if (currentSemesterNumber <= 0 || currentSemesterNumber > maxSemesterNumber) {
            String semNumOutOfBounds = "The argument provided to current semester must be between 1 and "
                    + maxSemesterNumber;
            throw new CommandException(semNumOutOfBounds);
        }

        this.currentSemesterNumber = currentSemesterNumber;
        modulePlanner.setCurrentSemesterNumber(currentSemesterNumber);
    }

    @Override
    public void setMasterPlan(Plan plan) throws CommandException {
        requireNonNull(plan);
        if (hasMasterPlan()) {
            Plan oldMasterPlan = getMasterPlan();
            oldMasterPlan.setMasterPlan(false);
        }
        plan.setMasterPlan(true);
    }

    @Override
    public StringProperty getCurrentCommand() {
        return currentCommand;
    }

    @Override
    public void setCurrentCommand(String command) {
        this.currentCommand.set(command);
    }

    private boolean hasMasterPlan() {
        return getFilteredPlanList().stream().anyMatch(p -> p.getIsMasterPlan());
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
        return modulePlanner.equals(other.modulePlanner)
                && userPrefs.equals(other.userPrefs)
                && filteredPlans.equals(other.filteredPlans);
    }

    @Override
    public boolean hasModule(int planNumber, int semNumber, Module module) throws CommandException {
        CollectionUtil.requireAllNonNull(semNumber, module);
        try {
            Plan plan = modulePlanner.getPersonList().get(planNumber);
            Semester semester = plan.getSemesterBySemNumber(semNumber);
            List<Module> addedModules = semester.getModules();
            for (Module m : addedModules) {
                if (m.getModuleCode().equals(module.getModuleCode())) {
                    return true;
                }
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Plan or Semester index is invalid", e);
        } catch (NullPointerException e) {
            throw new CommandException("Plan or Semester index is invalid", e);
        }
    }

    @Override
    public void addModule(int planNumber, int semNumber, Module module) {
        Plan originalPlan = modulePlanner.getPersonList().get(planNumber);
        Plan newPlan = originalPlan;
        Semester semester = newPlan.getSemesterBySemNumber(semNumber);
        semester.addModule(module);
        newPlan.addNumModules();
        if (originalPlan.getIsMasterPlan()) {
            newPlan.setMasterPlan(true);
        }
        modulePlanner.setPlan(originalPlan, newPlan);
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public void deleteModule(Plan plan, Semester semester, Module module) {
        Semester newSemester = semester.removeModule(module);
        Plan newPlan = plan.changePlan(semester, newSemester);
        if (plan.getIsMasterPlan()) {
            newPlan.setMasterPlan(true);
        }
        modulePlanner.setPlan(plan, newPlan);
        updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
    }

    @Override
    public boolean isValidGrade(String grade) {
        return GRADE.containsKey(grade);
    }
}
