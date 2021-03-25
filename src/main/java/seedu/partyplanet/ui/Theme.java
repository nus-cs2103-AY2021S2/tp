package seedu.partyplanet.ui;

import java.util.List;

/**
 * Enumeration of available themes.
 */
public enum Theme {
    DARK, PASTEL;

    /**
     * Returns a list of filepaths to stylesheets for each theme.
     */
    public static List<String> getStyleSheets(Theme theme) {
        switch (theme) {
        case DARK:
            return List.of("view/DarkTheme.css", "view/Extensions.css");
        case PASTEL: // fallthrough
        default:
            return List.of("view/PinkPastelTheme.css", "view/ExtensionsPinkPastel.css");
        }
    }
}
