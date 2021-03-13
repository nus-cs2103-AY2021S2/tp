package seedu.address.commons.core;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.Objects;

public class Alias implements Serializable {
    private final String aliasName;
    private final String command;

    public Alias(String alias, String command) {
        requireAllNonNull(alias, command);

        this.aliasName = alias;
        this.command = command;
    }

    public String getAliasName() {
        return aliasName;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliasName, command);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Alias)) {
            return false;
        }

        Alias other = (Alias) obj;
        return aliasName.equals(other.aliasName)
                && command.equals(other.command);
    }

    @Override
    public String toString() {
        return "Alias: " + aliasName + " Command: " + command;
    }
}