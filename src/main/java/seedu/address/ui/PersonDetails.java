package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.ui.panel.DetailLessonListPanel;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetails extends UiPart<Region> {
    private static final String FXML = "PersonDetails.fxml";
    private Logic logic;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private DetailLessonListPanel lessonListPanel;
    private Person person;


    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label education;
    @FXML
    private Label studentContact;
    @FXML
    private Label address;
    @FXML
    private Label guardianContact;
    @FXML
    private StackPane lessonListPanelPlaceholder;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonDetails() {
        super(FXML);
        this.person = null;
        name.setText("");
        education.setText("");
        studentContact.setText("");
        address.setText("");
        guardianContact.setText("");
    }

    public void setPerson(Person person) {
        this.person = person;

        // Set student name
        name.setText(person.getName().fullName);

        // Set student contact (phone and email)
        String studentContactString = person.getPhone().value;
        if (person.getEmail().isPresent()) {
            studentContactString += "\n" + person.getEmail().get().value;
        }
        studentContact.setText(studentContactString);

        // Set student address
        if (person.getAddress().isPresent()) {
            address.setText(person.getAddress().get().value);
        } else {
            address.setText("");
        }

        // Set student education information (education level and school)
        String educationString = "";
        if (person.getLevel().isPresent()) {
            educationString += person.getLevel().get().getFullLevel();
        }
        if (person.getLevel().isPresent() && person.getSchool().isPresent()) {
            educationString += "\n";
        }
        if (person.getSchool().isPresent()) {
            educationString += person.getSchool().get().fullSchoolName;
        }
        education.setText(educationString);

        // Set guardian contacts (guardian name and phone number)
        String guardianContactString = "";
        if (person.getGuardianName().isPresent()) {
            guardianContactString += person.getGuardianName().get().fullName;
        }
        if (person.getGuardianName().isPresent() && person.getGuardianPhone().isPresent()) {
            guardianContactString += "\n";
        }
        if (person.getGuardianPhone().isPresent()) {
            guardianContactString += person.getGuardianPhone().get().value;
        }
        guardianContact.setText(guardianContactString);
    }

    public void setLessonList(ObservableList<Lesson> lessonList) {
        lessonListPanel = new DetailLessonListPanel(lessonList);
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());
    }
}
