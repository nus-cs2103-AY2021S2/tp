package chim.testutil;

import chim.model.Chim;
import chim.model.customer.Customer;

/**
 * A utility class to help with building Chim objects.
 * Example usage: <br>
 *     {@code Chim c = new ChimBuilder().withCustomer("John", "Doe").build();}
 */
public class ChimBuilder {

    private final Chim chim;

    public ChimBuilder() {
        chim = new Chim();
    }

    public ChimBuilder(Chim chim) {
        this.chim = chim;
    }

    /**
     * Adds a new {@code Customer} to the {@code Chim} that we are building.
     */
    public ChimBuilder withCustomer(Customer customer) {
        chim.addCustomer(customer);
        return this;
    }

    public Chim build() {
        return chim;
    }
}
