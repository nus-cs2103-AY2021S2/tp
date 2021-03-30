package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.InfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new InfoCommand object
 */

public class InfoCommandParser implements Parser<InfoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InfoCommand
     * and returns an InfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InfoCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            return new InfoCommand();
        } else {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE);

            if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InfoCommand.MESSAGE_USAGE));
            }
            String moduleCode = argMultimap.getValue(PREFIX_MODULE_CODE).get();

            return new InfoCommand(moduleCode);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).get().equals(""));
    }
}
