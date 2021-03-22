package seedu.iscam.logic.parser;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.RelocateMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.client.Location;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;

public class RelocateMeetingCommandParser implements Parser<RelocateMeetingCommand> {

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
