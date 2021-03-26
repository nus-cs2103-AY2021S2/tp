package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.garment.ContainsKeywordsPredicate;
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

    //have a overloaded meth
    //hacking
    //create a new class called multiple arguments predicate
    @Override
    public void updateFilteredGarmentList(List<ContainsKeywordsPredicate> predicateList) {
        requireNonNull(predicateList);
        //using the main list of filteredarguments. but since this is a void method how is
        //it displaying it correctly tho?
        FilteredList<Garment> updatedFilteredGarments = filteredGarments;
        System.out.println(updatedFilteredGarments);
        System.out.println("0");
        for (Predicate<Garment> predicate : predicateList) {
            updatedFilteredGarments.setPredicate(predicate);
            System.out.println("1");
            System.out.println(updatedFilteredGarments);
            updatedFilteredGarments = new FilteredList<>(updatedFilteredGarments);
        }
        //currently shows the correct num of garment and filteredglist print is correct, but somehow not translating
        // to the app
        //this also affects the list command anyways so not a good soln
        filteredGarments = updatedFilteredGarments;
        System.out.println("2");
        System.out.println(updatedFilteredGarments);
        System.out.println(filteredGarments);

        /*
        //sizepred not of super type of namepred to do 'and'
        ContainsKeywordsPredicate allPredicates = predicateList.get(0);
        System.out.println("outside");
        System.out.println(allPredicates);
        filteredGarments.setPredicate(allPredicates);
        System.out.println(filteredGarments);
        for (int i = 1; i < predicateList.size(); i++) {
            allPredicates.and(predicateList.get(i));
            System.out.println("inside");
            System.out.println(predicateList.get(i));

            System.out.println(allPredicates);
            filteredGarments.setPredicate(allPredicates);
            System.out.println(filteredGarments);
        }
        filteredGarments.setPredicate(allPredicates);
        System.out.println("out");
        System.out.println(filteredGarments);*/
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
