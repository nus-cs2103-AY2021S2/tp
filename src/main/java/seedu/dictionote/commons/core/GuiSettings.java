package seedu.dictionote.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import javafx.geometry.Orientation;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double MIN_SPLIT_RATIO = 0.1;
    private static final double MAX_SPLIT_RATIO = 0.9;

    private static final double DEFAULT_CONTACT_SPLIT_RATIO = 0.25;
    private static final double DEFAULT_DICTIONARY_SPLIT_RATIO = 0.3;
    private static final double DEFAULT_NOTE_SPLIT_RATIO = 0.3;
    private static final double DEFAULT_MAIN_SPLIT_RATIO = 0.5;

    private static final boolean DEFAULT_CONTACT_PANEL_IS_VISIBLE = true;
    private static final boolean DEFAULT_NOTE_LIST_PANEL_IS_VISIBLE = true;
    private static final boolean DEFAULT_NOTE_CONTENT_PANEL_IS_VISIBLE = true;
    private static final boolean DEFAULT_DICTIONARY_LIST_PANEL_IS_VISIBLE = true;
    private static final boolean DEFAULT_DICTIONARY_CONTENT_PANEL_IS_VISIBLE = true;

    private static final boolean DEFAULT_NOTE_ORIENTATION_ISVERTICIAL = false;
    private static final boolean DEFAULT_DICTIONARY_ORIENTATION_ISVERTICIAL = false;


    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    private boolean isDictionaryPanelVerticial;
    private boolean isNotePanelVerticial;

    private double contactSplitRatio;
    private double dictionarySplitRatio;
    private double noteSplitRatio;
    private double mainSplitRatio;

    private final boolean isContactPanelVisible;
    private final boolean isNoteListPanelVisible;
    private final boolean isNoteContentPanelVisible;
    private final boolean isDictionaryListPanelVisible;
    private final boolean isDictionaryContentPanelVisible;



    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = 0;
        windowHeight = 0;
        windowCoordinates = null; // null represent no coordinates

        contactSplitRatio = DEFAULT_CONTACT_SPLIT_RATIO;
        dictionarySplitRatio = DEFAULT_DICTIONARY_SPLIT_RATIO;
        noteSplitRatio = DEFAULT_NOTE_SPLIT_RATIO;
        mainSplitRatio = DEFAULT_MAIN_SPLIT_RATIO;

        isContactPanelVisible = DEFAULT_CONTACT_PANEL_IS_VISIBLE;
        isDictionaryContentPanelVisible = DEFAULT_DICTIONARY_CONTENT_PANEL_IS_VISIBLE;
        isDictionaryListPanelVisible = DEFAULT_DICTIONARY_LIST_PANEL_IS_VISIBLE;
        isNoteContentPanelVisible = DEFAULT_NOTE_CONTENT_PANEL_IS_VISIBLE;
        isNoteListPanelVisible = DEFAULT_NOTE_LIST_PANEL_IS_VISIBLE;

        isDictionaryPanelVerticial = DEFAULT_DICTIONARY_ORIENTATION_ISVERTICIAL;
        isNotePanelVerticial = DEFAULT_NOTE_ORIENTATION_ISVERTICIAL;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition,
                       double contactSplitRatio, double dictionarySplitRatio,
                       double noteSplitRatio, double mainSplitRatio,
                       boolean isContactPanelVisible, boolean isDictionaryContentPanelVisible,
                       boolean isDictionaryListPanelVisible, boolean isNoteContentPanelVisible,
                       boolean isNoteListPanelVisible, boolean isDictionaryPanelVerticial,
                       boolean isNotePanelVerticial) {

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);

        setContactSplitRatio(contactSplitRatio);
        setDictionarySplitRatio(dictionarySplitRatio);
        setNoteSplitRatio(noteSplitRatio);
        setMainSplitRatio(mainSplitRatio);

        this.isContactPanelVisible = isContactPanelVisible;
        this.isDictionaryContentPanelVisible = isDictionaryContentPanelVisible;
        this.isDictionaryListPanelVisible = isDictionaryListPanelVisible;
        this.isNoteContentPanelVisible = isNoteContentPanelVisible;
        this.isNoteListPanelVisible = isNoteListPanelVisible;

        this.isDictionaryPanelVerticial = isDictionaryPanelVerticial;
        this.isNotePanelVerticial = isNotePanelVerticial;
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

    public boolean isContactPanelVisible() {
        return isContactPanelVisible;
    }

    public boolean isNoteListPanelVisible() {
        return isNoteListPanelVisible;
    }

    public boolean isNoteContentPanelVisible() {
        return isNoteContentPanelVisible;
    }

    public boolean isDictionaryListPanelVisible() {
        return isDictionaryListPanelVisible;
    }

    public boolean isDictionaryContentPanelVisible() {
        return isDictionaryContentPanelVisible;
    }

    public double getContactSplitRatio() {
        return contactSplitRatio;
    }

    public double getDictionarySplitRatio() {
        return dictionarySplitRatio;
    }

    public double getNoteSplitRatio() {
        return noteSplitRatio;
    }

    public double getMainSplitRatio() {
        return mainSplitRatio;
    }

    public void setContactSplitRatio(double contactSplitRatio) {
        if (isValidSplitRatio(contactSplitRatio)) {
            this.contactSplitRatio = getRoundedValue(contactSplitRatio);
        }
    }

    public void setDictionarySplitRatio(double dictionarySplitRatio) {
        if (isValidSplitRatio(dictionarySplitRatio)) {
            this.dictionarySplitRatio = getRoundedValue(dictionarySplitRatio);
        }
    }

    public void setNoteSplitRatio(double noteSplitRatio) {
        if (isValidSplitRatio(noteSplitRatio)) {
            this.noteSplitRatio = getRoundedValue(noteSplitRatio);
        }
    }

    public void setMainSplitRatio(double mainSplitRatio) {
        if (isValidSplitRatio(mainSplitRatio)) {
            this.mainSplitRatio = getRoundedValue(mainSplitRatio);
        }
    }

    private boolean isValidSplitRatio(double ratio) {
        return ratio >= MIN_SPLIT_RATIO && ratio <= MAX_SPLIT_RATIO;
    }

    private double getRoundedValue(double d) {
        return ((double) Math.round(d * 10000)) / 10000;
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
            && Objects.equals(windowCoordinates, o.windowCoordinates)
            && contactSplitRatio == o.contactSplitRatio
            && dictionarySplitRatio == o.dictionarySplitRatio
            && noteSplitRatio == o.noteSplitRatio
            && mainSplitRatio == o.mainSplitRatio
            && isContactPanelVisible == o.isContactPanelVisible
            && isNoteListPanelVisible == o.isNoteListPanelVisible
            && isNoteContentPanelVisible == o.isNoteContentPanelVisible
            && isDictionaryListPanelVisible == o.isDictionaryListPanelVisible
            && isDictionaryContentPanelVisible == o.isDictionaryContentPanelVisible;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, contactSplitRatio, dictionarySplitRatio,
            noteSplitRatio, mainSplitRatio, isContactPanelVisible, isDictionaryContentPanelVisible,
            isDictionaryListPanelVisible, isNoteContentPanelVisible, isNoteListPanelVisible, isDictionaryPanelVerticial,
            isNotePanelVerticial
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        sb.append("ContactSplitRatio : " + contactSplitRatio + "\n");
        sb.append("DictionarySplitRatio : " + dictionarySplitRatio + "\n");
        sb.append("NoteSplitRatio : " + noteSplitRatio + "\n");
        sb.append("MainSplitRatio : " + mainSplitRatio + "\n");
        sb.append("IsContactPanelVisible : " + isContactPanelVisible + "\n");
        sb.append("IsDictionaryContentPanelVisible : " + isDictionaryContentPanelVisible + "\n");
        sb.append("IsDictionaryListPanelVisible : " + isDictionaryListPanelVisible + "\n");
        sb.append("IsNoteContentPanelVisible : " + isNoteContentPanelVisible + "\n");
        sb.append("IsNoteListPanelVisible : " + isNoteListPanelVisible + "\n");
        sb.append("isDictionaryPanelVerticial : " + isDictionaryPanelVerticial + "\n");
        sb.append("isNotePanelVerticial : " + isNotePanelVerticial + "\n");
        return sb.toString();
    }

    public Orientation getDictionaryPanelOrientation() {
        return isDictionaryPanelVerticial ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    }

    public void toggleDictionaryPanelOrientation() {
        isDictionaryPanelVerticial = !isDictionaryPanelVerticial;
    }

    public Orientation getNotePanelOrientation() {
        return isNotePanelVerticial ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    }

    public void toggleNotePanelOrientation() {
        isNotePanelVerticial = !isNotePanelVerticial;
    }
}
