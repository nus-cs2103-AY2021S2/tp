package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESET_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetCommand object
 */
public class ResetCommandParser implements Parser<ResetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ResetCommand
     * and returns a ResetCommand for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ResetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String resetType;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RESET_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESET_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ResetCommand.MESSAGE_USAGE));
        }

        resetType = argMultimap.getValue(PREFIX_RESET_TYPE).get();

        if (!(resetType.equals("blank") || resetType.equals("template"))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ResetCommand.MESSAGE_USAGE));
        }

        return new ResetCommand(resetType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
