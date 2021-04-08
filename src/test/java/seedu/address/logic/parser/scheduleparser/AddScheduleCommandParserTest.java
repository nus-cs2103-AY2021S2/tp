package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_FROM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_TO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_FROM_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_TO_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TIME_FROM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TIME_TO_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedulecommands.AddScheduleCommand;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.schedule.Schedule;

public class AddScheduleCommandParserTest {

    private final AddScheduleCommandParser parser = new AddScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Schedule expectedSchedule = MATHS_HOMEWORK_SCHEDULE;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));

        // multiple title - last title accepted
        assertParseSuccess(parser, TITLE_DESC_TWO + TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));

        // multiple date - last date accepted
        assertParseSuccess(parser, TITLE_DESC_ONE + DATE_DESC_TWO + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));

        // multiple time_from - last time_from accepted
        assertParseSuccess(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_TWO + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));

        // multiple time_to - last time_to accepted
        assertParseSuccess(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_TWO + TIME_TO_DESC_ONE + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));

        // multiple desc - last desc accepted
        assertParseSuccess(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_TWO + DESC_DESC_ONE, new AddScheduleCommand(expectedSchedule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_SCHEDULE_TITLE_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, TITLE_DESC_ONE + VALID_SCHEDULE_DATE_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + VALID_SCHEDULE_TIME_FROM_ONE
                + TIME_TO_DESC_ONE + DESC_DESC_ONE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + VALID_SCHEDULE_TIME_TO_ONE + DESC_DESC_ONE, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                + TIME_TO_DESC_ONE + VALID_SCHEDULE_DESCRIPTION_ONE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SCHEDULE_TITLE_ONE + VALID_SCHEDULE_DATE_ONE + VALID_SCHEDULE_TIME_FROM_ONE
                + VALID_SCHEDULE_TIME_TO_ONE + VALID_SCHEDULE_DESCRIPTION_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                        + TIME_TO_DESC_ONE + DESC_DESC_ONE,
                Title.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, TITLE_DESC_ONE + INVALID_DATE_DESC + TIME_FROM_DESC_ONE
                        + TIME_TO_DESC_ONE + DESC_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS);

        // invalid time_from
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + INVALID_TIME_FROM_DESC
                        + TIME_TO_DESC_ONE + DESC_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS);

        // invalid time_to
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                        + INVALID_TIME_TO_DESC + DESC_DESC_ONE,
                AppointmentDateTime.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                        + TIME_TO_DESC_ONE + INVALID_DESC_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                        + TIME_TO_DESC_ONE + INVALID_DESC_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_ONE + DATE_DESC_ONE + TIME_FROM_DESC_ONE
                        + TIME_TO_DESC_ONE + DESC_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "invalid input",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
    }
}
