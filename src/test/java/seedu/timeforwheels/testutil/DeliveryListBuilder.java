package seedu.timeforwheels.testutil;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.customer.Customer;

/**
 * A utility class to help with building Deliverylist objects.
 * Example usage: <br>
 *     {@code DeliveryList ab = new DeliveryListBuilder().withCustomer("John", "Doe").build();}
 */
public class DeliveryListBuilder {

    private DeliveryList deliveryList;

    public DeliveryListBuilder() {
        deliveryList = new DeliveryList();
    }

    public DeliveryListBuilder(DeliveryList deliveryList) {
        this.deliveryList = deliveryList;
    }

    /**
     * Adds a new {@code Customer} to the {@code DeliveryList} that we are building.
     */
    public DeliveryListBuilder withCustomer(Customer customer) {
        deliveryList.addCustomer(customer);
        return this;
    }

    public DeliveryList build() {
        return deliveryList;
    }
}
