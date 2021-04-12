package seedu.address.testutil;

import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.CommandAlias;

/**
 * A utility class to help with building UniqueAliasMap objects.
 * Example usage: <br>
 *     {@code UniqueAliasMap aliasMap =
 *         new UniqueAliasMapBuilder().withCommandAlias("a").withCommandAlias("e").build();}
 */
public class UniqueAliasMapBuilder {

    private UniqueAliasMap aliasMap;

    public UniqueAliasMapBuilder() {
        aliasMap = new UniqueAliasMap();
    }

    public UniqueAliasMapBuilder(UniqueAliasMap aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Adds a new {@code CommandAlias} to the {@code UniqueAliasMap} that we are building.
     */
    public UniqueAliasMapBuilder withCommandAlias(CommandAlias commandAlias) {
        aliasMap.addAlias(commandAlias);
        return this;
    }

    public UniqueAliasMap build() {
        return aliasMap;
    }

}
