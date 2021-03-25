package seedu.weeblingo.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardCard extends UiPart<Region> {

    private static final String FXML = "FlashcardListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FlashcardBook level 4</a>
     */

    public final Flashcard flashcard;

    private final String image = this.getClass().getResource("/images/cat_mascot.png").toExternalForm();

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label answer;
    @FXML
    private Label question;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Flashcard} and index to display.
     */
    public FlashcardCard(Flashcard flashcard, int displayedIndex, boolean showAnswer) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText("Question: " + displayedIndex + ". ");
        setQuestion(flashcard.getQuestion().value);
        showImage();
        setAnswer(showAnswer ? "Answer: " + flashcard.getAnswer().value : "Answer: ");
        flashcard.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        FlashcardCard card = (FlashcardCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }

    private void setQuestion(String toSet) {
        question.setText(toSet);
    }

    private void setAnswer(String toSet) {
        answer.setText(toSet);
    }

    private void showImage() {
        cardPane.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");
    }

}
