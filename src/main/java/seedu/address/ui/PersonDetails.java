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
    private Label school;
    @FXML
    private Label level;
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
    @FXML
    private StackPane lessonListPanelPlaceholder;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonDetails() {
        super(FXML);
        this.person = null;
        name.setText("");
        school.setText("");
        studentPhone.setText("");
        address.setText("");
        guardianName.setText("");
        guardianPhone.setText("");
        studentEmail.setText("");
        level.setText("");
    }

    public void setPerson(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        studentPhone.setText(person.getPhone().value);
        if (person.getSchool().isPresent()) {
            school.setText(person.getSchool().get().fullSchoolName);
        } else {
            school.setText("");
        }
        if (person.getLevel().isPresent()) {
            level.setText(person.getLevel().get().getLevel());
        } else {
            level.setText("");
        }
        if (person.getAddress().isPresent()) {
            address.setText(person.getAddress().get().value);
        } else {
            address.setText("");
        }
        if (person.getGuardianName().isPresent()) {
            guardianName.setText(person.getGuardianName().get().fullName);
        } else {
            guardianName.setText("");
        }
        if (person.getGuardianPhone().isPresent()) {
            guardianPhone.setText(person.getGuardianPhone().get().value);
        } else {
            guardianPhone.setText("");
        }
        if (person.getEmail().isPresent()) {
            studentEmail.setText(person.getEmail().get().value);
        } else {
            studentEmail.setText("");
        }
    }

    public void setLessonList(ObservableList<Lesson> lessonList) {
        lessonListPanel = new DetailLessonListPanel(lessonList);
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());
    }
}
