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
import seedu.address.model.garment.LastUse;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.model.garment.Type;

/**
 * Contains utility methods for populating {@code Wardrobe} with sample data.
 */
public class SampleDataUtil {
    public static Garment[] getSampleGarments() {
        return new Garment[] {
            new Garment(new Name("Blazer"), new Size("34"), new Colour("black"),
                new DressCode("formal"), new Type("upper"),
                getDescriptionSet("home", "missing button"), new LastUse("Never")),
            new Garment(new Name("Dress Shirt"), new Size("29"), new Colour("white"),
                new DressCode("formal"), new Type("upper"),
                getDescriptionSet("home", "wrinkled"), new LastUse("2021-04-01")),
            new Garment(new Name("Jeans"), new Size("29"), new Colour("blue"),
                new DressCode("casual"), new Type("lower"),
                getDescriptionSet("school", "stained", "torn"), new LastUse("2021-03-30")),
            new Garment(new Name("Adidas Shoes"), new Size("26"), new Colour("orange"),
                new DressCode("active"), new Type("footwear"),
                getDescriptionSet("school", "sole worn out"), new LastUse("2021-03-29")),
            new Garment(new Name("Faculty Shirt"), new Size("43"), new Colour("purple"),
                new DressCode("casual"), new Type("upper"),
                getDescriptionSet("school", "SOC"), new LastUse("2021-03-28")),
            new Garment(new Name("Dress Shoes"), new Size("44"), new Colour("brown"),
                new DressCode("formal"), new Type("footwear"),
                getDescriptionSet("home", "needs polishing"), new LastUse("2021-03-27")),
            new Garment(new Name("Track Pants"), new Size("35"), new Colour("white"),
                new DressCode("active"), new Type("lower"),
                getDescriptionSet("home", "loose"), new LastUse("2021-03-26"))
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
