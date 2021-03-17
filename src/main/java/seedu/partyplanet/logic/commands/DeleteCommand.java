package seedu.partyplanet.logic.commands;

/**
 * Represents a deletion command.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts in currently filtered list\n"
            + "OR Deletes the person identified by the index number used in the displayed person list\n"
            + "OR Delete the person with the provided tag,\n"
            + "unless the person has other tags, then only the tag is removed\n"
            + "Parameters: [{INDEX (must be a positive integer) [INDEX]... | -t TAG [-t TAG]...}]\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted the following persons: %1$s";
}
