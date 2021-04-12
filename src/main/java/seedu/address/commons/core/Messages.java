package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PREFIX =
            "Invalid prefix! Please refer to the list of prefixes! \n%1$s";
    public static final String MESSAGE_INVALID_AGE_INPUT =
            "Invalid age input! \n%1$s";
    public static final String MESSAGE_BAD_AGE_RANGE_NOTATION =
            "Invalid age range notation (You used more than one '-'! Please refer to Tip 1! \n%1$s";
    public static final String MESSAGE_BAD_AGE_FILTER_KEYWORD_NOTATION =
            "Invalid age range notation (Age filter keywords and lower/upper bounds have to be an integer! "
                    + " \n%1$s";
    public static final String MESSAGE_BAD_LOWER_UPPER_AGE_RANGE_NOTATION =
            "Invalid age range notation (Your lower and upper bound for age filter have not been set correctly! "
                    + "You have inputted in this format: age/[upper_bound]-[lower_bound]! Do refer to Tip 1! \n%1$s";
    public static final String MESSAGE_INVALID_GENDER_INPUT =
            "Invalid gender input! \n%1$s";
    public static final String MESSAGE_EMPTY_ARGUMENT =
            "Missing keyword! Please refer to the instructions below: \n%1$s";
    public static final String MESSAGE_MULTIPLE_WORD_PER_ATTRIBUTE = "You can only input one keyword per attribute!";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The client index provided is invalid.";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d clients listed!";
    public static final String MESSAGE_INVALID_PLAN_INDEX = "The plan index provided is invalid.";
    public static final String messagePersonFilteredOverview(String s) {
        return "%1$d clients listed! \nYour filter keyword(s): " + s;
    }

}
