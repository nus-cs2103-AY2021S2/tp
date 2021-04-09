package seedu.smartlib.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.DateReturned;
import seedu.smartlib.model.record.Record;

/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";

    private final String bookName;
    private final String barcode;
    private final String readerName;
    private final String dateBorrowed;
    private final String dateReturned;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     *
     * @param bookName associated with the record.
     * @param barcode barcode associated with the record.
     * @param readerName name of the reader associated with the record.
     * @param dateBorrowed borrow date associated with the record.
     * @param dateReturned return date associated with the record.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("bookName") String bookName,
                             @JsonProperty("barcode") String barcode,
                             @JsonProperty("readerName") String readerName,
                             @JsonProperty("dateBorrowed") String dateBorrowed,
                             @JsonProperty("dateReturned") String dateReturned) {
        this.bookName = bookName;
        this.barcode = barcode;
        this.readerName = readerName;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     *
     * @param source record to be converted.
     */
    public JsonAdaptedRecord(Record source) {
        bookName = source.getBookName().toString();
        barcode = source.getBookBarcode().toString();
        readerName = source.getReaderName().toString();
        dateBorrowed = source.getDateBorrowed().toString();

        if (source.getDateReturned() == null) {
            dateReturned = null;
        } else {
            dateReturned = source.getDateReturned().toString();
        }
    }

    /**
     * Verifies validity of the book name.
     *
     * @throws IllegalValueException if the book name is null or invalid.
     */
    private void verifyBookName() throws IllegalValueException {
        if (bookName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(bookName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the book barcode.
     *
     * @throws IllegalValueException if the book barcode is null or invalid.
     */
    private void verifyBookBarcode() throws IllegalValueException {
        if (barcode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Barcode.class.getSimpleName()));
        }
        if (!Barcode.isValidBarcode(Integer.parseInt(barcode))) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the reader name.
     *
     * @throws IllegalValueException if the reader name is null or invalid.
     */
    private void verifyReaderName() throws IllegalValueException {
        if (readerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(readerName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the borrow date.
     *
     * @throws IllegalValueException if the borrow date is null or invalid.
     */
    private void verifyDateBorrowed() throws IllegalValueException {
        if (dateBorrowed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateBorrowed.class.getSimpleName()));
        }
        if (!DateBorrowed.isValidDate(dateBorrowed)) {
            throw new IllegalValueException(DateBorrowed.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code record} object.
     *
     * @return Record object converted from the storage file.
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {

        verifyBookName();
        final Name modelBookName = new Name(bookName);

        verifyBookBarcode();
        final Barcode modelBookBarcode = new Barcode(Integer.parseInt(barcode));

        verifyReaderName();
        final Name modelReaderName = new Name(readerName);

        verifyDateBorrowed();
        final DateBorrowed modelDateBorrowed = new DateBorrowed(dateBorrowed);

        final DateReturned modelDateReturned;
        if (dateReturned == null) {
            modelDateReturned = null;
        } else {
            modelDateReturned = new DateReturned(dateReturned);
        }
        return new Record(modelBookName, modelBookBarcode, modelReaderName, modelDateBorrowed, modelDateReturned);
    }

}
