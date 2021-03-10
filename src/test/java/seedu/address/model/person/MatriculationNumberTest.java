package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatriculationNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatriculationNumber(null));
    }

    @Test
    public void constructor_invalidMatric_throwsIllegalArgumentException() {
        String invalidMatric = "";
        assertThrows(IllegalArgumentException.class, () -> new MatriculationNumber(invalidMatric));
    }

    @Test
    public void isValidMatric() {
        // null matriculation number
        assertThrows(NullPointerException.class, () -> MatriculationNumber.isValidMatric(null));

        // invalid matriculation number
        assertFalse(MatriculationNumber.isValidMatric("")); // empty string
        assertFalse(MatriculationNumber.isValidMatric(" ")); // spaces only
        assertFalse(MatriculationNumber.isValidMatric("E0406245")); //NUSNET ID
        assertFalse(MatriculationNumber.isValidMatric("E0406245Z"));
        assertFalse(MatriculationNumber.isValidMatric("A0199S644")); //Alphabet in the numbers part
        assertFalse(MatriculationNumber.isValidMatric("A0%%$991F")); //Special char in the numbers part
        assertFalse(MatriculationNumber.isValidMatric("A1234H")); //Not long enough
        assertFalse(MatriculationNumber.isValidMatric("A123456L"));
        assertFalse(MatriculationNumber.isValidMatric("A019%264N"));

        // valid matriculation number
        assertTrue(MatriculationNumber.isValidMatric("A0123456L"));
        assertTrue(MatriculationNumber.isValidMatric("A0199264N"));
        assertTrue(MatriculationNumber.isValidMatric("A0607891T"));
    }
}
