package seedu.partyplanet.logic.commands;

/**
 * Represents a deletion command.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts in the displayed person list\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "OR Deletes the person identified by the index number used in the displayed person list\n"
            + "Parameters: INDEX [INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 3\n"
            + "OR Delete the person with all / any (if specified) of the provided tags in the displayed person list\n"
            + "Parameters: [--any] -t TAG [-t TAG]...\n"
            + "Example: " + COMMAND_WORD + " -t friends";

    public static final String MESSAGE_USAGE_CONCISE =
            COMMAND_WORD + " [{INDEX [INDEX]... | [--any] -t TAG [-t TAG]...}]";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted the following person(s): %s";
    public static final String MESSAGE_INVALID_PERSON_INDEX = "Invalid person index(es): %s";
}
