package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Colour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Size("34"), new Colour("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getDescriptionSet("friends")),
            new Person(new Name("Bernice Yu"), new Size("29"), new Colour("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getDescriptionSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Size("26"), new Colour("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getDescriptionSet("neighbours")),
            new Person(new Name("David Li"), new Size("43"), new Colour("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getDescriptionSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Size("44"), new Colour("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getDescriptionSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Size("35"), new Colour("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getDescriptionSet("colleagues"))
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
     * Returns a description set containing the list of strings given.
     */
    public static Set<Description> getDescriptionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Description::new)
                .collect(Collectors.toSet());
    }

}
