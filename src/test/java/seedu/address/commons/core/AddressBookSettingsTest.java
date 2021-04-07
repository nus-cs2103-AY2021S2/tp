package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

class AddressBookSettingsTest {
    private AddressBookSettings addressBookSettings = new AddressBookSettings();

    @Test
    void addressBookSettings_invalidComparator_throwInvalidParameterError() {
        assertThrows(InvalidParameterException.class, () -> new AddressBookSettings("invalid comparator"));
    }

    @Test
    void setComparator_nameComparator_setComparator() {
        addressBookSettings = new AddressBookSettings();
        addressBookSettings.setComparator(OPTION_NAME);

        String comparator = addressBookSettings.getComparator().getClass().getSimpleName();

        assertEquals(comparator, "NameComparator");
    }

    @Test
    void setComparator_invalidComparator_throwInvalidParameterError() {
        assertThrows(InvalidParameterException.class, () -> addressBookSettings.setComparator("invalid comparator"));
    }

    @Test
    void getComparator_defaultComparator_returnComparator() {
        AddressBookSettings addressBookSettings = new AddressBookSettings();
        String comparator = addressBookSettings.getComparator().getClass().getSimpleName();

        assertEquals(comparator, "DateComparator");
    }

    @Test
    void getComparator_validComparator_returnComparator() {
        AddressBookSettings addressBookSettings = new AddressBookSettings(OPTION_NAME);
        String comparator = addressBookSettings.getComparator().getClass().getSimpleName();

        assertEquals(comparator, "NameComparator");
    }

    @Test
    void testEquals() {
        // same object
        assertEquals(addressBookSettings, addressBookSettings);

        // different object and equal
        AddressBookSettings addressBookSettingsNew = new AddressBookSettings(OPTION_NAME);
        addressBookSettings.setComparator(OPTION_NAME);

        assertEquals(addressBookSettings, addressBookSettingsNew);

        // different comparator
        addressBookSettingsNew = new AddressBookSettings();

        assertNotEquals(addressBookSettings, addressBookSettingsNew);

        // different object
        assertNotEquals(addressBookSettings, new Object());
    }

    @Test
    void testToString() {
        addressBookSettings = new AddressBookSettings();
        String actual = addressBookSettings.toString();
        String expected = "Comparator : " + OPTION_DATE;

        assertEquals(actual, expected);
    }
}
