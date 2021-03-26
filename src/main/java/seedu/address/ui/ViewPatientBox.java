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
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class ViewPatientBox extends UiPart<Region> {

    public static final String STARTUP_MESSAGE = "Hello Doc, to view patient info: \n"
            + "try 'view INDEX'";

    private static final String FXML = "ViewPatientBox.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label tagInfo;
    @FXML
    private Label appointmentInfo;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane appointments;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public ViewPatientBox(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        tagInfo.setText("Tags:");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        appointmentInfo.setText(String.format("Appointments with %s:", person.getName().fullName));
        person.getAppointments().stream()
                .sorted(Comparator.comparing(appt -> appt.getDate()))
                .forEach(appt -> appointments.getChildren().add(new Label(appt.getDateDisplay())));
    }

    /**
     * Constructor for when the program just initializes
     */
    public ViewPatientBox() {
        super(FXML);
        this.person = null;
        name.setText(STARTUP_MESSAGE);
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
