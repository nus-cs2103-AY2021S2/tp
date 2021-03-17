package seedu.address.model.alias;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Alias and Command in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CommandAlias {

    private final Alias alias;
    private final Command command;

    /**
     * Every field must be present and not null.
     */
    public CommandAlias(Alias alias, Command command) {
        requireAllNonNull(alias, command);
        this.alias = alias;
        this.command = command;
    }

    public Alias getAlias() {
        return alias;
    }

    public Command getCommand() {
        return command;
    }

    /**
     * Returns true if both command aliases have the same alias name.
     * This defines a weaker notion of equality between two command alias.
     */
    public boolean isSameCommandAlias(CommandAlias otherCommandAlias) {
        if (otherCommandAlias == this) {
            return true;
        }

        return otherCommandAlias != null
                && otherCommandAlias.getAlias().equals(getAlias());
    }

    /**
     * Returns true if both aliases have the same identity and data fields.
     * This defines a stronger notion of equality between two aliases.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandAlias
                && getAlias().equals(((CommandAlias) other).getAlias()) // instanceof handles nulls
                && getCommand().equals(((CommandAlias) other).getCommand())); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(alias, command);
    }

    @Override
    public String toString() {
        return "alias " + getAlias() + "='" + getCommand() + "'";
    }

}
