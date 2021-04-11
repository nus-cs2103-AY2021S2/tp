package seedu.dictionote.commons.core;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import org.junit.jupiter.api.BeforeEach;
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


    double setRatioValidCase[] = {0.1,0.5,0.9};
    double setRatioInvalidCase[] = {Double.MIN_VALUE,0,0.0999,0.90001,1,Double.MAX_VALUE};

    GuiSettings DefaultSetting = new GuiSettings();
    GuiSettings CustomSetting = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, contactSplitRatio,
        dictionarySplitRatio, noteSplitRatio,  mainSplitRatio, isContactPanelVisible, isDictionaryContentPanelVisible,
        isDictionaryListPanelVisible, isNoteContentPanelVisible, isNoteListPanelVisible, isDictionaryPanelVerticial,
        isNotePanelVerticial);
    GuiSettings CustomSetting2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, contactSplitRatio,
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
        assertEquals(CustomSetting.isNoteListPanelVisible(),isContactPanelVisible);
    }

    @Test
    void isNoteContentPanelVisible() {
        assertEquals(CustomSetting.isNoteContentPanelVisible(),isNoteContentPanelVisible);
    }

    @Test
    void isDictionaryListPanelVisible() {
        assertEquals(CustomSetting.isDictionaryListPanelVisible(),isDictionaryListPanelVisible);
    }

    @Test
    void isDictionaryContentPanelVisible() {
        assertEquals(CustomSetting.isDictionaryContentPanelVisible(),isDictionaryContentPanelVisible);
    }

    @Test
    void getContactSplitRatio() {
        assertEquals(CustomSetting.getContactSplitRatio(),contactSplitRatio);
    }

    @Test
    void getDictionarySplitRatio() {
        assertEquals(CustomSetting.getDictionarySplitRatio(),dictionarySplitRatio);
    }

    @Test
    void getNoteSplitRatio() {
        assertEquals(CustomSetting.getNoteSplitRatio(),noteSplitRatio);
    }

    @Test
    void getMainSplitRatio() {
        assertEquals(CustomSetting.getMainSplitRatio(),mainSplitRatio);
    }

    @Test
    void setContactSplitRatio_success() {
        for(int i = 0; i< setRatioValidCase.length; i++) {
            CustomSetting.setContactSplitRatio(setRatioValidCase[i]);
            assertEquals(CustomSetting.getContactSplitRatio(),setRatioValidCase[i]);
        }
    }

    @Test
    void setContactSplitRatio_fail() {
        //nothing change when fail
        double defaultValue = 0.5;
        CustomSetting.setContactSplitRatio(defaultValue);

        for(int i = 0; i< setRatioInvalidCase.length; i++) {
            CustomSetting.setContactSplitRatio(setRatioInvalidCase[i]);
            assertEquals(CustomSetting.getContactSplitRatio(),defaultValue);
        }
    }
    @Test
    void setDictionarySplitRatio_success() {
        for(int i = 0; i< setRatioValidCase.length; i++) {
            CustomSetting.setDictionarySplitRatio(setRatioValidCase[i]);
            assertEquals(CustomSetting.getDictionarySplitRatio(),setRatioValidCase[i]);
        }
    }

    @Test
    void setDictionarySplitRatio_fail() {
        double defaultValue = 0.5;
        CustomSetting.setDictionarySplitRatio(defaultValue);

        for(int i = 0; i< setRatioInvalidCase.length; i++) {
            CustomSetting.setDictionarySplitRatio(setRatioInvalidCase[i]);
            assertEquals(CustomSetting.getDictionarySplitRatio(),defaultValue);
        }
    }

    @Test
    void setNoteSplitRatio_success() {
        for(int i = 0; i< setRatioValidCase.length; i++) {
            CustomSetting.setNoteSplitRatio(setRatioValidCase[i]);
            assertEquals(CustomSetting.getNoteSplitRatio(),setRatioValidCase[i]);
        }
    }

    @Test
    void setNoteSplitRatio_fail() {
        double defaultValue = 0.5;
        CustomSetting.setNoteSplitRatio(defaultValue);

        for(int i = 0; i< setRatioInvalidCase.length; i++) {
            CustomSetting.setNoteSplitRatio(setRatioInvalidCase[i]);
            assertEquals(CustomSetting.getNoteSplitRatio(), defaultValue);
        }
    }
    @Test
    void setMainSplitRatio_success() {
        for(int i = 0; i< setRatioValidCase.length; i++) {
            CustomSetting.setMainSplitRatio(setRatioValidCase[i]);
            assertEquals(CustomSetting.getMainSplitRatio(),setRatioValidCase[i]);
        }
    }

    @Test
    void setMainSplitRatio_fail() {
        double defaultValue = 0.5;
        CustomSetting.setMainSplitRatio(defaultValue);

        for(int i = 0; i< setRatioInvalidCase.length; i++) {
            CustomSetting.setMainSplitRatio(setRatioInvalidCase[i]);
            assertEquals(CustomSetting.getMainSplitRatio(), defaultValue);
        }
    }

    @Test
    void setMainSplitRatio() {
        assertEquals(CustomSetting.isContactPanelVisible(),isContactPanelVisible);
    }

    @Test
    void equals_equal() {
        assertEquals(CustomSetting,CustomSetting2);
    }

    @Test
    void equals_notequal() {
        assertNotEquals(CustomSetting,null);
        assertNotEquals(CustomSetting,DefaultSetting);
    }

    @Test
    void hashCode_equals() {
        assertEquals(CustomSetting.hashCode(),CustomSetting2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        assertNotEquals(CustomSetting.hashCode(), DefaultSetting.hashCode());
    }

    @Test
    void getDictionaryPanelOrientation() {
        assertEquals(CustomSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void toggleDictionaryPanelOrientation() {
        assertEquals(CustomSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
        CustomSetting.toggleDictionaryPanelOrientation();
        assertEquals(CustomSetting.getDictionaryPanelOrientation(), Orientation.VERTICAL);
        CustomSetting.toggleDictionaryPanelOrientation();
        assertEquals(CustomSetting.getDictionaryPanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void getNotePanelOrientation() {
        assertEquals(CustomSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
    }

    @Test
    void toggleNotePanelOrientation() {
        assertEquals(CustomSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
        CustomSetting.toggleNotePanelOrientation();
        assertEquals(CustomSetting.getNotePanelOrientation(), Orientation.VERTICAL);
        CustomSetting.toggleNotePanelOrientation();
        assertEquals(CustomSetting.getNotePanelOrientation(), Orientation.HORIZONTAL);
    }
}
