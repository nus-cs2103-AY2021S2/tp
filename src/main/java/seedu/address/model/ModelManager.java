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
import seedu.address.model.residence.Residence;

/**
 * Represents the in-memory model of the residence tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResidenceTracker residenceTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Residence> filteredResidences;

    /**
     * Initializes a ModelManager with the given residenceTracker and userPrefs.
     */
    public ModelManager(ReadOnlyResidenceTracker residenceTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(residenceTracker, userPrefs);

        logger.fine("Initializing with residence tracker: " + residenceTracker + " and user prefs " + userPrefs);

        this.residenceTracker = new ResidenceTracker(residenceTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredResidences = new FilteredList<>(this.residenceTracker.getResidenceList());

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
        return userPrefs.getResidenceTrackerFilePath();
    }

    @Override
    public void setResidenceTrackerFilePath(Path residenceTrackerFilePath) {
        requireNonNull(residenceTrackerFilePath);
        userPrefs.setResidenceTrackerFilePath(residenceTrackerFilePath);
    }

    //=========== ResidenceTracker ===========================================================================

    @Override
    public void setResidenceTracker(ReadOnlyResidenceTracker residenceTracker) {
        this.residenceTracker.resetData(residenceTracker);
    }

    @Override
    public ReadOnlyResidenceTracker getResidenceTracker() {
        return residenceTracker;
    }

    @Override
    public boolean hasResidence(Residence residence) {
        requireNonNull(residence);
        return residenceTracker.hasResidence(residence);
    }

    @Override
    public void deleteResidence(Residence target) {
        residenceTracker.removeResidence(target);
    }

    @Override
    public void addResidence(Residence residence) {
        residenceTracker.addResidence(residence);
        updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
    }

    @Override
    public void setResidence(Residence target, Residence editedResidence) {
        requireAllNonNull(target, editedResidence);

        residenceTracker.setResidence(target, editedResidence);
    }

    //=========== Filtered Residence and Residence List Accessors ===============================================

    /**
     * Returns an unmodifiable view of the list of {@code Residence} backed by the internal list of
     * {@code versionedResidenceTracker}
     */
    @Override
    public ObservableList<Residence> getFilteredResidenceList() {
        return filteredResidences;
    }


    @Override
    public void updateFilteredResidenceList(Predicate<Residence> predicate) {
        requireNonNull(predicate);
        filteredResidences.setPredicate(predicate);
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
                && filteredResidences.equals(other.filteredResidences);
    }

}
