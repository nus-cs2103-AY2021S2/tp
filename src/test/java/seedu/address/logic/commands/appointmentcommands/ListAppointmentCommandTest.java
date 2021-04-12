package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.APPOINTMENTBOOK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAppointmentCommand.
 */
public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
        expectedModel = TypicalModel.getTypicalModel(model, APPOINTMENTBOOK);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model, String.format(ListAppointmentCommand.MESSAGE_SUCCESS,
                model.getFilteredAppointmentList().size()), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListAppointmentCommand(), model, String.format(ListAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel.getFilteredAppointmentList().size()), expectedModel);
    }
}
