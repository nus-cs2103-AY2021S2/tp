package seedu.storemando.commons.util;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import seedu.storemando.ui.HelpWindow;

/**
 * A container for Browser specific utility functions
 */
public class BrowserUtil {


    public static boolean isBrowserAccessible() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    public static void displayWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {} //This exceptions will only be raised when the supplied url is invalid.
    }
}
