package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.PassengerBuilder;

public class PassengerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Passenger passenger = new PassengerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> passenger.getTags().remove(0));
    }

    @Test
    public void isSamePassenger() {
        // same object -> returns true
        assertTrue(ALICE.isSamePassenger(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePassenger(null));

        // same name, all other attributes different -> returns true
        Passenger editedAlice = new PassengerBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTripDay(VALID_TRIPDAY_BOB).withTripTime(VALID_TRIPTIME_BOB)
                .withTags(VALID_TAG_HUSBAND).withPrice(VALID_PRICE_AMY).build();
        assertTrue(ALICE.isSamePassenger(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PassengerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePassenger(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Passenger editedBob = new PassengerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePassenger(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PassengerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePassenger(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Passenger aliceCopy = new PassengerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different passenger -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Passenger editedAlice = new PassengerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PassengerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tripday -> returns false
        editedAlice = new PassengerBuilder(ALICE).withTripDay(VALID_TRIPDAY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different triptime -> returns false
        editedAlice = new PassengerBuilder(ALICE).withTripTime(VALID_TRIPTIME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different price -> returns false
        editedAlice = new PassengerBuilder(ALICE).withPrice(VALID_PRICE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PassengerBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // has driver, all other attributes same -> returns false
        editedAlice = new PassengerBuilder(ALICE).withDriver(new DriverBuilder().build()).buildWithDriver();
        assertFalse(ALICE.equals(editedAlice));

        // different driver, all other attributes same -> returns false
        editedAlice = new PassengerBuilder(ALICE).withDriver(
                new DriverBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build()
        ).buildWithDriver();
        assertFalse(ALICE.equals(editedAlice));
    }
}
