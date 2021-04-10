package chim.testutil;

import chim.model.Chim;
import chim.model.cheese.Cheese;
import chim.model.customer.Customer;
import chim.model.order.Order;

public class TypicalModels {

    /**
     * Returns a {@code Chim} with all the typical customers, orders and cheeses.
     */
    public static Chim getTypicalChim() {
        Chim ab = new Chim();
        for (Customer customer : TypicalCustomers.getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        for (Cheese cheese : TypicalCheese.getTypicalCheeses()) {
            ab.addCheese(cheese);
        }
        for (Order order : TypicalOrder.getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

}
