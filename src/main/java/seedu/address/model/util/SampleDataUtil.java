package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.DatesBook;
import seedu.address.model.LessonBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.date.Description;
import seedu.address.model.date.Details;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

/**
 * Contains utility methods for populating TutorsPet with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                Optional.of(new School("Abc Secondary School")),
                Optional.of(new Email("alexyeoh@example.com")),
                Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
                Optional.of(new Name("Jane Yeoh")),
                Optional.of(new Phone("87438800")),
                Optional.of(new Level("sec3")),
                getSubjectSet("math"), getLessonSet("monday 2000")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                Optional.of(new School("Xyz Secondary School")),
                Optional.of(new Email("berniceyu@example.com")),
                Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                Optional.of(new Name("Ben Yu")),
                Optional.of(new Phone("99272758")),
                Optional.of(new Level("sec4")),
                getSubjectSet("math", "phys"), getLessonSet(" monday 1800")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                Optional.of(new School("Cde Secondary School")),
                Optional.of(new Email("charlotte@example.com")),
                Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
                Optional.of(new Name("Claire Oliveiro")),
                Optional.of(new Phone("93210288")),
                Optional.of(new Level("pri6")),
                getSubjectSet("math"), getLessonSet("monday 1500")),
            new Person(new Name("David Li"), new Phone("91031282"),
                Optional.of(new School("Li Secondary School")),
                Optional.of(new Email("lidavid@example.com")),
                Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                Optional.of(new Name("Li Li")),
                Optional.of(new Phone("91031288")),
                Optional.of(new Level("sec3")),
                getSubjectSet("hist"), getLessonSet("tuesday 1000")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                Optional.of(new School("Efg Secondary School")),
                Optional.of(new Email("irfan@example.com")),
                Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")),
                Optional.of(new Name("Frank Ibrahim")),
                Optional.of(new Phone("92492022")),
                Optional.of(new Level("pri5")),
                getSubjectSet("cn"), getLessonSet("wednesday 1400")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                Optional.of(new School("Efg Secondary School")),
                Optional.of(new Email("royb@example.com")),
                Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")),
                Optional.of(new Name("Bob Balakrishnan")),
                Optional.of(new Phone("92624411")),
                Optional.of(new Level("sec4")),
                getSubjectSet("phys"), getLessonSet("wednesday 1200"))
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
     * Returns a subject set containing the list of strings given.
     */
    public static Set<Subject> getSubjectSet(String... strings) {
        return Arrays.stream(strings)
                .map(Subject::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a lesson set containing the list of strings given.
     */
    public static Set<Lesson> getLessonSet(String... strings) {
        return Arrays.stream(strings)
                .map(Lesson::new)
                .collect(Collectors.toSet());
    }

    public static ImportantDate[] getSampleImportantDates() {
        return new ImportantDate[] {
            new ImportantDate(new Description("A-Levels Mathematics Paper 1"), new Details("2021-11-03 0800")),
            new ImportantDate(new Description("A-Levels Mathematics Paper 2"), new Details("2021-11-06 0800"))
        };
    }

    public static ReadOnlyDatesBook getSampleDatesBook() {
        DatesBook sampleDb = new DatesBook();
        for (ImportantDate sampleImportantDate : getSampleImportantDates()) {
            sampleDb.addImportantDate(sampleImportantDate);
        }
        return sampleDb;
    }

    public static ReadOnlyLessonBook getSampleLessonBook() {
        LessonBook sampleLb = new LessonBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleLb.addPersonToLesson(samplePerson);
        }
        return sampleLb;
    }

}
