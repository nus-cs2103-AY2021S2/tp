package seedu.address.logic.parser.connections;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.connections.AddPersonToMeetingConnectionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

import java.util.Set;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddPersonToMeetingConnectionParser implements Parser<AddPersonToMeetingConnectionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonToMeetingConnectionCommand
     * and returns an AddPersonToMeetingConnectionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonToMeetingConnectionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CONNECTION, PREFIX_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPersonToMeetingConnectionCommand.MESSAGE_USAGE), pe);
        }

        Set<Group> groupSet = ParserUtil.parseGroups(argMultimap.getAllValues(PREFIX_GROUP));

        Set<Index> personConnectionSet = ParserUtil
                .parsePersonsConnection(argMultimap.getAllValues(PREFIX_PERSON_CONNECTION));

        return new AddPersonToMeetingConnectionCommand(index, personConnectionSet, groupSet);
    }
}
