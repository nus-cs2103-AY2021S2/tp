package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEPARATOR;

import seedu.address.logic.commands.CollectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code CollectCommand} object
 */
public class CollectCommandParser implements Parser<CollectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code CollectCommand}
     * and returns a {@code CollectCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CollectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_SEPARATOR);

        Prefix prefix = getOnePrefix(argMultimap,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        if (prefix == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CollectCommand.MESSAGE_USAGE));
        }

        String separator = argMultimap.getValue(PREFIX_SEPARATOR)
                .orElse(CollectCommand.DEFAULT_SEPARATOR);
        return new CollectCommand(prefix, separator);
    }

    /**
     * Returns the only prefix that contains nonempty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * Returns null if there is 0 or more than 1 such prefix.
     */
    private static Prefix getOnePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        Prefix prefix = null;
        for (Prefix currentPrefix : prefixes) {
            if (argumentMultimap.getValue(currentPrefix).isPresent()) {
                if (prefix != null) {
                    return null;
                }
                prefix = currentPrefix;
            }
        }
        return prefix;
    }
}
