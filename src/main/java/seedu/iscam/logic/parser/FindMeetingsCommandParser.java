package seedu.iscam.logic.parser;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.iscam.logic.commands.FindMeetingsCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.meeting.MeetingContainsKeywordsPredicate;

/**
 * Parses input arguments and create a new FindMeetingsCommand Object
 */
public class FindMeetingsCommandParser implements Parser<FindMeetingsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingsCommand
     * and returns a FindMeetingsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindMeetingsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingsCommand.MESSAGE_USAGE));
        }

        String[] meetingKeywords = trimmedArgs.split("\\s+");
        return new FindMeetingsCommand(new MeetingContainsKeywordsPredicate(Arrays.asList(meetingKeywords)));
    }
}
