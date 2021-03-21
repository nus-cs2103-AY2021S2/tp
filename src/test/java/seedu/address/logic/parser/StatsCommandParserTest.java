package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StatsCommand;

public class StatsCommandParserTest {
    private StatsCommandParser parser = new StatsCommandParser();
    @Test
    public void parse_validArgs_returnsStatsCommand() {
        assertParseSuccess(parser, "1", new StatsCommand(Optional.of(INDEX_FIRST_FLASHCARD)));
    }

    @Test
    public void parse_noArgs_returnsStatsCommand() {
        assertParseSuccess(parser, "", new StatsCommand(Optional.empty()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-10", String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }
}
