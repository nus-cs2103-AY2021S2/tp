package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.groupmate.Groupmate;

/**
 * An UI component that displays information of a {@code Groupmate}.
 */
public class GroupmateCard extends UiPart<Region> {

    private static final String FXML = "GroupmateCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Groupmate groupmate;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane roles;

    /**
     * Creates a {@code GroupmateCard} with the given {@code Groupmate} and index to display.
     */
    public GroupmateCard(Groupmate groupmate, int displayedIndex) {
        super(FXML);
        this.groupmate = groupmate;
        id.setText(displayedIndex + ". ");
        name.setText(groupmate.getName().fullName);
        groupmate.getRoles().stream()
                .sorted(Comparator.comparing(role -> role.roleName))
                .forEach(role -> roles.getChildren().add(new Label(role.roleName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupmateCard)) {
            return false;
        }

        // state check
        GroupmateCard card = (GroupmateCard) other;
        return id.getText().equals(card.id.getText())
                && groupmate.equals(card.groupmate);
    }
}
