package seedu.partyplanet.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.person.Remark;

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
    private VBox details;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (!Phone.isEmptyPhone(person.getPhone())) {
            addDetail(person.getPhone().value);
        }
        if (!Address.isEmptyAddress(person.getAddress())) {
            addDetail(person.getAddress().value);
        }
        if (!Email.isEmptyEmail(person.getEmail())) {
            addDetail(person.getEmail().value);
        }
        if (!Birthday.isEmptyDate(person.getBirthday())) {
            addDetail(person.getBirthday().displayValue);
        }
        if (!Remark.isEmptyRemark(person.getRemark())) {
            addDetail(person.getRemark().value);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Adds a new label to the contact with the following detail
     */
    private void addDetail(String detail) {
        Label label = new Label();
        label.setText("\u2022 " + detail);
        label.setWrapText(true);
        label.getStyleClass().add("cell_small_label");
        details.getChildren().add(label);
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
