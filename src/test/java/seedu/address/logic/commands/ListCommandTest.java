package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertiesWithClient;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListAppointmentCommand;
import seedu.address.logic.commands.list.ListPropertyCommand;
import seedu.address.model.Model;
import seedu.address.testutil.TypicalModelManager;

class ListCommandTest {
    private Model model = TypicalModelManager.getTypicalModelManager();
    private Model expectedModel = TypicalModelManager.getTypicalModelManager();

    @Test
    public void listPropertyTest() {
        //clear property list
        model.updateFilteredPropertyList(a -> false);

        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalPropertiesWithClient(), model.getFilteredPropertyList());
    }

    @Test
    public void listAppointmentTest() {
        //clear appointment list
        model.updateFilteredAppointmentList(a -> false);

        assertCommandSuccess(new ListAppointmentCommand(),
            model, ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
    }

    @Test
    public void listAllTest() {
        //clear all
        model.updateFilteredAppointmentList(a -> false);
        model.updateFilteredPropertyList(a -> false);

        assertCommandSuccess(new ListAllCommand(),
                model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
        assertEquals(getTypicalPropertiesWithClient(), model.getFilteredPropertyList());
    }
}
