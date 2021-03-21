package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new School("Abc Secondary School"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Name("Jane Yeoh"), new Phone("87438800"),
                getTagSet("math"), getLessonSet("monday 2000")),
            new Person(new Name("Bernice Yu"), new School("Xyz Secondary School"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Name("Ben Yu"), new Phone("99272758"),
                getTagSet("sec4", "physics"), getLessonSet(" monday 1800")),
            new Person(new Name("Charlotte Oliveiro"), new School("Cde Secondary School"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Name("Claire Oliveiro"), new Phone("93210288"),
                getTagSet("classA"), getLessonSet("monday 1500")),
            new Person(new Name("David Li"), new School("Li Secondary School"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Name("Li Li"), new Phone("91031288"), getTagSet("sec1"),
                getLessonSet("tuesday 1000")),
            new Person(new Name("Irfan Ibrahim"), new School("Efg Secondary School"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                new Name("Frank Ibrahim"), new Phone("92492022"),
                getTagSet("sec2"), getLessonSet("wednesday 1400")),
            new Person(new Name("Roy Balakrishnan"), new School("Efg Secondary School"),
                new Phone("92624417"), new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Name("Bob Balakrishnan"), new Phone("92624411"),
                getTagSet("physics"), getLessonSet("wednesday 1200"))
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
     * Returns a lesson set containing the list of strings given.
     */
    public static Set<Lesson> getLessonSet(String... strings) {
        return Arrays.stream(strings)
                .map(Lesson::new)
                .collect(Collectors.toSet());
    }

}
