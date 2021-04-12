package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.meeting.Meeting;


/**
 * An UI component that displays information of a {@code Meeting}.
 */

public class MeetingCard extends UiPart<Region> {
    private static final String FXML = "MeetingCard.fxml";

    public final Meeting meeting;

    @FXML
    private HBox meetingPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label description;
    @FXML
    private Label priority;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane personsRelated;

    /**
     * Creates a {@code meetingCard} with the given {@code meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        name.setText(meeting.getName().toString());
        LocalDateTime startDateTime = meeting.getStartLocalDateTime();
        LocalDateTime endDateTime = meeting.getTerminateLocalDateTime();
        startDate.setText(DateTimeUtil.prettyPrintFormatDateTime(startDateTime));
        endDate.setText(DateTimeUtil.prettyPrintFormatDateTime(endDateTime));
        description.setText(meeting.getDescription().toString());
        priority.setText(meeting.getPriority().toString());
        meeting.getGroups().stream()
                .sorted(Comparator.comparing(tag -> tag.groupName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.groupName)));

        // Only when the person meeting connection exist then it will be shown on the meeting card.
        if (!meeting.getConnectionToPerson().isEmpty()) {
            personsRelated.getChildren().add(new Label("People Attending:"));
            meeting.getConnectionToPerson().stream()
                    .sorted(Comparator.comparing(person -> person.getName().fullName))
                    .forEach(person -> personsRelated.getChildren()
                            .add(new Label("[" + person.getName().fullName + "]")));
        }
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
