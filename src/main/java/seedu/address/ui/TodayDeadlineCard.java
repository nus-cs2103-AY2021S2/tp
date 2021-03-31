package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.DeadlineWithProject;

/**
 * An UI component that displays information of a {@code DeadlineWithProject}.
 */
public class TodayDeadlineCard extends UiPart<Region> {

    private static final String FXML = "TodayDeadlineCard.fxml";

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
    private Label day;
    @FXML
    private Label completedLabel;
    @FXML
    private Label projectName;

    /**
     * Creates a {@code TodayDeadlineCard} with the given {@code DeadlineWithProject}.
     */
    public TodayDeadlineCard(DeadlineWithProject deadline) {
        super(FXML);
        requireNonNull(deadline);

        this.deadline = deadline;
        id.setText("");
        description.setText(deadline.getDescription());
        date.setText(DateUtil.decodeDate(deadline.getBy()));
        day.setText(DateUtil.decodeDateIntoDay(deadline.getBy()));
        completedLabel.setText(getTextToDisplay(deadline.getIsDone()));
        projectName.setText(deadline.getProjectName().toString());
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
        if (!(other instanceof TodayDeadlineCard)) {
            return false;
        }

        // state check
        TodayDeadlineCard card = (TodayDeadlineCard) other;
        return id.getText().equals(card.id.getText())
                && deadline.equals(card.deadline);
    }
}
