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
 * An Immutable AliasMap that is serializable to JSON format.
 */
@JsonRootName(value = "aliasmap")
class JsonSerializableAliasMap {

    public static final String MESSAGE_DUPLICATE_ALIAS = "Command aliases contains duplicate alias(es).";

    private final List<JsonAdaptedCommandAlias> commandAliases = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAliasMap} with the given command aliases.
     */
    @JsonCreator
    public JsonSerializableAliasMap(@JsonProperty("commandAliases") List<JsonAdaptedCommandAlias> commandAliases) {
        this.commandAliases.addAll(commandAliases);
    }

    /**
     * Converts a given {@code ReadOnlyUniqueAliasMap} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAliasMap}.
     */
    public JsonSerializableAliasMap(ReadOnlyUniqueAliasMap source) {
        commandAliases.addAll(source.getCommandAliases().values().stream().map(JsonAdaptedCommandAlias::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this command aliases into the model's {@code UniqueAliasMap} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniqueAliasMap toModelType() throws IllegalValueException {
        UniqueAliasMap uniqueAliasMap = new UniqueAliasMap();
        for (JsonAdaptedCommandAlias jsonAdaptedCommandAlias : this.commandAliases) {
            CommandAlias commandAlias = jsonAdaptedCommandAlias.toModelType();
            if (uniqueAliasMap.hasCommandAlias(commandAlias)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALIAS);
            }
            uniqueAliasMap.addAlias(commandAlias);
        }
        return uniqueAliasMap;
    }

}
