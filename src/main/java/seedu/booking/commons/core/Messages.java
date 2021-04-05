package seedu.booking.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    // Person related messages
    public static final String MESSAGE_DUPLICATE_PERSON_DISPLAYED_EMAIL = "This email address has been "
            + "registered in system. Please provide another email address\n";
    public static final String MESSAGE_DUPLICATE_PERSON_DISPLAYED_PHONE = "This number has been registered in system. "
            + "Please provide another number\n";
    public static final String MESSAGE_DUPLICATE_PERSON_DISPLAYED_NAME = "This name has been registered in system. "
            + "Please provide another name\n";
    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid name format, please try again.\n";
    public static final String MESSAGE_INVALID_PHONE_FORMAT = "Invalid phone format, please try again.\n";
    public static final String PROMPT_PHONE_MESSAGE = "Please enter the phone number of the booker.\n";
    public static final String PROMPT_EMAIL_PERSON_MESSAGE = "Please enter the email address of the booker.\n";
    public static final String PROMPT_NAME_MESSAGE = "Please enter the name of the booker.\n";

    // Venue related messages
    public static final String PROMPT_CAPACITY_MESSAGE = "Please enter the capacity of the venue.\n"
            + "Default capacity is set to 10 if no input is provided.";
    public static final String PROMPT_VENUE_DESC_MESSAGE = "Please provide an optional venue description.\n";
    public static final String PROMPT_DUPLICATE_VENUE_MESSAGE = "A venue already exists "
            + "in the system with the same name!\n";
    public static final String DEFAULT_VENUE_DESCRIPTION = "No description provided.";
    public static final String PROMPT_GENERAL_ERROR = "Error detected. Please exit command and try again.";

    // Booking related messages
    public static final String MESSAGE_BOOKING_DISPLAYED = "%1$d Booking listed!";
    public static final String MESSAGE_BOOKING_VENUE_FILTERED = "Here are the bookings at ";
    public static final String MESSAGE_BOOKING_TAG_FILTERED = "Here are the bookings with tag ";
    public static final String MESSAGE_BOOKING_PERSON_FILTERED = "Here are the bookings made by ";
    public static final String MESSAGE_BOOKING_DATE_FILTERED = "Here are the bookings on ";
    public static final String MESSAGE_BOOKING_FILTER_FAILED = "No bookings found.";
    public static final String MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX = "The booking index provided is invalid.";

    public static final String MESSAGE_DUPLICATE_VENUE = "The venue already exists in the system.";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL = "Email address "
            + "provided does not exist in the system.\n";
    public static final String MESSAGE_INVALID_VENUE_NAME = "The venue name provided is invalid.\n";
    public static final String MESSAGE_VENUE_DISPLAYED = "%1$d Venue(s) displayed!";
    public static final String MESSAGE_INVALID_BOOKING_ID = "The booking id provided does not exist.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_VENUE_LISTED_EMPTY = "There are no venues in the system.\n";
    public static final String MESSAGE_BOOKING_LISTED_EMPTY = "There are no bookings in the system.\n";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format! Input "
            + "should be in yyyy-mm-dd HH:MM format\n";
    public static final String MESSAGE_INVALID_TIME =
            "Invalid timing: The booking's starting time cannot be later than its ending time";
    public static final String MESSAGE_OVERLAPPING_BOOKING = "This time slot has been booked.";


    public static final String MESSAGE_INVALID_EMAIL_FORMAT = "Invalid email address format, please try again.\n";
    public static final String PROMPT_MESSAGE_TRY_AGAIN = "Please try again or enter exit_prompt to quit prompting.\n";

    public static final String PROMPT_EMAIL_MESSAGE = "Please provide the email address of the booker.\n"
            + "Ensure that the booker is already recorded in the system.\n";
    public static final String PROMPT_BOOKING_VENUE_MESSAGE = "Please provide the venue "
            + "name intended for the booking.\n";
    public static final String PROMPT_BOOKING_DESC_MESSAGE = "Please provide an optional booking description.\n";
    public static final String PROMPT_TAG_MESSAGE = "Please add any tags if applicable. "
        + "Multiple tags to be separated with commas.\n";
    public static final String PROMPT_NEWDATE_MESSAGE = "Please try another booking time period.\n";
    public static final String PROMPT_START_MESSAGE = "Please indicate the booking start time. "
            + "Please enter a valid input in yyyy-mm-dd HH:MM format\n";
    public static final String PROMPT_END_MESSAGE = "Please indicate the booking end time. "
            + "Please enter a valid input in yyyy-mm-dd HH:MM format\n";
}
