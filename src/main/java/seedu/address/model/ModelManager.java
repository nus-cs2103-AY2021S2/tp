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
import seedu.address.model.flashcard.Flashcard;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FlashBack flashBack;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyFlashBack flashBack, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(flashBack, userPrefs);

        logger.fine("Initializing with address book: " + flashBack + " and user prefs " + userPrefs);

        this.flashBack = new FlashBack(flashBack);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.flashBack.getFlashcardList());
    }

    public ModelManager() {
        this(new FlashBack(), new UserPrefs());
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
    public Path getFlashBackFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setFlashBackFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setFlashBack(ReadOnlyFlashBack addressBook) {
        this.flashBack.resetData(addressBook);
    }

    @Override
    public ReadOnlyFlashBack getFlashBack() {
        return flashBack;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashBack.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        flashBack.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        flashBack.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);

        flashBack.setFlashcard(target, editedFlashcard);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return filteredFlashcards;
    }

    @Override
    public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
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
        return flashBack.equals(other.flashBack)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

}
