package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.description.Description;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Size("34"), new Email("alexyeoh@example.com"),
                new DressCode("FORMAL"),
                getDescriptionSet("friends")),
            new Person(new Name("Bernice Yu"), new Size("29"), new Email("berniceyu@example.com"),
                new DressCode("CASUAL"),
                getDescriptionSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Size("26"), new Email("charlotte@example.com"),
                new DressCode("ACTIVE"),
                getDescriptionSet("neighbours")),
            new Person(new Name("David Li"), new Size("43"), new Email("lidavid@example.com"),
                new DressCode("FORMAL"),
                getDescriptionSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Size("44"), new Email("irfan@example.com"),
                new DressCode("CASUAL"),
                getDescriptionSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Size("35"), new Email("royb@example.com"),
                new DressCode("ACTIVE"),
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
