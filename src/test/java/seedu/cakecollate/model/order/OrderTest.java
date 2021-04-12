package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_BERRY_ORDER;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_DELIVERY_DATE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_REQUEST_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;
import static seedu.cakecollate.testutil.TypicalOrders.BOB;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.testutil.OrderBuilder;

public class OrderTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Order order = new OrderBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> order.getTags().remove(0));
    }

    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(ALICE.isSameOrder(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameOrder(null));

        // same name, address, order description, delivery date, all other attributes different -> returns true
        Order editedAlice = new OrderBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameOrder(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new OrderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameOrder(editedAlice));

        // different address, all other attributes same -> returns false
        editedAlice = new OrderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.isSameOrder(editedAlice));

        // different order description, all other attributes same -> returns false
        editedAlice = new OrderBuilder(ALICE).withOrderDescriptions(VALID_BERRY_ORDER).build();
        assertFalse(ALICE.isSameOrder(editedAlice));

        // different delivery date, all other attributes same -> returns false
        editedAlice = new OrderBuilder(ALICE).withDeliveryDate(VALID_DELIVERY_DATE_BOB).build();
        assertFalse(ALICE.isSameOrder(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Order editedBob = new OrderBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameOrder(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new OrderBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameOrder(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order aliceCopy = new OrderBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different order -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Order editedAlice = new OrderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new OrderBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new OrderBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new OrderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new OrderBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different order description -> returns false
        editedAlice = new OrderBuilder(ALICE).withOrderDescriptions(VALID_BERRY_ORDER).build();
        assertFalse(ALICE.equals(editedAlice));

        // different delivery date -> returns false
        editedAlice = new OrderBuilder(ALICE).withDeliveryDate(VALID_DELIVERY_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different delivery status -> returns false
        editedAlice = new OrderBuilder(ALICE).withDeliveryStatus(Status.CANCELLED).build();
        assertFalse(ALICE.equals(editedAlice));

        // different request -> returns false
        editedAlice = new OrderBuilder(ALICE).withRequest(VALID_REQUEST_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
