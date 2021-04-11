package seedu.address.ui;

import static seedu.address.model.property.Type.CONDO;
import static seedu.address.model.property.Type.HDB;
import static seedu.address.model.property.Type.LANDED;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.AppUtil;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.Client;

/**
 * An UI component that displays information of a {@code Property}.
 */
public class PropertyCard extends UiPart<Region> {

    private static final String FXML = "PropertyListCard.fxml";

    private static final Image HDB_ICON = AppUtil.getImage("/images/hdb_64.png");
    private static final Image CONDO_ICON = AppUtil.getImage("/images/condo_64.png");
    private static final Image LANDED_ICON = AppUtil.getImage("/images/landed_64.png");

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
        setMandatoryPropertyLabels();
        setOptionalPropertyLabels();
        setClientLabels();
        greyOutPropertiesWithPastDeadlines();
        setPropertyTypeIcon();
    }

    /**
     * Sets mandatory property fields to display.
     */
    public void setMandatoryPropertyLabels() {
        name.setText(property.getName().toString());
        propertyType.setText(property.getPropertyType().toString());
        address.setText(property.getAddress().toString());
        deadline.setText(property.getDeadline().toString());
        postalCode.setText(property.getPostalCode().toString());
        property.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets optional property fields to display.
     */
    public void setOptionalPropertyLabels() {
        if (property.getRemarks() == null) {
            remarks.setManaged(false);
        } else {
            remarks.setText(property.getRemarks().toString());
        }

        if (property.getStatus() == null) {
            status.setManaged(false);
        } else {
            status.setText(property.getStatus().toString());
        }
    }

    /**
     * Sets client fields to display if applicable.
     */
    public void setClientLabels() {
        if (property.getClient() == null) {
            askingPrice.setManaged(false);
            clientName.setManaged(false);
            clientContact.setManaged(false);
            clientEmail.setManaged(false);
        } else {
            setAskingPrice();
            setClientName();
            setClientContact();
            setClientEmail();
        }
    }

    /**
     * Sets client asking price to display if applicable.
     */
    public void setAskingPrice() {
        if (property.getAskingPrice() != null) {
            askingPrice.setText(property.getAskingPrice().toString());
        } else {
            askingPrice.setManaged(false);
        }
    }

    /**
     * Sets client name to display if applicable.
     */
    public void setClientName() {
        if (property.getClient().getClientName() != null) {
            clientName.setText(Client.STRING_CLIENT_NAME + property.getClient().getClientName().toString());
        } else {
            clientName.setManaged(false);
        }
    }

    /**
     * Sets client contact to display if applicable.
     */
    public void setClientContact() {
        if (property.getClient().getClientContact() != null) {
            clientContact.setText(Client.STRING_CLIENT_CONTACT + property.getClient().getClientContact().toString());
        } else {
            clientContact.setManaged(false);
        }
    }

    /**
     * Sets client email to display if applicable.
     */
    public void setClientEmail() {
        if (property.getClient().getClientEmail() != null) {
            clientEmail.setText(Client.STRING_CLIENT_EMAIL + property.getClient().getClientEmail().toString());
        } else {
            clientEmail.setManaged(false);
        }
    }

    /**
     * Greys out Property with expired deadline in Ui.
     */
    public void greyOutPropertiesWithPastDeadlines() {
        if (property.getDeadline().isOver()) {
            cardPane.setStyle("-fx-background-color: #696969");
            deadline.setStyle("-fx-text-fill: darkred");
        }
    }

    /**
     * Sets Property Icon to display base on Property type.
     */
    public void setPropertyTypeIcon() {
        switch (property.getPropertyType().toString()) {
        case HDB:
            propertyTypeIcon.setImage(HDB_ICON);
            break;
        case CONDO:
            propertyTypeIcon.setImage(CONDO_ICON);
            break;
        case LANDED:
            propertyTypeIcon.setImage(LANDED_ICON);
            break;
        default:
            throw new AssertionError(Type.MESSAGE_CONSTRAINTS);
        }
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

