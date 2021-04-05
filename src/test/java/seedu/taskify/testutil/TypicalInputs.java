package seedu.taskify.testutil;

import seedu.taskify.logic.commands.DeleteMultipleCommand;

/**
 * A utility class containing a list of {@code String} objects to be used in tests.
 */
public class TypicalInputs {
    public static final String DELETE_ALL_COMPLETED_INPUT = DeleteMultipleCommand.COMMAND_WORD + " " + "completed -all";
    public static final String DELETE_ALL_EXPIRED_INPUT = DeleteMultipleCommand.COMMAND_WORD + " " + "expired"
            + " -all";
    public static final String DELETE_ALL_UNCOMPLETED_INPUT = DeleteMultipleCommand.COMMAND_WORD
            + " " + "uncompleted -all";
}
