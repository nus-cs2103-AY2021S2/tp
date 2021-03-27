package seedu.iscam.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.RelocateMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.commons.Location;

/**
 * Parses input arguments and creates a new RelocateMeetingCommand object.
 */
public class RelocateMeetingCommandParser implements Parser<RelocateMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RelocateMeetingCommand
     * and returns an RelocateMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public RelocateMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOCATION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RelocateMeetingCommand.MESSAGE_USAGE));
        }

        Location location;
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RelocateMeetingCommand.MESSAGE_USAGE));
        }

        return new RelocateMeetingCommand(index, location);
    }
}
