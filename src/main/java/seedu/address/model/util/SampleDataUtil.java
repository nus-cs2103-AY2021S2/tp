package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getSubjectList(),
                getTagSet("mathematics")),
            new Person(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getSubjectList(),
                getTagSet("english")),
            new Person(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getSubjectList(),
                getTagSet("english", "literature")),
            new Person(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getSubjectList(),
                getTagSet("science")),
            new Person(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getSubjectList(),
                getTagSet("geography")),
            new Person(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getSubjectList(),
                getTagSet("history"))
        };
    }

    public static Appointment[] getSampleAppointment() {
        return new Appointment[] {
            new Appointment(new Email("alexyeoh@example.com"), new SubjectName("Mathematics"),
                    new AppointmentDateTime("2021-03-24 2:00PM"), new Address("Geylang")),
            new Appointment(new Email("berniceyu@example.com"), new SubjectName("Science"),
                    new AppointmentDateTime("2021-03-27 3:00PM"), new Address("Hougang"))
        };
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    // TODO: Create sample SubjectList
    public static SubjectList getSubjectList() {
        SubjectList subjectList = new SubjectList();
        return subjectList;
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment samplePerson : getSampleAppointment()) {
            sampleAb.addAppointment(samplePerson);
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
