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
            + "Parameters: INDEX (must be a positive integer) [INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 3\n"
            + "OR Delete the person with (any/exactly all) of the provided tags in the displayed person list\n"
            + "Parameters: [{--any | --exact}] -t TAG [-t TAG]...\n"
            + "Example: " + COMMAND_WORD + " -t friends";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD
                + " [{INDEX (must be a positive integer) [INDEX]... | [{--any | --exact}] -t TAG [-t TAG]...}]";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted the following persons: %s";
}
