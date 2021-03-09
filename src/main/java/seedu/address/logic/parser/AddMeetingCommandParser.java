package seedu.address.logic.parser;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT, PREFIX_ON, PREFIX_ADDRESS,
                PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT, PREFIX_ON, PREFIX_ADDRESS, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        // PLACEHOLDER ONLY - Replace with finding a client from Storage via ID
        Client client = new Client(new Name("John Doe"), new Phone("12345678"), new Email("john@gmail.com"),
                new Address("Kent Ridge"), new HashSet<Tag>());
        // Parse string into date and time
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        // Parse string into description
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Meeting meeting = new Meeting(client, LocalDateTime.now(), address, new Description("test"), tagList);
        return new AddMeetingCommand(meeting);
    }

    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
