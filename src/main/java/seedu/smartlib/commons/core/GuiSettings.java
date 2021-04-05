package seedu.smartlib.commons.core;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private static final double DEFAULT_HEIGHT = size.getHeight() / 2;
    private static final double DEFAULT_WIDTH = size.getWidth() / 2;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     *
     * @param windowWidth width of the GUI
     * @param windowHeight height of the GUI
     * @param xPosition x-position of the GUI
     * @param yPosition y-position of the GUI
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
    }

    /**
     * Returns the width of the GUI.
     *
     * @return the width of the GUI
     */
    public double getWindowWidth() {
        return windowWidth;
    }

    /**
     * Returns the height of the GUI.
     *
     * @return the height of the GUI
     */
    public double getWindowHeight() {
        return windowHeight;
    }

    /**
     * Returns the coordinates of the GUI.
     *
     * @return the coordinates of the GUI
     */
    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    /**
     * Checks if this GuiSettings object is equals to another GuiSettings object.
     *
     * @param other the other GuiSettings object to be compared
     * @return true if this GuiSettings object is equal to the other GuiSettings object, and false otherwise
     */
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

    /**
     * Generates a hashcode for this GuiSettings object.
     *
     * @return the hashcode for this GuiSettings object
     */
    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    /**
     * Returns this GuiSettings object in String format.
     *
     * @return this GuiSettings object in String format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }

}
