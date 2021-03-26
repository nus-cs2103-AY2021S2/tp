package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import javax.swing.*;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class ViewPatientBox extends UiPart<Region> {

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
    private FlowPane test;
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

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getAppointments().stream()
                .min(Comparator.comparing(Appointment::getDate))
                .ifPresent(appt -> appointments.getChildren().add(new Label(appt.getDateDisplay())));
    }

    /**
     * this is the constructor for when the program just initializes
     */
    public ViewPatientBox() {
        super(FXML);
        // create sample person to put in the box
        this.person = new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("flu", "runnynose"));
        person.addAppointment(new Appointment(LocalDateTime.now().plusDays(1)));
        person.addAppointment(new Appointment(LocalDateTime.now().plusDays(2)));

        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getAppointments().stream()
                .min(Comparator.comparing(Appointment::getDate))
                .ifPresent(appt -> appointments.getChildren().add(new Label(appt.getDateDisplay())));
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
