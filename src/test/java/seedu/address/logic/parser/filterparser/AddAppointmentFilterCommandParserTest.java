package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.AddAppointmentFilterCommand;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.testutil.TypicalFilters;

public class AddAppointmentFilterCommandParserTest {
    private AddAppointmentFilterCommandParser parser = new AddAppointmentFilterCommandParser();

    @Test
    public void test() {
        AppointmentFilter appointmentFilter = TypicalFilters.getMathFilter();
        AddAppointmentFilterCommand addAppointmentFilterCommand = new AddAppointmentFilterCommand(
                appointmentFilter);

        assertParseSuccess(
                parser,
                " n/Alice Tan s/Mathematics fr/2021-05-24 10:00AM to/2021-05-24 12:00PM l/Jurong West",
                addAppointmentFilterCommand);
    }
}
