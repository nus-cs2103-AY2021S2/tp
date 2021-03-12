package seedu.address.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import seedu.address.MainApp;

/**
 * Class for managing the theme of the application. Stores data on what theme is currently being applied.
 */
public class ThemeManager {
    /**
     * Template of the css used by the application.
     */
    private static String cssTemplate;

    /**
     * Current theme used by the application
     */
    private static Theme theme = null;

    /**
     * Path of the current theme
     */
    private static String themePath = null;

    /**
     * Path of the css file currently in use
     */
    private static String cssCacheUrl = null;

    /**
     * The mainScene property from a MainWindow instance.
     */
    private static Scene scene = null;

    static {
        ThemeManager.theme = ThemeFactory.getDefaultTheme();
        InputStream templateStream = MainApp.class.getResourceAsStream("/view/Template.css");
        ThemeManager.cssTemplate = new BufferedReader(new InputStreamReader(templateStream))
            .lines().collect(Collectors.joining("\n"));
        ThemeManager.updateCss();
    }

    /**
     * Sets the scene for themes to be applied to.
     *
     * @param scene The scene for themes to be applied to.
     */
    public static void setScene(Scene scene) {
        ThemeManager.scene = scene;
    }

    /**
     * Gets the theme currently in use by the application.
     *
     * @return The theme currently in use by the application.
     */
    public static Theme getTheme() {
        return ThemeManager.theme;
    }

    /**
     * Gets the path of the JSON theme file currently being applied.
     *
     * @return Path of the JSON theme file currently being applied.
     */
    public static String getThemePath() {
        return themePath;
    }

    /**
     * Sets the current theme of the application.
     *
     * @param newTheme  The new theme to be used.
     * @param themePath The path of the new theme to be used.
     */
    public static void setTheme(Theme newTheme, String themePath) {
        ThemeManager.theme = newTheme;
        ThemeManager.themePath = themePath;
        ThemeManager.updateCss();
    }

    /**
     * Updates the css and css file to be used by the application.
     *
     * @return true if the update was successful otherwise false.
     */
    private static boolean updateCss() {
        String cssString = ThemeManager.cssTemplate;
        cssString = cssString
            .replaceAll("\\$foreground", ThemeManager.theme.foreground)
            .replaceAll("\\$background", ThemeManager.theme.background);
        for (int i = 0; i < 16; i++) {
            cssString = cssString
                .replaceAll("\\$c" + Integer.toHexString(i), ThemeManager.theme.color[i]);
        }
        try {
            File temp = File.createTempFile("current", ".tmp");
            temp.deleteOnExit();
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write(cssString);
            out.close();
            ThemeManager.cssCacheUrl = "file:///" + temp.getAbsolutePath().replace(File.separator, "/");
        } catch (IOException ioException) {
            return false;
        }
        return true;
    }

    /**
     * Applies the current theme to the application.
     */
    public static void applyThemeToScene() {
        ThemeManager.scene.getStylesheets().clear();
        ThemeManager.scene.getStylesheets().add(ThemeManager.cssCacheUrl);
    }
}
