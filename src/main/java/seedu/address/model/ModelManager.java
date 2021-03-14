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
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;
import seedu.address.model.util.History;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Plan> filteredPlans;

    private boolean hasMasterPlan = false;
    private boolean hasCurrentSemester = false;

    private History history;
    private Plan masterPlan;
    private int currentSemesterNumber; // Semesters are indexed by ID

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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Plan plan) {
        requireNonNull(plan);
        return addressBook.hasPerson(plan);
    }

    @Override
    public void deletePerson(Plan target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Plan plan) {
        addressBook.addPerson(plan);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Plan target, Plan editedPlan) {
        requireAllNonNull(target, editedPlan);

        addressBook.setPerson(target, editedPlan);
    }

    //=========== Filtered Plan List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Plan} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Plan> getFilteredPersonList() {
        return filteredPlans;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Plan> predicate) {
        requireNonNull(predicate);
        filteredPlans.setPredicate(predicate);
    }

    @Override
    public History getHistory() throws CommandException {
        if (history == null) {
            history = createHistory();
        }
        return history;
    }

    private History createHistory() throws CommandException {
        if (!hasMasterPlan) {
            throw new CommandException("You must set a master plan first!");
        }
        if (!hasCurrentSemester) {
            throw new CommandException("You must set a current semester first!");
        }
        return new History(getMasterPlan(), getCurrentSemester());
    }

    @Override
    public Plan getMasterPlan() throws CommandException {
        if (!hasMasterPlan) {
            throw new CommandException("You must set a master plan first!");
        }

        return masterPlan;
    }

    @Override
    public Semester getCurrentSemester() throws CommandException {
        if (!hasCurrentSemester) {
            throw new CommandException("You must set a current semester first!");
        }

        Semester currentSem = null;
        for (Semester semester : getMasterPlan().getSemesters()) {
            if (semester.getSemNumber() == currentSemesterNumber) {
                currentSem = semester;
                break;
            }
        }

        // If the currentSem is still null it is possible that currentSemesterNumber no longer
        // matches any existing semesters, suggesting it may have been deleted.
        if (currentSem == null) {
            hasCurrentSemester = false;
            throw new CommandException("Set the current semester again, it may have been removed or deleted.");
        }
        return currentSem;
    }

    @Override
    public void setCurrentSemester(int currentSemesterNumber) throws CommandException {
        Plan masterPlan = getMasterPlan();
        int maxSemesterNumber = 0;
        for (Semester semester : masterPlan.getSemesters()) {
            if (semester.getSemNumber() > maxSemesterNumber) {
                maxSemesterNumber = semester.getSemNumber();
            }
        }

        if (currentSemesterNumber <= 0 || currentSemesterNumber > maxSemesterNumber) {
            String semNumOutOfBounds = "The argument provided to current semester must be between 1 and " +
                    maxSemesterNumber;
            throw new CommandException(semNumOutOfBounds);
        }

        this.currentSemesterNumber = currentSemesterNumber;
        this.hasCurrentSemester = true;
    }

    @Override
    public void setMasterPlan(Plan plan) {
        this.masterPlan = plan;
        this.hasMasterPlan = true;
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

}
