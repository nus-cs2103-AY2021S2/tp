package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.AbstractDate;

/**
 * Represents an Order's order date in the Cheese Inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class OrderDate extends AbstractDate {

    /**
     * Constructs a {@code OrderDate} with current date time.
     */
    private OrderDate() {
        super();
    }

    /**
     * Constructs a {@code OrderDate}.
     *
     * @param date A valid order date.
     */
    public OrderDate(String date) {
        super(date);
        checkArgument(isValidOrderDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidOrderDate(LocalDateTime value) {
        return true;
    }

    public static OrderDate now() {
        return new OrderDate();
    }
}
