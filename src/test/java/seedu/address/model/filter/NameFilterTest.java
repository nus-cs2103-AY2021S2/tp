package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutor.Name;

public class NameFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameFilter(null));
    }

    @Test
    public void constructor_invalidNameFilter_throwsIllegalArgumentException() {
        String invalidNameFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new NameFilter(invalidNameFilter));
    }

    @Test
    public void isValidNameFilter() {
        // null name filter
        assertThrows(NullPointerException.class, () -> NameFilter.isValidNameFilter(null));

        // invalid name filter
        assertFalse(NameFilter.isValidNameFilter("")); // empty string
        assertFalse(NameFilter.isValidNameFilter(" ")); // spaces only
        assertFalse(NameFilter.isValidNameFilter("^")); // only non-alphanumeric characters
        assertFalse(NameFilter.isValidNameFilter("peter*")); // contains non-alphanumeric characters

        // valid name filter
        assertTrue(NameFilter.isValidNameFilter("peter jack")); // alphabets only
        assertTrue(NameFilter.isValidNameFilter("12345")); // numbers only
        assertTrue(NameFilter.isValidNameFilter("peter the 2nd")); // alphanumeric characters
        assertTrue(NameFilter.isValidNameFilter("Capital Tan")); // with capital letters
        assertTrue(NameFilter.isValidNameFilter("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void test_name_success() {
        NameFilter nameFilter = new NameFilter("pete");

        assertTrue(nameFilter.test(new Name("pete")));
        assertTrue(nameFilter.test(new Name("Pete")));
        assertTrue(nameFilter.test(new Name("Peter Parker")));
        assertTrue(nameFilter.test(new Name("Alex Pete")));
        assertTrue(nameFilter.test(new Name("Apete")));

        assertFalse(nameFilter.test(new Name("Pet")));
        assertFalse(nameFilter.test(new Name("ete")));
        assertFalse(nameFilter.test(new Name("Alex")));
        assertFalse(nameFilter.test(null));
    }
}
