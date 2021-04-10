package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_CLEO;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.RelocateMeetingCommand;
import seedu.iscam.model.commons.Location;

class RelocateMeetingCommandParserTest {
    private RelocateMeetingCommandParser parser = new RelocateMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsRelocateMeetingCommand() {
        assertParseSuccess(parser, "1" + LOCATION_DESC_CLEO,
                new RelocateMeetingCommand(INDEX_FIRST_ITEM, new Location(VALID_LOCATION_CLEO)));
    }

    @Test
    public void parse_missingArg_failure() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RelocateMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexNotANumber_throwsParseException() {
        assertParseFailure(parser, "a" + LOCATION_DESC_CLEO,
                String.format(MESSAGE_INVALID_INDEX, RelocateMeetingCommand.MESSAGE_USAGE));
    }
}
