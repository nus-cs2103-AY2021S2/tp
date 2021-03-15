package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.logic.ReviewManager;

//TODO: REFACTOR THE CODE IN V1.3.
public class ReviewMode extends UiPart<Region> {
    public static final String EXIT_REVIEW_MODE = "Exit review mode";
    private static final String FXML = "ReviewMode.fxml";
    private static final String NEXT_CARD = "n";
    private static final String PREV_CARD = "p";
    private static final String SHOW_ANSWER = "a";
    private static final String QUIT_REVIEW_MODE = "q";
    private final ResultDisplay resultDisplay;
    private final ReviewManager manager;
    private final MainWindow parent;
    @FXML
    private TextField commandInReviewMode;
    @FXML
    private StackPane resultDisplayPlaceholderReviewMode;
    @FXML
    private Label progressLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private StackPane flashcardPlaceholderReviewMode;

    /**
     * Create the {@code ReviewMode} with the reference to {@code MainWindow}.
     */
    public ReviewMode(Logic logic, MainWindow parent) {
        super(FXML);
        this.parent = parent;
        this.resultDisplay = new ResultDisplay();
        resultDisplayPlaceholderReviewMode.getChildren().add(resultDisplay.getRoot());
        manager = new ReviewManager(logic);
        if (manager.getFlashcardDeckSize() > 0) {
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            setProgress();
        }
    }
    @FXML
    private void handleCommandEnteredReview() {
        String text = commandInReviewMode.getText().trim();
        switch (text) {
        case NEXT_CARD:
            handleNextCommand();
            break;
        case PREV_CARD:
            handlePrevCommand();
            break;
        case QUIT_REVIEW_MODE:
            handleQuitCommand();
            break;
        case SHOW_ANSWER:
            handleShowAnswerCommand();
            break;
        default:
            handleInvalidCommand();
            break;
        }
        commandInReviewMode.setText("");
    }
    private void handleNextCommand() {
        if (manager.hasNextFlashcard()) {
            manager.incrementCurrentIndex();
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            resultDisplay.setFeedbackToUser("Show next flashcard");
            setProgress();
        } else {
            resultDisplay.setFeedbackToUser("No more cards to show.");
        }
    }
    private void handlePrevCommand() {
        if (manager.hasPreviousFlashcard()) {
            manager.decrementCurrentIndex();
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            resultDisplay.setFeedbackToUser("Show previous flashcard");
            setProgress();
        } else {
            resultDisplay.setFeedbackToUser("No previous card to show.");
        }
    }
    private void handleQuitCommand() {
        parent.exitReviewMode();;
    }
    private void handleShowAnswerCommand() {
        FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
        flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
        resultDisplay.setFeedbackToUser("Show answer");
    }
    private void handleInvalidCommand() {
        resultDisplay.setFeedbackToUser("Invalid command in review mode.");
    }
    private void setProgress() {
        int currentIndex = manager.getCurrentIndex() + 1;
        int totalFlashcards = manager.getFlashcardDeckSize();
        String text = "Question " + currentIndex + "/" + totalFlashcards;
        progressLabel.setText(text);
        double completedPercentage = (double) currentIndex / (double) totalFlashcards;
        progressBar.setProgress(completedPercentage);
    }
}
