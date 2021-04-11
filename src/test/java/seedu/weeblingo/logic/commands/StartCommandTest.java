package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_IN_QUIZ_SESSION;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_NOT_IN_QUIZ_MODE;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_SUCCESS;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.weeblingo.commons.core.GuiSettings;
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
import seedu.weeblingo.testutil.Assert;

public class StartCommandTest {

    private static final StartCommand STARTCOMMAND_GENERIC = new StartCommand(VALID_START_INTEGER_GENERIC,
            VALID_TAGS_SET_GENERIC);

    private static final StartCommand STARTCOMMAND_HIRAGANA = new StartCommand(VALID_START_INTEGER_GENERIC,
            VALID_TAGS_SET_GENERIC);

    @Test
    public void execute_startInQuizSession_failure() {
        ModelStubQuizSessionMode modelStub = new ModelStubQuizSessionMode();
        Assert.assertThrows(CommandException.class, MESSAGE_IN_QUIZ_SESSION, () ->
                STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_startInCheckSuccess_failure() {
        ModelStubCheckSuccessMode modelStub = new ModelStubCheckSuccessMode();
        Assert.assertThrows(CommandException.class, MESSAGE_IN_QUIZ_SESSION, () ->
                STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_startInMenu_failure() {
        ModelStubMenuMode modelStub = new ModelStubMenuMode();
        Assert.assertThrows(CommandException.class, MESSAGE_NOT_IN_QUIZ_MODE, () ->
                STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_startInLearn_failure() {
        ModelStubLearnMode modelStub = new ModelStubLearnMode();
        Assert.assertThrows(CommandException.class, MESSAGE_NOT_IN_QUIZ_MODE, () ->
                STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_startInHistory_failure() {
        ModelStubHistoryMode modelStub = new ModelStubHistoryMode();
        Assert.assertThrows(CommandException.class, MESSAGE_NOT_IN_QUIZ_MODE, () ->
                STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_quizModeGeneric_success() throws CommandException {
        ModelStubQuizMode modelStub = new ModelStubQuizMode();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false);
        assertEquals(expectedCommandResult, STARTCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_quizSessionEndedModeHiragana_success() throws CommandException {
        ModelStubQuizSessionEndedMode modelStub = new ModelStubQuizSessionEndedMode();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false);
        assertEquals(expectedCommandResult, STARTCOMMAND_HIRAGANA.execute(modelStub));
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

    private class ModelStubMenuMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_MENU;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubQuizSessionMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubCheckSuccessMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_CHECK_SUCCESS;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubHistoryMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_HISTORY;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubLearnMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_LEARN;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubQuizMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

    private class ModelStubQuizSessionEndedMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ_SESSION_ENDED;
        }

        @Override
        public void clearQuizInstance() {

        }

        @Override
        public void startQuiz(int numberOfQuestions, Set<Tag> tags) {

        }

        @Override
        public void switchModeQuizSession() {

        }

    }

}
