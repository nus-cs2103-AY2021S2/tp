package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.ALICE;
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
        assertTrue(ALICE.isSameEndpoint(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEndpoint(null));

        // same name, all other attributes different -> returns true
        Endpoint editedAlice = new EndpointBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEndpoint(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EndpointBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameEndpoint(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Endpoint editedBob = new EndpointBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameEndpoint(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EndpointBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameEndpoint(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Endpoint aliceCopy = new EndpointBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different endpoint -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Endpoint editedAlice = new EndpointBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EndpointBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EndpointBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EndpointBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EndpointBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
