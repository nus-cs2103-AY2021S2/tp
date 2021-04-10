package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAGS_SET_HIRAGANA;
import static seedu.weeblingo.logic.commands.LearnCommand.MESSAGE_HAVE_TAGS;
import static seedu.weeblingo.logic.commands.LearnCommand.MESSAGE_NO_TAGS;
import static seedu.weeblingo.logic.commands.LearnCommand.MESSAGE_SUCCESS;

import java.nio.file.Path;
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
import seedu.weeblingo.testutil.Assert;

public class LearnCommandTest {

    private static final LearnCommand LEARNCOMMAND_GENERIC = new LearnCommand(VALID_TAGS_SET_GENERIC);
    private static final LearnCommand LEARNCOMMAND_HIRAGANA = new LearnCommand(VALID_TAGS_SET_HIRAGANA);

    @Test
    public void execute_menuModeGeneric_success() throws CommandException {
        ModelStubMenuMode modelStub = new ModelStubMenuMode();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS
                + MESSAGE_NO_TAGS, false, false);
        assertEquals(expectedCommandResult, LEARNCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_learnModeGeneric_success() throws CommandException {
        ModelStubLearnMode modelStub = new ModelStubLearnMode();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS
                + MESSAGE_NO_TAGS, false, false);
        assertEquals(expectedCommandResult, LEARNCOMMAND_GENERIC.execute(modelStub));
    }

    @Test
    public void execute_validTagHiragana_success() throws CommandException {
        ModelStubMenuMode modelStub = new ModelStubMenuMode();
        String expectedCommandResultString = MESSAGE_SUCCESS + MESSAGE_HAVE_TAGS + VALID_TAGS_SET_HIRAGANA.toString();
        CommandResult commandResult = LEARNCOMMAND_HIRAGANA.execute(modelStub);
        assertEquals(expectedCommandResultString, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_wrongModeGeneric_failure() {
        ModelStubQuizMode modelStub = new ModelStubQuizMode();
        Assert.assertThrows(CommandException.class, Messages.MESSAGE_NOT_IN_MENU_MODE, () ->
                LEARNCOMMAND_GENERIC.execute(modelStub));
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
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {

        }

        @Override
        public void switchModeLearn() {

        }

    }

    private class ModelStubLearnMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_LEARN;
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {

        }

        @Override
        public void switchModeLearn() {

        }

    }

    private class ModelStubQuizMode extends ModelStub {

        @Override
        public int getCurrentMode() {
            return Mode.MODE_QUIZ;
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {

        }

        @Override
        public void switchModeLearn() {

        }

    }

}
