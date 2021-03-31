package seedu.address.testutil;

import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * A utility class for Tags.
 */
public class TagsUtil {

    /**
     * Returns the part of command string for the given {@code tags}'s details.
     */
    public static String getTagsDetails(Set<Tag> tags) {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append(PREFIX_TAG + " " + tag.tagName + " ");
        }
        return builder.toString().stripTrailing();
    }

}
