package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;

/**
 * Contains utility methods for populating {@code Wardrobe} with sample data.
 */
public class SampleDataUtil {
    public static Garment[] getSampleGarments() {
        return new Garment[] {
            new Garment(new Name("Alex Yeoh"), new Size("34"), new Colour("blue"),
                new DressCode("FORMAL"),
                getDescriptionSet("friends")),
            new Garment(new Name("Bernice Yu"), new Size("29"), new Colour("blue"),
                new DressCode("CASUAL"),
                getDescriptionSet("colleagues", "friends")),
            new Garment(new Name("Charlotte Oliveiro"), new Size("26"), new Colour("blue"),
                new DressCode("ACTIVE"),
                getDescriptionSet("neighbours")),
            new Garment(new Name("David Li"), new Size("43"), new Colour("blue"),
                new DressCode("FORMAL"),
                getDescriptionSet("family")),
            new Garment(new Name("Irfan Ibrahim"), new Size("44"), new Colour("blue"),
                new DressCode("CASUAL"),
                getDescriptionSet("classmates")),
            new Garment(new Name("Roy Balakrishnan"), new Size("35"), new Colour("blue"),
                new DressCode("ACTIVE"),
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
