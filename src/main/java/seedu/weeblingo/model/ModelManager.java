package seedu.weeblingo.model;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FlashcardBook flashcardBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;
    private final FilteredList<Score> filteredHistoryScores;
    private final Mode mode;
    private Quiz quizInstance;

    /**
     * Initializes a ModelManager with the given flashcardBook and userPrefs.
     */
    public ModelManager(ReadOnlyFlashcardBook flashcardBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(flashcardBook, userPrefs);

        logger.fine("Initializing with address book: " + flashcardBook + " and user prefs " + userPrefs);

        this.flashcardBook = new FlashcardBook(flashcardBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.flashcardBook.getFlashcardList());
        filteredHistoryScores = new FilteredList<>(this.flashcardBook.getScoreHistoryList());
        this.mode = new Mode();
    }

    public ModelManager() {
        this(new FlashcardBook(), new UserPrefs());
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
    public Path getFlashcardBookFilePath() {
        return userPrefs.getFlashcardBookFilePath();
    }

    @Override
    public void setFlashcardBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setFlashcardsFilePath(addressBookFilePath);
    }

    //=========== FlashcardBook ================================================================================

    @Override
    public void setFlashcardBook(ReadOnlyFlashcardBook flashcardBook) {
        this.flashcardBook.resetData(flashcardBook);
    }

    @Override
    public ReadOnlyFlashcardBook getFlashcardBook() {
        return flashcardBook;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcardBook.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        flashcardBook.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        flashcardBook.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);

        flashcardBook.setFlashcard(target, editedFlashcard);
    }

    @Override
    public void tagFlashcard(Flashcard target, String tag) {

    }

    @Override
    public void addScore() {
        flashcardBook.addScore(quizInstance.giveScore());
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedFlashcardBook}
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

    // =================== Score History ==============================================
    @Override
    public ObservableList<Score> getFilteredScoreHistory() {
        return flashcardBook.getScoreHistoryList();
    }

    @Override
    public void updateFilteredScoreHistory(Predicate<Score> predicate) {
        requireNonNull(predicate);
        filteredHistoryScores.setPredicate(predicate);
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
        return flashcardBook.equals(other.flashcardBook)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

    //=========== Quiz Related =============================================================

    @Override
    public void startQuiz(int numberOfQuestions, Set<Tag> tags) throws CommandException {
        this.quizInstance = new Quiz(filteredFlashcards, numberOfQuestions, tags);
        Flashcard next = quizInstance.getNextQuestion();
        updateFilteredFlashcardList(curr -> curr.equals(next));
    }

    @Override
    public Flashcard getNextFlashcard() {
        requireNonNull(quizInstance);
        Flashcard next = quizInstance.getNextQuestion();
        updateFilteredFlashcardList(curr -> curr.equals(next));
        return next;
    }

    @Override
    public void getCurrentFlashcard() {
        requireNonNull(quizInstance);
        Flashcard current = quizInstance.getCurrentQuestion();
        updateFilteredFlashcardList(curr -> curr.equals(current));
    }

    @Override
    public int getCurrentIndex() {
        requireNonNull(quizInstance);
        return quizInstance.getCurrentQuizIndex();
    }

    /**
     * Checks if the attempt provided matches the correct answer of the current quiz.
     * @param attempt The answer which the user entered.
     * @return True if the answer matches, false otherwise.
     */
    public boolean isCorrectAttempt(Answer attempt) {
        requireNonNull(quizInstance);
        return quizInstance.isCorrectAttempt(attempt);
    }

    public void clearQuizInstance() {
        quizInstance = null;
    }

    public Quiz getQuizInstance() {
        return quizInstance;
    }

    @Override
    public String getQuizStatisticString() {
        return quizInstance.getStatisticString();
    }

    //=========== Mode Related =============================================================

    public Mode getMode() {
        return this.mode;
    }

    public int getCurrentMode() {
        return this.mode.getCurrentMode();
    }

    public void switchModeQuiz() {
        this.mode.switchModeQuiz();
    }

    public void switchModeLearn() {
        this.mode.switchModeLearn();
    }

    public void switchModeMenu() {
        this.mode.switchModeMenu();
    }

    public void switchModeHistory() {
        this.mode.switchModeHistory();
    }

    public void switchModeQuizSession() {
        this.mode.switchModeQuizSession();
    }

    public void switchModeCheckSuccess() {
        this.mode.switchModeCheckSuccess();
    }
}
