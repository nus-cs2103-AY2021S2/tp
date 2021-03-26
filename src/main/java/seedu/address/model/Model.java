package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.garment.ContainsKeywordsPredicate;
import seedu.address.model.garment.Garment;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Garment> PREDICATE_SHOW_ALL_GARMENTS = unused -> true;

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
     * Returns the user prefs' wardrobe file path.
     */
    Path getWardrobeFilePath();

    /**
     * Sets the user prefs' wardrobe file path.
     */
    void setWardrobeFilePath(Path wardrobeFilePath);

    /**
     * Replaces wardrobe data with the data in {@code wardrobe}.
     */
    void setWardrobe(ReadOnlyWardrobe wardrobe);

    /** Returns the Wardrobe */
    ReadOnlyWardrobe getWardrobe();

    /**
     * Returns true if a garment with the same identity as {@code garment} exists in the wardrobe.
     */
    boolean hasGarment(Garment garment);

    /**
     * Deletes the given garment.
     * The garment must exist in the wardrobe.
     */
    void deleteGarment(Garment target);

    /**
     * Adds the given garment.
     * {@code garment} must not already exist in the wardrobe.
     */
    void addGarment(Garment garment);

    /**
     * Replaces the given garment {@code target} with {@code editedGarment}.
     * {@code target} must exist in the wardrobe.
     * The garment identity of {@code editedGarment} must not be the same as another existing garment in the wardrobe.
     */
    void setGarment(Garment target, Garment editedGarment);

    /** Returns an unmodifiable view of the filtered garment list */
    ObservableList<Garment> getFilteredGarmentList();

    /**
     * Updates the filter of the filtered garment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGarmentList(Predicate<Garment> predicate);

    /**
     * Updates the filter of the filtered garment list to filter by the given {@code predicateList}.
     * @throws NullPointerException if {@code predicateList} is null.
     */
    //above meth works fine, but when i put into a list, ListList<Predicate<Garment>>, goes haywire?
    void updateFilteredGarmentList(List<ContainsKeywordsPredicate> predicateList);
    //hacking

}
