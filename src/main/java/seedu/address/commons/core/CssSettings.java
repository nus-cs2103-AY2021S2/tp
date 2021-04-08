package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Objects;

public class CssSettings implements Serializable {
    private static final String defaultCss = "MainWindow.fxml";

    private final String newCss;

    public CssSettings() {
        this.newCss = defaultCss;
    }

    public CssSettings(String newCss) {
        this.newCss = newCss;
    }

    public String getCssSettings() {
        return newCss;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CssSettings)) { //this handles null as well.
            return false;
        }

        CssSettings o = (CssSettings) other;

        return newCss == o.newCss;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newCss);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Css : " + newCss);
        return sb.toString();
    }
}
