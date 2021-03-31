package seedu.address.ui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The theme object. Stores the color palette of the application.
 */
public class Theme implements Serializable {

    public static final Pattern REGEX_HEX_COLOR = Pattern.compile("^#([a-fA-F0-9]{3}|[a-fA-F0-9]{6})$");

    /**
     * The foreground color of the application.
     */
    public final String foreground;

    /**
     * The background color of the application.
     */
    public final String background;

    /**
     * The 16 'ASCII' color of the application.
     * See <a href="https://invisible-island.net/xterm/manpage/xterm.html#h3-VT100-Widget-Resources">XTerm Colors</a>.
     */
    public final String[] color;

    /**
     * The default constructor.
     * Necessary for deserializing from JSON.
     */
    public Theme() {
        foreground = "";
        background = "";
        color = null;
    }

    /**
     * Constructs a Theme object based on given foreground, background, and colors.
     *
     * @param foreground Foreground of the application.
     * @param background Background of the application.
     * @param color      Colors 0 - 15 of the application.
     */
    public Theme(String foreground, String background, String[] color) {
        this.foreground = foreground;
        this.background = background;
        this.color = color;
    }

    /**
     * Checks if the Theme object is a valid theme.
     *
     * @return true if theme is valid otherwise false.
     */
    public boolean isValid() {
        if (this.foreground.isEmpty() || this.background.isEmpty()) {
            return false;
        }
        if (!REGEX_HEX_COLOR.matcher(this.foreground).find()) {
            return false;
        }
        if (!REGEX_HEX_COLOR.matcher(this.background).find()) {
            return false;
        }
        if (color == null) {
            return false;
        }
        if (color.length != 16) {
            return false;
        }
        for (String hex : color) {
            if (!REGEX_HEX_COLOR.matcher(hex).find()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Theme theme = (Theme) o;

        if (!Objects.equals(foreground, theme.foreground)) {
            return false;
        }
        if (!Objects.equals(background, theme.background)) {
            return false;
        }
        return Arrays.equals(color, theme.color);
    }

}
