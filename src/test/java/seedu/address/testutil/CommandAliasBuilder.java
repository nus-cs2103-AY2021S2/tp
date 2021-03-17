package seedu.address.testutil;

import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;

/**
 * A utility class to help with building CommandAlias objects.
 */
public class CommandAliasBuilder {

    public static final String DEFAULT_ALIAS = "aa";
    public static final String DEFAULT_COMMAND = "alias add";

    private Alias alias;
    private Command command;

    /**
     * Creates a {@code CommandAliasBuilder} with the default values.
     */
    public CommandAliasBuilder() {
        alias = new Alias(DEFAULT_ALIAS);
        command = new Command(DEFAULT_COMMAND);
    }

    /**
     * Initializes the CommandAliasBuilder with the data of {@code commandAliasToCopy}.
     */
    public CommandAliasBuilder(CommandAlias commandAliasToCopy) {
        alias = commandAliasToCopy.getAlias();
        command = commandAliasToCopy.getCommand();
    }

    /**
     * Sets the {@code Alias} of the {@code CommandAlias} that we are building.
     */
    public CommandAliasBuilder withAlias(String alias) {
        this.alias = new Alias(alias);
        return this;
    }

    /**
     * Sets the {@code Command} of the {@code CommandAlias} that we are building.
     */
    public CommandAliasBuilder withCommand(String command) {
        this.command = new Command(command);
        return this;
    }

    public CommandAlias build() {
        return new CommandAlias(alias, command);
    }

}
