package seedu.address.testutil;

import static seedu.address.testutil.TypicalCheese.getTypicalCheeses;
import static seedu.address.testutil.TypicalCustomers.getTypicalCustomers;
import static seedu.address.testutil.TypicalOrder.getTypicalOrders;

import seedu.address.model.Chim;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;

public class TypicalModels {

    /**
     * Returns a {@code Chim} with all the typical customers, orders and cheeses.
     */
    public static Chim getTypicalChim() {
        Chim ab = new Chim();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        for (Cheese cheese : getTypicalCheeses()) {
            ab.addCheese(cheese);
        }
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

}
