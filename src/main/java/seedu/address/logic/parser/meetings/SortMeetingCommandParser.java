package seedu.address.logic.parser.meetings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import seedu.address.logic.commands.meetings.SortMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingSortDirection;
import seedu.address.model.meeting.MeetingSortOption;


public class SortMeetingCommandParser implements Parser<SortMeetingCommand> {

    /**
     * Parses {@code args} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SortMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_BY, PREFIX_SORT_DIRECTION);

        try {
            String optionStringCaps = argMultimap.getValue(PREFIX_SORT_BY).get().toUpperCase();
            String directionStringCaps = argMultimap.getValue(PREFIX_SORT_DIRECTION).get().toUpperCase();

            MeetingSortOption option = MeetingSortOption.valueOf(optionStringCaps);
            MeetingSortDirection direction = MeetingSortDirection.valueOf(directionStringCaps);
            return new SortMeetingCommand(option, direction);
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
        }

    }



}
