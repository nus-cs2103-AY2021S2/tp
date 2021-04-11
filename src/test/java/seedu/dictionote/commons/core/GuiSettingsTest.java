package seedu.dictionote.commons.core;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import javafx.geometry.Orientation;

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

    private double[] setRatioValidCase = {0.1, 0.5, 0.9};
    private double[] setRatioInvalidCase = {Double.MIN_VALUE, 0, 0.0999, 0.90001, 1, Double.MAX_VALUE};

    private GuiSettings defaultSetting = new GuiSettings();
    private GuiSettings customSetting = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition,
        contactSplitRatio, dictionarySplitRatio, noteSplitRatio, mainSplitRatio, isContactPanelVisible,
        isDictionaryContentPanelVisible, isDictionaryListPanelVisible, isNoteContentPanelVisible,
        isNoteListPanelVisible, isDictionaryPanelVerticial, isNotePanelVerticial);
    private GuiSettings customSetting2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition,
        contactSplitRatio, dictionarySplitRatio, noteSplitRatio, mainSplitRatio, isContactPanelVisible,
        isDictionaryContentPanelVisible, isDictionaryListPanelVisible, isNoteContentPanelVisible,
        isNoteListPanelVisible, isDictionaryPanelVerticial, isNotePanelVerticial);

    @Test
    void getWindowWidth_equal() {
        assertEquals(customSetting.getWindowWidth(), windowWidth);
    }

    @Test
    void getWindowHeight_equal() {
        assertEquals(customSetting.getWindowHeight(), windowHeight);
    }

    @Test
    void getWindowCoordinates_equal() {
        assertEquals(customSetting.getWindowCoordinates(), windowCoordinates);
    }

    @Test
    void isContactPanelVisible_equal() {
        assertEquals(customSetting.isContactPanelVisible(), isContactPanelVisible);
    }

    @Test
    void isNoteListPanelVisible() {
        assertEquals(customSetting.isNoteListPanelVisible(), isContactPanelVisible);
    }

    @Test
    void isNoteContentPanelVisible() {
        assertEquals(customSetting.isNoteContentPanelVisible(), isNoteContentPanelVisible);
    }

    @Test
    void isDictionaryListPanelVisible() {
        assertEquals(customSetting.isDictionaryListPanelVisible(), isDictionaryListPanelVisible);
    }

    @Test
    void isDictionaryContentPanelVisible() {
        assertEquals(customSetting.isDictionaryContentPanelVisible(), isDictionaryContentPanelVisible);
    }

    @Test
    void getContactSplitRatio() {
        assertEquals(customSetting.getContactSplitRatio(), contactSplitRatio);
    }

    @Test
    void getDictionarySplitRatio() {
        assertEquals(customSetting.getDictionarySplitRatio(), dictionarySplitRatio);
    }

    @Test
    void getNoteSplitRatio() {
        assertEquals(customSetting.getNoteSplitRatio(), noteSplitRatio);
    }

    @Test
    void getMainSplitRatio() {
        assertEquals(customSetting.getMainSplitRatio(), mainSplitRatio);
    }

    @Test
    void setContactSplitRatio_success() {
        for (int i = 0; i < setRatioValidCase.length; i++) {
            customSetting.setContactSplitRatio(setRatioValidCase[i]);
            assertEquals(customSetting.getContactSplitRatio(), setRatioValidCase[i]);
        }
    }

    @Test
    void setContactSplitRatio_fail() {
        //nothing change when fail
        double defaultValue = 0.5;
        customSetting.setContactSplitRatio(defaultValue);

        for (int i = 0; i < setRatioInvalidCase.length; i++) {
            customSetting.setContactSplitRatio(setRatioInvalidCase[i]);
            assertEquals(customSetting.getContactSplitRatio(), defaultValue);
        }
    }

    @Test
    void setDictionarySplitRatio_success() {
        for (int i = 0; i < setRatioValidCase.length; i++) {
            customSetting.setDictionarySplitRatio(setRatioValidCase[i]);
            assertEquals(customSetting.getDictionarySplitRatio(), setRatioValidCase[i]);
        }
    }

    @Test
    void setDictionarySplitRatio_fail() {
        double defaultValue = 0.5;
        customSetting.setDictionarySplitRatio(defaultValue);

        for (int i = 0; i < setRatioInvalidCase.length; i++) {
            customSetting.setDictionarySplitRatio(setRatioInvalidCase[i]);
            assertEquals(customSetting.getDictionarySplitRatio(), defaultValue);
        }
    }

    @Test
    void setNoteSplitRatio_success() {
        for (int i = 0; i < setRatioValidCase.length; i++) {
            customSetting.setNoteSplitRatio(setRatioValidCase[i]);
            assertEquals(customSetting.getNoteSplitRatio(), setRatioValidCase[i]);
        }
    }

    @Test
    void setNoteSplitRatio_fail() {
        double defaultValue = 0.5;
        customSetting.setNoteSplitRatio(defaultValue);

        for (int i = 0; i < setRatioInvalidCase.length; i++) {
            customSetting.setNoteSplitRatio(setRatioInvalidCase[i]);
            assertEquals(customSetting.getNoteSplitRatio(), defaultValue);
        }
    }

    @Test
    void setMainSplitRatio_success() {
        for (int i = 0; i < setRatioValidCase.length; i++) {
            customSetting.setMainSplitRatio(setRatioValidCase[i]);
            assertEquals(customSetting.getMainSplitRatio(), setRatioValidCase[i]);
        }
    }

    @Test
    void setMainSplitRatio_fail() {
        double defaultValue = 0.5;
        customSetting.setMainSplitRatio(defaultValue);

        for (int i = 0; i < setRatioInvalidCase.length; i++) {
            customSetting.setMainSplitRatio(setRatioInvalidCase[i]);
            assertEquals(customSetting.getMainSplitRatio(), defaultValue);
        }
    }

    @Test
    void setMainSplitRatio() {
        assertEquals(customSetting.isContactPanelVisible(), isContactPanelVisible);
    }

    @Test
    void equals_equal() {
        assertEquals(customSetting, customSetting2);
    }

    @Test
    void equals_notequal() {
        assertNotEquals(customSetting, null);
        assertNotEquals(customSetting, defaultSetting);
    }

    @Test
    void hashCode_equals() {
        assertEquals(customSetting.hashCode(), customSetting2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        assertNotEquals(customSetting.hashCode(), defaultSetting.hashCode());
    }

    @Test
    void getDictionaryPanelOrientation() {
        assertEquals(customSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void toggleDictionaryPanelOrientation() {
        assertEquals(customSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
        customSetting.toggleDictionaryPanelOrientation();
        assertEquals(customSetting.getDictionaryPanelOrientation(), Orientation.VERTICAL);
        customSetting.toggleDictionaryPanelOrientation();
        assertEquals(customSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void getNotePanelOrientation() {
        assertEquals(customSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void toggleNotePanelOrientation() {
        assertEquals(customSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
        customSetting.toggleNotePanelOrientation();
        assertEquals(customSetting.getNotePanelOrientation(), Orientation.VERTICAL);
        customSetting.toggleNotePanelOrientation();
        assertEquals(customSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
    }
}
