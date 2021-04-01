package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;

/**
 * An UI component that displays information of a {@code Session}.
 */
public class SessionCard extends UiPart<Region> {

    private static final String FXML = "SessionListCard.fxml";

    public final Session session;

    @FXML
    private AnchorPane sessionCardPane;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label duration;
    @FXML
    private Label subject;
    @FXML
    private Label fee;
    @FXML
    private Label id;
    @FXML
    private Label sessionType;
    @FXML
    private Label endDate;
    @FXML
    private Label interval;


    /**
     * Creates a {@code SessionCard} with the given {@code Session} and index to display.
     */
    public SessionCard(Session session, int displayedIndex) {
        super(FXML);
        this.session = session;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM YYYY");
        String formattedStartTime = session.getSessionDate().getDateTime().format(dateTimeFormat);

        date.setText("Date: " + formattedStartTime);
        time.setText("Time: " + session.getSessionDate().getTime().toString());
        duration.setText("Duration: " + session.getDuration().getValue() + " mins");
        subject.setText("Subject: " + session.getSubject().getValue());
        fee.setText("Fee: $" + String.format("%.2f", session.getFee().getFee()));
        id.setText(displayedIndex + "");
        if (session instanceof RecurringSession) {
            RecurringSession recurringSession = (RecurringSession) session;
            String formattedEndTime = recurringSession.getLastSessionDate().getDateTime().format(dateTimeFormat);

            sessionType.setText("R");
            sessionType.setStyle("-fx-text-fill: orange");
            date.setText("Start Date: " + formattedStartTime);
            endDate.setText("End Date: " + formattedEndTime);
            interval.setText("Interval: " + recurringSession.getInterval().toString());
        } else {
            sessionType.setText("I");
            sessionType.setStyle("-fx-text-fill: #ADD8E6");
            endDate.setVisible(false);
            interval.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionCard)) {
            return false;
        }

        // state check
        SessionCard card = (SessionCard) other;
        return id.getText().equals(card.id.getText())
                && session.equals(card.session);
    }
}
