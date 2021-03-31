package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.repeatable.EventWithProject;

/**
 * An UI component that displays information of an {@code EventWithProject}.
 */
public class TodayEventCard extends UiPart<Region> {

    private static final String FXML = "TodayEventCard.fxml";

    public final EventWithProject eventWithProject;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label eventDescription;
    @FXML
    private Label day;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label projectName;

    /**
     * Creates an {@code EventCard} with the given {@code Event} without an index to display.
     */
    public TodayEventCard(EventWithProject eventWithProject) {
        super(FXML);
        requireNonNull(eventWithProject);

        this.eventWithProject = eventWithProject;
        id.setText("");
        eventDescription.setText(eventWithProject.getDescription());
        day.setText(DateUtil.decodeDateIntoDay(eventWithProject.getDate()));
        time.setText(TimeUtil.decodeTime(eventWithProject.getTime()));
        projectName.setText(eventWithProject.getProjectName().toString());

        if (eventWithProject.getIsWeekly()) {
            date.setText("every");
        } else {
            date.setText(DateUtil.decodeDate(eventWithProject.getDate()));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TodayEventCard)) {
            return false;
        }

        // state check
        TodayEventCard card = (TodayEventCard) other;
        return id.getText().equals(card.id.getText())
                && eventWithProject.equals(card.eventWithProject);
    }
}
