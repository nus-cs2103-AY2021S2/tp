package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.ReviewManager;


//TODO: REFACTOR THE CODE IN V1.3.
public class ReviewMode extends UiPart<Region> {
    public static final String EXIT_REVIEW_MODE = "Exit review mode";
    public static final String ENTER_REVIEW_MODE = "Enter review mode";
    public static final String INSTRUCTION = "n next flashcard    p previous flashcard    a show answer"
            + "    h hide answer    q exit review mode";
    private static final String FXML = "ReviewMode.fxml";
    private static final String NEXT_CARD = "n";
    private static final String PREV_CARD = "p";
    private static final String SHOW_ANSWER = "a";
    private static final String HIDE_ANSWER = "h";
    private static final String QUIT_REVIEW_MODE = "q";
    private static final String EMPTY_INPUT = "";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final ResultDisplay resultDisplay;
    private final ReviewManager manager;
    private final MainWindow parent;
    private boolean isAnswerShown;
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
            resultDisplay.setFeedbackToUser(ENTER_REVIEW_MODE + "\n" + INSTRUCTION);
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            isAnswerShown = false;
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            setProgress();
        }
    }
    @FXML
    private void handleCommandEnteredReview() {
        String command = commandInReviewMode.getText().trim();
        logger.info("----------------[USER COMMAND][" + command + "]");
        String feedback = "";
        switch (command) {
        case NEXT_CARD:
            feedback = handleNextCommand();
            break;
        case PREV_CARD:
            feedback = handlePrevCommand();
            break;
        case QUIT_REVIEW_MODE:
            feedback = handleQuitCommand();
            break;
        case SHOW_ANSWER:
            feedback = handleShowAnswerCommand();
            break;
        case HIDE_ANSWER:
            feedback = handleHideAnswerCommand();
            break;
        case EMPTY_INPUT:
            return;
        default:
            feedback = handleInvalidCommand();
            break;
        }
        logger.info("Result: " + feedback);
        commandInReviewMode.setText("");
    }

    /**
     * Displays the next flashcard.
     */
    private String handleNextCommand() {
        String feedback = "";
        if (manager.hasNextFlashcard()) {
            manager.incrementCurrentIndex();
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            isAnswerShown = false;
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            feedback = "Show next flashcard";
            setProgress();
        } else {
            feedback = "No more cards to show";
        }
        resultDisplay.setFeedbackToUser(feedback + "\n" + INSTRUCTION);
        return feedback;
    }

    /**
     * Displays the previous flashcard.
     */
    private String handlePrevCommand() {
        String feedback = "";
        if (manager.hasPreviousFlashcard()) {
            manager.decrementCurrentIndex();
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            isAnswerShown = false;
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            feedback = "Show previous flashcard";
            setProgress();
        } else {
            feedback = "No previous card to show";
        }
        resultDisplay.setFeedbackToUser(feedback + "\n" + INSTRUCTION);
        return feedback;
    }

    /**
     * Exits the review mode and goes back to the main window.
     */
    private String handleQuitCommand() {
        parent.exitReviewMode();
        return EXIT_REVIEW_MODE;
    }

    /**
     * Displays the answer of the current flashcard to the user.
     */
    private String handleShowAnswerCommand() {
        String feedback = "";
        if (!isAnswerShown) {
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            isAnswerShown = true;
            feedback = "Show answer";
        } else {
            feedback = "The answer is already shown";
        }
        resultDisplay.setFeedbackToUser(feedback + "\n" + INSTRUCTION);
        return feedback;
    }

    /**
     * Hides the answer of the current flashcard to the user.
     */
    private String handleHideAnswerCommand() {
        String feedback = "";
        if (isAnswerShown) {
            flashcardPlaceholderReviewMode.getChildren().clear();
            FlashbackViewCard flashbackViewCard = new FlashbackViewCard(manager.getCurrentFlashcard());
            flashbackViewCard.hideAnswer();
            flashcardPlaceholderReviewMode.getChildren().add(flashbackViewCard.getRoot());
            isAnswerShown = false;
            feedback = "Hide answer";
        } else {
            feedback = "The answer is already hidden";
        }
        resultDisplay.setFeedbackToUser(feedback + "\n" + INSTRUCTION);
        return feedback;
    }

    /**
     * Displays an error message to the user.
     */
    private String handleInvalidCommand() {
        String feedback = "Invalid command in review mode";
        resultDisplay.setFeedbackToUser(feedback + "\n" + INSTRUCTION);
        return feedback;
    }

    /**
     * Update the progress when the user type "n" or "p" command.
     */
    private void setProgress() {
        int currentIndex = manager.getCurrentIndex() + 1;
        int totalFlashcards = manager.getFlashcardDeckSize();
        String text = "Question " + currentIndex + "/" + totalFlashcards;
        progressLabel.setText(text);
        double completedPercentage = (double) currentIndex / (double) totalFlashcards;
        progressBar.setProgress(completedPercentage);
    }
}
