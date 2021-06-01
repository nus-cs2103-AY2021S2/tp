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

    public static final String MESSAGE_EVENT_NON_REPEATABLE = "%s, %s, %s";
    public static final String MESSAGE_EVENT_REPEATABLE = "Every %s, starting %s, %s";

    private static final String FXML = "TodayEventCard.fxml";

    public final EventWithProject eventWithProject;

    @FXML
    private HBox cardPane;
    @FXML
    private Label eventDescription;
    @FXML
    private Label dateTime;
    @FXML
    private Label projectName;

    /**
     * Creates a {@code TodayEventCard} with the given {@code EventWithProject}.
     */
    public TodayEventCard(EventWithProject eventWithProject) {
        super(FXML);
        requireNonNull(eventWithProject);

        this.eventWithProject = eventWithProject;
        eventDescription.setText(eventWithProject.getDescription());
        projectName.setText(eventWithProject.getProjectName().toString());

        if (eventWithProject.getIsWeekly()) {
            dateTime.setText(String.format(MESSAGE_EVENT_REPEATABLE,
                    DateUtil.decodeDateIntoDay(eventWithProject.getDate()),
                    DateUtil.decodeDate(eventWithProject.getDate()),
                    TimeUtil.decodeTime(eventWithProject.getTime())));
        } else {
            dateTime.setText(String.format(MESSAGE_EVENT_NON_REPEATABLE,
                    DateUtil.decodeDateIntoDay(eventWithProject.getDate()),
                    DateUtil.decodeDate(eventWithProject.getDate()),
                    TimeUtil.decodeTime(eventWithProject.getTime())));
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
        return eventWithProject.equals(card.eventWithProject);
    }
}
