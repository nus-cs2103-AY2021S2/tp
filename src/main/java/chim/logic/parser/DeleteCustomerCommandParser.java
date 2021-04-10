package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import chim.logic.commands.DeleteCustomerCommand;
import chim.logic.parser.exceptions.ParseException;
import chim.model.customer.Phone;

/**
 * Parses input arguments and creates a new DeleteCustomerCommand object
 */
public class DeleteCustomerCommandParser implements Parser<DeleteCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCustomerCommand
     * and returns a DeleteCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCustomerCommand.MESSAGE_USAGE));
        }

        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        return new DeleteCustomerCommand(phone);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
