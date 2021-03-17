package seedu.address.testutil;

import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.CommandAlias;

/**
 * A utility class to help with building UniqueAliasMap objects.
 * Example usage: <br>
 *     {@code UniqueAliasMap aliases = new UniqueAliasMapBuilder().withCommandAlias("a").withCommandAlias("e").build();}
 */
public class UniqueAliasMapBuilder {

    private UniqueAliasMap aliases;

    public UniqueAliasMapBuilder() {
        aliases = new UniqueAliasMap();
    }

    public UniqueAliasMapBuilder(UniqueAliasMap aliases) {
        this.aliases = aliases;
    }

    /**
     * Adds a new {@code CommandAlias} to the {@code UniqueAliasMap} that we are building.
     */
    public UniqueAliasMapBuilder withCommandAlias(CommandAlias commandAlias) {
        aliases.addAlias(commandAlias);
        return this;
    }

    public UniqueAliasMap build() {
        return aliases;
    }

}
