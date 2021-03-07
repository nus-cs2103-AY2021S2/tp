package seedu.smartlib.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;

/**
 * An Immutable SmartLib that is serializable to JSON format.
 */
@JsonRootName(value = "smartlib")
class JsonSerializableSmartLib {

    public static final String MESSAGE_DUPLICATE_READER = "Readers list contains duplicate reader(s).";

    private final List<JsonAdaptedReader> readers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSmartLib} with the given readers.
     */
    @JsonCreator
    public JsonSerializableSmartLib(@JsonProperty("readers") List<JsonAdaptedReader> readers) {
        this.readers.addAll(readers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSmartLib}.
     */
    public JsonSerializableSmartLib(ReadOnlySmartLib source) {
        readers.addAll(source.getReaderList().stream().map(JsonAdaptedReader::new).collect(Collectors.toList()));
    }

    /**
     * Converts this smartlib into the model's {@code SmartLib} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SmartLib toModelType() throws IllegalValueException {
        SmartLib smartLib = new SmartLib();
        for (JsonAdaptedReader jsonAdaptedReader : readers) {
            Reader reader = jsonAdaptedReader.toModelType();
            if (smartLib.hasReader(reader)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_READER);
            }
            smartLib.addReader(reader);
        }
        return smartLib;
    }

}
