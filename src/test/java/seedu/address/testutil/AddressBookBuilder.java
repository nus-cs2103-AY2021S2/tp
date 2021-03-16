package seedu.address.testutil;

import seedu.address.model.DeliveryList;
import seedu.address.model.person.Customer;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code DeliveryList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private DeliveryList deliveryList;

    public AddressBookBuilder() {
        deliveryList = new DeliveryList();
    }

    public AddressBookBuilder(DeliveryList deliveryList) {
        this.deliveryList = deliveryList;
    }

    /**
     * Adds a new {@code Customer} to the {@code DeliveryList} that we are building.
     */
    public AddressBookBuilder withPerson(Customer customer) {
        deliveryList.addCustomer(customer);
        return this;
    }

    public DeliveryList build() {
        return deliveryList;
    }
}
