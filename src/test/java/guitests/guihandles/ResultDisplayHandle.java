package guitests.guihandles;

import javafx.scene.control.TextArea;

/**
 * A handler for the {@code ResultDisplay} of the UI
 *
 * Reused from AB4 https://github.com/se-edu/addressbook-level4/
 */
public class ResultDisplayHandle extends NodeHandle<TextArea> {

    public static final String RESULT_DISPLAY_ID = "#resultDisplay";

    public ResultDisplayHandle(TextArea resultDisplayNode) {
        super(resultDisplayNode);
    }

    /**
     * Returns the text in the result display.
     */
    public String getText() {
        return getRootNode().getText();
    }
}
