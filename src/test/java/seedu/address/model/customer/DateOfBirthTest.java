package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DateOfBirthTest {
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy LL dd");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    void isValidDateOfBirth() {
        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // blank DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only

        // missing parts
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 01 ")); // missing date
        assertFalse(DateOfBirth.isValidDateOfBirth("19980505")); // missing spaces in between yyyy mm dd

        // invalid DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth(currentDate.plusYears(50).format(formatter))); // invalid future year
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 13 01")); // invalid month
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 10 32")); // invalid date

        // valid DateOfBirth
        assertTrue(DateOfBirth.isValidDateOfBirth(currentDate.minusYears(99).format(formatter))); //99 years ago
        assertTrue(DateOfBirth.isValidDateOfBirth(currentDate.minusYears(21).format(formatter))); //21 years ago
    }
}
