package seedu.address.logic.commands;

import static seedu.address.logic.parser.SelectIndexCommandParser.SPECIAL_INDEX;

/**
 * Represents a select command with hidden internal logic and the ability to be executed.
 */
public abstract class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";
    public static final String SHOW_SUB_COMMAND_WORD = "show";
    public static final String CLEAR_SUB_COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select or show selected person(s).\n"
            + "Parameters: { clear | show | shown | INDEX... }\n"
            + "Sub Commands: " + SHOW_SUB_COMMAND_WORD + " " + CLEAR_SUB_COMMAND_WORD + "\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + SHOW_SUB_COMMAND_WORD + "\n"
            + COMMAND_WORD + " " + CLEAR_SUB_COMMAND_WORD + "\n"
            + COMMAND_WORD + " " + SPECIAL_INDEX + "\n"
            + COMMAND_WORD + " 1 2 5";
}
