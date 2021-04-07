package seedu.address.ui;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import javafx.scene.control.Label;
import seedu.address.model.tag.ChildTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility classes and methods used for generating UI elements.
 */
public class UiUtil {
    /**
     * Generates a label of different colors based on whether the tag is a ChildTag.
     * @param tag the tag to create a label from.
     * @return Label with the tagName as text and different colors based on the tag type.
     */
    public static Label generateTagLabel(Tag tag) {
        Label label = new Label(tag.tagName);
        if (tag instanceof ChildTag) {
            label.setStyle("-fx-background-color: #ff5050");
        }
        return label;
    }

    /**
     * Converts a {@code Set<Tag>} to a sorted {@code Stream<Tag>} using the TagComparator class.
     * @param tagSet the set of tags to convert.
     * @return {@literal Stream<Tag>} of the tags sorted based on TagComparator.
     */
    public static Stream<Tag> streamTags(Set<Tag> tagSet) {
        return tagSet.stream()
                .sorted(new TagComparator());
    }

    public static class TagComparator implements Comparator<Tag> {

        @Override
        public int compare(Tag tag1, Tag tag2) {
            if (tag1 instanceof ChildTag) {
                if (tag2 instanceof ChildTag) {
                    return tag1.tagName.compareToIgnoreCase(tag2.tagName);
                } else {
                    return -1;
                }
            } else {
                if (tag2 instanceof ChildTag) {
                    return 1;
                } else {
                    return tag1.tagName.compareToIgnoreCase(tag2.tagName);
                }
            }
        }
    }
}
