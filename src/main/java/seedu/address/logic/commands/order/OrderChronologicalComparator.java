package seedu.address.logic.commands.order;

import java.util.Comparator;

import seedu.address.model.order.Order;


public class OrderChronologicalComparator implements Comparator<Order> {
    @Override

    public int compare(Order first, Order second) {
        if (first.getDatetime().isAfter(second.getDatetime())) {
            return 1;
        }
        return 0;
    }
}
