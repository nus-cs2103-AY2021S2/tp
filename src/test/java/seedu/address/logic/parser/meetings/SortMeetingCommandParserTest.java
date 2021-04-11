package seedu.address.logic.parser.meetings;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetings.SortMeetingCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.persons.SortPersonCommandParser;
import seedu.address.model.meeting.MeetingSortDirection;
import seedu.address.model.meeting.MeetingSortOption;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;

class SortMeetingCommandParserTest {
    private SortMeetingCommandParser parser = new SortMeetingCommandParser();

    @Test
    void parseEmpty() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs() {
        // no leading and trailing whitespaces
        SortMeetingCommand sortMeetingCommand =
                new SortMeetingCommand(MeetingSortOption.NAME, MeetingSortDirection.ASC);
        assertParseSuccess(parser, " "+PREFIX_SORT_BY+"name "+ PREFIX_SORT_DIRECTION+"asc",
                sortMeetingCommand);
    }
}
