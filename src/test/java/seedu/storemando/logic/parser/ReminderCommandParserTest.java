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
    public void parse_invalidSingleArg_throwsParseException() {
        assertParseFailure(parser, "3",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1 dayweek",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsReminderCommand() {
        long twoWeeksInDays = 14;
        ReminderCommand expectedReminderCommand = new ReminderCommand(new ItemExpiringPredicate(twoWeeksInDays));
        assertParseSuccess(parser, "2 weeks", expectedReminderCommand);

        long threeDays = 3;
        ReminderCommand expectedReminderCommand2 = new ReminderCommand(new ItemExpiringPredicate(threeDays));
        assertParseSuccess(parser, "3 days", expectedReminderCommand2);

        long oneWeekInDays = 7;
        assertParseSuccess(parser, "1 week", new ReminderCommand(new ItemExpiringPredicate(oneWeekInDays)));
        assertParseSuccess(parser, "1 weeks", new ReminderCommand(new ItemExpiringPredicate(oneWeekInDays)));

        long zeroDays = 0;
        assertParseSuccess(parser, "0 day", new ReminderCommand(new ItemExpiringPredicate(zeroDays)));
        assertParseSuccess(parser, "0 days", new ReminderCommand(new ItemExpiringPredicate(zeroDays)));
    }

    @Test
    public void parse_validArgsWithMultipleWhitespace_returnsReminderCommand() {
        long twoWeeksInDays = 14;
        ReminderCommand expectedReminderCommand = new ReminderCommand(new ItemExpiringPredicate(twoWeeksInDays));

        assertParseSuccess(parser, "2 weeks \t \n \t", expectedReminderCommand);
        assertParseSuccess(parser, "\t \n \t 2 weeks", expectedReminderCommand);
        assertParseSuccess(parser, "2 \t \n \t weeks", expectedReminderCommand);
    }

    @Test
    public void parse_inValidArgs_throwsParseException() {
        assertParseFailure(parser, "chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "2 chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        // integer provided out of range
        assertParseFailure(parser, "366 days", ReminderCommand.MESSAGE_INCORRECT_INTEGER);
        assertParseFailure(parser, "-366 weeks", ReminderCommand.MESSAGE_INCORRECT_INTEGER);

        // integer provided greater than maximum integer
        assertParseFailure(parser, "9223372036854775810 days", ReminderCommand.MESSAGE_INCORRECT_INTEGER);
        assertParseFailure(parser, "-9223372036854775810 weeks", ReminderCommand.MESSAGE_INCORRECT_INTEGER);
    }

}
