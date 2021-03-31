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
import seedu.address.model.garment.Garment;

/**
 * Represents the in-memory model of the wardrobe data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Wardrobe wardrobe;
    private final UserPrefs userPrefs;
    //private final FilteredList<Garment> filteredGarments;
    private FilteredList<Garment> filteredGarments;

    /**
     * Initializes a ModelManager with the given wardrobe and userPrefs.
     */
    public ModelManager(ReadOnlyWardrobe wardrobe, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(wardrobe, userPrefs);

        logger.fine("Initializing with wardrobe: " + wardrobe + " and user prefs " + userPrefs);

        this.wardrobe = new Wardrobe(wardrobe);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredGarments = new FilteredList<>(this.wardrobe.getGarmentList());
    }

    public ModelManager() {
        this(new Wardrobe(), new UserPrefs());
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
    public Path getWardrobeFilePath() {
        return userPrefs.getWardrobeFilePath();
    }

    @Override
    public void setWardrobeFilePath(Path wardrobeFilePath) {
        requireNonNull(wardrobeFilePath);
        userPrefs.setWardrobeFilePath(wardrobeFilePath);
    }

    //=========== Wardrobe ================================================================================

    @Override
    public void setWardrobe(ReadOnlyWardrobe wardrobe) {
        this.wardrobe.resetData(wardrobe);
    }

    @Override
    public ReadOnlyWardrobe getWardrobe() {
        return wardrobe;
    }

    @Override
    public boolean hasGarment(Garment garment) {
        requireNonNull(garment);
        return wardrobe.hasGarment(garment);
    }

    @Override
    public void deleteGarment(Garment target) {
        wardrobe.removeGarment(target);
    }

    @Override
    public void addGarment(Garment garment) {
        wardrobe.addGarment(garment);
        updateFilteredGarmentList(PREDICATE_SHOW_ALL_GARMENTS);
    }

    @Override
    public void setGarment(Garment target, Garment editedGarment) {
        requireAllNonNull(target, editedGarment);

        wardrobe.setGarment(target, editedGarment);
    }

    //=========== Filtered Garment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Garment} backed by the internal list of
     * {@code versionedWardrobe}
     */
    @Override
    public ObservableList<Garment> getFilteredGarmentList() {
        return filteredGarments;
    }

    @Override
    public void updateFilteredGarmentList(Predicate<Garment> predicate) {
        requireNonNull(predicate);
        filteredGarments.setPredicate(predicate);
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
        return wardrobe.equals(other.wardrobe)
                && userPrefs.equals(other.userPrefs)
                && filteredGarments.equals(other.filteredGarments);
    }

}
