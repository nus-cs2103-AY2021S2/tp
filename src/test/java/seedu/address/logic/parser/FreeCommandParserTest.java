package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ENTRY_START_DATE_IN_PAST;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_RANGE;
import static seedu.address.logic.commands.CommandTestUtil.PAST_DATE_INTERVAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FreeCommand;

/**
 * Contains tests to make sure the parser instantiates the correct {@code FreeCommand}.
 */
public class FreeCommandParserTest {

    private final FreeCommandParser parser = new FreeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FreeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_startTimeAfterEndTime_throwsParseException() {
        assertParseFailure(parser, INVALID_DATE_RANGE, MESSAGE_INVALID_DATE_RANGE);
    }

    @Test
    public void parse_pastInterval_throwsParseException() {
        assertParseFailure(parser, PAST_DATE_INTERVAL, MESSAGE_ENTRY_START_DATE_IN_PAST);
    }
}
