package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.statscommands.StatsCommand;
import seedu.address.logic.commands.statscommands.StatsCommandAll;
import seedu.address.logic.commands.statscommands.StatsCommandFaculty;
import seedu.address.logic.commands.statscommands.StatsCommandNus;
import seedu.address.logic.commands.statscommands.StatsCommandResidence;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.SchoolResidence;

public class StatsCommandParserTest {
    private final StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsStatsCommandAll() {
        StatsCommandAll expectedStatsCommandAll =
                new StatsCommandAll();
        assertParseSuccess(parser, "all", expectedStatsCommandAll);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n all   \n \t", expectedStatsCommandAll);
    }

    @Test
    public void parse_validArgs_returnsStatsCommandNus() {
        StatsCommandNus expectedStatsCommandNus =
                new StatsCommandNus();
        assertParseSuccess(parser, "NUS", expectedStatsCommandNus);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n NUS   \n \t", expectedStatsCommandNus);
    }

    @Test
    public void parse_validArgs_returnsStatsCommandFaculty() {
        StatsCommandFaculty expectedStatsCommandFaculty =
                new StatsCommandFaculty(new Faculty("COM"));
        assertParseSuccess(parser, "COM", expectedStatsCommandFaculty);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n COM   \n \t", expectedStatsCommandFaculty);
    }

    @Test
    public void parse_validArgs_returnsStatsCommandResidence() {
        StatsCommandResidence expectedStatsCommandResidence =
                new StatsCommandResidence(new SchoolResidence("CAPT"));
        assertParseSuccess(parser, "CAPT", expectedStatsCommandResidence);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CAPT   \n \t", expectedStatsCommandResidence);
    }

    //
    @Test
    public void parse_invalidArgs_returnsStatsCommandAll() {
        assertParseFailure(parser, "AlL", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "All", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "ALL", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " \n AlL   \n \t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " \n 123   \n \t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandNus() {
        assertParseFailure(parser, "nus", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "NuS", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "NUs", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "\n nUs   \n \t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandFaculty() {
        assertParseFailure(parser, "CoM", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandResidence() {
        assertParseFailure(parser, "CapT", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }
}
