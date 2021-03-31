package seedu.address.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import seedu.address.commons.util.FileUtil;

/**
 * Class for managing the theme of the application. Stores data on what theme is currently being applied.
 */
public class ThemeManager {

    /**
     * Template of the css used by the application.
     */
    private static final String CSS_TEMPLATE = FileUtil.getResourceAsString("/view/Template.css");

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
    private static String cssCacheUri = null;

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
     * Initialized the variables in ThemeManager.
     */
    public static void init() {
        ThemeManager.theme = ThemeFactory.getDefaultTheme();
        ThemeManager.cssCacheUri = getNewCssCacheUri(ThemeManager.theme);
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
        String newCssCache = getNewCssCacheUri(newTheme);
        if (newCssCache != null) {
            ThemeManager.cssCacheUri = getNewCssCacheUri(newTheme);
        }
    }

    public static String getCssCacheUri() {
        return ThemeManager.cssCacheUri;
    }

    /**
     * Returns the URI of the updated CSS cache.
     *
     * @return URI of the updated CSS cache.
     */
    private static String getNewCssCacheUri(Theme theme) {
        String cssString = generateCssFromTheme(theme);
        try {
            return createCssCacheFile(cssString);
        } catch (IOException ioException) {
            return null;
        }
    }

    /**
     * Generates CSS based on theme given.
     *
     * @param theme The theme to be used.
     * @return cssTemplate with colors assigned.
     */
    private static String generateCssFromTheme(Theme theme) {
        String cssString = ThemeManager.CSS_TEMPLATE;
        cssString = cssString
                .replaceAll("\\$foreground", theme.foreground)
                .replaceAll("\\$background", theme.background);
        for (int i = 0; i < 16; i++) {
            cssString = cssString
                    .replaceAll("\\$c" + Integer.toHexString(i), theme.color[i]);
        }
        return cssString;
    }

    /**
     * Creates a temporary file containing the given CSS.
     *
     * @param cssString CSS to be written to the file.
     * @return The temp file location.
     * @throws IOException The file cannot be created/written to.
     */
    private static String createCssCacheFile(String cssString) throws IOException {
        File temp = File.createTempFile("current", ".tmp");
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write(cssString);
        out.close();
        return temp.getAbsolutePath().replace(File.separator, "/");
    }

}
