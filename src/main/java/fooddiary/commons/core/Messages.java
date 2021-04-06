package fooddiary.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format. \n%1$s";
    public static final String MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX = "The entry index provided is invalid.";
    public static final String MESSAGE_ENTRIES_LISTED_OVERVIEW = "%1$d entry/entries listed.";
    public static final String MESSAGE_ENTRIES_LISTED_OVERVIEW_WITH_SUGGESTION = "%1$d entry/entries listed.\n%2$s";

    //suggestion messages for search features
    public static final String SUGGESTION_MESSAGE_EMPTY = "";
    public static final String SUGGESTION_MESSAGE_FOR_RATING = "Suggestion: One or more of the keywords provided "
            + "closely resembles a search for ratings. Ratings need to be of the form 'INTEGER/5', where the integer "
            + "can be from 0 to 5 both inclusive.";
    public static final String SUGGESTION_MESSAGE_FOR_PRICE = "Suggestion: One or more of the keywords provided "
            + "closely resembles a search for prices. Prices need to be of the form '$INTEGER' or '$INTEGER-INTEGER', "
            + "where the integers can be from 0 to 999 inclusive.";
    public static final String SUGGESTION_MESSAGE_FOR_RATING_AND_PRICE = "Suggestion: Two or more of the keywords "
            + "provided closely resembles searches for ratings and prices. Ratings need to be of the form 'INTEGER/5', "
            + "where the integer can be from 0 to 5 both inclusive. Prices need to be of the form '$INTEGER' or "
            + "'$INTEGER-INTEGER', where the integers can be from 0 to 999 inclusive.";
}
