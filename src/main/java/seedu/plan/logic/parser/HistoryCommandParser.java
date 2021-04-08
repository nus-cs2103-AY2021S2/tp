package seedu.plan.logic.parser;

import static seedu.plan.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.plan.logic.commands.HistoryCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

public class HistoryCommandParser implements Parser<HistoryCommand> {

    /**
     * Parses a History command
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public HistoryCommand parse(String args) throws ParseException {
        if (!args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));
        }
        return new HistoryCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
