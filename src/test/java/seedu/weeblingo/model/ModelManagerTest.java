package seedu.weeblingo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.I_CARD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.weeblingo.testutil.FlashcardBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FlashcardBook(), new FlashcardBook(modelManager.getFlashcardBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFlashcardsFilePath(Paths.get("weeblingo/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFlashcardsFilePath(Paths.get("new/weeblingo/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFlashcardBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFlashcardBookFilePath(null));
    }

    @Test
    public void setFlashcardBookFilePath_validPath_setsFlashcardBookFilePath() {
        Path path = Paths.get("weeblingo/book/file/path");
        modelManager.setFlashcardBookFilePath(path);
        assertEquals(path, modelManager.getFlashcardBookFilePath());
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashcardBook_returnsFalse() {
        assertFalse(modelManager.hasFlashcard(A_CARD));
    }

    @Test
    public void hasFlashcard_flashcardInFlashcardBook_returnsTrue() {
        modelManager.addFlashcard(A_CARD);
        assertTrue(modelManager.hasFlashcard(A_CARD));
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> modelManager.getFilteredFlashcardList().remove(0));
    }

    @Test
    public void equals() {
        FlashcardBook flashcardBook = new FlashcardBookBuilder().withFlashcard(A_CARD).withFlashcard(I_CARD).build();
        FlashcardBook differentAddressBook = new FlashcardBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(flashcardBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(flashcardBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = A_CARD.getQuestion().value.split("\\s+");
        modelManager.updateFilteredFlashcardList(new QuestionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(flashcardBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFlashcardsFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(flashcardBook, differentUserPrefs)));
    }
}
