package dog.pawbook.ui;

import java.util.Comparator;

import dog.pawbook.model.managedentity.Entity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays information of a {@code Owner}.
 */
public class EntityCard extends UiPart<Region> {

    private static final String FXML = "EntityListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Entity entity;

    @FXML
    private VBox contentPane;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EntityCard} with the given {@code Entity} and index to display.
     */
    public EntityCard(Entity entity, int displayedIndex) {
        super(FXML);
        this.entity = entity;
        id.setText(displayedIndex + ": ");
        name.setText(entity.getName().fullName);

        // todo: extract a method for this and use stream like tags
        String[] properties = entity.getOtherPropertiesAsString();
        for (String property : properties) {
            Label propertyLabel = new Label(property);
            propertyLabel.getStyleClass().add("cell_small_label");
            contentPane.getChildren().add(propertyLabel);
        }

        entity.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntityCard)) {
            return false;
        }

        // state check
        EntityCard card = (EntityCard) other;
        return id.getText().equals(card.id.getText())
                && entity.equals(card.entity);
    }
}
