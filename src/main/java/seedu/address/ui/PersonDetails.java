package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetails extends UiPart<Region> {

    private static final String FXML = "PersonDetails.fxml";

    private final Logger logger = LogsCenter.getLogger(PersonDetails.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label school;
    @FXML
    private Label studentPhone;
    @FXML
    private Label address;
    @FXML
    private Label studentEmail;
    @FXML
    private Label guardianName;
    @FXML
    private Label guardianPhone;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonDetails() {
        super(FXML);
        this.person = null;
        name.setText("Name");
        school.setText("");
        studentPhone.setText("");
        address.setText("");
        guardianName.setText("");
        guardianPhone.setText("");
        studentEmail.setText("");
    }

    public void setPerson(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        school.setText(person.getSchool().fullSchoolName);
        studentPhone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        guardianName.setText(person.getGuardianName().fullName);
        guardianPhone.setText(person.getGuardianPhone().value);
        studentEmail.setText(person.getEmail().value);
    }

}
