package guitests.guihandles;

import guitests.guihandles.exceptions.NodeNotFoundException;
import javafx.scene.Node;

/**
 * A handler for the info display placeholder of the UI
 */
public class InfoDisplayHandle extends NodeHandle<Node> {

    public static final String INFO_DISPLAY_ID = "#infoDisplayPlaceholder";

    /**
     * Creates a handler object for the info display placeholder.
     *
     * @param infoDisplayNode The node of the info display placeholder.
     */
    public InfoDisplayHandle(Node infoDisplayNode) {
        super(infoDisplayNode);
    }

    /**
     * Checks if the panel is contained inside the info display panel.
     * Check is dependent on MainWindow only showing one panel at a time as the method only checks if info display
     * contains the specified panel.
     *
     * @param id The id of the node that is to be checked.
     * @return true if info panel contains the node, false otherwise.
     */
    public boolean contains(String id) {
        try {
            getChildNode("#" + id);
            return true;
        } catch (NodeNotFoundException e) {
            return false;
        }
    }

}
