package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
     * Creates a {@code CompletableDeadlineCard} with the given {@code CompletableDeadline} without an index to display.
     */
    public CompletableDeadlineCard(CompletableDeadline deadline) {
        super(FXML);
        requireNonNull(deadline);

        this.deadline = deadline;
        id.setText("");
        description.setText(deadline.getDescription());
        date.setText(DateUtil.decodeDate(deadline.getBy()));
        completedLabel.setText(deadline.getIsDone() ? "✔" : "");
    }

    /**
     * Creates a {@code CompletableDeadlineCard} with the given {@code CompletableDeadline} and index to display.
     */
    public CompletableDeadlineCard(CompletableDeadline deadline, int displayedIndex) {
        super(FXML);
        requireAllNonNull(deadline, displayedIndex);

        this.deadline = deadline;
        id.setText(displayedIndex + ". ");
        description.setText(deadline.getDescription());
        date.setText(DateUtil.decodeDate(deadline.getBy()));
        completedLabel.setText(deadline.getIsDone() ? "✔" : "");
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
