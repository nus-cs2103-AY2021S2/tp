package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.session.Session;
import seedu.address.model.tuition.Tuition;

/**
 * An UI component that displays information of a {@code Tuition}, meant for Calendar.
 */
public class CalendarCard extends UiPart<Region> {
    private static final String FXML = "CalendarCard.fxml";
    private Tuition tuition;

    @FXML private Label periodFxml;
    @FXML private Label nameFxml;
    @FXML private Label subjectFxml;

    /**
     * Creates a {@code CalendarCard} with the given {@code Tuition} and index to display.
     */
    public CalendarCard(Tuition tuition) {
        super(FXML);
        this.tuition = tuition;

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Session tuitionSession = tuition.getSession();
        String name = tuition.getStudent().getName().fullName;
        String subject = tuition.getSession().getSubject().getValue();

        LocalDateTime sessionStartDateTime = tuitionSession.getSessionDate().getDateTime();
        LocalDateTime sessionEndDateTime = sessionStartDateTime.plusMinutes(tuitionSession.getDuration().getValue());

        String period = sessionStartDateTime.format(timeFormatter) + " - " + sessionEndDateTime.format(timeFormatter);

        periodFxml.setText(period);
        nameFxml.setText(name);
        subjectFxml.setText(subject);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalendarCard)) {
            return false;
        }

        // state check
        CalendarCard card = (CalendarCard) other;
        return card.tuition.equals(card.tuition);
    }
}
