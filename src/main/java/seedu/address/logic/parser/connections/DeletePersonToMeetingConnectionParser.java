package seedu.address.logic.parser.connections;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.connections.DeletePersonToMeetingConnectionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeletePersonToMeetingConnectionParser implements Parser<DeletePersonToMeetingConnectionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonToMeetingConnectionCommand
     * and returns an AddPersonToMeetingConnectionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonToMeetingConnectionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CONNECTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePersonToMeetingConnectionCommand.MESSAGE_USAGE), pe);
        }

        Set<Index> personConnectionSet = ParserUtil
                .parsePersonsConnection(argMultimap.getAllValues(PREFIX_PERSON_CONNECTION));

        return new DeletePersonToMeetingConnectionCommand(index, personConnectionSet);
    }
}
