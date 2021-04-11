package seedu.student.ui;

import java.util.Optional;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Student;

/**
 * A UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Appointment appointment;
    private Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label matriculationNumber;
    @FXML
    private Label time;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    /**
     * Creates an {@code AppointmentCard} with the given {@code Appointment} and index to display.
     * A {@code studentList} is provided to look up the contact information of the student that the appointment is for.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex, ObservableList<Student> studentList) {
        super(FXML);
        this.appointment = appointment;

        this.student = studentList.stream().filter(student -> student.getMatriculationNumber()
                .equals(appointment.getMatriculationNumber())).findFirst().get();
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().toString());
        matriculationNumber.setText(appointment.getMatriculationNumber().value);
        time.setText(String.format("%s - %s", appointment.getStartTime(), appointment.getEndTime()));
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);

        studentBind(this, studentList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }

    private static void studentBind(AppointmentCard apptCard, ObservableList<Student> studentList) {
        ListChangeListener<Student> listener = change -> {
            while (change.next()) {
                Optional<? extends Student> student = change.getList().stream().filter(s -> s.getMatriculationNumber()
                        .equals(apptCard.student.getMatriculationNumber())).findFirst();
                if (student.isPresent() && !apptCard.student.equals(student)) {
                    apptCard.student = student.get();
                    apptCard.name.setText(apptCard.student.getName().toString());
                    apptCard.phone.setText(apptCard.student.getPhone().value);
                    apptCard.email.setText(apptCard.student.getEmail().value);
                }
            }
        };
        studentList.addListener(listener);
    }
}
