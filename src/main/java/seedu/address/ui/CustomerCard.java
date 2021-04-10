package seedu.address.ui;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.customer.Car;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerCard extends UiPart<Region> {

    private static final String FXML = "CustomerListCard.fxml";
    private static final String DATE_OF_BIRTH = "DOB: ";
    private static final String CARS_OWNED = "carsOwned|COEexpiry: ";
    private static final String CARS_PREFERRED = "carsPreferred: ";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Customer customer;

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
    private Label email;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label carsOwned;
    @FXML
    private Label carsPreferred;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CustomerCard(Customer customer, int displayedIndex) {
        super(FXML);
        this.customer = customer;
        id.setText(displayedIndex + ". ");
        name.setText(customer.getName().fullName);
        phone.setText(customer.getPhone().value);
        email.setText(customer.getEmail().value);
        address.setText(customer.getAddress().value);
        dateOfBirth.setText(DATE_OF_BIRTH + customer.getDateOfBirth().birthDate);
        customer.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        carsOwned.setText(getCarsAsTextRepresentation(customer.getCarsOwned()));
        carsPreferred.setText(getCarsPreferredAsTextRepresentation(customer.getCarsPreferred()));
    }

    /**
     * Returns carsOwned attribute as a String.
     *
     * @param cars
     * @return String
     */
    private String getCarsAsTextRepresentation(Map<Car, CoeExpiry> cars) {
        StringBuilder sb = new StringBuilder(CARS_OWNED);
        if (cars == null || cars.isEmpty()) {
            sb.append("None");
        } else {
        }
        for (Map.Entry<Car, CoeExpiry> entry : cars.entrySet()) {
            sb.append("[" + entry.getKey() + "|");
            sb.append(entry.getValue() + "] ");
        }
        return sb.toString();
    }

    /**
     * Returns carsPreferred attribute as a String.
     *
     * @param cars
     * @return String
     */
    private String getCarsPreferredAsTextRepresentation(Set<Car> cars) {
        StringBuilder sb = new StringBuilder(CARS_PREFERRED);
        if (cars == null || cars.isEmpty()) {
            sb.append("None");
        } else {
            cars.forEach(x -> sb.append("[" + x + "] "));
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerCard)) {
            return false;
        }

        // state check
        CustomerCard card = (CustomerCard) other;
        return id.getText().equals(card.id.getText())
            && customer.equals(card.customer);
    }
}
