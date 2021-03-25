package seedu.address.model.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.trip.TripDay;
import seedu.address.model.trip.TripTime;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static final Optional<Price> NO_PRICE = Optional.empty();
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Prevents SampleDataUtil from being instantiated.
     */
    private SampleDataUtil() {}

    public static Passenger[] getSamplePassengers() {

        return new Passenger[] {
            new Passenger(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new TripDay(DayOfWeek.valueOf("MONDAY")), new TripTime(LocalTime.parse("1400", timeFormat)),
                NO_PRICE,
                getTagSet("friends")),
            new Passenger(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new TripDay(DayOfWeek.valueOf("WEDNESDAY")), new TripTime(LocalTime.parse("2130", timeFormat)),
                NO_PRICE,
                getTagSet("colleagues", "friends")),
            new Passenger(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new TripDay(DayOfWeek.valueOf("THURSDAY")), new TripTime(LocalTime.parse("0845", timeFormat)),
                NO_PRICE,
                getTagSet("neighbours")),
            new Passenger(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new TripDay(DayOfWeek.valueOf("FRIDAY")), new TripTime(LocalTime.parse("1305", timeFormat)),
                NO_PRICE,
                getTagSet("family")),
            new Passenger(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new TripDay(DayOfWeek.valueOf("TUESDAY")), new TripTime(LocalTime.parse("1930", timeFormat)),
                NO_PRICE,
                getTagSet("classmates")),
            new Passenger(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new TripDay(DayOfWeek.valueOf("MONDAY")), new TripTime(LocalTime.parse("0915", timeFormat)),
                NO_PRICE,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Passenger samplePassenger : getSamplePassengers()) {
            sampleAb.addPassenger(samplePassenger);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
