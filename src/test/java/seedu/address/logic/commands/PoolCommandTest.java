package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_EVENING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDrivers.DRIVER_ALICE;
import static seedu.address.testutil.TypicalDrivers.DRIVER_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBookPassengers;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.pool.Pool;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.PoolBuilder;

public class PoolCommandTest {

    private final Driver driver = new DriverBuilder().build();
    private final Set<Index> commuters = new CommuterBuilder().build();
    private final TripDay tripDay = new TripDay(VALID_TRIPDAY_FRIDAY);
    private final TripTime tripTimeMorning = new TripTime(VALID_TRIPTIME_MORNING);
    private final TripTime tripTimeEvening = new TripTime(VALID_TRIPTIME_EVENING);
    private final Set<Tag> tags = SampleDataUtil.getTagSet(VALID_TAG_IT);

    private final Model model = new ModelManager(getTypicalAddressBookPassengers(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PoolCommand(null, null, null,
            null, null));
    }

    @Test
    public void execute_poolAcceptedByModel_addSuccessfulWithWarning() throws Exception {
        Pool validPool = new PoolBuilder().withModel(model).withIndex(INDEX_FIRST)
                .withIndex(INDEX_SECOND).withTripDay(VALID_TRIPDAY_FRIDAY).withTags(VALID_TAG_IT).build();

        CommandResult commandResult = new PoolCommand(driver, commuters, tripDay, tripTimeMorning, tags).execute(model);

        String driverName = validPool.getDriverAsStr();
        String passengerNames = validPool.getPassengerNames();

        assertEquals(String.format(PoolCommand.MESSAGE_POOL_SUCCESS_WITH_WARNING, driverName, passengerNames),
                commandResult.getFeedbackToUser());
        assertTrue(model.hasPool(validPool));
    }

    @Test
    public void execute_poolAcceptedByModel_addSuccessfulNoWarning() throws Exception {
        Pool validPool = new PoolBuilder().withModel(model).withIndex(INDEX_FIRST)
                .withIndex(INDEX_SECOND).withTripDay(VALID_TRIPDAY_FRIDAY)
                .withTripTime(VALID_TRIPTIME_EVENING).withTags(VALID_TAG_IT).build();

        CommandResult commandResult = new PoolCommand(driver, commuters, tripDay, tripTimeEvening, tags).execute(model);

        String driverName = validPool.getDriverAsStr();
        String passengerNames = validPool.getPassengerNames();

        assertEquals(String.format(PoolCommand.MESSAGE_POOL_SUCCESS, driverName, passengerNames),
                commandResult.getFeedbackToUser());
        assertTrue(model.hasPool(validPool));
    }

    @Test
    public void execute_duplicatePool_throwsCommandException() {
        Pool duplicatePool = new PoolBuilder().withModel(model).withIndex(INDEX_FIRST).withIndex(INDEX_SECOND)
                .withTags(VALID_TAG_IT).withTripTime(VALID_TRIPTIME_EVENING)
                .withTripDay(VALID_TRIPDAY_FRIDAY).build();

        model.addPool(duplicatePool);

        PoolCommand poolCommand = new PoolCommand(driver, commuters, tripDay, tripTimeEvening, tags);

        assertThrows(CommandException.class,
                PoolCommand.MESSAGE_DUPLICATE_POOL, () -> poolCommand.execute(model)
        );
    }

    @Test
    public void execute_tripdayMismatch_throwsCommandException() {
        final TripDay mismatchedTripDay = new TripDay(VALID_TRIPDAY_MONDAY);

        PoolCommand poolCommand = new PoolCommand(driver, commuters, mismatchedTripDay, tripTimeEvening, tags);
        assertThrows(CommandException.class,
                PoolCommand.MESSAGE_TRIPDAY_MISMATCH, () -> poolCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        PoolCommand poolAliceDrivingCommand = new PoolCommand(DRIVER_ALICE, commuters, tripDay, tripTimeEvening, tags);
        PoolCommand poolBobDrivingCommand = new PoolCommand(DRIVER_BOB, commuters, tripDay, tripTimeEvening, tags);

        // same object -> returns true
        assertTrue(poolAliceDrivingCommand.equals(poolAliceDrivingCommand));

        // same values -> returns true
        PoolCommand poolAliceDrivingCommandCopy = new PoolCommand(DRIVER_ALICE, commuters, tripDay, tripTimeEvening,
                tags);
        assertTrue(poolAliceDrivingCommand.equals(poolAliceDrivingCommandCopy));

        // different types -> returns false
        assertFalse(poolAliceDrivingCommand.equals(1));

        // null -> returns false
        assertFalse(poolAliceDrivingCommand.equals(null));

        // different passenger -> returns false
        assertFalse(poolAliceDrivingCommand.equals(poolBobDrivingCommand));
    }
}
