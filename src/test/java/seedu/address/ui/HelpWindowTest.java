package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import guitests.guihandles.HelpWindowHandle;

/**
 * @@author {se-edu}-reused
 * Reused with modification from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Contains tests for the {@code HelpWindow}.
 */
public class HelpWindowTest extends GuiUnitTest {

    private HelpWindow helpWindow;
    private HelpWindowHandle helpWindowHandle;

    @BeforeEach
    public void setUp() throws Exception {
        guiRobot.interact(() -> helpWindow = new HelpWindow());
        FxToolkit.registerStage(helpWindow::getRoot);
        helpWindowHandle = new HelpWindowHandle(helpWindow.getRoot());
    }

    @Test
    public void display() throws Exception {
        FxToolkit.showStage();
        assertEquals(HelpWindow.HELP_MESSAGE, helpWindowHandle.getHelpMessage());
    }

    @Test
    public void isShowing_helpWindowIsShowing_returnsTrue() {
        guiRobot.interact(helpWindow::show);
        assertTrue(helpWindow.isShowing());
    }

    @Test
    public void isShowing_helpWindowIsHiding_returnsFalse() {
        assertFalse(helpWindow.isShowing());
    }

}
