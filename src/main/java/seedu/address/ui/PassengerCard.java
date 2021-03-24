package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
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
    private static final String ICON_PATH_PHONE = "/images/phone.png";
    private static final String ICON_PATH_ADDRESS = "/images/address.png";
    private static final String ICON_PATH_DRIVER = "/images/driver.png";
    private static final String ICON_PATH_TIME = "/images/time.png";

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
    private FlowPane tags;
    @FXML
    private DriverCard driverCard;
    @FXML
    private VBox driverCardContainer;
    @FXML
    private VBox cardFieldContainer;
    @FXML
    private ColumnConstraints columnConstraints;

    /**
     * Creates a {@code PassengerCard} with the given {@code Passenger} and index to display.
     */
    public PassengerCard(Passenger passenger, int displayedIndex) {
        super(FXML);
        this.passenger = passenger;
        id.setText(displayedIndex + ". ");
        name.setText(passenger.getName().fullName);
        passenger.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // collect the fields that uses the icon and label format
        List<Region> cardFields = new ArrayList<>();
        cardFields.add(new LabelWithIcon(ICON_PATH_PHONE, passenger.getPhone().value).getRoot());
        cardFields.add(new LabelWithIcon(ICON_PATH_ADDRESS, passenger.getAddress().value).getRoot());
        cardFields.add(new LabelWithIcon(ICON_PATH_TIME,
                passenger.getTripDay() + " " + passenger.getTripTime()).getRoot());
        cardFields.add(new LabelWithIcon(ICON_PATH_DRIVER, passenger.getDriverStr()).getRoot());
        cardFieldContainer.getChildren().addAll(cardFields);

        //if there is a driver, create the driverCard and display the info, else release the reserved width
        passenger.getDriver().ifPresentOrElse(x -> {
                driverCard = DriverCard.newDriverCard(x);
                driverCardContainer.getChildren().add(driverCard.getRoot());
            }, () -> columnConstraints.setPercentWidth(0)
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
