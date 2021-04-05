package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.room.TypicalRooms.ROOM_SUITE_AC_NOT_OCCUPIED;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.room.RoomBuilder;

public class RoomNumberOrTagsContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RoomNumberOrTagsContainsKeywordsPredicate firstPredicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        RoomNumberOrTagsContainsKeywordsPredicate secondPredicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomNumberOrTagsContainsKeywordsPredicate firstPredicateCopy =
                new RoomNumberOrTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different resident -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_roomNumberContainsKeywords_returnsTrue() {
        // One keyword
        RoomNumberOrTagsContainsKeywordsPredicate predicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(Collections.singletonList("01"));
        assertTrue(predicate.test(new RoomBuilder().withRoomNumber("01-123").build()));

        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Collections.singletonList("123"));
        assertTrue(predicate.test(new RoomBuilder().withRoomNumber("01-123").build()));

        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Collections.singletonList("01-123"));
        assertTrue(predicate.test(new RoomBuilder().withRoomNumber("01-123").build()));

        // Multiple keywords
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("01", "02"));
        assertTrue(predicate.test(new RoomBuilder().withRoomNumber("01-021").build()));

        // Only one matching keyword
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("01", "02"));
        assertTrue(predicate.test(new RoomBuilder().withRoomNumber("02-123").build()));

    }

    @Test
    public void test_roomNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoomNumberOrTagsContainsKeywordsPredicate predicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RoomBuilder().withRoomNumber("01-123").build()));

        // Non-matching keyword
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("01-123"));
        assertFalse(predicate.test(new RoomBuilder().withRoomNumber("02-234").build()));

        // Keywords match occupancy and room type, but does not match room number
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("suite_ac", "n"));
        assertFalse(predicate.test(new RoomBuilder(ROOM_SUITE_AC_NOT_OCCUPIED)
                .build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        RoomNumberOrTagsContainsKeywordsPredicate predicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(Collections.singletonList("tagOne"));
        assertTrue(predicate.test(new RoomBuilder().withTags("tagOne").build()));

        // Multiple keywords
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("tagOne", "tagTwo"));
        assertTrue(predicate.test(new RoomBuilder().withTags("tagOne").build()));

        // Only one matching keyword
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("tagOne", "tagTwo"));
        assertTrue(predicate.test(new RoomBuilder().withTags("tagZero", "tagOne").build()));

    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoomNumberOrTagsContainsKeywordsPredicate predicate =
                new RoomNumberOrTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RoomBuilder().withTags("tagOne").build()));

        // Non-matching keyword
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("tagOne", "tagTwo"));
        assertFalse(predicate.test(new RoomBuilder().withTags("tagThree").build()));

        // Keywords match occupancy and room type, but does not match room number
        predicate = new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList("suite_ac", "n"));
        assertFalse(predicate.test(new RoomBuilder(ROOM_SUITE_AC_NOT_OCCUPIED)
                .withTags("LOW") // Tag "Low" does not contain any of the characters in "suite_ac" and "N"
                .build()));
    }
}
