package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Labeled;

/**
 * @@author {se-edu}-reused
 * Reused with modifications from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * A handle for the {@code StatusBarFooter} at the footer of the application.
 */
public class StatusBarFooterHandle extends NodeHandle<Node> {
    public static final String STATUS_BAR_PLACEHOLDER = "#statusbarPlaceholder";

    private static final String SAVE_LOCATION_STATUS_ID = "#saveLocationStatus";

    private final Labeled saveLocationNode;

    /**
     * Constructs a {@code StatusBarFooterHandle} handler object.
     *
     * @param statusBarFooterNode Node of the {@code StatusBarFooter}.
     */
    public StatusBarFooterHandle(Node statusBarFooterNode) {
        super(statusBarFooterNode);
        saveLocationNode = getChildNode(SAVE_LOCATION_STATUS_ID);
    }

    /**
     * Returns the text of the 'save location' portion of the status bar.
     */
    public String getSaveLocation() {
        return saveLocationNode.getText();
    }
}
