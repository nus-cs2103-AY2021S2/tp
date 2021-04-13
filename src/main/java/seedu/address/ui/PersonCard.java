package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.insurancepolicy.InsurancePolicy;
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
    private FlowPane tags;
    @FXML
    private Label insurancePolicies;
    @FXML
    private Label meetings;
    @FXML
    private VBox gridPane;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person.isShowPolicyList()) {
            if (!person.getPolicies().isEmpty()) {
                insurancePolicies.setText(person.getPolicies().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n")));
            } else {
                insurancePolicies.setText(InsurancePolicy.MESSAGE_NO_POLICY);
            }
        } else {
            gridPane.getChildren().remove(insurancePolicies);
            gridPane.setMinHeight(gridPane.getMinHeight() - 20);
        }

        if (!person.getTags().isEmpty()) {
            person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }

        if (person.getPhone().isPresent()) {
            phone.setText(person.getPhone().get().value);
        } else {
            gridPane.getChildren().remove(phone);
            gridPane.setMinHeight(gridPane.getMinHeight() - 20);
        }

        if (person.getAddress().isPresent()) {
            address.setText(person.getAddress().get().value);
        } else {
            gridPane.getChildren().remove(address);
            gridPane.setMinHeight(gridPane.getMinHeight() - 20);
        }

        if (person.getEmail().isPresent()) {
            email.setText(person.getEmail().get().value);
        } else {
            gridPane.getChildren().remove(email);
            gridPane.setMinHeight(gridPane.getMinHeight() - 20);
        }

        if (!person.getMeetings().isEmpty()) {
            meetings.setText(person.getMeetings().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n")));
        } else {
            gridPane.getChildren().remove(meetings);
            gridPane.setMinHeight(gridPane.getMinHeight() - 20);
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
