package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.reader.UniqueReaderList;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.record.UniqueRecordList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SmartLib implements ReadOnlySmartLib {

    private final UniqueReaderList readers;
    private final UniqueRecordList records;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        readers = new UniqueReaderList();
        records = new UniqueRecordList();
    }

    public SmartLib() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public SmartLib(ReadOnlySmartLib toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setReaders(List<Reader> readers) {
        this.readers.setReaders(readers);
    }

    /**
     * Replaces the contents of the record list with {@code records}.
     * {@code records} must not contain duplicate records.
     */
    public void setRecords(List<Record> records) {
        this.records.setRecords(records);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlySmartLib newData) {
        requireNonNull(newData);

        setReaders(newData.getReaderList());
        setRecords(newData.getRecordList());
    }

    //// person-level operations

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     */
    public boolean hasReader(Reader reader) {
        requireNonNull(reader);
        return readers.contains(reader);
    }

    /**
     * Returns true if a record with the same identity as {@code record} exists in the registered record base.
     */
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return records.contains(record);
    }

    /**
     * Adds a reader to the registered reader base.
     * The reader must not already exist in the registered reader base.
     */
    public void addReader(Reader p) {
        readers.addReader(p);
    }

    /**
     * Adds a record to the registered record base.
     * The record must not already exist in the registered record base.
     */
    public void addRecord(Record r) {
        records.addRecord(r);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setReader(Reader target, Reader editedReader) {
        requireNonNull(editedReader);

        readers.setReader(target, editedReader);
    }

    /**
     * Removes {@code key} from this {@code SmartLib}.
     * {@code key} must exist in the SmartLib registered reader base.
     */
    public void removeReader(Reader key) {
        readers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return readers.asUnmodifiableObservableList().size() + " persons"
                + "\n" + records.asUnmodifiableObservableList().size() + " records";
        // TODO: refine later
    }

    @Override
    public ObservableList<Reader> getReaderList() {
        return readers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Record> getRecordList() {
        return records.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SmartLib // instanceof handles nulls
                && readers.equals(((SmartLib) other).readers));
    }

    @Override
    public int hashCode() {
        return readers.hashCode();
    }
}
