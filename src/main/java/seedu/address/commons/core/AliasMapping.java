package seedu.address.commons.core;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;

public class AliasMapping implements Serializable {
    private Map<String, Alias> mapping;

    public AliasMapping() {
        this.mapping = new HashMap<>();
    }

    private AliasMapping(AliasMapping aliasMapping) {
        requireNonNull(aliasMapping);
        this.mapping = new HashMap<>(aliasMapping.mapping);
    }

    public Alias getAlias(String aliasName) {
        return mapping.get(aliasName);
    }

    public void addAlias(Alias alias) {
        mapping.put(alias.getAliasName(), alias);
    }

    public boolean containsAlias(String aliasName) {
        return mapping.containsKey(aliasName);
    }

    public boolean isReservedKeyword(String aliasName) {
        switch (aliasName) {
        case AddCommand.COMMAND_WORD:
        case AliasCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
            return true;
        default:
            return false;
        }
    }

    public boolean aliasCommandWordContainsAlias(String commandWord) {
        return mapping.containsKey(commandWord);
    }

    @Override
    public int hashCode() {
        return mapping.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AliasMapping)) {
            return false;
        }

        AliasMapping other = (AliasMapping) obj;

        // contains the same keys, for the keys it contains, it maps to the same inputs
        if (!mapping.keySet().equals(other.mapping.keySet())) {
            return false;
        }
        return mapping
                .keySet()
                .stream()
                .allMatch(key -> mapping.get(key).equals(other.mapping.get(key)));
    }
}