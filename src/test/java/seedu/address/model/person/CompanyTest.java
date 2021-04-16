package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid companies
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only

        // valid companies
        assertTrue(Company.isValidCompany("Google"));
        assertTrue(Company.isValidCompany("C")); // one character
        assertTrue(Company.isValidCompany("Edward Hopper House Museum & Study Center")); // long company name
    }
}
