package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeachingAssistant.AMY;
import static seedu.address.testutil.TypicalTeachingAssistant.BEN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains unit tests for {@code UniqueContactList}.
 */
public class UniqueContactListTest {

    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.contains(null));
    }

    @Test
    public void contains_contactNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(AMY));
    }

    @Test
    public void contains_contactInList_returnsTrue() {
        uniqueContactList.add(AMY);
        assertTrue(uniqueContactList.contains(AMY));
    }

    @Test
    public void contains_copiedContact_returnsTrue() {
        uniqueContactList.add(new ContactBuilder(AMY).build());
        assertTrue(uniqueContactList.contains(AMY));
    }

    @Test
    public void contains_contactWithDifferentAttribute_returnsFalse() {
        uniqueContactList.add(AMY);
        Contact impostor = new ContactBuilder(AMY).withName("Impostor").build();
        assertFalse(uniqueContactList.contains(impostor));
    }

    @Test
    public void add_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.add(null));
    }

    @Test
    public void add_duplicateContact_throwsDuplicateContactException() {
        uniqueContactList.add(AMY);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(AMY));
    }

    @Test
    public void setContact_nullTargetContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(null, AMY));
    }

    @Test
    public void setContact_nullEditedContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(AMY, null));
    }

    @Test
    public void setContact_targetContactNotInList_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.setContact(AMY, AMY));
    }

    @Test
    public void setContact_editedContactIsSameContact_success() {
        uniqueContactList.add(AMY);
        uniqueContactList.setContact(AMY, AMY);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(AMY);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasSameIdentity_success() {
        uniqueContactList.add(AMY);
        Contact copiedAmy = new ContactBuilder(AMY).build();
        uniqueContactList.setContact(AMY, copiedAmy);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(copiedAmy);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasDifferentIdentity_success() {
        uniqueContactList.add(AMY);
        uniqueContactList.setContact(AMY, BEN);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BEN);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasNonUniqueIdentity_throwsDuplicateContactException() {
        uniqueContactList.add(AMY);
        uniqueContactList.add(BEN);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContact(AMY, BEN));
    }

    @Test
    public void remove_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_contactDoesNotExist_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(AMY));
    }

    @Test
    public void remove_existingContact_removesContact() {
        uniqueContactList.add(AMY);
        uniqueContactList.remove(AMY);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullUniqueContactList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((UniqueContactList) null));
    }

    @Test
    public void setContacts_uniqueContactList_replacesOwnListWithProvidedUniqueContactList() {
        uniqueContactList.add(AMY);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BEN);
        uniqueContactList.setContacts(expectedUniqueContactList);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((List<Contact>) null));
    }

    @Test
    public void setContacts_list_replacesOwnListWithProvidedList() {
        uniqueContactList.add(AMY);
        List<Contact> contactList = Collections.singletonList(BEN);
        uniqueContactList.setContacts(contactList);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BEN);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_listWithDuplicateContacts_throwsDuplicateContactException() {
        List<Contact> listWithDuplicateContacts = Arrays.asList(AMY, AMY);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContacts(listWithDuplicateContacts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }
}
