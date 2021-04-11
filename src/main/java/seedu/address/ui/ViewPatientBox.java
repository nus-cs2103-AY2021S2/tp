package seedu.address.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class ViewPatientBox extends UiPart<Region> {

    public static final String STARTUP_MESSAGE = "Hello Doc, to view patient info: \n"
            + "try 'view INDEX'";

    private static final String FXML = "ViewPatientBox.fxml";

    public final Patient patient;

    // labels
    @FXML
    private Label dateOfBirthLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label bloodTypeLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label appointmentInfo;
    @FXML
    private Label medicalRecordInfo;
    @FXML
    private Label appointmentLabel;
    @FXML
    private Label medicalRecordLabel;

    // field values
    @FXML
    private HBox cardPane;
    @FXML
    private Text name;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label gender;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label bloodType;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    @FXML
    private FlowPane ptags;
    @FXML
    private FlowPane patientBoxAppointments;
    @FXML
    private FlowPane patientBoxMedicalRecords;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public ViewPatientBox(Patient patient) {
        super(FXML);
        this.patient = patient;
        setLabels();
        name.setText(patient.getName().fullName);
        dateOfBirth.setText(patient.getDateOfBirth().toString());
        gender.setText(patient.getGender().value);
        phone.setText(patient.getPhone().value);
        address.setText(patient.getAddress().value);
        email.setText(patient.getEmail().value);
        bloodType.setText(patient.getBloodType().value);
        height.setText(patient.getHeight().value);
        weight.setText(patient.getWeight().value);

        // tags
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> ptags.getChildren().add(new Label(tag.tagName)));

        // appointments
        patient.getAppointments().stream()
                .sorted(Comparator.comparing(appt -> appt.getDate()))
                .forEach(appt -> patientBoxAppointments.getChildren().add(new Label(appt.getDateDisplay())));
        if (patientBoxAppointments.getChildren().size() == 1) {
            appointmentInfo.setText(String.format("1 scheduled appointment with %s:", patient.getName().fullName));
        } else if (patientBoxAppointments.getChildren().size() != 0) {
            appointmentInfo.setText(String.format("%d scheduled appointments with %s:",
                    patientBoxAppointments.getChildren().size(), patient.getName().fullName));
        } else {
            appointmentInfo.setText(String.format("No scheduled appointments with %s.", patient.getName().fullName));
        }

        // medical records
        int index = 1;
        for (MedicalRecord mrec : patient.getRecords()) {
            patientBoxMedicalRecords.getChildren().add(new Label(index + ". " + mrec.getDateNoTime()));
            index++;
        }
        if (patientBoxMedicalRecords.getChildren().size() == 1) {
            medicalRecordInfo.setText("1 medical record found:");
        } else if (patientBoxMedicalRecords.getChildren().size() != 0) {
            medicalRecordInfo.setText(String.format("%d medical record(s) found:",
                    patientBoxMedicalRecords.getChildren().size()));
        } else {
            medicalRecordInfo.setText("No medical records found.");
        }
    }

    /**
     * Constructor for when the program just initializes
     */
    public ViewPatientBox() {
        super(FXML);
        this.patient = null;
        name.setText(STARTUP_MESSAGE);
    }

    public void setText(String s) {
        this.name.setText(s);
    }

    private void setLabels() {
        dateOfBirthLabel.setText("DoB: ");
        genderLabel.setText("Gender: ");
        phoneLabel.setText("Phone: ");
        addressLabel.setText("Address: ");
        emailLabel.setText("Email: ");
        bloodTypeLabel.setText("ABO: ");
        heightLabel.setText("Height: ");
        weightLabel.setText("Weight: ");
        appointmentLabel.setText("Appointments");
        medicalRecordLabel.setText("Medical Records");
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
