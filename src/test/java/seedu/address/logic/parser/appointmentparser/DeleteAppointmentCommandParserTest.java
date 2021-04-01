package seedu.address.logic.parser.appointmentparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAppointmentCommandParserTest {

    private DeleteAppointmentCommandParser parser =
            new DeleteAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroArg_throwsParseExceptinon() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeArg_throwsParseExceptinon() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_validArg_returnsDeleteAppointmentCommand() throws ParseException {
        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(Index.fromOneBased(1));

        assertParseSuccess(parser, "1", deleteAppointmentCommand);
    }

}
