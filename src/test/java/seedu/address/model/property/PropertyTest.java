package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalClients.CLIENT_ALICE;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropertyTest {

    @Test
    public void isSameProperty() {
        // same object -> returns true
        assertTrue(MAYFAIR.isSameProperty(MAYFAIR));

        // null -> returns false
        assertFalse(MAYFAIR.isSameProperty(null));

        // same address and postal code, all other attributes different -> returns true
        Property editedMayfair = new PropertyBuilder(MAYFAIR).withName(VALID_NAME_BURGHLEY_DRIVE)
                .withType(VALID_TYPE_BURGHLEY_DRIVE).withDeadline(LocalDate.parse("2021-07-31"))
                .build();
        assertTrue(MAYFAIR.isSameProperty(editedMayfair));

        // different address, all other attributes same -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress(VALID_ADDRESS_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));

        // different postal, all other attributes same -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withPostal(VALID_POSTAL_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));

        // address has trailing spaces, all other attributes same -> returns false
        String addressWithTrailingSpaces = VALID_NAME_MAYFAIR + " ";
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress(addressWithTrailingSpaces).build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));
    }

    @Test
    @DisplayName("Test for getAskingPrice method, which checks for existence of client. ")
    public void getAskingPrice() {
        // no client
        Property mayfair = new PropertyBuilder(MAYFAIR).withName(VALID_NAME_BURGHLEY_DRIVE).build();
        assertNull(mayfair.getAskingPrice());

        // with client
        Property mayFairWithClient = new PropertyBuilder(MAYFAIR)
                .withName(VALID_NAME_BURGHLEY_DRIVE)
                .withClient(CLIENT_ALICE).build();
        assertEquals(mayFairWithClient.getAskingPrice(), CLIENT_ALICE.getClientAskingPrice());
    }

    @Test
    @DisplayName("Tests for equals. ")
    public void equals() {
        // same values -> returns true
        Property mayfairCopy = new PropertyBuilder(MAYFAIR).build();
        assertTrue(MAYFAIR.equals(mayfairCopy));

        // same object -> returns true
        assertTrue(MAYFAIR.equals(MAYFAIR));

        // null -> returns false
        assertFalse(MAYFAIR.equals(null));

        // different type -> returns false
        assertFalse(MAYFAIR.equals(5));

        // different person -> returns false
        assertFalse(MAYFAIR.equals(BURGHLEY_DRIVE));

        // different name -> returns false
        Property editedMayfair = new PropertyBuilder(MAYFAIR).withName(VALID_NAME_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different type -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withType(VALID_TYPE_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different address -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress(VALID_ADDRESS_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different postal -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withPostal(VALID_POSTAL_BURGHLEY_DRIVE).build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different deadline -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withDeadline(LocalDate.parse("2021-07-31"))
                .build();
        assertFalse(MAYFAIR.equals(editedMayfair));
    }
}
