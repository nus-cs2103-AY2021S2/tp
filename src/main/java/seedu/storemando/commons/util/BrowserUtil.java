package seedu.storemando.commons.util;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


/**
 * A container for Browser specific utility functions
 */
public class BrowserUtil {

    public static final String USERGUIDE_URL = "https://ay2021s2-cs2103t-w10-2.github.io/tp/UserGuide.html";

    /**
     * Checks if the user has a browser installed.
     *
     * @return true if the user has a browser installed, false otherwise.
     */
    public static boolean isBrowserAccessible() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    /**
     * Checks if the user is connected to the internet.
     *
     * @return true if the user is connected to the internet, false otherwise.
     */
    public static boolean isConnectedToInternet() {
        try {
            URL url = new URL(USERGUIDE_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Opens the specified url in user's browser.
     */
    public static void displayUserGuideWebsite() {
        try {
            Desktop.getDesktop().browse(new URI(USERGUIDE_URL));
        } catch (IOException | URISyntaxException e) {
            //This exceptions will only be raised when the supplied url is invalid.
        }
    }

    /**
     * Checks if the desktop is supported and there is internet connection.
     */
    public static boolean canOpenBrowser() {
        return isBrowserAccessible() && isConnectedToInternet();
    }

}
