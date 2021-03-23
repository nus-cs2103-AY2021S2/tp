package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.driver.Driver;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Passenger}.
 */
public class DriverCard extends UiPart<Region> {

    private static final String FXML = "DriverCard.fxml";
    private static final String MESSAGE = "Pooled by:";
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

    /**
     * Creates a {@code PassengerCard} with the given {@code Passenger} and index to display.
     */
    private DriverCard(Driver driver) {
        super(FXML);
        this.driver = driver;
        message.setText(MESSAGE);
        name.setText(driver.getName().fullName);
        phone.setText(driver.getPhone().value);
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
