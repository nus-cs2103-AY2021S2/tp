//Solution below adapted from https://github.com/briyanii/main
package seedu.address.commons.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.alias.AliasCommand;
import seedu.address.logic.commands.alias.ListAliasCommand;
import seedu.address.logic.commands.alias.UnaliasCommand;
import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.CloseIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.commands.resident.ListUnallocatedResidentsCommand;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.commands.residentroom.DeallocateResidentRoomCommand;
import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.commands.room.DeleteRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.FindRoomCommand;
import seedu.address.logic.commands.room.ListRoomCommand;
import seedu.address.logic.commands.undoredo.RedoCommand;
import seedu.address.logic.commands.undoredo.UndoCommand;

/**
 * Represents the current user's {@code Alias} command mapping.
 * Guarantees: fields are present and not null, fields values are mutable
 */
public class AliasMapping implements Serializable {
    private Map<String, Alias> mapping;

    /**
     * Creates an empty AliasMapping.
     */
    public AliasMapping() {
        this.mapping = new HashMap<>();
    }

    /**
     * Sets the mapping to the mapping of the specified AliasMapping object.
     *
     * @param aliasMapping The specified AliasMapping object.
     */
    public void setAliasMapping(AliasMapping aliasMapping) {
        this.mapping = new HashMap<>(aliasMapping.mapping);
    }

    /**
     * Returns the map object.
     *
     * @return The map object.
     */
    public Map<String, Alias> getMapping() {
        return this.mapping;
    }

    /**
     * Returns an Alias object from alias name.
     *
     * @param aliasName Name of the alias.
     * @return The alias with the specified name.
     */
    public Alias getAlias(String aliasName) {
        return mapping.get(aliasName);
    }

    /**
     * Adds a new Alias object to the current mapping.
     *
     * @param alias The alias object to be added.
     */
    public void addAlias(Alias alias) {
        assert alias != null;
        mapping.put(alias.getAliasName(), alias);
    }

    /**
     * Deletes a user-defined alias from the current mapping.
     *
     * @param aliasName The name of the alias to be deleted.
     */
    public void deleteAlias(String aliasName) {
        assert aliasName != null;
        assert mapping.containsKey(aliasName);
        mapping.remove(aliasName);
    }

    /**
     * Checks if the current mapping contains an Alias based on alias name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the mapping contains the alias.
     */
    public boolean containsAlias(String aliasName) {
        return mapping.containsKey(aliasName);
    }

    /**
     * Checks if alias name is a reserved keyword.
     *
     * @param aliasName Name of the alias.
     * @return Whether the alias name is reserved.
     */
    public boolean isReservedKeyword(String aliasName) {
        switch (aliasName) {
        //====== System Commands ======
        case ClearCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case ViewHistoryCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD:

        //====== Resident Commands ======
        case AddResidentCommand.COMMAND_WORD:
        case EditResidentCommand.COMMAND_WORD:
        case DeleteResidentCommand.COMMAND_WORD:
        case FindResidentCommand.COMMAND_WORD:
        case ListResidentCommand.COMMAND_WORD:
        case ListUnallocatedResidentsCommand.COMMAND_WORD:

        //====== Room Commands ======
        case AddRoomCommand.COMMAND_WORD:
        case EditRoomCommand.COMMAND_WORD:
        case DeleteRoomCommand.COMMAND_WORD:
        case FindRoomCommand.COMMAND_WORD:
        case ListRoomCommand.COMMAND_WORD:
        case AllocateResidentRoomCommand.COMMAND_WORD:
        case DeallocateResidentRoomCommand.COMMAND_WORD:

        //====== Issue Commands ======
        case AddIssueCommand.COMMAND_WORD:
        case ListIssueCommand.COMMAND_WORD:
        case FindIssueCommand.COMMAND_WORD:
        case EditIssueCommand.COMMAND_WORD:
        case DeleteIssueCommand.COMMAND_WORD:
        case CloseIssueCommand.COMMAND_WORD:

        //====== Alias Commands ======
        case AliasCommand.COMMAND_WORD:
        case UnaliasCommand.COMMAND_WORD:
        case ListAliasCommand.COMMAND_WORD:
            return true;
        default:
            return false;
        }
    }

    /**
     * Checks if the command used is an existing alias.
     *
     * @param commandWord The command word.
     * @return Whether the command word is recursive.
     */
    public boolean isRecursiveKeyword(String commandWord) {
        return mapping.containsKey(commandWord);
    }

    /**
     * Returns the current number of user-defined aliases.
     *
     * @return Size of the current mapping.
     */
    public int size() {
        assert mapping != null;
        return mapping.size();
    }

    @Override
    public int hashCode() {
        return mapping.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AliasMapping)) {
            return false;
        }

        AliasMapping am = (AliasMapping) o;

        return mapping.keySet().stream().allMatch(key -> mapping.get(key).equals(am.mapping.get(key)));
    }

    @Override
    public String toString() {
        final String format = "%d:\t%s = %s\n";
        StringBuilder msg = new StringBuilder();
        int count = 0;
        List<String> aliasByName = new ArrayList<>(mapping.keySet());
        Collections.sort(aliasByName);

        for (String aliasName : aliasByName) {
            count++;
            msg.append(String.format(format, count, aliasName, mapping.get(aliasName).getCommand()));
        }
        return msg.toString();
    }
}
