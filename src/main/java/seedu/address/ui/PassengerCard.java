package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.passenger.Passenger;

/**
 * An UI component that displays information of a {@code Passenger}.
 */
public class PassengerCard extends UiPart<Region> {

    private static final String FXML = "PassengerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Passenger passenger;

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
    private Label tripDayTime;
    @FXML
    private Label driver;
    @FXML
    private FlowPane tags;
    @FXML
    private DriverCard driverCard;
    @FXML
    private VBox driverCardContainer;

    /**
     * Creates a {@code PassengerCard} with the given {@code Passenger} and index to display.
     */
    public PassengerCard(Passenger passenger, int displayedIndex) {
        super(FXML);
        this.passenger = passenger;
        id.setText(displayedIndex + ". ");
        name.setText(passenger.getName().fullName);
        phone.setText(passenger.getPhone().value);
        address.setText(passenger.getAddress().value);
        tripDayTime.setText(passenger.getTripDay() + " " + passenger.getTripTime());
        driver.setText(passenger.getDriverStr());
        passenger.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        passenger.getDriver().ifPresent(x -> {
                    driverCard = DriverCard.newDriverCard(x);
                    driverCardContainer.getChildren().add(driverCard.getRoot());
                }
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PassengerCard)) {
            return false;
        }

        // state check
        PassengerCard card = (PassengerCard) other;
        return id.getText().equals(card.id.getText())
                && passenger.equals(card.passenger);
    }
}
