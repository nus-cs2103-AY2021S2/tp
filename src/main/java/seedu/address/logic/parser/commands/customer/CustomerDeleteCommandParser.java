package seedu.address.logic.parser.commands.customer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCE_DELETE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.customer.CustomerDeleteCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class CustomerDeleteCommandParser implements Parser<CustomerDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomerDeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FORCE_DELETE);

        try {

            if (argMultimap.getValue(PREFIX_FORCE_DELETE).isEmpty()) {
                Index index = ParserUtil.parseIndex(args);
                return new CustomerDeleteCommand(index, false);
            } else {
                Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
                return new CustomerDeleteCommand(index, true);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomerDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
