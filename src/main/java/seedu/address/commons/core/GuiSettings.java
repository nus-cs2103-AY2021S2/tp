package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 */
public class GuiSettings implements Serializable {

    public enum PanelToShow {
        CUSTOMER_LIST,
        CHEESE_LIST,
        ORDER_LIST
    }

    private static final PanelToShow DEFAULT_PANEL = PanelToShow.CUSTOMER_LIST;
    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;

    private PanelToShow panel;
    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    /**
     * Constructs a {@code GuiSettings} with the default UI panel, height, width and position.
     */
    public GuiSettings() {
        panel = DEFAULT_PANEL;
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified UI panel, height, width and position.
     */
    public GuiSettings(PanelToShow panel, double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.panel = panel;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
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

    public void setPanelToCustomerList() {
        panel = PanelToShow.CUSTOMER_LIST;
    }

    public void setPanelToCheeseList() {
        panel = PanelToShow.CHEESE_LIST;
    }

    public void setPanelToOrderList() {
        panel = PanelToShow.ORDER_LIST;
    }

    public PanelToShow getPanel() {
        return panel;
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

        return panel == o.panel
                && windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(panel, windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Panel: " + panel + "\n");
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }
}
