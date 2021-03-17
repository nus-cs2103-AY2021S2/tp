package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.CommandAlias;

/**
 * An Immutable Aliases that is serializable to JSON format.
 */
@JsonRootName(value = "aliases")
class JsonSerializableAliases {

    public static final String MESSAGE_DUPLICATE_ALIAS = "Aliases contains duplicate alias(s).";

    private final List<JsonAdaptedCommandAlias> aliases = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAliases} with the given aliases.
     */
    @JsonCreator
    public JsonSerializableAliases(@JsonProperty("aliases") List<JsonAdaptedCommandAlias> aliases) {
        this.aliases.addAll(aliases);
    }

    /**
     * Converts a given {@code ReadOnlyUniqueAliasMap} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAliases}.
     */
    public JsonSerializableAliases(ReadOnlyUniqueAliasMap source) {
        aliases.addAll(source.getAliases().values().stream().map(JsonAdaptedCommandAlias::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this aliases into the model's {@code UniqueAliasMap} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniqueAliasMap toModelType() throws IllegalValueException {
        UniqueAliasMap uniqueAliasMap = new UniqueAliasMap();
        for (JsonAdaptedCommandAlias jsonAdaptedCommandAlias : this.aliases) {
            CommandAlias commandAlias = jsonAdaptedCommandAlias.toModelType();
            if (uniqueAliasMap.hasCommandAlias(commandAlias)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALIAS);
            }
            uniqueAliasMap.addAlias(commandAlias);
        }
        return uniqueAliasMap;
    }

}
