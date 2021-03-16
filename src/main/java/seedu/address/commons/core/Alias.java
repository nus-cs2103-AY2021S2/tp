//Solution below adapted from https://github.com/briyanii/main
package seedu.address.commons.core;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an user-defined alias for a command shortcut
 * Guarantees: fields are present and not null, field values are validated, immutable.
 */
public class Alias implements Serializable {
    public static final String MESSAGE_NAME_CONSTRAINTS = "Only alphanumeric characters are allowed in alias names";

    public static final String NAME_REGEX = "\\p{Alnum}+";

    private final String aliasName;
    private final String command;

    /**
     * Creates an empty Alias object.
     */
    public Alias() {
        this.aliasName = null;
        this.command = null;
    }

    /**
     * Creates an Alias object.
     */
    public Alias(String aliasName, String command) {
        requireAllNonNull(aliasName, command);
        checkArgument(isValidName(aliasName), MESSAGE_NAME_CONSTRAINTS);

        this.aliasName = aliasName;
        this.command = command;
    }

    /**
     * Validates alias name.
     */
    public static boolean isValidName(String aliasName) {
        return Pattern.matches(NAME_REGEX, aliasName);
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
