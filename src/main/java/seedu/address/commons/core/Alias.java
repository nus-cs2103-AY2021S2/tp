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
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Alias name can only contain alphanumeric characters and cannot be empty";
    public static final String MESSAGE_COMMAND_CONSTRAINTS = "Command cannot be empty";

    public static final String NAME_REGEX = "\\p{Alnum}+";

    private final String aliasName;
    private final String command;

    /**
     * Creates an Alias object.
     * All fields should not be null.
     *
     * @param aliasName Name of alias.
     * @param command Command content.
     * @throws NullPointerException If the input is null.
     */
    public Alias(String aliasName, String command) {
        requireAllNonNull(aliasName, command);
        checkArgument(isValidName(aliasName), MESSAGE_NAME_CONSTRAINTS);
        checkArgument(isValidCommand(command), MESSAGE_COMMAND_CONSTRAINTS);

        this.aliasName = aliasName;
        this.command = command;
    }

    /**
     * Validates alias name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the name matches the regex pattern and is not empty.
     */
    public static boolean isValidName(String aliasName) {
        return Pattern.matches(NAME_REGEX, aliasName) && !aliasName.isEmpty();
    }

    /**
     * Validates command.
     *
     * @param command Command string.
     * @return Whether the command is not empty.
     */
    public static boolean isValidCommand(String command) {
        return !command.isEmpty();
    }

    public String getAliasName() {
        assert aliasName != null;
        return aliasName;
    }

    public String getCommand() {
        assert aliasName != null;
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
