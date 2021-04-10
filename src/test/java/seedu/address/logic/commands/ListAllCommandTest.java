package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalModelManager.getTypicalModelManager;
import static seedu.address.testutil.TypicalProperties.getTypicalProperties;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

class ListAllCommandTest {
    private Model model = getTypicalModelManager();
    private Model expectedModel = getTypicalModelManager();

    @Test
    public void listAllTest() {
        //clear all
        model.updateFilteredAppointmentList(a -> false);
        model.updateFilteredPropertyList(a -> false);

        assertCommandSuccess(new ListAllCommand(),
                model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
        assertEquals(getTypicalProperties(), model.getFilteredPropertyList());
    }
}
