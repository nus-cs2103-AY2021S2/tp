package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.passenger.exceptions.DuplicatePassengerException;
import seedu.address.model.person.passenger.exceptions.PassengerNotFoundException;
import seedu.address.testutil.PassengerBuilder;

public class UniquePassengerListTest {

    private final UniquePassengerList uniquePassengerList = new UniquePassengerList();

    @Test
    public void contains_nullPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.contains(null));
    }

    @Test
    public void contains_passengerNotInList_returnsFalse() {
        assertFalse(uniquePassengerList.contains(ALICE));
    }

    @Test
    public void contains_passengerInList_returnsTrue() {
        uniquePassengerList.add(ALICE);
        assertTrue(uniquePassengerList.contains(ALICE));
    }

    @Test
    public void contains_passengerWithSameIdentityFieldsInList_returnsTrue() {
        uniquePassengerList.add(ALICE);
        Passenger editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HR)
                .build();
        assertTrue(uniquePassengerList.contains(editedAlice));
    }

    @Test
    public void add_nullPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.add(null));
    }

    @Test
    public void add_duplicatePassenger_throwsDuplicatePassengerException() {
        uniquePassengerList.add(ALICE);
        assertThrows(DuplicatePassengerException.class, () -> uniquePassengerList.add(ALICE));
    }

    @Test
    public void setPassenger_nullTargetPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.setPassenger(null, ALICE));
    }

    @Test
    public void setPassenger_nullEditedPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.setPassenger(ALICE, null));
    }

    @Test
    public void setPassenger_targetPassengerNotInList_throwsPassengerNotFoundException() {
        assertThrows(PassengerNotFoundException.class, () -> uniquePassengerList.setPassenger(ALICE, ALICE));
    }

    @Test
    public void setPassenger_editedPassengerIsSamePassenger_success() {
        uniquePassengerList.add(ALICE);
        uniquePassengerList.setPassenger(ALICE, ALICE);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        expectedUniquePassengerList.add(ALICE);
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassenger_editedPassengerHasSameIdentity_success() {
        uniquePassengerList.add(ALICE);
        Passenger editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HR)
                .build();
        uniquePassengerList.setPassenger(ALICE, editedAlice);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        expectedUniquePassengerList.add(editedAlice);
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassenger_editedPassengerHasDifferentIdentity_success() {
        uniquePassengerList.add(ALICE);
        uniquePassengerList.setPassenger(ALICE, BOB);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        expectedUniquePassengerList.add(BOB);
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassenger_editedPassengerHasNonUniqueIdentity_throwsDuplicatePassengerException() {
        uniquePassengerList.add(ALICE);
        uniquePassengerList.add(BOB);
        assertThrows(DuplicatePassengerException.class, () -> uniquePassengerList.setPassenger(ALICE, BOB));
    }

    @Test
    public void remove_nullPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.remove(null));
    }

    @Test
    public void remove_passengerDoesNotExist_throwsPassengerNotFoundException() {
        assertThrows(PassengerNotFoundException.class, () -> uniquePassengerList.remove(ALICE));
    }

    @Test
    public void remove_existingPassenger_removesPassenger() {
        uniquePassengerList.add(ALICE);
        uniquePassengerList.remove(ALICE);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassengers_nullUniquePassengerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.setPassengers((UniquePassengerList) null));
    }

    @Test
    public void setPassengers_uniquePassengerList_replacesOwnListWithProvidedUniquePassengerList() {
        uniquePassengerList.add(ALICE);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        expectedUniquePassengerList.add(BOB);
        uniquePassengerList.setPassengers(expectedUniquePassengerList);
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassengers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePassengerList.setPassengers((List<Passenger>) null));
    }

    @Test
    public void setPassengers_list_replacesOwnListWithProvidedList() {
        uniquePassengerList.add(ALICE);
        List<Passenger> passengerList = Collections.singletonList(BOB);
        uniquePassengerList.setPassengers(passengerList);
        UniquePassengerList expectedUniquePassengerList = new UniquePassengerList();
        expectedUniquePassengerList.add(BOB);
        assertEquals(expectedUniquePassengerList, uniquePassengerList);
    }

    @Test
    public void setPassengers_listWithDuplicatePassengers_throwsDuplicatePassengerException() {
        List<Passenger> listWithDuplicatePassengers = Arrays.asList(ALICE, ALICE);
        assertThrows(
                DuplicatePassengerException.class, () -> uniquePassengerList.setPassengers(listWithDuplicatePassengers)
        );
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePassengerList.asUnmodifiableObservableList().remove(0));
    }
}
