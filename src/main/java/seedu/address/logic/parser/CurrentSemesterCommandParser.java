package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.CurrentSemesterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CurrentSemesterCommand
 */
public class CurrentSemesterCommandParser implements Parser<CurrentSemesterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CurrentSemesterCommand
     * and returns an CurrentSemesterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CurrentSemesterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SEM_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SEM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CurrentSemesterCommand.MESSAGE_USAGE));
        }

        int currentSemesterNumber = Integer.valueOf(argMultimap.getValue(PREFIX_SEM_NUMBER).get());
        return new CurrentSemesterCommand(currentSemesterNumber);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
