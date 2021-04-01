package seedu.address.logic.parser.appointmentparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.EditAppointmentCommand;

public class EditAppointmentCommandParserTest {

    private EditAppointmentCommandParser editAppointmentCommandParserTest =
            new EditAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(editAppointmentCommandParserTest, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(editAppointmentCommandParserTest, "2 Chloelim",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditAppointmentCommand.MESSAGE_USAGE));
    }


}
