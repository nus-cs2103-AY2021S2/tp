package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.commands.CommandTestUtil.DATE_DESC_AMY_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.DATE_DESC_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_MATRIC_DESC;
import static seedu.student.logic.commands.CommandTestUtil.MATRIC_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.student.logic.commands.CommandTestUtil.START_TIME_DESC_AMY_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.START_TIME_DESC_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_DATE_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_START_TIME_BOB_APPOINTMENT;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.student.testutil.TypicalAppointments.BOB_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.AddAppointmentCommand;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(BOB_APPOINTMENT).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_BOB_APPOINTMENT,
                new AddAppointmentCommand(expectedAppointment));

        // multiple dates - last date accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + DATE_DESC_AMY_APPOINTMENT + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_BOB_APPOINTMENT,
                new AddAppointmentCommand(expectedAppointment));

        // multiple start times  - last start ime accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_AMY_APPOINTMENT + START_TIME_DESC_BOB_APPOINTMENT,
                new AddAppointmentCommand(expectedAppointment));

        // multiple end times  - last end time accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                    + START_TIME_DESC_BOB_APPOINTMENT,
            new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // extra matriculation number prefix
        assertParseFailure(parser, MATRIC_DESC_BOB + DATE_DESC_BOB_APPOINTMENT
                + START_TIME_DESC_BOB_APPOINTMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MatriculationNumber.MESSAGE_CONSTRAINTS));

        // missing date prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + VALID_DATE_BOB_APPOINTMENT
                + START_TIME_DESC_BOB_APPOINTMENT, expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                + VALID_START_TIME_BOB_APPOINTMENT, expectedMessage);

        // matriculation number prefix extra, all other prefixes missing
        assertParseFailure(parser, MATRIC_DESC_BOB + VALID_DATE_BOB_APPOINTMENT
                + VALID_START_TIME_BOB_APPOINTMENT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid matriculation number
        assertParseFailure(parser, INVALID_MATRIC_DESC + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_BOB_APPOINTMENT,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatriculationNumber.MESSAGE_CONSTRAINTS));
    }
}
