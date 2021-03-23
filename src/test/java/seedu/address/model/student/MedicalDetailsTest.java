package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalDetails(null));
    }

    @Test
    public void constructor_invalidMedicalDetails_throwsIllegalArgumentException() {
        String invalidMedicalDetails = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalDetails(invalidMedicalDetails));
    }

    @Test
    public void isValidMedicalDetails() {
        // null medical details
        assertThrows(NullPointerException.class, () -> MedicalDetails.isValidMedicalDetails(null));

        // invalid medical details
        assertFalse(MedicalDetails.isValidMedicalDetails("")); // empty string
        assertFalse(MedicalDetails.isValidMedicalDetails(" ")); // spaces only

        // valid medical details
        assertTrue(MedicalDetails.isValidMedicalDetails("history of anaphylaxis"));
        assertTrue(MedicalDetails.isValidMedicalDetails("this is rubbish")); // gibberish
        assertTrue(MedicalDetails.isValidMedicalDetails("Received heart bypass surgery in 2018")); // long history
    }
}
