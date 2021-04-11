package seedu.address.model.person.driver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDrivers.DRIVER_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.TypicalDrivers;

public class DriverTest {
    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Driver(new Name(VALID_NAME_BOB), null));
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Driver(null, new Phone(VALID_PHONE_BOB)));
    }

    @Test
    public void isSameDriver() {
        // same object -> returns true
        assertTrue(DRIVER_ALICE.isSameDriver(DRIVER_ALICE));

        // null -> returns false
        assertFalse(DRIVER_ALICE.isSameDriver(null));

        // different phone, all other attributes same -> returns false
        Driver editedAlice = new DriverBuilder(DRIVER_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DRIVER_ALICE.isSameDriver(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DriverBuilder(DRIVER_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(DRIVER_ALICE.isSameDriver(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Driver editedBob = new DriverBuilder(TypicalDrivers.DRIVER_BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(TypicalDrivers.DRIVER_BOB.isSameDriver(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new DriverBuilder(TypicalDrivers.DRIVER_BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalDrivers.DRIVER_BOB.isSameDriver(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Driver aliceCopy = new DriverBuilder(DRIVER_ALICE).build();
        assertTrue(DRIVER_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DRIVER_ALICE.equals(DRIVER_ALICE));

        // null -> returns false
        assertFalse(DRIVER_ALICE.equals(null));

        // different type -> returns false
        assertFalse(DRIVER_ALICE.equals(5));

        // different passenger -> returns false
        assertFalse(DRIVER_ALICE.equals(TypicalDrivers.DRIVER_BOB));

        // different name -> returns false
        Driver editedAlice = new DriverBuilder(DRIVER_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(DRIVER_ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DriverBuilder(DRIVER_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DRIVER_ALICE.equals(editedAlice));
    }
}
