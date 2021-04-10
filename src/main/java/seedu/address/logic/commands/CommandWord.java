package seedu.address.logic.commands;

/**
 * Collection of command words.
 */
public enum CommandWord {
    ADD("add"),
    ADDSHORTCUT("addshortcut"),
    CLEAR("clear"),
    CLEARSHORTCUT("clearshortcut"),
    DELETE("delete"),
    DELETESHORTCUT("deleteshortcut"),
    EDIT("edit"),
    EDITSHORTCUT("editshortcut"),
    EXIT("exit"),
    FIND("find"),
    HELP("help"),
    LIST("list"),
    LISTSHORTCUT("listshortcut"),
    LOCK("lock"),
    POLICY("policy"),
    SORT("sort"),
    UNLOCK("unlock");

    private final String command;

    CommandWord(String command) {
        this.command = command;
    }

    /**
     * Returns true if {@code test} is contained in the values of CommandWord.
     */
    public static boolean contains(String test) {
        for (CommandWord c : CommandWord.values()) {
            if (c.toString().equals(test)) {
                return true;
            }
        }
        return false;
    }

    @Override public String toString() {
        return command;
    }
}
