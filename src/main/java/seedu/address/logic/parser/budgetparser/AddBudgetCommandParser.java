package seedu.address.logic.parser.budgetparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;

import seedu.address.logic.commands.budgetcommands.AddBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Budget;


public class AddBudgetCommandParser implements Parser<AddBudgetCommand> {

    /**
     * Primary method to parse.
     * @return AddBudgetCommand for execution.
     * @throws ParseException
     */
    public AddBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BUDGET);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_BUDGET)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddBudgetCommand.MESSAGE_USAGE));
        }

        Budget budget =
                ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get());

        return new AddBudgetCommand(budget);
    }

}
