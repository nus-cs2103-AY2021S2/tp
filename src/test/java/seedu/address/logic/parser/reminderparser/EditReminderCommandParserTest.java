package seedu.address.logic.parser.reminderparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.REMINDER_DESC_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_TWO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.remindercommands.EditReminderCommand;
import seedu.address.logic.commands.remindercommands.EditReminderCommand.EditReminderDescriptor;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.ReminderDate;
import seedu.address.testutil.EditReminderDescriptorBuilder;

public class EditReminderCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReminderCommand.MESSAGE_USAGE);

    private EditReminderCommandParser parser = new EditReminderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_REMINDER_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditReminderCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DESC_DESC, Description.MESSAGE_CONSTRAINTS); // invalid desc
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, ReminderDate.MESSAGE_CONSTRAINTS); // invalid date

        // invalid title followed by valid desc
        assertParseFailure(parser, "1" + INVALID_DESC_DESC + REMINDER_DATE_DESC_ONE, Description.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid desc
        assertParseFailure(parser, "1" + REMINDER_DESC_DESC_ONE + INVALID_DATE_DESC, ReminderDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESC_DESC + INVALID_DATE_DESC, Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + REMINDER_DESC_DESC_TWO + REMINDER_DATE_DESC_TWO;

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withDescription(VALID_REMINDER_DESC_TWO)
                .withReminderDate(VALID_REMINDER_DATE_TWO).build();
        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + REMINDER_DESC_DESC_THREE;
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withDescription(VALID_REMINDER_DESC_THREE).build();
        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timeFrom timeTo
        userInput = targetIndex.getOneBased() + REMINDER_DATE_DESC_THREE;
        descriptor = new EditReminderDescriptorBuilder().withReminderDate(VALID_REMINDER_DATE_THREE).build();
        expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + REMINDER_DESC_DESC_ONE + REMINDER_DATE_DESC_ONE
                + REMINDER_DESC_DESC_TWO + REMINDER_DATE_DESC_TWO;

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withDescription(VALID_REMINDER_DESC_TWO).withReminderDate(VALID_REMINDER_DATE_TWO)
                .build();
        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_DESC_DESC + REMINDER_DESC_DESC_TWO;
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withDescription(VALID_REMINDER_DESC_TWO).build();
        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + REMINDER_DESC_DESC_TWO + INVALID_DATE_DESC + REMINDER_DATE_DESC_TWO;
        descriptor = new EditReminderDescriptorBuilder().withReminderDate(VALID_REMINDER_DATE_TWO)
                .withDescription(VALID_REMINDER_DESC_TWO).build();
        expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "2 Chloelim", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditReminderCommand.MESSAGE_USAGE));
    }
}
