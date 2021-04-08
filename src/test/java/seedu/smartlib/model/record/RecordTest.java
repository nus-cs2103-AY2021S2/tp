package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_AUTHOR_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PUBLISHER_HARRY;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.HARRY_PORTER;
import static seedu.smartlib.testutil.TypicalModels.MAZE;
import static seedu.smartlib.testutil.TypicalModels.PROMISE_LAND;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;
import static seedu.smartlib.testutil.TypicalModels.RECORD_B;
import static seedu.smartlib.testutil.TypicalModels.RECORD_C;
import static seedu.smartlib.testutil.TypicalModels.SECRET;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.testutil.Assert;
import seedu.smartlib.testutil.BookBuilder;

public class RecordTest {

    @Test
    public void constructor_withDateBorrowedOnly() {
        // EP: valid params -> no exceptions thrown
        assertDoesNotThrow(
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed()
                )
        );

        // EP: null params -> throws NullPointerException
        assertThrows(NullPointerException.class,
                () -> new Record(
                        new Name(null),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed()
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        new Name(null),
                        RECORD_A.getDateBorrowed()
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        new DateBorrowed((LocalDateTime) null)
                )
        );
    }

    @Test
    public void constructor_withDateReturnedOnly() {
        // EP: valid params -> no exceptions thrown
        assertDoesNotThrow(
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        new DateReturned(LocalDateTime.now())
                )
        );

        // EP: null params -> throws NullPointerException
        assertThrows(NullPointerException.class,
                () -> new Record(
                        new Name(null),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        new DateReturned(LocalDateTime.now())
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        new Name(null),
                        new DateReturned(LocalDateTime.now())
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        new DateReturned((LocalDateTime) null)
                )
        );
    }

    @Test
    public void constructor_withDateBorrowedAndDateReturned() {
        // EP: valid params -> no exceptions thrown
        assertDoesNotThrow(
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed(),
                        new DateReturned(LocalDateTime.now())
                )
        );

        // EP: null params -> throws NullPointerException
        assertThrows(NullPointerException.class,
                () -> new Record(
                        new Name(null),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed(),
                        new DateReturned(LocalDateTime.now())
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        new Name(null),
                        RECORD_A.getDateBorrowed(),
                        new DateReturned(LocalDateTime.now())
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        new DateBorrowed((LocalDateTime) null),
                        new DateReturned(LocalDateTime.now())
                )
        );
        assertThrows(NullPointerException.class,
                () -> new Record(
                        RECORD_A.getBookName(),
                        RECORD_A.getBookBarcode(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed(),
                        new DateReturned((LocalDateTime) null)
                )
        );
    }

    @Test
    public void isReturned() {
        // EP: not returned
        assertFalse(RECORD_A.isReturned());

        // EP: is returned
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed(),
                new DateReturned(LocalDateTime.now())
        );
        assertTrue(record.isReturned());
    }

    @Test
    public void getBookName() {
        assertEquals(new Name(VALID_NAME_HARRY), RECORD_A.getBookName());
    }

    @Test
    public void getBookBarcode() {
        assertEquals(new Barcode(Barcode.MAX_VALUE), RECORD_A.getBookBarcode());
    }

    @Test
    public void getReaderName() {
        assertEquals(new Name("Alex Yeoh"), RECORD_A.getReaderName());
    }

    @Test
    public void getDateBorrowed() {
        // EP: no borrow date -> returns null
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                new DateReturned(LocalDateTime.now())
        );
        assertNull(record.getDateBorrowed());

        // EP: with borrow date -> returns correct value
        assertEquals(new DateBorrowed("2020-11-23T08:30:00"), RECORD_A.getDateBorrowed());
    }

    @Test
    public void getDateReturned() {
        // EP: no return date -> returns null
        assertNull(RECORD_A.getDateReturned());

        // EP: with return date -> returns correct value
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed(),
                new DateReturned(RECORD_A.getDateBorrowed().getValue())
        );
        assertEquals(new DateReturned(RECORD_A.getDateBorrowed().getValue()), record.getDateReturned());
    }

    @Test
    public void isSameRecord() {
        // same object -> returns true
        assertTrue(RECORD_A.isSameRecord(RECORD_A));

        // null -> returns false
        assertFalse(RECORD_A.isSameRecord(null));

        // all attributes same -> returns true
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        assertTrue(RECORD_A.isSameRecord(record));

        // different reader name, all other attributes same -> returns false
        record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_B.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        assertFalse(RECORD_A.isSameRecord(record));

        // different barcode, all other attributes same -> returns false
        record = new Record(
                RECORD_A.getBookName(),
                RECORD_B.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        assertFalse(RECORD_A.isSameRecord(record));

        // one has date returned but one doesn't, all other attributes same -> returns true
        record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed(),
                new DateReturned(RECORD_A.getDateBorrowed().getValue())
        );
        assertTrue(RECORD_A.isSameRecord(record));

        // different return date, all other attributes same -> returns false
        Record record2 = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed(),
                new DateReturned(RECORD_C.getDateBorrowed().getValue())
        );
        assertFalse(record.isSameRecord(record2));

        // one has date borrowed but one doesn't, all other attributes same -> returns true
        record2 = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                new DateReturned(RECORD_A.getDateBorrowed().getValue())
        );
        assertTrue(record.isSameRecord(record2));

        // different borrow date, all other attributes same -> returns false
        record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_B.getDateBorrowed()
        );
        assertFalse(RECORD_A.isSameRecord(record));
    }

    @Test
    public void getBorrowDuration() {
        // EP: empty borrow date -> throw AssertionError
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                new DateReturned(RECORD_A.getDateBorrowed().getValue())
        );
        assertThrows(AssertionError.class, record::getBorrowDuration);

        // EP: empty return date -> throw AssertionError
        assertThrows(AssertionError.class, RECORD_A::getBorrowDuration);

        // EP: with return date -> returns correct Duration
        record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed(),
                new DateReturned(RECORD_A.getDateBorrowed().getValue())
        );
        assertEquals(Duration.ZERO, record.getBorrowDuration());
    }

    @Test
    public void equals() {
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );

        // null -> returns false
        assertFalse(record.equals(null));

        // different types -> returns false
        assertFalse(record.equals(0.5f));
        assertFalse(record.equals(" "));

        // same object -> returns true
        assertTrue(record.equals(record));

        // same values -> returns true
        assertTrue(record.equals(RECORD_A));

        // different values -> returns false
        assertFalse(record.equals(RECORD_B));
    }

    @Test
    public void tostring() {
        Record record = new Record(
                RECORD_A.getBookName(),
                RECORD_A.getBookBarcode(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );

        // same object -> returns same toString value
        assertEquals(record.toString(), record.toString());

        // same values -> returns same toString value
        assertEquals(record.toString(), RECORD_A.toString());

        // different values -> returns different toString value
        assertNotEquals(record.toString(), RECORD_B.toString());
    }

}
