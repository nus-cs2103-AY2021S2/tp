package seedu.storemando.commons.util;


import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import seedu.storemando.ui.HelpWindow;

/**
 * A container for Browser specific utility functions
 */
public class BrowserUtil {


    public static boolean isBrowserAccessible() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    public static boolean isConnectedToInternet() {
        try {
            URL url = new URL(HelpWindow.USERGUIDE_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void displayWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {} //This exceptions will only be raised when the supplied url is invalid.
    }


}
