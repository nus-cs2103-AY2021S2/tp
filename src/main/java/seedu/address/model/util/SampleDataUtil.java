package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Review;
import seedu.address.model.person.Rating;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Rating("1"), new Review("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("FastFood")),
            new Person(new Name("Bernice Yu"), new Rating("2"), new Review("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("Dessert", "FastFood")),
            new Person(new Name("Charlotte Oliveiro"), new Rating("3"), new Review("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("Vegan")),
            new Person(new Name("David Li"), new Rating("5"), new Review("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("Western")),
            new Person(new Name("Irfan Ibrahim"), new Rating("4"), new Review("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("Indian")),
            new Person(new Name("Roy Balakrishnan"), new Rating("2"), new Review("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("Malay"))
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
