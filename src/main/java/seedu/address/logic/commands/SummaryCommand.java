package seedu.address.logic.commands;

import seedu.address.model.Model;

public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Shows the summary of task of event completion status.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Summary: ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, true, false);
    }
}
