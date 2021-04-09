package seedu.address.logic.parser.reminderparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.remindercommands.AddReminderCommand;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;

public class AddReminderCommandParserTest {

    private final AddReminderCommandParser parser = new AddReminderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Reminder expectedReminder = MATHS_TUITION_PAYMENT_REMINDER;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE,
                new AddReminderCommand(expectedReminder));

        // multiple description - last title accepted
        assertParseSuccess(parser, REMINDER_DESC_DESC_TWO + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE,
                new AddReminderCommand(expectedReminder));

        // multiple date - last date accepted
        assertParseSuccess(parser, REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_TWO + REMINDER_DATE_DESC_ONE,
                new AddReminderCommand(expectedReminder));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE);

        // missing desc prefix
        assertParseFailure(parser, VALID_REMINDER_DESC_ONE + REMINDER_DATE_DESC_ONE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, REMINDER_DESC_DESC_ONE + VALID_REMINDER_DATE_ONE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_REMINDER_DESC_ONE + VALID_REMINDER_DATE_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_DESC_DESC + REMINDER_DATE_DESC_ONE,
                Description.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, REMINDER_DESC_DESC_ONE + INVALID_DATE_DESC,
                ReminderDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESC_DESC + INVALID_DATE_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "invalid input",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
    }
}
