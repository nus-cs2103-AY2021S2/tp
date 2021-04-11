package seedu.address.testutil;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {

    public static final Tag PHOTOSHOP = new Tag("Photoshop");
    public static final Tag ILLUSTRATOR = new Tag("Illustrator");
    public static final Tag COMMON_TAG = new Tag("InDesign");
    public static final String COMMON_TAG_STRING = "InDesign";

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns the common tag.
     */
    public static Set<Tag> getCommonTags() {
        return new TreeSet<>(Arrays.asList(COMMON_TAG));
    }

    /**
     * Returns a set of typical tags.
     */
    public static Set<Tag> getTypicalTags() {
        return new TreeSet<>(Arrays.asList(PHOTOSHOP, ILLUSTRATOR));
    }
}
