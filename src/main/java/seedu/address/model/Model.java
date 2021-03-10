package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Garment;

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
     * Deletes the given person.
     * The person must exist in the wardrobe.
     */
    void deleteGarment(Garment target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the wardrobe.
     */
    void addGarment(Garment garment);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the wardrobe.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setGarment(Garment target, Garment editedGarment);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Garment> getFilteredGarmentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGarmentList(Predicate<Garment> predicate);
}
