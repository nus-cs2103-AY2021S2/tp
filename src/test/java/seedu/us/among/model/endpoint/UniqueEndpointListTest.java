package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.ALICE;
import static seedu.us.among.testutil.TypicalEndpoints.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.endpoint.exceptions.DuplicateApiEndpointException;
import seedu.us.among.model.endpoint.exceptions.EndpointNotFoundException;
import seedu.us.among.testutil.EndpointBuilder;

public class UniqueEndpointListTest {

    private final UniqueEndpointList uniqueEndpointList = new UniqueEndpointList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueEndpointList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueEndpointList.add(ALICE);
        assertTrue(uniqueEndpointList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEndpointList.add(ALICE);
        Endpoint editedAlice = new EndpointBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEndpointList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueEndpointList.add(ALICE);
        assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.setEndpoint(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueEndpointList.add(ALICE);
        uniqueEndpointList.setEndpoint(ALICE, ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(ALICE);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueEndpointList.add(ALICE);
        Endpoint editedAlice = new EndpointBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueEndpointList.setEndpoint(ALICE, editedAlice);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(editedAlice);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueEndpointList.add(ALICE);
        uniqueEndpointList.setEndpoint(ALICE, BOB);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueEndpointList.add(ALICE);
        uniqueEndpointList.add(BOB);
        assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.setEndpoint(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueEndpointList.add(ALICE);
        uniqueEndpointList.remove(ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((UniqueEndpointList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueEndpointList.add(ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(BOB);
        uniqueEndpointList.setEndpoints(expectedUniqueEndpointList);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((List<Endpoint>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueEndpointList.add(ALICE);
        List<Endpoint> endpointList = Collections.singletonList(BOB);
        uniqueEndpointList.setEndpoints(endpointList);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Endpoint> listWithDuplicateEndpoints = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateApiEndpointException.class, () ->
                uniqueEndpointList.setEndpoints(listWithDuplicateEndpoints));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEndpointList.asUnmodifiableObservableList().remove(0));
    }
}
