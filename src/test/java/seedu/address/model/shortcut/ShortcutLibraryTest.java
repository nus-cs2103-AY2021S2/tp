package seedu.address.model.shortcut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME;
import static seedu.address.model.shortcut.ShortcutLibrary.NO_SHORTCUT_FEEDBACK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalShortcuts.getTypicalShortcutLibrary;

import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ShortcutLibraryTest {

    private final ShortcutLibrary shortcutLibrary = new ShortcutLibrary();
    private final ShortcutLibrary shortcutLibraryWithMap = new ShortcutLibrary(new HashMap<>());

    @Test
    public void constructor() {
        assertEquals(Collections.emptyMap(), shortcutLibrary.getShortcuts());
    }

    @Test
    public void constructorWithMap() {
        assertEquals(Collections.emptyMap(), shortcutLibraryWithMap.getShortcuts());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> shortcutLibrary.resetData(null));
    }

    @Test
    public void resetData_withValidShortcutLibrary_replacesData() {
        ShortcutLibrary newData = getTypicalShortcutLibrary();
        shortcutLibrary.resetData(newData.getShortcuts());
        assertEquals(newData, shortcutLibrary);
    }

    @Test
    public void hasShortcut_nullShortcut_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> shortcutLibrary.hasShortcut(null));
    }

    @Test
    public void hasShortcut_shortcutNotInShortcutLibrary_returnsFalse() {
        assertFalse(shortcutLibrary.hasShortcut(VALID_SHORTCUT_NAME));
    }

    @Test
    public void hasShortcut_shortcutInShortcutLibrary_returnsTrue() {
        shortcutLibrary.addShortcut(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);
        assertTrue(shortcutLibrary.hasShortcut(VALID_SHORTCUT_NAME));
    }

    @Test
    public void isEmpty_hasShortcuts_returnsFalse() {
        shortcutLibrary.addShortcut(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);
        assertFalse(shortcutLibrary.isEmpty());
    }

    @Test
    public void isEmpty_noShortcuts_returnsTrue() {
        assertTrue(shortcutLibrary.isEmpty());
    }

    @Test
    public void getAllShortcutsInString_noShortcuts_returnNoShortcutFeedback() {
        assertTrue(shortcutLibrary.getAllShortcutsInString().equals(NO_SHORTCUT_FEEDBACK));
    }

}
