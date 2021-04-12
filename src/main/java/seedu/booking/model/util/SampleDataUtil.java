package seedu.booking.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * Contains utility methods for populating {@code BookingSystem} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new HashSet<Tag>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new HashSet<Tag>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new HashSet<Tag>()),
            new Person(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new HashSet<Tag>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new HashSet<Tag>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), new HashSet<Tag>())
        };
    }

    public static Venue[] getSampleVenues() {
        return new Venue[] {
            new Venue(new VenueName("Sports Hall"), new Capacity(50),
                   "Open to only students and staff.", new HashSet<Tag>()),
            new Venue(new VenueName("Dining Hall"), new Capacity(30),
                    "Open 24 hours.", new HashSet<Tag>()),
            new Venue(new VenueName("Lecture Hall"), new Capacity(70),
                    "Open to only students and staff.", new HashSet<Tag>())
        };
    }

    public static Booking[] getSampleBookings() {
        return new Booking[] {
            new Booking(new Email("alexyeoh@example.com"), new VenueName("Sports Hall"),
                    new Description("For badminton competition."),
                    new StartTime(LocalDateTime.of(2021, 04, 16, 15, 30)),
                    new EndTime(LocalDateTime.of(2021, 04, 16, 18, 30)),
                    new HashSet<Tag>()),
            new Booking(new Email("berniceyu@example.com"), new VenueName("Lecture Hall"),
                    new Description("For CS2103 Project Meeting."),
                    new StartTime(LocalDateTime.of(2021, 04, 12, 07, 30)),
                    new EndTime(LocalDateTime.of(2021, 04, 16, 10, 30)),
                    new HashSet<Tag>())
        };
    }

    public static ReadOnlyBookingSystem getSampleBookingSystem() {
        BookingSystem sampleAb = new BookingSystem();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Venue sampleVenue : getSampleVenues()) {
            sampleAb.addVenue(sampleVenue);
        }
        for (Booking sampleBooking : getSampleBookings()) {
            sampleAb.addBooking(sampleBooking);
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
