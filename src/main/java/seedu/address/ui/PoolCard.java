package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.pool.Pool;

/**
 * An UI component that displays information of a {@code Passenger}.
 */
public class PoolCard extends UiPart<Region> {

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

    public final Pool pool;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox cardFieldContainer;
    @FXML
    private ColumnConstraints columnConstraints;

    /**
     * Creates a {@code PassengerCard} with the given {@code Passenger} and index to display.
     */
    public PoolCard(Pool pool, int displayedIndex) {
        super(FXML);
        this.pool = pool;
        id.setText(displayedIndex + ". ");
        name.setText("Trip by " + pool.getDriver().getName().toString());

        //TODO: Mark I removed the getDriverAsStr() method below here as the passenger no longer has a driver object
        // attached to it. Just letting you know in case it causes problems.

        // collect the fields that uses the icon and label format
        List<Region> cardFields = new ArrayList<>();
        cardFields.add(new LabelWithIcon(ICON_PATH_DRIVER, pool.getPassengers().stream().map(passenger
            -> passenger.getName().toString()).collect(Collectors.joining(", "))).getRoot());
        cardFields.add(new LabelWithIcon(ICON_PATH_PHONE, pool.getDriver().getPhone().toString()).getRoot());
        cardFields.add(new LabelWithIcon(ICON_PATH_TIME,
                pool.getTripDay() + " " + pool.getTripTime()).getRoot());
        cardFieldContainer.getChildren().addAll(cardFields);

        //TODO: Mark I removed the getDriver().ifPresentOrElse() block of code below here as the passenger no longer has
        // a driver object attached to it. Just letting you know in case it causes problems.
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PoolCard)) {
            return false;
        }

        // state check
        PoolCard card = (PoolCard) other;
        return id.getText().equals(card.id.getText())
                && pool.equals(card.pool);
    }
}
