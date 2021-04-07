package seedu.address.logic.parser.appointmentparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.model.appointment.DateViewPredicate;

public class ViewAppointmentCommandParserTest {

    private ViewAppointmentCommandParser parser = new ViewAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "2021-05-24",
                new ViewAppointmentCommand(new DateViewPredicate(APPOINTMENT_FIRST_DATE)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewAppointmentCommand.MESSAGE_USAGE));
    }
}
