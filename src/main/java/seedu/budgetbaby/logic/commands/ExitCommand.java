package seedu.budgetbaby.logic.commands;

import seedu.budgetbaby.model.BudgetBabyModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting BudgetBaby...";

    @Override
    public CommandResult execute(BudgetBabyModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
