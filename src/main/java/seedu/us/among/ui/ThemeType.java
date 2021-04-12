package seedu.us.among.ui;

import java.util.HashMap;
import java.util.Map;

//@@author tjtanjin-reused
//Reused from https://www.stubbornjava.com/posts/java-enum-lookup-by-name-or-field-without-throwing-exceptions
// with minor modifications
public enum ThemeType {
    LIGHT, DARK, IMPOSTER, MATERIAL;

    private static final Map<String, ThemeType> themeIndex = new HashMap<>();

    static {
        for (ThemeType theme : ThemeType.values()) {
            themeIndex.put(theme.name(), theme);
        }
    }

    public static boolean getIfPresent(String theme) {
        return getTheme(theme) != null;
    }

    public static ThemeType getTheme(String theme) {
        return themeIndex.get(theme.toUpperCase());
    }
}
//@@author
