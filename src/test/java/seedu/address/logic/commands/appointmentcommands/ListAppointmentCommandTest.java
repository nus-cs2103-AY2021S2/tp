package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.ModelManagerBuilder.ModelType.APPOINTMENTBOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
<<<<<<< HEAD
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;
=======
>>>>>>> ea5229aed0439b3402fac8ee538d20297d6b4b00

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAppointmentCommand.
 */
public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
<<<<<<< HEAD
        model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook());
        expectedModel = new ModelManager(model.getTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(), model.getGradeBook());

=======
        model = ModelManagerBuilder.getModelManager();
        expectedModel = ModelManagerBuilder.getModelManager(model, APPOINTMENTBOOK);
>>>>>>> ea5229aed0439b3402fac8ee538d20297d6b4b00
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
