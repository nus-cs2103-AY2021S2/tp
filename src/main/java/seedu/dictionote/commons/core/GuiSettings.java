package seedu.dictionote.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

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


    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

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

        this.contactSplitRatio = DEFAULT_CONTACT_SPLIT_RATIO;
        this.dictionarySplitRatio = DEFAULT_DICTIONARY_SPLIT_RATIO;
        this.noteSplitRatio = DEFAULT_NOTE_SPLIT_RATIO;
        this.mainSplitRatio = DEFAULT_MAIN_SPLIT_RATIO;

        isContactPanelVisible = true;
        isDictionaryContentPanelVisible = true;
        isDictionaryListPanelVisible = true;
        isNoteContentPanelVisible = true;
        isNoteListPanelVisible = true;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition,
                       double contactSplitRatio, double dictionarySplitRatio,
                       double noteSplitRatio, double mainSplitRatio,
                       boolean isContactPanelVisible, boolean isDictionaryContentPanelVisible,
                       boolean isDictionaryListPanelVisible, boolean isNoteContentPanelVisible,
                       boolean isNoteListPanelVisible) {

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);

        this.contactSplitRatio = contactSplitRatio;
        this.dictionarySplitRatio = dictionarySplitRatio;
        this.noteSplitRatio = noteSplitRatio;
        this.mainSplitRatio = mainSplitRatio;

        this.isContactPanelVisible = isContactPanelVisible;
        this.isDictionaryContentPanelVisible = isDictionaryContentPanelVisible;
        this.isDictionaryListPanelVisible = isDictionaryListPanelVisible;
        this.isNoteContentPanelVisible = isNoteContentPanelVisible;
        this.isNoteListPanelVisible = isNoteListPanelVisible;
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
            this.contactSplitRatio = contactSplitRatio;
        }
    }

    public void setDictionarySplitRatio(double dictionarySplitRatio) {
        if (isValidSplitRatio(dictionarySplitRatio)) {
            this.dictionarySplitRatio = dictionarySplitRatio;
        }
    }

    public void setNoteSplitRatio(double noteSplitRatio) {
        if (isValidSplitRatio(noteSplitRatio)) {
            this.noteSplitRatio = noteSplitRatio;
        }
    }

    public void setMainSplitRatio(double mainSplitRatio) {
        if (isValidSplitRatio(mainSplitRatio)) {
            this.mainSplitRatio = mainSplitRatio;
        }
    }

    private boolean isValidSplitRatio(double ratio) {
        return ratio >= MIN_SPLIT_RATIO && ratio <= MAX_SPLIT_RATIO;
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
            isDictionaryListPanelVisible, isNoteContentPanelVisible, isNoteListPanelVisible
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
        return sb.toString();
    }
}
