package seedu.cakecollate.commons.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ORDER_DISPLAYED_INDEX = "The order index provided is invalid";
    public static final String MESSAGE_ORDERS_LISTED_OVERVIEW = "%1$d orders listed!";
    public static final String MESSAGE_ORDERS_REMINDER_OVERVIEW = "REMINDER! \n"
            + "Date Today is "
            + DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDate.now())
            + ".\n"
            + "%1$d orders with an upcoming delivery date!";
}

