package seedu.iScam.logic.parser;

import static seedu.iScam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.iScam.logic.commands.AddMeetingCommand;
import seedu.iScam.logic.parser.exceptions.ParseException;
import seedu.iScam.model.client.*;
import seedu.iScam.model.client.Location;
import seedu.iScam.model.meeting.Description;
import seedu.iScam.model.meeting.Meeting;
import seedu.iScam.model.tag.Tag;

public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT, PREFIX_ON, PREFIX_ADDRESS,
                PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT, PREFIX_ON, PREFIX_ADDRESS, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        // PLACEHOLDER ONLY - Replace with finding a client from Storage via ID
        Client client = new Client(new Name("John Doe"), new Phone("12345678"), new Email("john@gmail.com"),
                new Location("Kent Ridge"), new HashSet<Tag>());
        // Parse string into date and time
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_ADDRESS).get());
        // Parse string into description
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Meeting meeting = new Meeting(client, LocalDateTime.now(), location, new Description("test"), tagList);
        return new AddMeetingCommand(meeting);
    }

    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
