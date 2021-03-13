package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;
import seedu.address.model.description.Description;
import seedu.address.model.person.Colour;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;

/**
 * Contains utility methods for populating {@code Wardrobe} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Size("34"), new Colour("alexyeoh@example.com"),
                new DressCode("FORMAL"),
                getDescriptionSet("friends")),
            new Person(new Name("Bernice Yu"), new Size("29"), new Colour("berniceyu@example.com"),
                new DressCode("CASUAL"),
                getDescriptionSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Size("26"), new Colour("charlotte@example.com"),
                new DressCode("ACTIVE"),
                getDescriptionSet("neighbours")),
            new Person(new Name("David Li"), new Size("43"), new Colour("lidavid@example.com"),
                new DressCode("FORMAL"),
                getDescriptionSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Size("44"), new Colour("irfan@example.com"),
                new DressCode("CASUAL"),
                getDescriptionSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Size("35"), new Colour("royb@example.com"),
                new DressCode("ACTIVE"),
                getDescriptionSet("colleagues"))
        };
    }

    public static ReadOnlyWardrobe getSampleWardrobe() {
        Wardrobe sampleAb = new Wardrobe();
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
