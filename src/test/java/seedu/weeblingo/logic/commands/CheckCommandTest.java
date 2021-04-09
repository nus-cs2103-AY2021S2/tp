package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.Quiz;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.ReadOnlyUserPrefs;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.model.tag.Tag;
import seedu.weeblingo.testutil.FlashcardBuilder;

public class CheckCommandTest {

    @Test
    public void constructor_nullAttempt_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckCommand(null));
    }

    @Test
    public void exectue_correctAnswer_checkSuccessful() throws Exception {
        Answer correctAttempt = new FlashcardBuilder().build().getAnswer();
        ModelStubCheckSuccessful modelStub = new ModelStubCheckSuccessful();
        CommandResult commandResult = new CheckCommand(correctAttempt).execute(modelStub);

        assertEquals(CheckCommand.CORRECT_ATTEMPT + "\n" + CheckCommand.MESSAGE_HELPER,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void exectue_wrongAnswer_checkFailure() throws Exception {
        Answer wrongAttempt = new FlashcardBuilder().build().getAnswer();
        ModelStubCheckFailure modelStub = new ModelStubCheckFailure();
        CommandResult commandResult = new CheckCommand(wrongAttempt).execute(modelStub);

        assertEquals(wrongAttempt + CheckCommand.WRONG_ATTEMPT + "\n" + CheckCommand.MESSAGE_HELPER,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonQuizSessionMode_throwsCommandException() {
        Answer attempt = new FlashcardBuilder().build().getAnswer();
        ModelStubMenuMode modelStub = new ModelStubMenuMode();
        CheckCommand checkCommand = new CheckCommand(attempt);

        assertThrows(CommandException.class, Messages.MESSAGE_NOT_IN_QUIZ_SESSION,
                () -> checkCommand.execute(modelStub));
    }

    @Test
    public void execute_checkSuccessMode_throwsCommandException() {
        Answer attempt = new FlashcardBuilder().build().getAnswer();
        ModelStubCheckSuccessMode modelStub = new ModelStubCheckSuccessMode();
        CheckCommand checkCommand = new CheckCommand(attempt);

        assertThrows(CommandException.class, CheckCommand.MULTIPLE_CHECKING_AFTER_SUCCESS,
                () -> checkCommand.execute(modelStub));
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
        public Path getFlashcardBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBookFilePath(Path flashcardBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBook(ReadOnlyFlashcardBook flashcardBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashcardBook getFlashcardBook() {
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
        public void addFlashcard(Flashcard flashcard) {
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
        public ObservableList<Score> getFilteredScoreHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScoreHistory(Predicate<Score> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Quiz getQuizInstance() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Flashcard getNextFlashcard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCurrentIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showAttemptedQuestions() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearQuizInstance() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isCorrectAnswer(Answer attempt) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Mode getMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCurrentMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void tagFlashcard(Flashcard target, String tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeQuiz() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeLearn() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeMenu() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeQuizSession() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeCheckSuccess() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeQuizSessionEnded() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getQuizStatisticString() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Integer> getCorrectAttemptsIndexes() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addScore() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubCheckSuccessful extends ModelStub {
        @Override
        public boolean isCorrectAnswer(Answer attempt) {
            return true;
        }

        @Override
        public void switchModeCheckSuccess() {
        }

        @Override
        public String getQuizStatisticString() {
            return "";
        }

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION;
        }
    }

    private class ModelStubCheckFailure extends ModelStub {
        @Override
        public boolean isCorrectAnswer(Answer attempt) {
            return false;
        }

        @Override
        public String getQuizStatisticString() {
            return "";
        }

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION;
        }
    }

    private class ModelStubMenuMode extends ModelStub {
        @Override
        public int getCurrentMode() {
            return Mode.MODE_MENU;
        }
    }

    private class ModelStubCheckSuccessMode extends ModelStub {
        @Override
        public int getCurrentMode() {
            return Mode.MODE_CHECK_SUCCESS;
        }
    }

}
