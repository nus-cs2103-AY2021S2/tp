package seedu.student.logic.parser;

import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.student.logic.parser.StatsCommandParser.MESSAGE_INVALID_STATS_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.statscommands.StatsCommand;
import seedu.student.logic.commands.statscommands.StatsCommandAll;
import seedu.student.logic.commands.statscommands.StatsCommandFaculty;
import seedu.student.logic.commands.statscommands.StatsCommandNus;
import seedu.student.logic.commands.statscommands.StatsCommandResidence;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.SchoolResidence;

public class StatsCommandParserTest {
    private final StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
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
        assertParseFailure(parser, "AlL", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "All", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "ALL", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " \n AlL   \n \t", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "abcd", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " \n 123   \n \t", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandNus() {
        assertParseFailure(parser, "nus", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "NuS", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "NUs", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "\n nUs   \n \t", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandFaculty() {
        assertParseFailure(parser, "CoM", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsStatsCommandResidence() {
        assertParseFailure(parser, "CapT", String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT,
                StatsCommand.MESSAGE_USAGE));
    }
}
