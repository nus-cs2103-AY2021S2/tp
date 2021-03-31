package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
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
        return new HashSet<>(Arrays.asList(COMMON_TAG));
    }

    /**
     * Returns a set of typical tags.
     */
    public static Set<Tag> getTypicalTags() {
        return new HashSet<>(Arrays.asList(PHOTOSHOP, ILLUSTRATOR));
    }
}
