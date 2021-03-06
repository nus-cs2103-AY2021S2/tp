package seedu.address.logic.commands;

/**
 * Adds a person to the address book.
 */
public abstract class AddCommand extends Command {
    public abstract static final String MESSAGE_USAGE;
    public abstract static final String MESSAGE_SUCCESS;

    public static final String COMMAND_WORD = "add";
}
