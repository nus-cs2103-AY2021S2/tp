package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.AT;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashback.model.flashcard.exceptions.FlashcardNotFoundException;
import seedu.flashback.testutil.FlashcardBuilder;

public class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_flashcardNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(PYTHAGOREAN));
    }

    @Test
    public void contains_flashcardInList_returnsTrue() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        assertTrue(uniqueFlashcardList.contains(PYTHAGOREAN));
    }

    @Test
    public void contains_flashcardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        assertTrue(uniqueFlashcardList.contains(editedAlice));
    }

    @Test
    public void add_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicateFlashcard_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(PYTHAGOREAN));
    }

    @Test
    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setCard(null, PYTHAGOREAN));
    }

    @Test
    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setCard(PYTHAGOREAN, null));
    }

    @Test
    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList
                .setCard(PYTHAGOREAN, PYTHAGOREAN));
    }

    @Test
    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        uniqueFlashcardList.setCard(PYTHAGOREAN, PYTHAGOREAN);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(PYTHAGOREAN);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        Flashcard editedAlice = new FlashcardBuilder(PYTHAGOREAN)
                .withPriority(VALID_PRIORITY_OCTOPUS).withTags(VALID_TAG_EQUATION).build();
        uniqueFlashcardList.setCard(PYTHAGOREAN, editedAlice);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(editedAlice);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        uniqueFlashcardList.setCard(PYTHAGOREAN, AT);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(AT);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        uniqueFlashcardList.add(AT);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setCard(PYTHAGOREAN, AT));
    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(PYTHAGOREAN));
    }

    @Test
    public void remove_existingFlashcard_removesFlashcard() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        uniqueFlashcardList.remove(PYTHAGOREAN);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setCards((UniqueFlashcardList) null));
    }

    @Test
    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(AT);
        uniqueFlashcardList.setCards(expectedUniqueFlashcardList);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setCards((List<Flashcard>) null));
    }

    @Test
    public void setFlashcards_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(PYTHAGOREAN);
        List<Flashcard> flashcardList = Collections.singletonList(AT);
        uniqueFlashcardList.setCards(flashcardList);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(AT);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(PYTHAGOREAN, PYTHAGOREAN);
        assertThrows(DuplicateFlashcardException.class, () ->
                uniqueFlashcardList.setCards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }
}
