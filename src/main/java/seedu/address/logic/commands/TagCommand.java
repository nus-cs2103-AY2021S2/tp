package seedu.address.logic.commands;

/**
 * Represents a tag command with hidden internal logic and the ability to be executed.
 */
public abstract class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    public static final String ADD_SUB_COMMAND_WORD = "add";
    public static final String DELETE_SUB_COMMAND_WORD = "delete";
    public static final String MESSAGE_NO_SELECTED_PERSON = "You have not selected any person.";
    public static final String MESSAGE_NO_SHOWN_PERSON = "There are no shown person.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add or delete tags of person(s) in the address book.\n"
            + "Parameters: "
            + "SUB_COMMAND [PARAMETERS]\n"
            + "Sub Commands: "
            + ADD_SUB_COMMAND_WORD + " INDEX … -t TAG …, "
            + DELETE_SUB_COMMAND_WORD + " INDEX … -t TAG …\n"
            + "Example: "
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " 1 2 -t Java -t Python\n"
            + COMMAND_WORD + " " + DELETE_SUB_COMMAND_WORD + " 1 2 3 -t Python";

}
