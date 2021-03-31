package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.garment.Colour;
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
    private Label lastused;
    @FXML
    private FlowPane descriptions;
    @FXML
    private Label sampleColour;
    @FXML
    private Label type;

    /**
     * Creates a {@code GarmentCode} with the given {@code Garment} and index to display.
     */
    public GarmentCard(Garment garment, int displayedIndex) {
        super(FXML);
        this.garment = garment;
        String sample = Colour.SAMPLES.get(garment.getColour().colour);

        id.setText(displayedIndex + ". ");
        name.setText(garment.getName().fullName);
        size.setText("Size: " + garment.getSize().value);
        dresscode.setText("DressCode: " + garment.getDressCode().value);
        lastused.setText("Last used: " + garment.getLastUse().value);
        garment.getDescriptions().stream()
                .sorted(Comparator.comparing(description -> description.descriptionName))
                .forEach(description -> descriptions.getChildren()
                        .add(new Label("<" + description.descriptionName + ">")));

        Image colourImage = new Image(sample);
        ImageView colourView = new ImageView(colourImage);
        colourView.setFitHeight(80);
        colourView.setPreserveRatio(true);
        sampleColour.setGraphic(colourView);

        Image typeImage;
        if (garment.getType().value.equals("upper")) {
            typeImage = new Image("images/upper.jpeg");
        } else if (garment.getType().value.equals("lower")) {
            typeImage = new Image("images/lower.jpeg");
        } else {
            typeImage = new Image("images/footwear.jpeg");
        }
        ImageView typeView = new ImageView(typeImage);
        typeView.setFitHeight(80);
        typeView.setPreserveRatio(true);
        type.setGraphic(typeView);
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
