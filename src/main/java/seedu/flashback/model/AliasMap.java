package seedu.flashback.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import seedu.flashback.logic.commands.AddCommand;
import seedu.flashback.logic.commands.AliasCommand;
import seedu.flashback.logic.commands.ClearCommand;
import seedu.flashback.logic.commands.DeleteCommand;
import seedu.flashback.logic.commands.EditCommand;
import seedu.flashback.logic.commands.ExitCommand;
import seedu.flashback.logic.commands.FilterCommand;
import seedu.flashback.logic.commands.FindCommand;
import seedu.flashback.logic.commands.HelpCommand;
import seedu.flashback.logic.commands.ListCommand;
import seedu.flashback.logic.commands.RedoCommand;
import seedu.flashback.logic.commands.ReviewCommand;
import seedu.flashback.logic.commands.SortCommand;
import seedu.flashback.logic.commands.StatsCommand;
import seedu.flashback.logic.commands.UndoCommand;
import seedu.flashback.logic.commands.ViewCommand;

/**
 * Contains all mapping for command and alias.
 */
public class AliasMap {

    public static final String ALPHANUMERICAL_REGEX = "\\p{Alnum}+";
    private static final String[] commandsWord = {
        AddCommand.COMMAND_WORD,
        AliasCommand.COMMAND_WORD,
        ClearCommand.COMMAND_WORD,
        DeleteCommand.COMMAND_WORD,
        EditCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD,
        FilterCommand.COMMAND_WORD,
        FindCommand.COMMAND_WORD,
        HelpCommand.COMMAND_WORD,
        ListCommand.COMMAND_WORD,
        RedoCommand.COMMAND_WORD,
        ReviewCommand.COMMAND_WORD,
        SortCommand.COMMAND_WORD,
        StatsCommand.COMMAND_WORD,
        UndoCommand.COMMAND_WORD,
        ViewCommand.COMMAND_WORD
    };
    private static final String[] reviewWords = {
        "n",
        "p",
        "a",
        "h",
        "t",
        "f",
        "q"
    };

    private HashMap<String, String> aliasMap;

    public AliasMap() {
        aliasMap = new HashMap<>();
    }

    public void setAliasMap(HashMap<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Returns true if {@code addAlias()} can be added to flashback
     */
    public boolean canAddAlias(String command, String alias) {
        if (aliasMap.containsKey(alias)) {
            return false;
        }
        if (aliasMap.containsValue(alias)) {
            return false;
        }
        if (isCommand(alias)) {
            return false;
        }
        if (!isCommand(command)) {
            return false;
        }
        return true;
    }

    /**
     * Adds an alias into aliasMap
     */
    public void addAlias(String command, String alias) {
        if (!canAddAlias(command, alias)) {
            throw new UnableToAddAliasException();
        }
        if (aliasMap.containsKey(command)) {
            aliasMap.replace(command, alias);
        } else {
            aliasMap.put(command, alias);
        }
    }

    /**
     * Parses alias to the command text
     */
    public String parseAlias(String input) {
        if (!isAlias(input)) {
            return input;
        }
        for (Map.Entry<String, String> entry: aliasMap.entrySet()) {
            if (entry.getValue().equals(input)) {
                return entry.getKey();
            }
        }
        return input;
    }

    /**
     * Returns true if input is an alias
     */
    public boolean isAlias(String input) {
        return aliasMap.containsValue(input);
    }

    /**
     * Returns true if input is a command
     */
    public boolean isCommand(String input) {
        return Arrays.stream(commandsWord).anyMatch(c -> c.equals(input));
    }

    /**
     * Returns true if input is a review command
     */
    public boolean isReview(String input) {
        return Arrays.stream(reviewWords).anyMatch(c -> c.equals(input));
    }

    /**
     * Returns true if aliasMap is corrupted
     */
    public boolean isCorrupted() {
        for (Map.Entry<String, String> entry: aliasMap.entrySet()) {
            if (!Arrays.asList(commandsWord).contains(entry.getKey())) {
                return true;
            }
            if (Arrays.asList(commandsWord).contains(entry.getValue())) {
                return true;
            }
            if (Arrays.asList(reviewWords).contains(entry.getValue())) {
                return true;
            }
            if (!entry.getValue().matches(ALPHANUMERICAL_REGEX)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasMap // instanceof handles nulls
                && aliasMap.equals(((AliasMap) other).aliasMap));
    }

    public static class UnableToAddAliasException extends RuntimeException {
        private UnableToAddAliasException() {
            super("Alias cannot be added. Please check if a valid command is entered, and alias name is unique.");
        }
    }
}
