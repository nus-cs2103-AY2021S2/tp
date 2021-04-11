package seedu.weeblingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_GOJUON;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.I_CARD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.weeblingo.model.flashcard.exceptions.FlashcardNotFoundException;
import seedu.weeblingo.testutil.FlashcardBuilder;

public class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_flashcardNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(A_CARD));
    }

    @Test
    public void contains_flashcardInList_returnsTrue() {
        uniqueFlashcardList.add(A_CARD);
        assertTrue(uniqueFlashcardList.contains(A_CARD));
    }

    @Test
    public void contains_flashcardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(A_CARD);
        Flashcard editedCard = new FlashcardBuilder(A_CARD).withQuestion(VALID_QUESTION_A)
                .withAnswer(VALID_ANSWER_A).withTags(VALID_TAG_GOJUON).withUserTags(VALID_TAG_EASY).build();
        assertTrue(uniqueFlashcardList.contains(editedCard));
    }

    @Test
    public void add_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicateFlashcard_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(A_CARD);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(A_CARD));
    }

    @Test
    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, A_CARD));
    }

    @Test
    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(A_CARD, null));
    }

    @Test
    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(A_CARD, A_CARD));
    }

    @Test
    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
        uniqueFlashcardList.add(A_CARD);
        uniqueFlashcardList.setFlashcard(A_CARD, A_CARD);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(A_CARD);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
        uniqueFlashcardList.add(A_CARD);
        Flashcard editedCard = new FlashcardBuilder(A_CARD).withQuestion(VALID_QUESTION_I)
                .withAnswer(VALID_ANSWER_I).withTags(VALID_TAG_GOJUON).withUserTags(VALID_TAG_EASY)
                .build();
        uniqueFlashcardList.setFlashcard(A_CARD, editedCard);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(editedCard);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
        uniqueFlashcardList.add(A_CARD);
        uniqueFlashcardList.setFlashcard(A_CARD, I_CARD);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(I_CARD);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(A_CARD);
        uniqueFlashcardList.add(I_CARD);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(A_CARD, I_CARD));
    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(A_CARD));
    }

    @Test
    public void remove_existingFlashcard_removesFlashcard() {
        uniqueFlashcardList.add(A_CARD);
        uniqueFlashcardList.remove(A_CARD);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
        uniqueFlashcardList.add(A_CARD);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(I_CARD);
        uniqueFlashcardList.setFlashcards(expectedUniqueFlashcardList);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setFlashcards_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(A_CARD);
        List<Flashcard> flashcardList = Collections.singletonList(I_CARD);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(I_CARD);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(A_CARD, A_CARD);
        assertThrows(DuplicateFlashcardException.class, ()
            -> uniqueFlashcardList.setFlashcards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }
}
