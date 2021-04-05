package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Objects;

public class CssSettings implements Serializable {
    private static final String DefaultCss = "MainWindow.fxml";

    private final String NewCss;

    public CssSettings() {
        this.NewCss = DefaultCss;
    }

    public CssSettings(String NewCss) {
        this.NewCss = NewCss;
    }

    public String getCssSettings() {
        return NewCss;
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

        return NewCss == o.NewCss;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NewCss);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Css : " + NewCss);
        return sb.toString();
    }
}
