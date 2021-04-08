package seedu.address.model.tag;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains utility functions for testing UniqueTagList.
 */
public class UniqueTagListTestUtil {
    public static final Tag TAG_HUSBAND = new Tag(VALID_TAG_HUSBAND);
    public static final Tag TAG_FRIEND = new Tag(VALID_TAG_FRIEND);
    public static final Set<Tag> SET_TAG_HUSBAND = new HashSet<>();
    public static final Set<Tag> SET_TAG_FRIEND = new HashSet<>();
    public static final Set<Tag> SET_MULTIPLE_TAGS = new HashSet<>();

    static {
        SET_TAG_HUSBAND.add(TAG_HUSBAND);
        SET_TAG_FRIEND.add(TAG_FRIEND);
        SET_MULTIPLE_TAGS.add(TAG_HUSBAND);
        SET_MULTIPLE_TAGS.add(TAG_FRIEND);
    }
}
