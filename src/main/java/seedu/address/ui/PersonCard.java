package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthday;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox dates;
    @FXML
    private VBox meetings;
    @FXML
    private ImageView picture;

    @FXML
    private Label goal;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        birthday.setText(person.getBirthday().toUi());
        String goalText;
        if (person.getGoal().get().getFrequency().equals(Goal.Frequency.NONE)) {
            goalText = "No goal set for this person";
        } else {
            LocalDate deadline = person.getGoalDeadline(LocalDate.now());
            if (deadline.getYear() == DateUtil.ZERO_DAY.getYear()) {
                goalText = "Yet to meet this person!";
            } else if (deadline.plusDays(1).isAfter(LocalDate.now())) {
                goalText = String.format("Deadline for goal: %s", DateUtil.toUi(deadline));
            } else {
                goalText = String.format("Missed the deadline on :( %s", DateUtil.toUi(deadline));
            }
        }
        goal.setText(goalText);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // Temporary UI to test dates and meetings
        person.getDates().forEach(date -> dates.getChildren().add(new Label(date.toString())));
        person.getMeetings().forEach(meeting -> meetings.getChildren().add(new Label(meeting.toUi())));

        Optional<Picture> personPicture = person.getPicture();
        if (personPicture.isPresent()) {
            File imgFile = new File(personPicture.get().getAbsoluteFilePath());
            try {
                Image userImage = new Image(new FileInputStream(imgFile));
                picture.setImage(userImage);
            } catch (IOException e) {
                throw new RuntimeException("Unable to read input stream for person");
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
