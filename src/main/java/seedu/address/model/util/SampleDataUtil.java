package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.util.DateTimeUtil.DATETIME_FORMAT;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTutorSubjectList(),
                getTagSet("mathematics")),
            new Person(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTutorSubjectList(),
                getTagSet("english")),
            new Person(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTutorSubjectList(),
                getTagSet("english", "literature")),
            new Person(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTutorSubjectList(),
                getTagSet("science")),
            new Person(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTutorSubjectList(),
                getTagSet("geography")),
            new Person(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTutorSubjectList(),
                getTagSet("history"))
        };
    }

    public static Appointment[] getSampleAppointment() {
        return new Appointment[] {
            new Appointment(new Email("alexyeoh@example.com"), new SubjectName("Mathematics"),
                    new AppointmentDateTime("2020-02-24 14:00"), new Address("Geylang")),
            new Appointment(new Email("bernice@example.com"), new SubjectName("Science"),
                    new AppointmentDateTime("2020-02-27 15:00"), new Address("Hougang"))
        };
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment samplePerson : getSampleAppointment()) {
            sampleAb.addAppointment(samplePerson);
        }
        return sampleAb;
    }

    // TODO: Create sample Tutor Subject data
    public static List<TutorSubject> getTutorSubjectList() {
        List<TutorSubject> tutorSubjects = new ArrayList<>();
        return tutorSubjects;
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
