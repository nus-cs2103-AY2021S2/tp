package seedu.address.logic.parser.meetings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.DateTime;

class FindMeetingCommandParserTest {
    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    void parseBad() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validEmptyDesc() {
        Set<Index> emptySet = Set.of();
        // no leading and trailing whitespaces
        FindMeetingCommand command =
                new FindMeetingCommand(meeting -> true, emptySet);
        assertParseSuccess(parser, " " + PREFIX_DESCRIPTION + "",
                command);
    }


    @Test
    public void parse_validName() {
        Set<Index> emptySet = Set.of();
        // no leading and trailing whitespaces
        FindMeetingCommand command =
                new FindMeetingCommand(meeting -> true, emptySet);
        assertParseSuccess(parser, " " + PREFIX_NAME + "n",
                command);
    }

    @Test
    public void parse_invalidTime() {
        Set<Index> emptySet = Set.of();
        // no leading and trailing whitespaces
        FindMeetingCommand command =
                new FindMeetingCommand(meeting -> true, emptySet);
        assertParseFailure(parser,
                " " + PREFIX_TIME + "YO",
                "findm: " + DateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_all() throws ParseException {
        Set<Index> oneSet = new HashSet<>();
        oneSet.add(Index.fromOneBased(1));
        // no leading and trailing whitespaces
        FindMeetingCommand command =
                new FindMeetingCommand(meeting -> true, oneSet);

        parser.parse(" " + PREFIX_PERSON_CONNECTION + "1 "
                + PREFIX_NAME + "name " + PREFIX_TIME + "2020-06-12 11:30 " + PREFIX_DESCRIPTION + "desc "
                + PREFIX_PRIORITY + "3 " + PREFIX_GROUP + "something");
    }


}
