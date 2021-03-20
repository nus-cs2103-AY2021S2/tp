package seedu.booking.model.util;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

/**
 * Contains utility methods for populating {@code BookingSystem} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"))
        };
    }

    public static ReadOnlyBookingSystem getSampleBookingSystem() {
        BookingSystem sampleAb = new BookingSystem();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }


}
