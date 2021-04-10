package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.ProgramOccursOnDatePredicate;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ScheduleCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model.updateFilteredEntityList(x -> x.getValue() instanceof Program);
    }

    @Test
    public void execute_emptyArguments_success() {
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(new ProgramOccursOnDatePredicate(LocalDate.now())));
        assertCommandSuccess(new ScheduleCommand(LocalDate.now()), model,
               ScheduleCommand.MESSAGE_SUCCESS_NO_SCHEDULE, expectedModel);
    }

    @Test
    public void execute_validArgumentNoProgram_success() {
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(
                new ProgramOccursOnDatePredicate(LocalDate.of(2021, 1, 1))));
        assertCommandSuccess(new ScheduleCommand(LocalDate.of(2021, 1, 1)), model,
                ScheduleCommand.MESSAGE_SUCCESS_NO_SCHEDULE, expectedModel);
    }

    @Test
    public void execute_validArgumentHaveProgram_success() {
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(
                new ProgramOccursOnDatePredicate(LocalDate.of(2021, 10, 10))));
        assertCommandSuccess(new ScheduleCommand(LocalDate.of(2021, 10, 10)), model,
                ScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_invalidArgument_success() {
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(
                new ProgramOccursOnDatePredicate(LocalDate.of(2021, 10, 10))));
        assertCommandSuccess(new ScheduleCommand(LocalDate.of(2021, 10, 10)), model,
                ScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
