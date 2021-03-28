package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.TripDay;
import seedu.address.model.pool.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.PassengerBuilder;
import seedu.address.testutil.PoolBuilder;

public class PoolCommandTest {

    private final Driver driver = new DriverBuilder().build();
    private final Set<Index> commuters = new CommuterBuilder().build();
    private final TripDay tripDay = new TripDay(VALID_TRIPDAY_MONDAY);
    private final TripTime tripTime = new TripTime(VALID_TRIPTIME_MORNING);
    private final Set<Tag> tags = new HashSet<>();

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PoolCommand(null, null, null,
            null, null));
    }


}
