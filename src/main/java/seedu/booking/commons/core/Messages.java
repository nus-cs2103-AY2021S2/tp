package seedu.booking.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_BOOKING_DISPLAYED = "%1$d Booking listed!";
    public static final String MESSAGE_BOOKING_VENUE_FILTERED = "Here are the bookings at ";
    public static final String MESSAGE_BOOKING_TAG_FILTERED = "Here are the bookings with tag ";
    public static final String MESSAGE_BOOKING_PERSON_FILTERED = "Here are the bookings made by ";
    public static final String MESSAGE_BOOKING_DATE_FILTERED = "Here are the bookings on ";
    public static final String MESSAGE_BOOKING_FILTER_FAILED = "No bookings found";
    public static final String MESSAGE_DUPLICATE_VENUE = "The venue already exists in the system.";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL = "Email address "
            + "provided does not exist in the system.\n";
    public static final String MESSAGE_INVALID_VENUE_NAME = "The venue name provided is invalid\n";
    public static final String MESSAGE_VENUE_DISPLAYED = "%1$d Venue(s) displayed!";
    public static final String MESSAGE_INVALID_BOOKING_ID = "The booking id provided does not exist";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_VENUE_LISTED_EMPTY = "There are no venues in the system.\n";
    public static final String MESSAGE_BOOKING_LISTED_EMPTY = "There are no bookings in the system.\n";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format, date "
            + "should be yyyy-mm-dd HH:mm:ss\n";

    public static final String MESSAGE_INVALID_EMAIL_FORMAT = "Invalid email address format, please try again.\n";
    public static final String MESSAGE_PROMPT_TRYAGAIN = "Please try again or enter exit_prompt to quit prompting.\n";

    public static final String PROMPT_EMAIL_MESSAGE = "What is your email address? (Please make ensure this"
        + " email address has been registered in the system)\n";
    public static final String PROMPT_VENUE_MESSAGE = "Which venue would you like to book?\n";
    public static final String PROMPT_DESC_MESSAGE = "Please give a booking description.(optional)\n";
    public static final String PROMPT_TAG_MESSAGE = "Please give booking tags. "
        + "Multiple tags to be separated with spaces.\n";
    public static final String PROMPT_START_MESSAGE = "When does the booking start? "
            + "Please enter in yyyy-mm-dd HH:MM:ss format\n";
    public static final String PROMPT_END_MESSAGE = "When does the booking end? "
            + "Please enter in yyyy-mm-dd HH:MM:ss format\n";
}
