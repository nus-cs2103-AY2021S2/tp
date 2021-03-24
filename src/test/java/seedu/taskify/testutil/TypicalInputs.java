package seedu.taskify.testutil;

import seedu.taskify.logic.commands.DeleteMultipleCommand;

/**
 * A utility class containing a list of {@code String} objects to be used in tests.
 */
public class TypicalInputs {
    public static final String DELETE_ALL_COMPLETED_INPUT = DeleteMultipleCommand.COMMAND_WORD + " " + "completed -all";
    public static final String DELETE_ALL_IN_PROGRESS_INPUT = DeleteMultipleCommand.COMMAND_WORD + " " + "in progress"
            + " -all";
    public static final String DELETE_ALL_NOT_DONE_INPUT = DeleteMultipleCommand.COMMAND_WORD + " " + "not done -all";
}
