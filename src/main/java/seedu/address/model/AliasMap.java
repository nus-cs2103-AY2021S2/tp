package seedu.address.model;

import java.util.HashMap;

/**
 * Contains all mapping for command and alias.
 */
public class AliasMap {
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

    public static class UnableToAddAliasException extends RuntimeException {
        private UnableToAddAliasException() {
            super("Alias cannot be added, please make sure that the alias is unique and not an original command.");
        }
    }
}
