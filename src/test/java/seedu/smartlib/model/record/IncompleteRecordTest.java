package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_HARRY;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Barcode;

public class IncompleteRecordTest {

    @Test
    public void constructor_withDateBorrowed() {
        // EP: valid params -> no exceptions thrown
        assertDoesNotThrow(() -> new IncompleteRecord(
                        RECORD_A.getBookName(),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed()
                ));

        // EP: null params -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> new IncompleteRecord(
                        new Name(null),
                        RECORD_A.getReaderName(),
                        RECORD_A.getDateBorrowed()
                ));
        assertThrows(NullPointerException.class, () -> new IncompleteRecord(
                        RECORD_A.getBookName(),
                        new Name(null),
                        RECORD_A.getDateBorrowed()
                ));
        assertThrows(NullPointerException.class, () -> new IncompleteRecord(
                        RECORD_A.getBookName(),
                        RECORD_A.getReaderName(),
                        new DateBorrowed((LocalDateTime) null)
                ));
    }

    @Test
    public void constructor_withDateReturned() {
        // EP: valid params -> no exceptions thrown
        assertDoesNotThrow(() -> new IncompleteRecord(
                        RECORD_A.getBookBarcode(),
                        new DateReturned(LocalDateTime.now())
                ));

        // EP: null DateReturned -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> new IncompleteRecord(
                        RECORD_A.getBookBarcode(),
                        new DateReturned((LocalDateTime) null)
                ));
    }

    @Test
    public void getBookName() {
        IncompleteRecord incompleteRecord = new IncompleteRecord(
                RECORD_A.getBookName(),
                RECORD_A.getReaderName(),
                RECORD_A.getDateBorrowed()
        );
        assertEquals(new Name(VALID_NAME_HARRY), incompleteRecord.getBookName());
    }

    @Test
    public void getBookBarcode() {
        IncompleteRecord incompleteRecord = new IncompleteRecord(
                RECORD_A.getBookBarcode(),
                new DateReturned(LocalDateTime.now())
        );
        assertEquals(new Barcode(Barcode.MAX_VALUE), incompleteRecord.getBookBarcode());
    }

}
