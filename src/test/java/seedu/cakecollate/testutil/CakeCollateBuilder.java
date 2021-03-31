package seedu.cakecollate.testutil;

import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.order.Order;

/**
 * A utility class to help with building CakeCollate objects.
 * Example usage: <br>
 *     {@code CakeCollate ab = new CakeCollateBuilder().withOrder("John", "Doe").build();}
 */
public class CakeCollateBuilder {

    private CakeCollate cakeCollate;

    public CakeCollateBuilder() {
        cakeCollate = new CakeCollate();
    }

    public CakeCollateBuilder(CakeCollate cakeCollate) {
        this.cakeCollate = cakeCollate;
    }

    /**
     * Adds a new {@code Order} to the {@code CakeCollate} that we are building.
     */
    public CakeCollateBuilder withOrder(Order order) {
        cakeCollate.addOrder(order);
        return this;
    }

    public CakeCollate build() {
        return cakeCollate;
    }
}
