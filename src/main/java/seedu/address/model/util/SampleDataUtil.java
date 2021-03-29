package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Address;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Name;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TimeAdded;
import seedu.address.model.tag.ChildTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new TimeAdded("2021-03-21 06:55:40.11")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new TimeAdded("2021-03-21 06:55:43.11")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new TimeAdded("2021-03-21 06:55:41.11")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new TimeAdded("2021-03-21 06:55:42.11")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new TimeAdded("2021-03-21 06:55:45.11")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new TimeAdded("2021-03-21 06:55:44.11"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    /**
     * Returns a ChildTag set containing the list of strings given.
     */
    public static Set<Tag> getChildTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(ChildTag::new)
                .collect(Collectors.toSet());
    }


    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(new Name("Parent teacher meeting 1"), new Address("Child 1's school"),
                    new DateTime("21/03/2021 10:00"), getPersonSet(), getChildTagSet("child1")),
            new Appointment(new Name("Parent teacher meeting 2"), new Address("Child 2's school"),
                    new DateTime("03/10/2021 14:00"), getPersonSet(), getChildTagSet("child2")),
            new Appointment(new Name("Parent teacher meeting 3"), new Address("Child 3's school"),
                    new DateTime("02/04/2021 11:00"), getPersonSet(), getChildTagSet("child3"))
        };
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    /**
     * Returns a person set containing the list of strings given.
     */
    public static Set<Person> getPersonSet(String... strings) {
        return Arrays.stream(getSamplePersons())
                .collect(Collectors.toSet());
    }



}
