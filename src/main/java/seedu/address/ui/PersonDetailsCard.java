package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

public class PersonDetailsCard extends UiPart<Region> {

    private static final String FXML = "PersonDetailsCard.fxml";

    public final Person person;

    @FXML
    private Label name;
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
    private StackPane picturePlaceholder;

    /**
     * Creates a {@code PersonDetailsCard} with the given {@code Person}.
     */
    public PersonDetailsCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        birthday.setText(person.getBirthday().toUi());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // Temporary UI to test dates and meetings
        person.getDates().forEach(date -> dates.getChildren().add(new Label(date.toUi())));
        person.getMeetings().forEach(meeting -> meetings.getChildren().add(new Label(meeting.toUi())));

        ProfilePicture profilePicture = new ProfilePicture(person, new Insets(0, 0, 10, 0));
        picturePlaceholder.getChildren().add(profilePicture.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailsCard)) {
            return false;
        }

        // state check
        PersonDetailsCard card = (PersonDetailsCard) other;
        return person.equals(card.person);
    }
}
