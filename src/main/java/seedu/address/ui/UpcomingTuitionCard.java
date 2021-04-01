package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * An UI component that displays information of an upcoming tuition.
 */
public class UpcomingTuitionCard extends UiPart<Region> {

    private static final String FXML = "UpcomingTuitionCard.fxml";

    public final Student student;
    public final Session session;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label duration;
    @FXML
    private Label subject;
    @FXML
    private Label address;

    /**
     * Creates a {@code UpcomingTuitionCard} with the given {@code Tuition} and index to display.
     */
    public UpcomingTuitionCard(Tuition tuition) {
        super(FXML);
        this.student = tuition.getStudent();
        this.session = tuition.getSession();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM YYYY");

        name.setText("Name: " + student.getName().fullName);
        address.setText("Address: " + student.getAddress().value);
        date.setText("Date: " + session.getSessionDate().getDateTime().format(dateTimeFormat));
        time.setText("Time: " + session.getSessionDate().getTime().toString());
        duration.setText("Duration: " + session.getDuration().getValue() + " mins");
        subject.setText("Subject: " + session.getSubject().getValue());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionCard)) {
            return false;
        }

        // state check
        UpcomingTuitionCard card = (UpcomingTuitionCard) other;
        return student.equals(card.student)
                && session.equals(card.session);
    }
}
