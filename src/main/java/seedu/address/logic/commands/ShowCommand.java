package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a plan to the address book.
 */
public abstract class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows information about the Current Semester. "
            + "Parameters: mcs | cap";

    public static final String MESSAGE_SUCCESS_MCS = "Current MCs this semester: %1$d";
    public static final String MESSAGE_SUCCESS_CAP = "Current CAP is: %1$f";


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
