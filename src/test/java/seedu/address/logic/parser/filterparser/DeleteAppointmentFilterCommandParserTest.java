package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.DeleteAppointmentFilterCommand;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.testutil.TypicalFilters;

public class DeleteAppointmentFilterCommandParserTest {
    private DeleteAppointmentFilterCommandParser parser = new DeleteAppointmentFilterCommandParser();

    @Test
    public void test() {
        AppointmentFilter appointmentFilter = TypicalFilters.getMathFilter();
        DeleteAppointmentFilterCommand deleteAppointmentFilterCommand = new DeleteAppointmentFilterCommand(
                appointmentFilter);

        assertParseSuccess(
                parser,
                " n/Alice Tan s/Mathematics fr/2021-05-24 10:00AM to/2021-05-24 12:00PM l/Jurong West",
                deleteAppointmentFilterCommand);
    }
}
