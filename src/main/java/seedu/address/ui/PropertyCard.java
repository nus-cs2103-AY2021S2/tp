package seedu.address.ui;

import static seedu.address.model.property.Type.CONDO;
import static seedu.address.model.property.Type.HDB;
import static seedu.address.model.property.Type.LANDED;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.Property;
import seedu.address.model.property.client.Client;

/**
 * An UI component that displays information of a {@code Property}.
 */
public class PropertyCard extends UiPart<Region> {

    private static final String FXML = "PropertyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Property property;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label propertyType;
    @FXML
    private Label address;
    @FXML
    private Label postalCode;
    @FXML
    private Label deadline;
    @FXML
    private Label askingPrice;
    @FXML
    private Label clientName;
    @FXML
    private Label clientContact;
    @FXML
    private Label clientEmail;
    @FXML
    private Label remarks;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView propertyTypeIcon;

    /**
     * Creates a {@code PropertyCode} with the given {@code Property} and index to display.
     */
    public PropertyCard(Property property, int displayedIndex) {
        super(FXML);
        this.property = property;
        id.setText(displayedIndex + ". ");
        name.setText(property.getName().toString());
        propertyType.setText(property.getPropertyType().toString());
        address.setText(property.getAddress().toString());
        deadline.setText(property.getDeadline().toString());
        postalCode.setText(property.getPostalCode().toString());
        property.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setMaxWidth(200);
                    label.setWrapText(true);
                    tags.getChildren().add(label);
                });

        Deadline currentDate = new Deadline(LocalDate.now());
        if (currentDate.compareTo(property.getDeadline()) > 0) {
            cardPane.setStyle("-fx-background-color: #696969");
        }

        if (property.getRemarks() == null) {
            remarks.setManaged(false);
        } else {
            remarks.setText(property.getRemarks().toString());
        }

        if (property.getClient() == null) {
            askingPrice.setManaged(false);
            clientName.setManaged(false);
            clientContact.setManaged(false);
            clientEmail.setManaged(false);
        } else {
            askingPrice.setText(property.getAskingPrice().toString());
            clientName.setText(Client.STRING_CLIENT_NAME + property.getClient().getClientName().toString());
            clientContact.setText(Client.STRING_CLIENT_CONTACT + property.getClient().getClientContact().toString());
            clientEmail.setText(Client.STRING_CLIENT_EMAIL + property.getClient().getClientEmail().toString());
        }

        if (property.getStatus() == null) {
            status.setManaged(false);
        } else {
            status.setText(property.getStatus().toString());
        }
        
        Image icon;
        switch (property.getPropertyType().toString()) {
        case HDB:
            icon = new Image(getClass().getResourceAsStream("/images/hdb_64.png"));
            break;
        case CONDO:
            icon = new Image(getClass().getResourceAsStream("/images/condo_64.png"));
            break;
        case LANDED:
            icon = new Image(getClass().getResourceAsStream("/images/landed_64.png"));
            break;
        default:
            throw new AssertionError("Property type not recognized");
        }
        propertyTypeIcon.setImage(icon);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PropertyCard)) {
            return false;
        }

        // state check
        PropertyCard card = (PropertyCard) other;
        return id.getText().equals(card.id.getText())
                && property.equals(card.property);
    }
}

