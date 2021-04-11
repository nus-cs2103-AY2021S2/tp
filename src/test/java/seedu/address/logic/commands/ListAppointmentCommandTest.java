package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalModelManager.getTypicalModelManager;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

public class ListAppointmentCommandTest {
    private Model model = getTypicalModelManager();
    private Model expectedModel = getTypicalModelManager();

    @Test
    public void listAppointmentTest() {
        //clear appointment list
        model.updateFilteredAppointmentList(a -> false);

        assertCommandSuccess(new ListAppointmentCommand(),
                model, ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
    }
}
