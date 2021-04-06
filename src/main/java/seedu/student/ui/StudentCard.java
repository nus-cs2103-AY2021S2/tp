package seedu.student.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.student.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    private static final Background vaccinatedBg = new Background(new BackgroundFill(Color.web("#0277BD"),
            new CornerRadii(5.0), new Insets(-5.0)));
    private static final Background notVaccinatedBg = new Background(new BackgroundFill(Color.web("#DD2C00"),
            new CornerRadii(5.0), new Insets(-5.0)));

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label vaccinationStatus;
    @FXML
    private Label id;
    @FXML
    private Label matriculationNumber;
    @FXML
    private Label faculty;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label residence;
    @FXML
    private Label medicalDetails;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        vaccinationStatus.setText(student.getVaccinationStatus().textUI);
        vaccinationStatus.setBackground(
                student.isVaccinated()
                        ? vaccinatedBg
                        : notVaccinatedBg);
        matriculationNumber.setText(student.getMatriculationNumber().value);
        faculty.setText(student.getFaculty().value);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        residence.setText(student.getSchoolResidence().toString());
        medicalDetails.setText(student.getMedicalDetails().value);
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
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
