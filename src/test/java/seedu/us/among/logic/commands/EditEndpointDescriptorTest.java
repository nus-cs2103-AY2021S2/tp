package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_GET;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_FACT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_DATA_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR_NEW;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.EditCommand.EditEndpointDescriptor;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;

public class EditEndpointDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEndpointDescriptor descriptorWithSameValues = new EditEndpointDescriptor(DESC_GET);
        assertTrue(DESC_GET.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GET.equals(DESC_GET));

        // null -> returns false
        assertFalse(DESC_GET.equals(null));

        // different types -> returns false
        assertFalse(DESC_GET.equals(5));

        // different values -> returns false
        assertFalse(DESC_GET.equals(DESC_POST));

        // different name -> returns false
        EditEndpointDescriptor editedAmy = new EditEndpointDescriptorBuilder(DESC_GET).withName(VALID_METHOD_POST)
                .build();
        assertFalse(DESC_GET.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_GET).withAddress(VALID_ADDRESS_FACT).build();
        assertFalse(DESC_GET.equals(editedAmy));

        // different data -> return false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_GET).withData(VALID_DATA_PAIR_NEW).build();
        assertFalse(DESC_GET.equals(editedAmy));

        // different header -> return false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_GET).withHeaders(VALID_HEADER_PAIR_NEW).build();
        assertFalse(DESC_GET.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_GET).withTags(VALID_TAG_CAT).build();
        assertFalse(DESC_GET.equals(editedAmy));
    }
}
