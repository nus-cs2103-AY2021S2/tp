package seedu.address.model.tag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains utility functions for testing classes to do with UniqueTagList.
 */
public class UniqueTagListTestUtil {
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_VIM = "vim";
    public static final String VALID_TAG_YELLOW = "yellow";
    public static final String VALID_TAG_ZOO = "zoo";
    public static final Tag TAG_FRIEND = new Tag(VALID_TAG_FRIEND);
    public static final Tag TAG_HUSBAND = new Tag(VALID_TAG_HUSBAND);
    public static final Tag TAG_VIM = new Tag(VALID_TAG_VIM);
    public static final Tag TAG_YELLOW = new Tag(VALID_TAG_YELLOW);
    public static final Tag TAG_ZOO = new Tag(VALID_TAG_ZOO);
    public static final Set<Tag> SET_TAG_FRIEND = new HashSet<>();
    public static final Set<Tag> SET_TAG_HUSBAND = new HashSet<>();

    static {
        SET_TAG_FRIEND.add(TAG_FRIEND);
        SET_TAG_HUSBAND.add(TAG_HUSBAND);
    }

    /**
     * Builds a set with the given variable number of tag arguments. Maintains order that was passed into arguments.
     *
     * @param tags Variable number of Tags.
     * @return Set of the given tags.
     */
    public static Set<Tag> buildSetOfTags(Tag... tags) {
        return new HashSet<>(Arrays.asList(tags));
    }

    /**
     * Builds a UniqueTagList with the given list of tags.
     *
     * @param tags List of tags.
     * @return UniqueTagList with the given tags.
     */
    public static UniqueTagList buildWithTags(List<Tag> tags) {
        UniqueTagList utl = new UniqueTagList();
        tags.forEach(utl::add);
        return utl;
    }
}
