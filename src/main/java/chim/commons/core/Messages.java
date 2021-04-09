package chim.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX = "The cheese index provided is invalid.";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX = "The customer index provided is invalid.";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_PHONE =
            "The customer phone number provided is invalid.";
    public static final String MESSAGE_INVALID_ORDER_DISPLAYED_INDEX = "The order index provided is invalid.";
    public static final String MESSAGE_INVALID_ORDER_COMPLETE = "The order index provided is already completed.";
    public static final String MESSAGE_ORDERS_FOUND_OVERVIEW = "%1$d orders with parameters of %2$s "
            + "has been found!";
    public static final String MESSAGE_ORDERS_NOT_FOUND_OVERVIEW = "No orders with parameters of %1$s "
            + "has been found!";
    public static final String MESSAGE_CUSTOMERS_FOUND_OVERVIEW = "%1$d customers with parameters of %2$s "
            + "has been found!";
    public static final String MESSAGE_CUSTOMERS_NOT_FOUND_OVERVIEW = "No customers with parameters of %1$s "
            + "has been found!";
    public static final String MESSAGE_CHEESES_FOUND_OVERVIEW = "%1$d cheeses with parameters of %2$s has been found";
    public static final String MESSAGE_CHEESES_NOT_FOUND_OVERVIEW = "No cheeses with parameters of %1$s has been found";
    public static final String MESSAGE_INSUFFICIENT_CHEESES_FOR_ORDER = "There is insufficient cheese "
            + "to complete the order.\nMake sure that the cheeses have not expired.";
    public static final String MESSAGE_INVALID_CHEESE_ASSIGNED_TO_COMPLETED_ORDER = "The cheese is assigned to "
            + "a completed order and cannot be deleted.";
    public static final String MESSAGE_INVALID_ORDER_CUSTOMER_ID = "Order %1$d's customer ID does not exist.";
    public static final String MESSAGE_INVALID_ORDER_CHEESE_ID = "Order %1$d's cheese ID does not exist.";
    public static final String MESSAGE_INVALID_CHEESE_MULTIPLE_ORDER = "Order %1$d's Cheese %2$d has been "
            + "assigned to another order.";
    public static final String MESSAGE_INVALID_CHEESE_NOT_ASSIGNED = "Cheese %2$d in Order %1$d has not been "
            + "marked assigned.";
    public static final String MESSAGE_INVALID_ORDER_CHEESE_CHEESE_TYPE = "Cheese %1$d's cheese type does not "
            + "match Order %2$d's cheese type.";
    public static final String MESSAGE_INVALID_ASSIGNED_CHEESE = "Cheese %1$d is marked assigned but not "
            + "assigned to any order.";
    public static final String MESSAGE_INVALID_MANUFACTURE_DATE_COMPLETED_DATE = "Cheese %2$d's manufacture date must "
            + "be before order %1$d completed date.";
    public static final String MESSAGE_INVALID_EXPIRY_DATE_COMPLETED_DATE = "Cheese %2$d's expiry date must be after "
            + "Order %1$d's completed date.";
    public static final String MESSAGE_CHEESE_QUANTITY_EXCEED = "The specified cheese quantity has exceeded the allowed"
            + " upper limit of 1000. You may add more cheeses using multiple transactions.";

}
