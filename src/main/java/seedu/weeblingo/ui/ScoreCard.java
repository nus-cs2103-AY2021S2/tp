package seedu.weeblingo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.weeblingo.model.score.Score;

/**
 * An UI component that displays information of a {@code Score}.
 */
public class ScoreCard extends UiPart<Region> {
    private static final String FXML = "ScoreCard.fxml"; // TODO: it is very primitive now

    public final Score score;
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FlashcardBook level 4</a>
     */
    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label completedTime;
    @FXML
    private Label numOfQuestionsAttempted;
    @FXML
    private Label numOfQuestionsCorrect;
    @FXML
    private Label correctRatio;

    /**
     * Creates a {@code ScoreCard} with the given {@code Score} and index to display.
     */
    public ScoreCard(Score score, int displayedIndex) {
        super(FXML);
        this.score = score;
        id.setText(displayedIndex + ". ");
        completedTime.setText(score.getCompletedTime());
        numOfQuestionsAttempted.setText(score.getNumberOfQuestionsAttempted());
        numOfQuestionsCorrect.setText(score.getNumberOfQuestionsCorrect());
        correctRatio.setText(score.getCorrectRatioString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardCard)) {
            return false;
        }

        // state check
        ScoreCard card = (ScoreCard) other;
        return id.getText().equals(card.id.getText())
                && score.equals(card.score);
    }
}
