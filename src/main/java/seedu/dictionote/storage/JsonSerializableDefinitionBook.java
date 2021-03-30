package seedu.dictionote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.DefinitionBook;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.dictionary.Definition;

/**
 * An Immutable DefinitionBook that is serializable to JSON format.
 */
@JsonRootName(value = "definitionbook")
class JsonSerializableDefinitionBook {

    public static final String MESSAGE_DUPLICATE_DEFINITIONS = "definition list contains duplicate definition(s).";

    private final List<JsonAdaptedDefinition> definitions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDefinitionBook} with the given definition.
     */
    @JsonCreator
    public JsonSerializableDefinitionBook(@JsonProperty("definitions") List<JsonAdaptedDefinition> definitions) {
        this.definitions.addAll(definitions);
    }

    /**
     * Converts a given {@code ReadOnlyDefinitionBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDefinitionsBook}.
     */
    public JsonSerializableDefinitionBook(ReadOnlyDefinitionBook source) {
        definitions.addAll(source.getDefinitionList().stream()
                .map(JsonAdaptedDefinition::new).collect(Collectors.toList()));
    }

    /**
     * Converts this dictionote book into the model's {@code DefinitionBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DefinitionBook toModelType() throws IllegalValueException {
        DefinitionBook definitionBook = new DefinitionBook();
        for (JsonAdaptedDefinition jsonAdaptedDefinition : definitions) {
            Definition definition = jsonAdaptedDefinition.toModelType();
            if (definitionBook.hasDefinition(definition)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DEFINITIONS);
            }
            definitionBook.addDefinition(definition);
        }
        return definitionBook;
    }

}
