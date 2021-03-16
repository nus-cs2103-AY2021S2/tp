package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ReminderCommand;
import seedu.storemando.model.expirydate.ItemExpiringPredicate;

public class ReminderCommandParserTest {

    private final ReminderCommandParser parser = new ReminderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArg_returnsReminderCommand() {
        long numOfDays = 3;
        ReminderCommand expectedReminderCommand = new ReminderCommand(new ItemExpiringPredicate(numOfDays));
        assertParseSuccess(parser, "3", expectedReminderCommand);
    }

    @Test
    public void parse_validSMultipleArg_returnsReminderCommand() {
        long numOfDays = 14;
        ReminderCommand expectedReminderCommand = new ReminderCommand(new ItemExpiringPredicate(numOfDays));
        assertParseSuccess(parser, "2 weeks", expectedReminderCommand);
    }

    @Test
    public void parse_inValidArgs_throwsParseException() {
        assertParseFailure(parser, "chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "2 chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "2 chocolate factory",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }
}
