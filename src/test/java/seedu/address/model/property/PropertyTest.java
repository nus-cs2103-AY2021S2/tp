package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LOCALDATE_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BURGHLEY_DRIVE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.JURONG;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropertyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Property property = new PropertyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> property.getTags().remove(0));
    }

    @Test
    public void isSameProperty() {
        // same object -> returns true
        assertTrue(MAYFAIR.isSameProperty(MAYFAIR));

        // null -> returns false
        assertFalse(MAYFAIR.isSameProperty(null));

        // same address and postal code, all other attributes different -> returns true
        Property editedMayfair = new PropertyBuilder(MAYFAIR).withName(VALID_NAME_BURGHLEY_DRIVE)
                .withType(VALID_TYPE_BURGHLEY_DRIVE).withDeadline(VALID_DEADLINE_LOCALDATE_BURGHLEY_DRIVE)
                .withTags().build();
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
        Property mayfair = new PropertyBuilder(MAYFAIR).build();
        assertNull(mayfair.getAskingPrice());

        // with client
        Property mayFairWithClient = new PropertyBuilder(MAYFAIR)
                .withClient(ALICE).build();
        assertEquals(mayFairWithClient.getAskingPrice(), ALICE.getClientAskingPrice());
    }

    @Test
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

        // different property -> returns false
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
        editedMayfair = new PropertyBuilder(MAYFAIR).withDeadline(VALID_DEADLINE_LOCALDATE_BURGHLEY_DRIVE)
                .build();
        assertFalse(MAYFAIR.equals(editedMayfair));
    }

    @Test
    public void testStringConversion() {
        // without tags
        Property mayfair = new PropertyBuilder(MAYFAIR).withTags().build();
        assertEquals("Mayfair; Type: Condo; Address: 1 Jurong East Street 32, #08-111; "
                + "Postal Code: 609477; Deadline: Dec 31, 2021",
                mayfair.toString());

        Property burghleyDrive = new PropertyBuilder(BURGHLEY_DRIVE).withTags().build();
        assertEquals("Burghley Drive; Type: Landed; Address: 12 Burghley Drive; Postal Code: 558977; "
                + "Deadline: Jul 31, 2021; Remarks: Lowest selling price is $5,040,0000; Client Name: Bob; "
                + "Client Contact: 98664535; Client Email: bob@gmail.com; Client Asking Price: $800,000",
                burghleyDrive.toString());

        Property woodlandsCrescent = new PropertyBuilder(WOODLANDS_CRESCENT).withTags().build();
        assertEquals("Woodlands Crescent; Type: Hdb; Address: Blk 784 Woodlands Crescent #01-01; "
                + "Postal Code: 731784; Deadline: Aug 01, 2021; Client Name: Caleb; Client Contact: 84459627; "
                + "Client Email: caleb_goh@gmail.com; Client Asking Price: $350,000",
                woodlandsCrescent.toString());

        Property jurong = new PropertyBuilder(JURONG).withTags().build();
        assertEquals("Jurong; Type: Hdb; Address: Jurong Ave 1, #01-01; Postal Code: 640111; "
                + "Deadline: Apr 01, 2021; Client Name: Darren; Client Contact: 81347564; "
                + "Client Email: darren_likes_swe@hotmail.com.sg; Client Asking Price: $2,000,000",
                jurong.toString());
    }
}
