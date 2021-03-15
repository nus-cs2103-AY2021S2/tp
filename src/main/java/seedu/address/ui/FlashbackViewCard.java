package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import seedu.address.model.flashcard.Flashcard;

public class FlashbackViewCard extends UiPart<Region> {
    private static final String FXML = "FlashbackViewCard.fxml";
    public final Flashcard flashcard;
    @FXML
    private VBox answerPlaceholder;
    @FXML
    private Label question;
    @FXML
    private Label answer;
    @FXML
    private FlowPane tags;
    @FXML
    private Label priority;
    @FXML
    private Label category;

    /**
     * Creates a {@code FlashbackViewCard} with the given {@code flashcard}.
     */
    public FlashbackViewCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        question.setText(flashcard.getQuestion().fullQuestion);
        answer.setText(flashcard.getAnswer().toString());
        category.setText(flashcard.getCategory().toString());
        flashcard.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        String text = flashcard.getPriority().toString();
        priority.setText(text);
        priority.setStyle("-fx-text-fill: white;");
        if (text.equals("High")) {
            priority.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (text.equals("Mid")) {
            priority.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            priority.setStyle("-fx-text-fill: black;");
        } else {
            assert text.equals("Low");
            priority.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Hides the answer of the question from the user.
     */
    public void hideAnswer() {
        answerPlaceholder.setVisible(false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashbackViewCard)) {
            return false;
        }

        // state check
        FlashbackViewCard card = (FlashbackViewCard) other;
        return flashcard.equals(card.flashcard);
    }
}
