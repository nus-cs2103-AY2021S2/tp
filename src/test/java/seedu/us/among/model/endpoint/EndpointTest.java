package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;
import seedu.us.among.testutil.Assert;
import seedu.us.among.testutil.TypicalEndpoints;

public class EndpointTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Endpoint endpoint = new EndpointBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> endpoint.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalEndpoints.ALICE.isSameEndpoint(TypicalEndpoints.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalEndpoints.ALICE.isSameEndpoint(null));

        // same name, all other attributes different -> returns true
        Endpoint editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalEndpoints.ALICE.isSameEndpoint(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.isSameEndpoint(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Endpoint editedBob = new EndpointBuilder(TypicalEndpoints.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalEndpoints.BOB.isSameEndpoint(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EndpointBuilder(TypicalEndpoints.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalEndpoints.BOB.isSameEndpoint(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Endpoint aliceCopy = new EndpointBuilder(TypicalEndpoints.ALICE).build();
        Assertions.assertTrue(TypicalEndpoints.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalEndpoints.ALICE.equals(TypicalEndpoints.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(5));

        // different endpoint -> returns false
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(TypicalEndpoints.BOB));

        // different name -> returns false
        Endpoint editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EndpointBuilder(TypicalEndpoints.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalEndpoints.ALICE.equals(editedAlice));
    }
}
