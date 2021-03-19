package fooddiary.model;

import fooddiary.commons.core.GuiSettings;
import fooddiary.model.entry.Entry;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;

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
     * Returns the user prefs' food diary file path.
     */
    Path getFoodDiaryFilePath();

    /**
     * Sets the user prefs' food diary file path.
     */
    void setFoodDiaryFilePath(Path foodDiaryFilePath);

    /**
     * Replaces food diary data with the data in {@code addressBook}.
     */
    void setFoodDiary(ReadOnlyFoodDiary foodDiary);

    /**
     * Returns the AddressBook
     */
    ReadOnlyFoodDiary getFoodDiary();

    /**
     * Returns true if a entry with the same identity as {@code entry} exists in the food diary.
     */
    boolean hasEntry(Entry entry);

    /**
     * Deletes the given entry.
     * The entry must exist in the food diary.
     */
    void deleteEntry(Entry target);

    /**
     * Adds the given entry.
     * {@code entry} must not already exist in the food diary.
     */
    void addEntry(Entry entry);

    /**
     * Replaces the given entry {@code target} with {@code editedPerson}.
     * {@code target} must exist in the food diary.
     * The entry identity of {@code editedPerson} must not be the same as another existing entry in the food diary.
     */
    void setEntry(Entry target, Entry editedEntry);

    /**
     * Returns an unmodifiable view of the filtered entry list
     */
    ObservableList<Entry> getFilteredEntryList();

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntryList(Predicate<Entry> predicate);
}
