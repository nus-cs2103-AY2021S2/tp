package seedu.flashback.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashback.commons.core.GuiSettings;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.model.FlashBack;
import seedu.flashback.model.Model;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.ReadOnlyUserPrefs;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.testutil.FlashcardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_flashcardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFlashcardAdded modelStub = new ModelStubAcceptingFlashcardAdded();
        Flashcard validFlashcard = new FlashcardBuilder().build();

        CommandResult commandResult = new AddCommand(validFlashcard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashcard), modelStub.flashcardsAdded);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        Flashcard validFlashcard = new FlashcardBuilder().build();
        AddCommand addCommand = new AddCommand(validFlashcard);
        ModelStub modelStub = new ModelStubWithFlashcard(validFlashcard);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_FLASHCARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Flashcard alice = new FlashcardBuilder().withQuestion("Alice").build();
        Flashcard bob = new FlashcardBuilder().withQuestion("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFlashBackFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashBackFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashBack(ReadOnlyFlashBack newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashBack getFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitFlashBack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredFlashcardList(Comparator<Flashcard> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isCommand(String input) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isReview(String input) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAlias(String input) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(String command, String alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canAddAlias(String command, String alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String parseAlias(String alias) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithFlashcard extends ModelStub {
        private final Flashcard flashcard;

        ModelStubWithFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            this.flashcard = flashcard;
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return this.flashcard.isSameCard(flashcard);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingFlashcardAdded extends ModelStub {
        final ArrayList<Flashcard> flashcardsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return flashcardsAdded.stream().anyMatch(flashcard::isSameCard);
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            flashcardsAdded.add(flashcard);
        }

        @Override
        public ReadOnlyFlashBack getFlashBack() {
            return new FlashBack();
        }

        @Override
        public void commitFlashBack() {
        }
    }

}
