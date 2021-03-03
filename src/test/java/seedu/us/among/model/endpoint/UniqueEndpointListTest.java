package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
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
        assertFalse(uniqueEndpointList.contains(GET));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueEndpointList.add(GET);
        assertTrue(uniqueEndpointList.contains(GET));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEndpointList.add(GET);
        Endpoint editedAlice = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).withTags(VALID_TAG_CAT).build();
        assertTrue(uniqueEndpointList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueEndpointList.add(GET);
        assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.add(GET));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(null, GET));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(GET, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.setEndpoint(GET, GET));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.setEndpoint(GET, GET);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(GET);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueEndpointList.add(GET);
        Endpoint editedAlice = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).withTags(VALID_TAG_CAT).build();
        uniqueEndpointList.setEndpoint(GET, editedAlice);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(editedAlice);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.setEndpoint(GET, BOB);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.add(BOB);
        assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.setEndpoint(GET, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.remove(GET));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.remove(GET);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((UniqueEndpointList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueEndpointList.add(GET);
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
        uniqueEndpointList.add(GET);
        List<Endpoint> endpointList = Collections.singletonList(BOB);
        uniqueEndpointList.setEndpoints(endpointList);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(BOB);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Endpoint> listWithDuplicateEndpoints = Arrays.asList(GET, GET);
        assertThrows(DuplicateApiEndpointException.class,
                () -> uniqueEndpointList.setEndpoints(listWithDuplicateEndpoints));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> uniqueEndpointList.asUnmodifiableObservableList().remove(0));
    }
}
