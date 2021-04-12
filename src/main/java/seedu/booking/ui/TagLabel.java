package seedu.booking.ui;

import javafx.scene.control.Label;

import seedu.booking.model.Tag;

/**
 * An UI component that displays the tag name of a {@code Tag}.
 * Additionally it sets the color of the tag
 */
public class TagLabel extends Label {

    private final static String[] colors = new String[]{"a85b6b","75a784","004D80","dd9b73","d66b6b"};

    private final Tag tag;

    /**
     * Creates a {@code TagLabel} with the given {@code Tag}.
     */
    public TagLabel(Tag tag) {
        super(tag.tagName);
        this.tag = tag;
        String colorHex = colors[Math.abs(tag.hashCode()) % colors.length];
        this.setStyle("-fx-background-color: #" + colorHex + ";");
    }

}
