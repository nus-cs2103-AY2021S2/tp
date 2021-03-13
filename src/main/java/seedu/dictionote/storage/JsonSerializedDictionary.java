package seedu.dictionote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.dictionary.Content;

/**
 * An Immutable NoteBook that is serializable to JSON format.
 */
@JsonRootName(value = "dictionary")
class JsonSerializableDictionary {

    public static final String MESSAGE_CONTENT_NOT_FOUND = "It seems like no such content exists.";

    private final List<JsonAdaptedContent> contents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDictionary} with the given content.
     */
    @JsonCreator
    public JsonSerializableDictionary(@JsonProperty("content") List<JsonAdaptedContent> contents) {
        this.contents.addAll(contents);
    }

    /**
     * Converts a given {@code ReadOnlyDictionary} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContent}.
     */
    public JsonSerializableDictionary(ReadOnlyDictionary source) {
        contents.addAll(source.getContentList().stream().map(JsonAdaptedContent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this dictionote book into the model's {@code Dictionary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Dictionary toModelType() throws IllegalValueException {
        Dictionary dictionary = new Dictionary();
        for (JsonAdaptedContent jsonAdaptedContent : contents) {
            Content content = jsonAdaptedContent.toModelType();
            if (!dictionary.hasContent(content)) {
                throw new IllegalValueException(MESSAGE_CONTENT_NOT_FOUND);
            }
            dictionary.addContent(content);
        }
        return dictionary;
    }

}
