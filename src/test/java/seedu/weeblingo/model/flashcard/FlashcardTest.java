package seedu.weeblingo.model.flashcard;

public class FlashcardTest {

    //commented out for now
//    @Test
//    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
//        Flashcard flashcard = new FlashcardBuilder().build();
//        assertThrows(UnsupportedOperationException.class, () -> flashcard.getTags().remove(0));
//    }
//
//    @Test
//    public void isSameFlashcard() {
//        // same object -> returns true
//        assertTrue(ALICE.isSameFlashcard(ALICE));
//
//        // null -> returns false
//        assertFalse(ALICE.isSameFlashcard(null));
//
//        // same name, all other attributes different -> returns true
//        Flashcard editedAlice = new FlashcardBuilder(ALICE).withPhone(VALID_PHONE_BOB).withQuestion(VALID_QUESTION_B)
//                .withAnswer(VALID_ANSWER_B).withTags(VALID_TAG_HUSBAND).build();
//        assertTrue(ALICE.isSameFlashcard(editedAlice));
//
//        // different name, all other attributes same -> returns false
//        editedAlice = new FlashcardBuilder(ALICE).withName(VALID_NAME_BOB).build();
//        assertFalse(ALICE.isSameFlashcard(editedAlice));
//
//        // name differs in case, all other attributes same -> returns false
//        Flashcard editedBob = new FlashcardBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
//        assertFalse(BOB.isSameFlashcard(editedBob));
//
//        // name has trailing spaces, all other attributes same -> returns false
//        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
//        editedBob = new FlashcardBuilder(BOB).withName(nameWithTrailingSpaces).build();
//        assertFalse(BOB.isSameFlashcard(editedBob));
//    }
//
//    @Test
//    public void equals() {
//        // same values -> returns true
//        Flashcard aliceCopy = new FlashcardBuilder(ALICE).build();
//        assertTrue(ALICE.equals(aliceCopy));
//
//        // same object -> returns true
//        assertTrue(ALICE.equals(ALICE));
//
//        // null -> returns false
//        assertFalse(ALICE.equals(null));
//
//        // different type -> returns false
//        assertFalse(ALICE.equals(5));
//
//        // different flashcard -> returns false
//        assertFalse(ALICE.equals(BOB));
//
//        // different name -> returns false
//        Flashcard editedAlice = new FlashcardBuilder(ALICE).withName(VALID_NAME_BOB).build();
//        assertFalse(ALICE.equals(editedAlice));
//
//        // different phone -> returns false
//        editedAlice = new FlashcardBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
//        assertFalse(ALICE.equals(editedAlice));
//
//        // different email -> returns false
//        editedAlice = new FlashcardBuilder(ALICE).withQuestion(VALID_QUESTION_B).build();
//        assertFalse(ALICE.equals(editedAlice));
//
//        // different address -> returns false
//        editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_B).build();
//        assertFalse(ALICE.equals(editedAlice));
//
//        // different tags -> returns false
//        editedAlice = new FlashcardBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
//        assertFalse(ALICE.equals(editedAlice));
//    }
}
