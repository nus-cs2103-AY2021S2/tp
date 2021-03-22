package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_DATA_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_GET;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_COOL;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
import static seedu.us.among.testutil.TypicalEndpoints.GET1;
import static seedu.us.among.testutil.TypicalEndpoints.POST;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class EndpointTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Endpoint endpoint = new EndpointBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> endpoint.getTags().remove(0));
    }

    @Test
    public void isSameMethod() {
        // same object -> returns true
        assertTrue(GET.equals(GET));

        // null -> returns false
        assertFalse(GET.equals(null));

        // same method and address, all other attributes different -> returns false
        Endpoint editedGet = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_RANDOM).withTags(VALID_TAG_COOL)
                .build();
        assertFalse(GET.equals(editedGet));

        // same name, all other attributes different -> returns false
        editedGet = new EndpointBuilder(GET).withAddress(VALID_ADDRESS_FACT).withTags(VALID_TAG_CAT).build();
        assertFalse(GET.equals(editedGet));

        // different name, all other attributes same -> returns false
        editedGet = new EndpointBuilder(GET).withMethod(VALID_METHOD_POST).build();
        assertFalse(GET.equals(editedGet));

        // name differs in case, all other attributes same -> returns false
        Endpoint editedPost = new EndpointBuilder(POST).withMethod(VALID_METHOD_GET.toLowerCase()).build();
        assertFalse(POST.equals(editedPost));

        // to-do add more tests for this

    }

    @Test
    public void equals() {
        // same values -> returns true
        Endpoint getCopy = new EndpointBuilder(GET).build();
        assertTrue(GET.equals(getCopy));

        // same object -> returns true
        assertTrue(GET.equals(GET));

        // null -> returns false
        assertFalse(GET.equals(null));

        // different type -> returns false
        assertFalse(GET.equals(5));

        // different endpoint -> returns false
        assertFalse(GET.equals(POST));

        // different name -> returns false
        Endpoint editedGet = new EndpointBuilder(GET1).withMethod(VALID_METHOD_POST).build();
        assertFalse(GET1.equals(editedGet));

        // different address -> returns false
        editedGet = new EndpointBuilder(GET1).withAddress(VALID_ADDRESS_FACT).build();
        assertFalse(GET1.equals(editedGet));

        // different tags -> returns false
        editedGet = new EndpointBuilder(GET1).withTags(VALID_TAG_CAT).build();
        assertFalse(GET1.equals(editedGet));

        // different data -> returns false
        editedGet = new EndpointBuilder(GET1).withData(VALID_DATA_PAIR_NEW).build();
        assertFalse(GET1.equals(editedGet));

        // different headers -> returns false
        editedGet = new EndpointBuilder(GET1).withHeaders(VALID_HEADER_PAIR_NEW).build();
        assertFalse(GET1.equals(editedGet));
    }
}
