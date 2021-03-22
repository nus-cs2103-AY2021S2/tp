package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VaccinationStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VaccinationStatus(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new VaccinationStatus(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null vaccination status
        assertThrows(NullPointerException.class, () -> VaccinationStatus.isValidStatus(null));

        // invalid vaccination statuses
        assertFalse(VaccinationStatus.isValidStatus("")); // empty string
        assertFalse(VaccinationStatus.isValidStatus(" ")); // spaces only
        assertFalse(VaccinationStatus.isValidStatus("unvaccinated"));
        assertFalse(VaccinationStatus.isValidStatus("v@ccinated")); // spaces only
        assertFalse(VaccinationStatus.isValidStatus("vaxxed")); // spaces only
        assertFalse(VaccinationStatus.isValidStatus("vax")); // spaces only


        // valid vaccination statuses
        assertTrue(VaccinationStatus.isValidStatus("Not Vaccinated"));
        assertTrue(VaccinationStatus.isValidStatus("NOT VACCINATED"));
        assertTrue(VaccinationStatus.isValidStatus("vaccinated"));
        assertTrue(VaccinationStatus.isValidStatus("NOT VACCINATED"));
        assertTrue(VaccinationStatus.isValidStatus("nOT vACCINATED"));
        assertTrue(VaccinationStatus.isValidStatus("NOT vaccinated"));
    }
}
