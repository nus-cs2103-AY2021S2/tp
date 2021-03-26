package seedu.address.logic.parser.budgetparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.budgetcommands.DeleteBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;


public class DeleteBudgetCommandParser {

    /**
     * Primary method that throws an {@code EditBudgetCommand} to be executed.
     * @throws ParseException
     */
    public DeleteBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteBudgetCommand.MESSAGE_USAGE));
        }

        return new DeleteBudgetCommand();

    }

}
