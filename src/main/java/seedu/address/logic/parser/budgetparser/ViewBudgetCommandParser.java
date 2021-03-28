package seedu.address.logic.parser.budgetparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.budgetcommands.ViewBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewBudgetCommandParser {

    /**
     * Primary method that returns an {@code ViewBudgetCommand} to be executed.
     * @throws ParseException
     */
    public ViewBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewBudgetCommand.MESSAGE_USAGE));
        }

        return new ViewBudgetCommand();

    }

}
