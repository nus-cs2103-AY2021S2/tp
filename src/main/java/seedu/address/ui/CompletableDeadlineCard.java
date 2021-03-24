package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.CompletableDeadline;

/**
 * An UI component that displays information of a {@code CompletableDeadline}.
 */
public class CompletableDeadlineCard extends UiPart<Region> {

    private static final String FXML = "CompletableDeadlineListCard.fxml";

    public final CompletableDeadline deadline;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label completedLabel;

    /**
     * Creates a {@code CompletableDeadlineCard} with the given {@code CompletableDeadline} and index to display.
     */
    public CompletableDeadlineCard(CompletableDeadline deadline, int displayedIndex) {
        super(FXML);
        this.deadline = deadline;
        id.setText(displayedIndex + ". ");
        description.setText(deadline.getDescription());
        date.setText(DateUtil.decodeDate(deadline.getBy()));
        completedLabel.setText(getTextToDisplay(deadline.getIsDone()));
    }

    /**
     * Returns the text of the completed label that is to be displayed.
     *
     * @return {@code String} containing the text that is to be displayed.
     */
    public static String getTextToDisplay(boolean isDone) {
        return isDone ? "✔" : "";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompletableDeadlineCard)) {
            return false;
        }

        // state check
        CompletableDeadlineCard card = (CompletableDeadlineCard) other;
        return id.getText().equals(card.id.getText())
                && deadline.equals(card.deadline);
    }
}
