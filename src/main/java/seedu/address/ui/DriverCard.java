package seedu.address.ui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An UI component that displays information of a {@code Passenger}.
 */
public class DriverCard extends UiPart<Region> {

    private static final String FXML = "DriverCard.fxml";
    private static final String POOL_MESSAGE = "Pooled by:";
    private static final String ICON_PATH_PHONE = "/images/phone.png";
    private static final String ICON_PATH_NAME = "/images/name.png";
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
     * Creates a {@code PassengerCard} with the given {@code Passenger} and index to display.
     */
    private DriverCard(Driver driver) {
        super(FXML);
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
