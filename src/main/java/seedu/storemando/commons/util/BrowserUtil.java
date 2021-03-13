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

    /**
     * Checks if the user has a browser installed.
     *
     * @return true if the user has a browser installed, false otherwise.
     */
    public static boolean isBrowserAccessible() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    /** Checks if the user is connected to the internet.
     *
     * @return true if the user is connected to the internet, false otherwise.
     */
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

    /**
     * Opens the specified url in user's browser.
     *
     * @param url link of the website to visit.
     */
    public static void displayWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {} //This exceptions will only be raised when the supplied url is invalid.
    }


}
