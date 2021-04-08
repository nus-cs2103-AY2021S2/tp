package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_DATE_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_FROM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_TO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_THREE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_TO_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_TO_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_TWO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE);

    private EditScheduleCommandParser parser = new EditScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_SCHEDULE_TITLE_ONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditScheduleCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_DESC_DESC, Description.MESSAGE_CONSTRAINTS); // invalid desc
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + DATE_DESC_ONE + INVALID_TIME_FROM_DESC + TIME_TO_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS); // invalid time_from
        assertParseFailure(parser, "1" + DATE_DESC_ONE + TIME_FROM_DESC_ONE + INVALID_TIME_TO_DESC,
                AppointmentDateTime.MESSAGE_CONSTRAINTS); // invalid time_to

        // invalid title followed by valid desc
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + DESC_DESC_ONE, Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid desc
        assertParseFailure(parser, "1" + TITLE_DESC_ONE + INVALID_DESC_DESC, Description.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE + INVALID_TIME_TO_DESC,
                AppointmentDateTime.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_DESC_DESC + INVALID_DATE_DESC + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DATE_DESC + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingDateFields_failure() {
        assertParseFailure(parser, "1" + DATE_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);
        assertParseFailure(parser, "1" + TIME_FROM_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);
        assertParseFailure(parser, "1" + TIME_TO_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);

        assertParseFailure(parser, "1" + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);
        assertParseFailure(parser, "1" + TIME_FROM_DESC_ONE + DATE_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);
        assertParseFailure(parser, "1" + DATE_DESC_ONE + TIME_TO_DESC_ONE, MESSAGE_MISSING_DATE_FIELD);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_TWO + DESC_DESC_TWO
                + DATE_DESC_TWO + TIME_FROM_DESC_TWO + TIME_TO_DESC_TWO;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_TWO)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_TWO).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_TWO + DESC_DESC_TWO;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_TWO)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_THREE;
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_THREE)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESC_DESC_THREE;
        descriptor = new EditScheduleDescriptorBuilder().withDescription(VALID_SCHEDULE_DESCRIPTION_THREE).build();
        expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timeFrom timeTo
        userInput = targetIndex.getOneBased() + DATE_DESC_THREE + TIME_FROM_DESC_THREE + TIME_TO_DESC_THREE;
        descriptor = new EditScheduleDescriptorBuilder().withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_THREE)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_THREE).build();
        expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_ONE + DESC_DESC_ONE + DATE_DESC_ONE
                + TIME_FROM_DESC_ONE + TIME_TO_DESC_ONE + TITLE_DESC_TWO + DESC_DESC_TWO + DATE_DESC_TWO
                + TIME_FROM_DESC_TWO + TIME_TO_DESC_TWO;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_TWO)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_TWO)
                .build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + TITLE_DESC_TWO;
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withTitle(VALID_SCHEDULE_TITLE_TWO).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TITLE_DESC_TWO + INVALID_DESC_DESC + DESC_DESC_TWO;
        descriptor = new EditScheduleDescriptorBuilder().withTitle(VALID_SCHEDULE_TITLE_TWO)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO)
                .build();
        expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "2 Chloelim", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE));
    }
}
