package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Address;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;

/**
 * Contains utility methods for populating {@code Wardrobe} with sample data.
 */
public class SampleDataUtil {
    public static Garment[] getSampleGarments() {
        return new Garment[] {
            new Garment(new Name("Alex Yeoh"), new Size("34"), new Colour("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getDescriptionSet("friends")),
            new Garment(new Name("Bernice Yu"), new Size("29"), new Colour("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getDescriptionSet("colleagues", "friends")),
            new Garment(new Name("Charlotte Oliveiro"), new Size("26"), new Colour("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getDescriptionSet("neighbours")),
            new Garment(new Name("David Li"), new Size("43"), new Colour("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getDescriptionSet("family")),
            new Garment(new Name("Irfan Ibrahim"), new Size("44"), new Colour("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getDescriptionSet("classmates")),
            new Garment(new Name("Roy Balakrishnan"), new Size("35"), new Colour("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getDescriptionSet("colleagues"))
        };
    }

    public static ReadOnlyWardrobe getSampleWardrobe() {
        Wardrobe sampleAb = new Wardrobe();
        for (Garment sampleGarment : getSampleGarments()) {
            sampleAb.addGarment(sampleGarment);
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
