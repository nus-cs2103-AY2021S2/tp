package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.model.order.ReminderDatePredicate;

public class RemindCommandParserTest {

    private RemindCommandParser parser = new RemindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRemindCommand() {
        assertParseSuccess(parser, "1", new RemindCommand(new ReminderDatePredicate(1)));
        assertParseSuccess(parser, "0", new RemindCommand(new ReminderDatePredicate(0)));
    }
}
