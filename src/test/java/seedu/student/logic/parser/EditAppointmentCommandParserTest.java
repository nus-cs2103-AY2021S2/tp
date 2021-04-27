package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.commands.CommandTestUtil.DATE_DESC_AMY_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.DATE_DESC_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_MATRIC_DESC;
import static seedu.student.logic.commands.CommandTestUtil.START_TIME_DESC_AMY_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.START_TIME_DESC_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_DATE_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_START_TIME_BOB_APPOINTMENT;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.student.testutil.TypicalAppointments.BOB_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.EditAppointmentCommand;
import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.testutil.EditAppointmentDescriptorBuilder;

class EditAppointmentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        MatriculationNumber matriculationNumber = new MatriculationNumber(VALID_MATRIC_BOB);
        EditAppointmentDescriptor expectedAppointment = new EditAppointmentDescriptorBuilder(BOB_APPOINTMENT).build();

        // multiple dates - last date accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + DATE_DESC_AMY_APPOINTMENT + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_BOB_APPOINTMENT,
                new EditAppointmentCommand(matriculationNumber, expectedAppointment));

        // multiple start times  - last start time accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                        + START_TIME_DESC_AMY_APPOINTMENT + START_TIME_DESC_BOB_APPOINTMENT,
                new EditAppointmentCommand(matriculationNumber, expectedAppointment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing date prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + VALID_DATE_BOB_APPOINTMENT
                + START_TIME_DESC_BOB_APPOINTMENT, MESSAGE_INVALID_FORMAT);

        // missing start time prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + DATE_DESC_BOB_APPOINTMENT
                + VALID_START_TIME_BOB_APPOINTMENT, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, VALID_MATRIC_BOB + VALID_DATE_BOB_APPOINTMENT
                + VALID_START_TIME_BOB_APPOINTMENT, MESSAGE_INVALID_FORMAT);
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
