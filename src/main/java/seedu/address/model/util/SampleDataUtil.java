package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Remark EMPTY_REMARK = new Remark("");
    private static final Set<Tag> EMPTY_TAG = new TreeSet<>();

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Company("Amazon"), new JobTitle("Network Administrator"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK, getTagSet("Network")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Company("Amazon"), new JobTitle("Junior Software Engineer"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Remark("On leave from 01/04/21-05/04/21"), getTagSet("Photoshop")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Company("Amazon"), new JobTitle("HR Manager"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Remark("Unavailable from 2pm-3pm"), EMPTY_TAG),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Company("Amazon"), new JobTitle("Database Administrator"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK, EMPTY_TAG),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Company("Amazon"), new JobTitle("Senior Software Engineer"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Remark ("Emergency tech support contact"), getTagSet("Photoshop", "Illustrator")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Company("Amazon"), new JobTitle("Software Engineer Intern"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Remark("Studying Computer Science in NUS"), getTagSet("Java", "Python", "C")),
            new Person(new Name("Samuel Lim"), new Phone("94318630"), new Email("samuellim@example.com"),
                    new Company("Google"), new JobTitle("Senior Software Engineer"),
                    new Address("25 Crowhurst Drive"), EMPTY_REMARK, EMPTY_TAG),
            new Person(new Name("John Tan"), new Phone("87243350"), new Email("johntan@example.com"),
                    new Company("Facebook"), new JobTitle("Managing Director"),
                    new Address("88 Watten Terrace"), EMPTY_REMARK, EMPTY_TAG)
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

}
