package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnscheduleCommand;

class UnscheduleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE);
    private final UnscheduleCommandParser parser = new UnscheduleCommandParser();

    @Test
    public void parse_validArgs_returnsScheduleCommand() {
        assertParseSuccess(parser, "1", new UnscheduleCommand(INDEX_FIRST_PERSON, false, false));
        assertParseSuccess(parser, "all", new UnscheduleCommand(null, true, false));
        assertParseSuccess(parser, "expired", new UnscheduleCommand(null, false, true));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, "-5", MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        assertParseFailure(parser, "0", MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
    }
}
