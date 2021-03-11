package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Birthday("1999-12-12"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Birthday("1998-10-05"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),new Birthday("2001-01-07"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Birthday("1965-09-07"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Birthday("1983-05-04"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Birthday("1977-11-11"),
                getTagSet("colleagues"))
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
