package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
import static seedu.us.among.testutil.TypicalEndpoints.BOB;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class EndpointTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Endpoint endpoint = new EndpointBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> endpoint.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(GET.isSameEndpoint(GET));

        // null -> returns false
        assertFalse(GET.isSameEndpoint(null));

        // same name, all other attributes different -> returns true
        Endpoint editedAlice = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).withTags(VALID_TAG_CAT).build();
        assertTrue(GET.isSameEndpoint(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EndpointBuilder(GET).withMethod(VALID_METHOD_POST).build();
        assertFalse(GET.isSameEndpoint(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Endpoint editedBob = new EndpointBuilder(BOB).withMethod(VALID_METHOD_POST.toLowerCase()).build();
        assertFalse(BOB.isSameEndpoint(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_METHOD_POST + " ";
        editedBob = new EndpointBuilder(BOB).withMethod(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameEndpoint(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Endpoint aliceCopy = new EndpointBuilder(GET).build();
        assertTrue(GET.equals(aliceCopy));

        // same object -> returns true
        assertTrue(GET.equals(GET));

        // null -> returns false
        assertFalse(GET.equals(null));

        // different type -> returns false
        assertFalse(GET.equals(5));

        // different endpoint -> returns false
        assertFalse(GET.equals(BOB));

        // different name -> returns false
        Endpoint editedAlice = new EndpointBuilder(GET).withMethod(VALID_METHOD_POST).build();
        assertFalse(GET.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).build();
        assertFalse(GET.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EndpointBuilder(GET).withTags(VALID_TAG_CAT).build();
        assertFalse(GET.equals(editedAlice));
    }
}
