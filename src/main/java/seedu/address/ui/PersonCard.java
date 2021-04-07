package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label gender;
    @FXML
    private Label birthdate;
    @FXML
    private Label numNotes;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane plans;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        id.setWrapText(true);
        name.setText(person.getName().fullName);
        name.setWrapText(true);
        phone.setText(person.getPhone().value);
        phone.setWrapText(true);
        address.setText(person.getAddress().value);
        address.setWrapText(true);
        email.setText(person.getEmail().value);
        email.setWrapText(true);
        gender.setText(person.getGender().value);
        gender.setWrapText(true);
        if (person.getNumNotes() == 0) {
            numNotes.setText("\u25B6 You have no notes");
        } else {
            numNotes.setText(String.format("\u25B6 You have %d note%s",
                    person.getNumNotes(), person.getNumNotes() == 1 ? "" : "s"));
        }
        birthdate.setText("DOB: " + person.getBirthdate().value.toString());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getChildren().forEach(child -> {
            if (child instanceof Label) {
                Label label = (Label) child;
                label.setWrapText(true);
                label.setMaxWidth(300);
            }
        });
        person.getPlanStringsList()
                .forEach(plan -> plans.getChildren().add(new Label(plan)));
        plans.getChildren().forEach(child -> {
            if (child instanceof Label) {
                Label label = (Label) child;
                label.setWrapText(true);
                label.setMaxWidth(200);
            }
        });
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
