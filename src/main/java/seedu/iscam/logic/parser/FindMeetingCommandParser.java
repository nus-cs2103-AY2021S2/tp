package seedu.iscam.logic.parser;

import seedu.iscam.logic.commands.FindMeetingsCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.meeting.MeetingContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindMeetingCommandParser implements Parser<FindMeetingsCommand> {
    public FindMeetingsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingsCommand.MESSAGE_USAGE));
        }

        String[] meetingKeywords = trimmedArgs.split("\\s+");
        return new FindMeetingsCommand(new MeetingContainsKeywordsPredicate(Arrays.asList(meetingKeywords)));
    }
}
