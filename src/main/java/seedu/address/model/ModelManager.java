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
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResidenceTracker residenceTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyResidenceTracker residenceTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(residenceTracker, userPrefs);

        logger.fine("Initializing with address book: " + residenceTracker + " and user prefs " + userPrefs);

        this.residenceTracker = new ResidenceTracker(residenceTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.residenceTracker.getPersonList());
    }

    public ModelManager() {
        this(new ResidenceTracker(), new UserPrefs());
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
    public Path getResidenceTrackerFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setResidenceTrackerFilePath(Path residenceTrackerFilePath) {
        requireNonNull(residenceTrackerFilePath);
        userPrefs.setAddressBookFilePath(residenceTrackerFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setResidenceTracker(ReadOnlyResidenceTracker residenceTracker) {
        this.residenceTracker.resetData(residenceTracker);
    }

    @Override
    public ReadOnlyResidenceTracker getResidenceTracker() {
        return residenceTracker;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return residenceTracker.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        residenceTracker.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        residenceTracker.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        residenceTracker.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedResidenceTracker}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return residenceTracker.equals(other.residenceTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
