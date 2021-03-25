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
 * An UI component that displays information of a {@code Tuition}.
 */
public class TuitionCard extends UiPart<Region> {
    // TODO: Some Ui to differentiate recurring sessions from others.

    private static final String FXML = "TuitionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;
    public final Session session;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label sessionDate;
    @FXML
    private Label duration;
    @FXML
    private Label subject;
    @FXML
    private Label fee;

    /**
     * Creates a {@code TuitionCard} with the given {@code Tuition} and index to display.
     */
    public TuitionCard(Tuition tuition) {
        super(FXML);
        this.student = tuition.getStudent();
        this.session = tuition.getSession();
        id.setText(tuition.getStudentIndex() + "-" + tuition.getSessionIndex());
        name.setText(student.getName().fullName);
        sessionDate.setText("Date: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .format(session.getSessionDate().getDateTime()));
        duration.setText("Duration: " + session.getDuration().getValue() + " mins");
        subject.setText("Subject: " + session.getSubject().getValue());
        fee.setText("Fee: $" + String.format("%.2f", session.getFee().getFee()));
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
        TuitionCard card = (TuitionCard) other;
        return id.getText().equals(card.id.getText())
                && session.equals(card.session);
    }
}
