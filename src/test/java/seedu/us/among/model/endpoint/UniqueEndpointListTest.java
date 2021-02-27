package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.endpoint.exceptions.DuplicateApiEndpointException;
import seedu.us.among.model.endpoint.exceptions.ApiEndpointNotFoundException;
import seedu.us.among.testutil.EndpointBuilder;
import seedu.us.among.testutil.Assert;
import seedu.us.among.testutil.TypicalEndpoints;

public class UniqueEndpointListTest {

    private final UniqueEndpointList uniqueEndpointList = new UniqueEndpointList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueEndpointList.contains(TypicalEndpoints.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        assertTrue(uniqueEndpointList.contains(TypicalEndpoints.ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        Endpoint editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEndpointList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        Assert.assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.add(TypicalEndpoints.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(null, TypicalEndpoints.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(ApiEndpointNotFoundException.class, () -> uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, TypicalEndpoints.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, TypicalEndpoints.ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(TypicalEndpoints.ALICE);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        Endpoint editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, editedAlice);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(editedAlice);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, TypicalEndpoints.BOB);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(TypicalEndpoints.BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        uniqueEndpointList.add(TypicalEndpoints.BOB);
        Assert.assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.setEndpoint(TypicalEndpoints.ALICE, TypicalEndpoints.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(ApiEndpointNotFoundException.class, () -> uniqueEndpointList.remove(TypicalEndpoints.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        uniqueEndpointList.remove(TypicalEndpoints.ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((UniqueEndpointList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(TypicalEndpoints.BOB);
        uniqueEndpointList.setEndpoints(expectedUniqueEndpointList);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((List<Endpoint>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueEndpointList.add(TypicalEndpoints.ALICE);
        List<Endpoint> endpointList = Collections.singletonList(TypicalEndpoints.BOB);
        uniqueEndpointList.setEndpoints(endpointList);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(TypicalEndpoints.BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Endpoint> listWithDuplicateEndpoints = Arrays.asList(TypicalEndpoints.ALICE, TypicalEndpoints.ALICE);
        Assert.assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.setEndpoints(listWithDuplicateEndpoints));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEndpointList.asUnmodifiableObservableList().remove(0));
    }
}
