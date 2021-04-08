package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.weeblingo.commons.core.GuiSettings;
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
    public void execute_check_success() throws Exception {
        Answer attempt = new FlashcardBuilder().build().getAnswer();
        ModelStubCheckSuccessful modelStub = new ModelStubCheckSuccessful();
        CommandResult commandResult = new CheckCommand(attempt).execute(modelStub);
        assertEquals(CheckCommand.CORRECT_ATTEMPT + "\n"
                + CheckCommand.MESSAGE_HELPER, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_check_failure() throws Exception {
        Answer attempt = new FlashcardBuilder().build().getAnswer();
        ModelStubCheckFailure modelStub = new ModelStubCheckFailure();
        CommandResult commandResult = new CheckCommand(attempt).execute(modelStub);
        assertEquals(attempt + CheckCommand.WRONG_ATTEMPT + "\n" + CheckCommand.MESSAGE_HELPER,
                commandResult.getFeedbackToUser());
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
        public void addFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcardBook(ReadOnlyFlashcardBook newData) {
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
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {
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
        public boolean isCorrectAttempt(Answer attempt) {
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
        public ObservableList<Score> getFilteredScoreHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScoreHistory(Predicate<Score> s) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Quiz getQuizInstance() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeQuiz() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchModeLearn() {
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
        public void addScore() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getQuizStatisticString() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getCorrectAttemptsString() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always checks and returns a correct answer.
     */
    private class ModelStubCheckSuccessful extends ModelStub {

        @Override
        public boolean isCorrectAttempt(Answer attempt) {
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

    /**
     * A Model stub that always checks and returns a wrong answer.
     */
    private class ModelStubCheckFailure extends ModelStub {

        @Override
        public boolean isCorrectAttempt(Answer attempt) {
            return false;
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
}
