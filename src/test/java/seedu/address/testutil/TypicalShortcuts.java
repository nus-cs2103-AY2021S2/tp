package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.shortcut.Shortcut;
import seedu.address.model.shortcut.ShortcutLibrary;

public class TypicalShortcuts {
    public static final Shortcut FIRST = new Shortcut("ls", "listshortcut");
    public static final Shortcut SECOND = new Shortcut("sna", "sort -n -asc");
    public static final Shortcut THIRD = new Shortcut("fa", "find n/alex");
    public static final Shortcut FOURTH = new Shortcut("h", "help");
    public static final Shortcut FIFTH = new Shortcut("listphone", "list -p");

    private TypicalShortcuts() {} // prevents instantiation

    /**
     * Returns an {@code ShortcutLibrary} with all the typical shortcuts.
     */
    public static ShortcutLibrary getTypicalShortcutLibrary() {
        ShortcutLibrary sl = new ShortcutLibrary();
        for (Shortcut shortcut : getTypicalShortcuts()) {
            sl.addShortcut(shortcut.getShortcutName(), shortcut.getShortcutCommand());
        }
        return sl;
    }

    public static List<Shortcut> getTypicalShortcuts() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH));
    }
}


