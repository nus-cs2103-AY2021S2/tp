package seedu.partyplanet.logic.commands;

/**
 * Represents an event deletion command.
 */
public abstract class EDeleteCommand extends Command {

    public static final String COMMAND_WORD = "edelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all events in currently filtered list\n"
            + "OR Deletes the event identified by the index number used in the displayed event list\n"
            + "Parameters: [INDEX [INDEX]...]\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted the following event(s): %s";
    public static final String MESSAGE_INVALID_EVENT_INDEX = "Invalid event index(es): %s";
    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " [INDEX [INDEX]...]";
}
