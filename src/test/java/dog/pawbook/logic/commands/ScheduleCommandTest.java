package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.ProgramOccursOnDatePredicate;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.testutil.ProgramBuilder;

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

        // Credit: https://www.baeldung.com/java-override-system-time
        Clock clock = Clock.fixed(Instant.parse("2022-01-01T00:00:00Z"), ZoneId.of("UTC"));
        LocalDate testDate = LocalDate.now(clock);

        // Test no programs success
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(new ProgramOccursOnDatePredicate(testDate)));
        assertCommandSuccess(new ScheduleCommand(LocalDate.of(2022, 01, 01)), model,
               ScheduleCommand.MESSAGE_SUCCESS_NO_SCHEDULE, expectedModel);

        // Test one program success
        Program program1 = new ProgramBuilder().withName(VALID_NAME_OBEDIENCE_TRAINING)
                .withSessions(VALID_SESSION_OBEDIENCE_TRAINING).withTags(VALID_TAG_ALL).build();

        model.addEntity(program1);
        expectedModel.addEntity(program1);

        assertCommandSuccess(new ScheduleCommand(testDate), model,
                ScheduleCommand.MESSAGE_SUCCESS, expectedModel);
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
    public void execute_nullArgument_failure() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null));
    }
}
