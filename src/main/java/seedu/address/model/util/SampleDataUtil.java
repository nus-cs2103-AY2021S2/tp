package seedu.address.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        List<Session> alexSessionList = new ArrayList<>();
        alexSessionList.add(new RecurringSession(new SessionDate("2021-03-10T12:00"), new Duration("90"),
            new Subject("Math"), new Fee("40.0"), new Interval("7"),
            new SessionDate("2021-04-07T12:00")));

        List<Session> berniceSessionList = new ArrayList<>();
        berniceSessionList.add(new Session(new SessionDate("2021-02-01T12:00"),
            new Duration("120"),
            new Subject("Math"), new Fee("23.4")));
        berniceSessionList.add(new RecurringSession(new SessionDate("2021-01-25T12:00"),
            new Duration("90"),
            new Subject("Math"), new Fee("31.3"), new Interval("7"),
            new SessionDate("2021-02-22T12:00")));

        List<Session> davidSessionList = new ArrayList<>();
        davidSessionList.add(new Session(new SessionDate("2021-04-01T12:00"),
            new Duration("90"),
            new Subject("Science"), new Fee("24.4")));

        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), "Primary 2", new Phone("87419031"),
                "Father", alexSessionList),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), "Secondary 4", new Phone("90128102"),
                "Mother", berniceSessionList),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), "Junior College 2", new Phone("91109117"),
                "Father"),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), "University CS1101S",
                new Phone("88519021"), "Mother", davidSessionList),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), "Primary 5", new Phone("95651000"),
                "Father"),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), "Primary 4", new Phone("89651021"),
                "Father")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

}
