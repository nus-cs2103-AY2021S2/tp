package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import seedu.address.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashbackCard extends UiPart<Region> {

    private static final String FXML = "FlashcardListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Flashcard flashcard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label question;
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label category;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;

    /**
     * Creates a {@code FlashbackCard} with the given {@code flashcard} and index to display.
     */
    public FlashbackCard(Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText(displayedIndex + ". ");
        question.setText(flashcard.getQuestion().fullQuestion);
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
        category.setText(flashcard.getCategory().value);
        remark.setText(flashcard.getRemark().value);
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
        if (!(other instanceof FlashbackCard)) {
            return false;
        }

        // state check
        FlashbackCard card = (FlashbackCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }
}
