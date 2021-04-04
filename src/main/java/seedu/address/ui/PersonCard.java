package seedu.address.ui;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.person.Person;

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
    private Label debt;
    @FXML
    private FlowPane tags;
    @FXML
    private StackPane picturePlaceholder;

    @FXML
    private Label goal;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
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
        debt.setText("Debt: " + person.getDebt().toUi());
        String goalText;
        if (person.getGoal().isNoneFrequency()) {
            goalText = "No goal set for this person";
        } else {
            LocalDate deadline = person.getGoalDeadline(LocalDate.now());
            if (deadline.equals(DateUtil.ZERO_DAY)) {
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

        ProfilePicture profilePicture = new ProfilePicture(person, new Insets(5, 5, 5, 5));
        picturePlaceholder.getChildren().add(profilePicture.getRoot());
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
