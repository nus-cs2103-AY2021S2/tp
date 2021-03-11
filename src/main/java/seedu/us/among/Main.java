package seedu.us.among;

import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;

import javafx.application.Application;

/**
 * The main entry point to the application.
 *
 * This is a workaround for the following error when imPoster is made the
 * entry point of the application:
 *
 *     Error: JavaFX runtime components are missing, and are required to run this application
 *
 * The reason is that imPoster extends Application. In that case, the
 * LauncherHelper will check for the javafx.graphics module to be present
 * as a named module. We don't use JavaFX via the module system so it can't
 * find the javafx.graphics module, and so the launch is aborted.
 *
 * By having a separate main class (Main) that doesn't extend Application
 * to be the entry point of the application, we avoid this issue.
 */
public class Main {
    //Solution adapted from https://mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final boolean IS_WINDOWS = (OS.contains("win"));
    private static final boolean IS_MAC = (OS.contains("mac"));

    /**
     * Entry point of program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //loading an image from a file
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = Main.class.getResource("/images/imPosterDock.png");
        final Image image = defaultToolkit.getImage(imageResource);

        //linux not handled, icon already shows by default
        if (IS_WINDOWS) {
            setWindowsIcon(image);
        } else if (IS_MAC) {
            setMacIcon(image);
        }
        Application.launch(ImPoster.class, args);
    }

    //Solutions below adapted from https://stackoverflow.com/a/56924202/14966239
    /**
     * Sets icon on mac dock.
     *
     * @param image image to set
     */
    public static void setMacIcon(Image image) {
        try {
            final Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(image);
        } catch (Exception e) {
            //unsupported versions
        }
    }

    /**
     * Sets icon on windows taskbar.
     *
     * @param image image to set
     */
    public static void setWindowsIcon(Image image) {
        try {
            final JFrame jFrame = new JFrame();
            jFrame.setIconImage(image);
            jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {
            //unsupported versions
        }
    }
}
