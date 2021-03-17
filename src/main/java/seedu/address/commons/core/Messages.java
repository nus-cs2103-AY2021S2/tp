package seedu.address.commons.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSONS_REMINDER_OVERVIEW = "REMINDER! \n"
            + "Date Today is "
            + DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDate.now())
            + ".\n"
            + "%1$d orders with an upcoming delivery date!";
}

