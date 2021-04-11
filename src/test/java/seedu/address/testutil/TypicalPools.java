package seedu.address.testutil;

import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BENSON;
import static seedu.address.testutil.TypicalPassengers.CARL;
import static seedu.address.testutil.TypicalPassengers.DANIEL;
import static seedu.address.testutil.TypicalPassengers.ELLE;
import static seedu.address.testutil.TypicalPassengers.FIONA;
import static seedu.address.testutil.TypicalPassengers.GEORGE;
import static seedu.address.testutil.TypicalPassengers.HILARY;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

/**
 * A utility class containing a list of {@code Pool} objects to be used in tests.
 */
public class TypicalPools {

    public static final List<Passenger> HOMEPOOL_PASSENGERS = new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
    public static final List<Passenger> OFFICEPOOL_PASSENGERS = new ArrayList<>(Arrays.asList(DANIEL, ELLE, FIONA));
    public static final List<Passenger> WORKPOOL_PASSENGERS = new ArrayList<>(Arrays.asList(ALICE, GEORGE, HILARY));

    public static final Pool HOMEPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.DRIVER_ALICE)
            .withTripDay(DayOfWeek.FRIDAY)
            .withTripTime(LocalTime.of(18, 0))
            .withPassengers(HOMEPOOL_PASSENGERS)
            .withTags("marketing")
            .build();

    public static final Pool OFFICEPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.DRIVER_ALICE)
            .withTripDay(DayOfWeek.MONDAY)
            .withTripTime(LocalTime.of(6, 30))
            .withPassengers(OFFICEPOOL_PASSENGERS)
            .withTags("sales", "marketing").build();

    public static final Pool WORKPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.DRIVER_BOB)
            .withTripDay(DayOfWeek.WEDNESDAY)
            .withTripTime(LocalTime.of(14, 0))
            .withPassengers(WORKPOOL_PASSENGERS)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPools() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pools.
     */
    public static AddressBook getTypicalAddressBookPools() {
        AddressBook ab = new AddressBook();
        for (Pool pool : getTypicalPools()) {
            ab.addPool(pool);
        }
        return ab;
    }

    public static List<Pool> getTypicalPools() {
        return new ArrayList<>(Arrays.asList(HOMEPOOL, OFFICEPOOL, WORKPOOL));
    }
}
