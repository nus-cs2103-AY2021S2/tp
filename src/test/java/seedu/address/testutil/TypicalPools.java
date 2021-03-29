package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.pool.Pool;

/**
 * A utility class containing a list of {@code Pool} objects to be used in tests.
 */
public class TypicalPools {

    public static final Pool HOMEPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.ALICE)
            .withTripDay(DayOfWeek.FRIDAY)
            .withTripTime(LocalTime.of(18, 0))
            .withPassengers(new PassengerSetBuilder().withDefaultPassengers().build())
            .withTags("friends")
            .build();

    public static final Pool OFFICEPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.ALICE)
            .withTripDay(DayOfWeek.MONDAY)
            .withTripTime(LocalTime.of(6, 30))
            .withPassengers(new PassengerSetBuilder().withDefaultPassengers().build())
            .withTags("owesMoney", "friends").build();

    public static final Pool WORKPOOL = new PoolBuilder()
            .withDriver(TypicalDrivers.BOB)
            .withTripDay(DayOfWeek.WEDNESDAY)
            .withTripTime(LocalTime.of(14, 0))
            .withPassengers(new PassengerSetBuilder().withDefaultPassengers().build())
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPools() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pools.
     */
    public static AddressBook getTypicalAddressBook() {
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
