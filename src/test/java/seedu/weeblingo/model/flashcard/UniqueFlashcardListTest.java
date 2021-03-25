package seedu.weeblingo.model.flashcard;

import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    //    @Test
    //    public void contains_flashcardNotInList_returnsFalse() {
    //        assertFalse(uniqueFlashcardList.contains(ALICE));
    //    }

    //    @Test
    //    public void contains_flashcardInList_returnsTrue() {
    //        uniqueFlashcardList.add(ALICE);
    //        assertTrue(uniqueFlashcardList.contains(ALICE));
    //    }

    //    @Test
    //    public void contains_flashcardWithSameIdentityFieldsInList_returnsTrue() {
    //        uniqueFlashcardList.add(ALICE);
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE)
    //        .withAnswer(VALID_ANSWER_B).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(uniqueFlashcardList.contains(editedAlice));
    //    }

    @Test
    public void add_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    //    @Test
    //    public void add_duplicateFlashcard_throwsDuplicateFlashcardException() {
    //        uniqueFlashcardList.add(ALICE);
    //        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(ALICE));
    //    }

    //    @Test
    //    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, ALICE));
    //    }

    //    @Test
    //    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, null));
    //    }

    //    @Test
    //    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
    //        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, ALICE));
    //    }

    //    @Test
    //    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
    //        uniqueFlashcardList.add(ALICE);
    //        uniqueFlashcardList.setFlashcard(ALICE, ALICE);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        expectedUniqueFlashcardList.add(ALICE);
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    //    @Test
    //    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
    //        uniqueFlashcardList.add(ALICE);
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE)
    //        .withAnswer(VALID_ANSWER_B).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        uniqueFlashcardList.setFlashcard(ALICE, editedAlice);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        expectedUniqueFlashcardList.add(editedAlice);
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    //    @Test
    //    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
    //        uniqueFlashcardList.add(ALICE);
    //        uniqueFlashcardList.setFlashcard(ALICE, BOB);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        expectedUniqueFlashcardList.add(BOB);
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    //    @Test
    //    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
    //        uniqueFlashcardList.add(ALICE);
    //        uniqueFlashcardList.add(BOB);
    //        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, BOB));
    //    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    //    @Test
    //    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
    //        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(ALICE));
    //    }

    //    @Test
    //    public void remove_existingFlashcard_removesFlashcard() {
    //        uniqueFlashcardList.add(ALICE);
    //        uniqueFlashcardList.remove(ALICE);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    //    @Test
    //    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
    //        uniqueFlashcardList.add(ALICE);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        expectedUniqueFlashcardList.add(BOB);
    //        uniqueFlashcardList.setFlashcards(expectedUniqueFlashcardList);
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    //    @Test
    //    public void setFlashcards_list_replacesOwnListWithProvidedList() {
    //        uniqueFlashcardList.add(ALICE);
    //        List<Flashcard> flashcardList = Collections.singletonList(BOB);
    //        uniqueFlashcardList.setFlashcards(flashcardList);
    //        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
    //        expectedUniqueFlashcardList.add(BOB);
    //        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    //    }

    //    @Test
    //    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
    //        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(ALICE, ALICE);
    //        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList
    //        .setFlashcards(listWithDuplicateFlashcards));
    //    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }
}
