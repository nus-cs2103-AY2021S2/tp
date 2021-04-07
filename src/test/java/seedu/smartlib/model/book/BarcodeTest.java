package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BarcodeTest {

    @Test
    public void constructor_invalidBarcode_throwsAssertionError() {
        int invalidBarcode = 1;
        assertThrows(AssertionError.class, () -> new Barcode(invalidBarcode));
    }

    @Test
    public void isValidBarcode() {
        // invalid barcode numbers
        assertFalse(Barcode.isValidBarcode(1000000000 - 1)); // less than Barcode.MIN_VALUE
        assertFalse(Barcode.isValidBarcode(123)); // less than Barcode.MIN_VALUE
        assertFalse(Barcode.isValidBarcode(2000000000 + 1)); // larger than Barcode.MAX_VALUE
        assertFalse(Barcode.isValidBarcode(2000000010)); // larger than Barcode.MAX_VALUE

        // valid barcode numbers
        assertTrue(Barcode.isValidBarcode(1080060009)); // >= Barcode.MIN_VALUE && <= Barcode.MAX_VALUE
        assertTrue(Barcode.isValidBarcode(1000000000)); // exactly Barcode.MIN_VALUE
        assertTrue(Barcode.isValidBarcode(1000000001)); // Barcode.MIN_VALUE + 1
        assertTrue(Barcode.isValidBarcode(2000000000)); // exactly Barcode.MAX_VALUE
        assertTrue(Barcode.isValidBarcode(1999999999)); // Barcode.MAX_VALUE - 1
    }

    @Test
    public void equals() {
        Barcode barcode = new Barcode(Barcode.MAX_VALUE);
        Barcode barcodeCopy = new Barcode(Barcode.MAX_VALUE);
        Barcode barcode2 = new Barcode(Barcode.MIN_VALUE);

        // null -> returns false
        assertFalse(barcode.equals(null));

        // different types -> returns false
        assertFalse(barcode.equals(0.5f));
        assertFalse(barcode.equals(" "));

        // same object -> returns true
        assertTrue(barcode.equals(barcode));

        // same values -> returns true
        assertTrue(barcode.equals(barcodeCopy));

        // different values -> returns false
        assertFalse(barcode.equals(barcode2));
    }

    @Test
    public void hashcode() {
        Barcode barcode = new Barcode(Barcode.MAX_VALUE);
        Barcode barcodeCopy = new Barcode(Barcode.MAX_VALUE);
        Barcode barcode2 = new Barcode(Barcode.MIN_VALUE);

        // same object -> returns same hashcode
        assertEquals(barcode.hashCode(), barcode.hashCode());

        // same values -> returns same hashcode
        assertEquals(barcode.hashCode(), barcodeCopy.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(barcode.hashCode(), barcode2.hashCode());
    }

}
