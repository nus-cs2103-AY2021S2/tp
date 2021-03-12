package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.model.item.ItemExpiringPredicate;

public class ReminderCommandParserTest {

    private final ReminderCommandParser parser = new ReminderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsReminderCommand() {
        long numOfDays = 3;
        ReminderCommand expectedReminderCommand = new ReminderCommand(new ItemExpiringPredicate(numOfDays));
        assertParseSuccess(parser, "3", expectedReminderCommand);
    }
}
