package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.garment.Garment;

/**
 * An UI component that displays information of a {@code Garment}.
 */
public class GarmentCard extends UiPart<Region> {

    private static final String FXML = "GarmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Garment garment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label size;
    @FXML
    private Label dresscode;
    @FXML
    private Label colour;
    @FXML
    private Label type;
    @FXML
    private FlowPane descriptions;

    /**
     * Creates a {@code GarmentCode} with the given {@code Garment} and index to display.
     */
    public GarmentCard(Garment garment, int displayedIndex) {
        super(FXML);
        this.garment = garment;
        id.setText(displayedIndex + ". ");
        name.setText(garment.getName().fullName);
        size.setText("Size: " + garment.getSize().value);
        dresscode.setText("DressCode: " + garment.getDressCode().value);
        colour.setText("Colour: " + garment.getColour().colour);
        type.setText("Type: " + garment.getType().value);
        garment.getDescriptions().stream()
                .sorted(Comparator.comparing(description -> description.descriptionName))
                .forEach(description -> descriptions.getChildren()
                        .add(new Label("<" + description.descriptionName + ">")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GarmentCard)) {
            return false;
        }

        // state check
        GarmentCard card = (GarmentCard) other;
        return id.getText().equals(card.id.getText())
                && garment.equals(card.garment);
    }
}
