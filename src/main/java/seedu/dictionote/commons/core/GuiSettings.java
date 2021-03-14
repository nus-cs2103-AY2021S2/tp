package seedu.dictionote.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    private float contactSplitRatio = 0.25f;
    private float dictionarySplitRatio = 0.3f;
    private float noteSplitRatio = 0.3f;
    private float mainSplitRatio = 0.5f;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = 0;
        windowHeight = 0;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, float contactSplitRatio,
                       float dictionarySplitRatio, float noteSplitRatio, float mainSplitRatio) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.contactSplitRatio = contactSplitRatio;
        this.dictionarySplitRatio = dictionarySplitRatio;
        this.noteSplitRatio = noteSplitRatio;
        this.mainSplitRatio = mainSplitRatio;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }

    public float getContactSplitRatio() {
        return contactSplitRatio;
    }

    public void setContactSplitRatio(float contactSplitRatio) {
        this.contactSplitRatio = contactSplitRatio;
    }

    public float getDictionarySplitRatio() {
        return dictionarySplitRatio;
    }

    public void setDictionarySplitRatio(float dictionarySplitRatio) {
        this.dictionarySplitRatio = dictionarySplitRatio;
    }

    public float getNoteSplitRatio() {
        return noteSplitRatio;
    }

    public void setNoteSplitRatio(float noteSplitRatio) {
        this.noteSplitRatio = noteSplitRatio;
    }

    public float getMainSplitRatio() {
        return mainSplitRatio;
    }

    public void setMainSplitRatio(float mainSplitRatio) {
        this.mainSplitRatio = mainSplitRatio;
    }
}
