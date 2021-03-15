package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
import static seedu.us.among.testutil.TypicalEndpoints.POST;

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
    public void contains_nullMethod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.contains(null));
    }

    @Test
    public void contains_methodNotInList_returnsFalse() {
        assertFalse(uniqueEndpointList.contains(GET));
    }

    @Test
    public void contains_methodInList_returnsTrue() {
        uniqueEndpointList.add(GET);
        assertTrue(uniqueEndpointList.contains(GET));
    }

    @Test
    public void contains_methodWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEndpointList.add(GET);
        Endpoint editedGet = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_RANDOM).withData("{key: value}")
                .withHeaders("\"key: value\"").withTags(VALID_TAG_1, VALID_TAG_CAT).build();
        assertTrue(uniqueEndpointList.contains(editedGet));
    }

    @Test
    public void add_nullMethod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.add(null));
    }

    @Test
    public void add_duplicateMethod_throwsDuplicateMethodException() {
        uniqueEndpointList.add(GET);
        assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.add(GET));
    }

    @Test
    public void setMethod_nullTargetMethod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(null, GET));
    }

    @Test
    public void setMethod_nullEditedMethod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoint(GET, null));
    }

    @Test
    public void setMethod_targetMethodNotInList_throwsMethodNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.setEndpoint(GET, GET));
    }

    @Test
    public void setMethod_editedMethodIsSameMethod_success() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.setEndpoint(GET, GET);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(GET);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setMethod_editedMethodHasSameIdentity_success() {
        uniqueEndpointList.add(GET);
        Endpoint editedGet = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).withTags(VALID_TAG_CAT).build();
        uniqueEndpointList.setEndpoint(GET, editedGet);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(editedGet);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setMethod_editedMethodHasDifferentIdentity_success() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.setEndpoint(GET, POST);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(POST);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    // @Test
    // public void setMethod_editedMethodHasNonUniqueIdentity_throwsDuplicateMethodException() {
    //     uniqueEndpointList.add(GET);
    //     uniqueEndpointList.add(POST);
    //     assertThrows(DuplicateApiEndpointException.class, () -> uniqueEndpointList.setEndpoint(GET, POST));
    // }

    @Test
    public void remove_nullMethod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.remove(null));
    }

    @Test
    public void remove_methodDoesNotExist_throwsMethodNotFoundException() {
        assertThrows(EndpointNotFoundException.class, () -> uniqueEndpointList.remove(GET));
    }

    @Test
    public void remove_existingMethod_removesMethod() {
        uniqueEndpointList.add(GET);
        uniqueEndpointList.remove(GET);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setMethods_nullUniqueMethodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((UniqueEndpointList) null));
    }

    @Test
    public void setMethods_uniqueMethodList_replacesOwnListWithProvidedUniqueMethodList() {
        uniqueEndpointList.add(GET);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(POST);
        uniqueEndpointList.setEndpoints(expectedUniqueEndpointList);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setMethods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEndpointList.setEndpoints((List<Endpoint>) null));
    }

    @Test
    public void setMethods_list_replacesOwnListWithProvidedList() {
        uniqueEndpointList.add(GET);
        List<Endpoint> endpointList = Collections.singletonList(POST);
        uniqueEndpointList.setEndpoints(endpointList);
        UniqueEndpointList expectedUniqueEndpointList = new UniqueEndpointList();
        expectedUniqueEndpointList.add(POST);
        assertEquals(expectedUniqueEndpointList, uniqueEndpointList);
    }

    @Test
    public void setMethods_listWithDuplicateMethods_throwsDuplicateMethodException() {
        List<Endpoint> listWithDuplicateEndpoints = Arrays.asList(GET, GET);
        assertThrows(DuplicateApiEndpointException.class, (
            ) -> uniqueEndpointList.setEndpoints(listWithDuplicateEndpoints));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEndpointList.asUnmodifiableObservableList().remove(0));
    }
}
