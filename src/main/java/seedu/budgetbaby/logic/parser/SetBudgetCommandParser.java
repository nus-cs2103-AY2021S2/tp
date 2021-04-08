package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.budgetbaby.logic.commands.SetBudgetCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.Budget;

/**
 * Parses input arguments and creates a new SetBudgetCommand object.
 */
public class SetBudgetCommandParser implements BudgetBabyCommandParser<SetBudgetCommand> {
    @Override
    public SetBudgetCommand parse(String args) throws ParseException {
        try {
            List<String> stringAmounts = ArgumentTokenizer.tokenizeWithoutPrefix(args);
            String lastAmount = stringAmounts.get(stringAmounts.size() - 1);

            if (countDigitsAfterDecimalPlaces(lastAmount) == 0
                    || countDigitsAfterDecimalPlaces(lastAmount) > 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SetBudgetCommand.MESSAGE_USAGE));
            }

            double amount = ParserUtil.parseBudgetAmount(args);
            Budget budget = new Budget(amount);
            return new SetBudgetCommand(budget);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetBudgetCommand.MESSAGE_USAGE));
        }
    }

    private int countDigitsAfterDecimalPlaces(String amount) {
        int decimalIndex = amount.indexOf('.');
        if (decimalIndex < 0) {
            return -1;
        }

        return amount.substring(decimalIndex + 1).length();
    }
}
