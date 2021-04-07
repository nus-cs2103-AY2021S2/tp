package seedu.iscam.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.iscam.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ClientBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label meetingName;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label meetingLocation;
    @FXML
    private Label description;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code meetingCode} with the given {@code meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        meetingName.setText(meeting.getClientName().fullName);
        meetingName.setWrapText(true);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        String dateTimeString = dateTimeFormatter.format(meeting.getDateTime().get());
        dateTime.setText(dateTimeString.substring(0, dateTimeString.length() - 3));
        meetingLocation.setText(meeting.getLocation().value);
        meetingLocation.setWrapText(true);
        description.setText(meeting.getDescription().value);
        description.setWrapText(true);
        if (meeting.getStatus().isComplete()) {
            status.setText("Completed");
        } else {
            status.setText("Not Completed");
        }
        meeting.getTags().stream()
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
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
