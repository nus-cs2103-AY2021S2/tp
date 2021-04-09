package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
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
import seedu.weeblingo.testutil.QuizBuilder;

public class NextCommandTest {

    @Test
    public void execute_hasNextQuestion_nextSuccessful() throws CommandException {
        ModelStubHasNextQuestion modelStub = new ModelStubHasNextQuestion();
        CommandResult commandResult = new NextCommand().execute(modelStub);

        assertEquals(NextCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_hasNoNextQuestion_quizEnded() throws CommandException {
        ModelStubNoNextQuestion modelStub = new ModelStubNoNextQuestion();
        CommandResult commandResult = new NextCommand().execute(modelStub);
        List<Integer> emptyList = new ArrayList<>();

        assertEquals(NextCommand.MESSAGE_QUIZ_ENDED + "\n" + NextCommand.MESSAGE_CORRECT_ATTEMPTS_HELPER
                + emptyList.toString() + "\n", commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonQuizSessionMode_throwsCommandException() {
        NextCommand nextCommand = new NextCommand();
        ModelStubMenuMode modelStub = new ModelStubMenuMode();

        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUIZ_SESSION, () -> nextCommand.execute(modelStub));
    }

    @Test
    public void execute_quizSessionEndedMode_throwsCommandException() {
        NextCommand nextCommand = new NextCommand();
        ModelStubQuizSessionEndedMode modelStub = new ModelStubQuizSessionEndedMode();

        assertThrows(CommandException.class,
                NextCommand.MESSAGE_QUIZ_ALREADY_ENDED
                        + NextCommand.MESSAGE_QUIZ_END_ACTIONS, () -> nextCommand.execute(modelStub));
    }

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
        public Quiz getQuizInstance() throws CommandException {
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

    private class ModelStubHasNextQuestion extends ModelStub {
        @Override
        public Quiz getQuizInstance() throws CommandException {
            return new QuizBuilder().build();
        }

        @Override
        public Flashcard getNextFlashcard() {
            return new FlashcardBuilder().build();
        }

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION;
        }

        @Override
        public void switchModeQuizSession() {

        }
    }

    private class ModelStubNoNextQuestion extends ModelStub {
        @Override
        public Quiz getQuizInstance() throws CommandException {
            return new QuizBuilder().build();
        }

        @Override
        public Flashcard getNextFlashcard() {
            return null;
        }

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION;
        }

        @Override
        public void addScore() {

        }

        @Override
        public void showAttemptedQuestions() {

        }

        @Override
        public List<Integer> getCorrectAttemptsIndexes() {
            return new ArrayList<>();
        }

        @Override
        public void switchModeQuizSessionEnded() {

        }

        @Override
        public String getQuizStatisticString() {
            return "";
        }
    }

    private class ModelStubMenuMode extends ModelStub {
        @Override
        public int getCurrentMode() {
            return Mode.MODE_MENU;
        }
    }

    private class ModelStubQuizSessionEndedMode extends ModelStub {
        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION_ENDED;
        }
    }

}

