package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.driver.Driver;

/**
 * An UI component that displays information of a {@code Driver}.
 */
public class DriverCard extends UiPart<Region> {

    public static final String ICON_PATH_NAME = "/images/name.png";
    public static final String ICON_PATH_PHONE = "/images/phone.png";
    public static final String POOL_MESSAGE = "Pooled by:";

    private static final String FXML = "DriverCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Driver driver;

    @FXML
    private HBox driverCardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label message;
    @FXML
    private VBox cardFieldsContainer;

    /**
     * Creates a {@code DriverCard} with the given {@code iver}.
     */
    private DriverCard(Driver driver) {
        super(FXML);
        requireNonNull(driver);
        this.driver = driver;
        message.setText(POOL_MESSAGE);
        List<Region> cardFieldsContent = new ArrayList<>();
        cardFieldsContent.add(new LabelWithIcon(ICON_PATH_NAME, driver.getName().fullName).getRoot());
        cardFieldsContent.add(new LabelWithIcon(ICON_PATH_PHONE, driver.getPhone().value).getRoot());
        cardFieldsContainer.getChildren().addAll(cardFieldsContent);
    }

    public static DriverCard newDriverCard(Driver driver) {
        return new DriverCard(driver);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DriverCard)) {
            return false;
        }

        // state check
        DriverCard card = (DriverCard) other;
        return driver.equals(card.driver);
    }
}
