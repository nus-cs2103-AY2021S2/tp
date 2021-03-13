package seedu.smartlib.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";

    private final String bookName;
    private final String readerName;
    private final String dateBorrowed;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("bookName") String bookName, @JsonProperty("readerName") String readerName,
                             @JsonProperty("dateBorrowed") String dateBorrowed) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        bookName = source.getBookName().fullName;
        readerName = source.getReaderName().fullName;
        dateBorrowed = source.getDateBorrowed().toString();
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {

        if (bookName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(bookName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelBookName = new Name(bookName);


        if (readerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(readerName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelReaderName = new Name(readerName);

        if (dateBorrowed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DateBorrowed.class.getSimpleName()));
        }
        if (!DateBorrowed.isValidDate(dateBorrowed)) {
            throw new IllegalValueException(DateBorrowed.MESSAGE_CONSTRAINTS);
        }
        final DateBorrowed modelDateBorrowed = new DateBorrowed(dateBorrowed);

        return new Record(modelBookName, modelReaderName, modelDateBorrowed);
    }

}
