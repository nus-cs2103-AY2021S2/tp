package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;

public class UniqueContactListTest {

    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.contains(null));
    }

    @Test
    public void contains_contactNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        assertTrue(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_copiedContact_returnsTrue() {
        uniqueContactList.add(new ContactBuilder(ALICE).build());
        assertTrue(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactWithDifferentAttribute_returnsFalse() {
        uniqueContactList.add(ALICE);
        Contact impostor = new ContactBuilder(ALICE).withName("Impostor").build();
        assertFalse(uniqueContactList.contains(impostor));
    }

    @Test
    public void add_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.add(null));
    }

    @Test
    public void add_duplicateContact_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(ALICE));
    }

    @Test
    public void setContact_nullTargetContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(null, ALICE));
    }

    @Test
    public void setContact_nullEditedContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(ALICE, null));
    }

    @Test
    public void setContact_targetContactNotInList_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.setContact(ALICE, ALICE));
    }

    @Test
    public void setContact_editedContactIsSameContact_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(ALICE);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasSameIdentity_success() {
        uniqueContactList.add(ALICE);
        Contact copiedAlice = new ContactBuilder(ALICE).build();
        uniqueContactList.setContact(ALICE, copiedAlice);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(copiedAlice);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasDifferentIdentity_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, BENSON);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BENSON);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasNonUniqueIdentity_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        uniqueContactList.add(BENSON);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContact(ALICE, BENSON));
    }

    @Test
    public void remove_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_contactDoesNotExist_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(ALICE));
    }

    @Test
    public void remove_existingContact_removesContact() {
        uniqueContactList.add(ALICE);
        uniqueContactList.remove(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullUniqueContactList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((UniqueContactList) null));
    }

    @Test
    public void setContacts_uniqueContactList_replacesOwnListWithProvidedUniqueContactList() {
        uniqueContactList.add(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BENSON);
        uniqueContactList.setContacts(expectedUniqueContactList);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((List<Contact>) null));
    }

    @Test
    public void setContacts_list_replacesOwnListWithProvidedList() {
        uniqueContactList.add(ALICE);
        List<Contact> contactList = Collections.singletonList(BENSON);
        uniqueContactList.setContacts(contactList);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BENSON);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_listWithDuplicateContacts_throwsDuplicateContactException() {
        List<Contact> listWithDuplicateContacts = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContacts(listWithDuplicateContacts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }
}
