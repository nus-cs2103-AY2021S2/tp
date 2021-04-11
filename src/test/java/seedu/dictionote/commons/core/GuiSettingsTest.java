package seedu.dictionote.commons.core;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuiSettingsTest {

    private double windowWidth = 1;
    private double windowHeight = 2;
    private int xPosition = 10;
    private int yPosition = 20;
    private Point windowCoordinates = new Point(xPosition, yPosition);

    private boolean isDictionaryPanelVerticial = false;
    private boolean isNotePanelVerticial = false;

    private double contactSplitRatio = 0.5;
    private double dictionarySplitRatio = 0.5;
    private double noteSplitRatio = 0.5;
    private double mainSplitRatio = 0.5;

    private boolean isContactPanelVisible = false;
    private boolean isNoteListPanelVisible = false;
    private boolean isNoteContentPanelVisible = false;
    private boolean isDictionaryListPanelVisible = false;
    private boolean isDictionaryContentPanelVisible = false;


    GuiSettings DefaultSetting = new GuiSettings();
    GuiSettings CustomSetting = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, contactSplitRatio,
        dictionarySplitRatio, noteSplitRatio,  mainSplitRatio, isContactPanelVisible, isDictionaryContentPanelVisible,
        isDictionaryListPanelVisible, isNoteContentPanelVisible, isNoteListPanelVisible, isDictionaryPanelVerticial,
        isNotePanelVerticial);

    @Test
    void getWindowWidth_equal() {
        assertEquals(CustomSetting.getWindowWidth(),windowWidth);
    }

    @Test
    void getWindowHeight_equal() {
        assertEquals(CustomSetting.getWindowHeight(),windowHeight);
    }

    @Test
    void getWindowCoordinates_equal() {
        assertEquals(CustomSetting.getWindowCoordinates(),windowCoordinates);
    }

    @Test
    void isContactPanelVisible_equal() {
        assertEquals(CustomSetting.isContactPanelVisible(),isContactPanelVisible);
    }

    @Test
    void isNoteListPanelVisible() {
        assertEquals(CustomSetting.isContactPanelVisible(),isContactPanelVisible);
    }

    @Test
    void isNoteContentPanelVisible() {
    }

    @Test
    void isDictionaryListPanelVisible() {
    }

    @Test
    void isDictionaryContentPanelVisible() {
    }

    @Test
    void getContactSplitRatio() {
    }

    @Test
    void getDictionarySplitRatio() {
    }

    @Test
    void getNoteSplitRatio() {
    }

    @Test
    void getMainSplitRatio() {
    }

    @Test
    void setContactSplitRatio() {
    }

    @Test
    void setDictionarySplitRatio() {
    }

    @Test
    void setNoteSplitRatio() {
    }

    @Test
    void setMainSplitRatio() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void getDictionaryPanelOrientation() {
    }

    @Test
    void toggleDictionaryPanelOrientation() {
    }

    @Test
    void getNotePanelOrientation() {
    }

    @Test
    void toggleNotePanelOrientation() {
    }
}
