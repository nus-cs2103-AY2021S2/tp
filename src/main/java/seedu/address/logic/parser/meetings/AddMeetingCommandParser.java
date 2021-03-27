package seedu.address.logic.parser.meetings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.meetings.AddMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_START_TIME, PREFIX_END_TIME,
                        PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_GROUP);

        // If the meeting has its meetingName and start time as well as the end time,
        // then it's sufficient for definition.
        // If no description, then the description will be set as empty.
        // If no priority, then the priority will be set as 1.
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        MeetingName meetingName = ParserUtil.parseMeetingName(argMultimap.getValue(PREFIX_NAME).get());
        DateTime startTime = ParserUtil.parseMeetingDateTime(argMultimap.getValue(PREFIX_START_TIME).get());
        DateTime endTime = ParserUtil.parseMeetingDateTime(argMultimap.getValue(PREFIX_END_TIME).get());
        Description description;
        Priority priority;

        // Optional fields:
        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            description = new Description("");
        } else {
            description = ParserUtil.parseMeetingDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            priority = new Priority("1");
        } else {
            priority = ParserUtil.parseMeetingPriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        Set<Group> tagList = ParserUtil.parseGroups(argMultimap.getAllValues(PREFIX_GROUP));

        Meeting meeting;
        try {
            meeting = new Meeting(meetingName, startTime, endTime, priority, description, tagList);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
        return new AddMeetingCommand(meeting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
