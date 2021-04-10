package seedu.iscam.logic.parser.meetingcommands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.RescheduleMeetingCommand;
import seedu.iscam.logic.parser.ArgumentMultimap;
import seedu.iscam.logic.parser.ArgumentTokenizer;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.ParserUtil;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.logic.parser.exceptions.ParseIndexException;
import seedu.iscam.model.meeting.DateTime;

/**
 * Parses input arguments and creates a RescheduleMeetingCommand object.
 */
public class RescheduleMeetingCommandParser implements Parser<RescheduleMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RescheduleMeetingCommand
     * and returns a RescheduleMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public RescheduleMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ON);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseIndexException pie) {
            throw pie;
        } catch (ParseException e) {
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RescheduleMeetingCommand.MESSAGE_USAGE));
        }

        DateTime dateTime;
        if (argMultimap.getValue(PREFIX_ON).isPresent()) {
            dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_ON).get());
        } else {
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RescheduleMeetingCommand.MESSAGE_USAGE));
        }

        return new RescheduleMeetingCommand(index, dateTime);
    }
}
