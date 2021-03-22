package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class FlashcardFilterPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FlashcardFilterPredicate firstPredicate =
                new FlashcardFilterPredicate(firstPredicateKeywordList, secondPredicateKeywordList,
                        secondPredicateKeywordList, firstPredicateKeywordList);
        FlashcardFilterPredicate secondPredicate =
                new FlashcardFilterPredicate(secondPredicateKeywordList, secondPredicateKeywordList,
                        secondPredicateKeywordList, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FlashcardFilterPredicate firstPredicateCopy =
                new FlashcardFilterPredicate(firstPredicateKeywordList, secondPredicateKeywordList,
                secondPredicateKeywordList, firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_flashcardFilter_returnsTrue() {
        // One keyword one field
        FlashcardFilterPredicate predicate =
                new FlashcardFilterPredicate(new ArrayList<>(), Collections.singletonList("Alice"),
                        new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Who is Bob?")
                .withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("Alice Bob").withTags("test", "testing").build()));

        // One partial keyword one field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Collections.singletonList("Ali"),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Who is Bob?")
                .withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("Alice Bob").withTags("test", "testing").build()));

        // One keyword multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Collections.singletonList("Alice"),
                        new ArrayList<>(), Collections.singletonList("testing"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("testing").withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("Alice Bob").withTags("test", "testing").build()));

        // One partial keyword multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Collections.singletonList("ic"),
                new ArrayList<>(), Collections.singletonList("sti"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("testing").withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("Alice Bob").withTags("test", "testing").build()));

        // Multiple keywords one field
        predicate = new FlashcardFilterPredicate(Arrays.asList("Alice", "Bob"), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob")
                .withCategory("People").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("test", "testing").build()));

        // Multiple partial keywords one field
        predicate = new FlashcardFilterPredicate(Arrays.asList("Ali", "B"), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").withCategory("People").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("test", "testing").build()));

        // Multiple keywords multiple field
        predicate = new FlashcardFilterPredicate(Arrays.asList("Alice", "Bob"), Arrays.asList("People", "Human"),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").withCategory("People").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Bob").withCategory("people humAn").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withCategory("human").withTags("test", "testing").build()));

        // Multiple partial keywords multiple field
        predicate = new FlashcardFilterPredicate(Arrays.asList("ic", "ob"), Arrays.asList("eopl", "man"),
                new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").withCategory("People").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Bob").withCategory("people humAn").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withCategory("human").withTags("test", "testing").build()));

        // Only one matching keyword one field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("High", "Mid"), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").withPriority("Mid").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("High").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("test", "testing").build()));

        // Only one matching partial keyword one field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("Hi", "id"), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").withPriority("Mid").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("High").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("test", "testing").build()));

        // Only one matching keyword multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Arrays.asList("People", "human"),
                Arrays.asList("High", "Mid"), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("human").withPriority("Mid").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("High").withCategory("people testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("human people").withTags("test", "testing").build()));

        // Only one matching partial keyword multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Arrays.asList("Peop", "uma"),
                Arrays.asList("ig", "id"), new ArrayList<>());
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("human").withPriority("Mid").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("High").withCategory("people testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withCategory("human people").withTags("test", "testing").build()));

        // Mixed-case keywords one field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), Arrays.asList("rAndOm", "teStinG"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("People").withTags("random").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("RANDOM", "testing").build()));

        // Mixed-case partial keywords one field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), Arrays.asList("And", "teS"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("People").withTags("random").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob")
                .withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("RANDOM", "testing").build()));

        // Mixed-case keywords multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("mId", "lOW"), Arrays.asList("rAndOm", "teStinG"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").withTags("random").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Low").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("RANDOM", "testing").build()));

        // Mixed-case partial keywords multiple field
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("Id", "lO"), Arrays.asList("AndO", "eSti"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").withTags("random").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Low").withTags("test", "testing").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid")
                .withQuestion("Alice Bob").withTags("RANDOM", "testing").build()));
    }

    @Test
    public void test_flashcardFilter_returnsFalse() {
        // Only 1 matching field
        FlashcardFilterPredicate predicate =
                new FlashcardFilterPredicate(new ArrayList<>(), Arrays.asList("People", "Human"),
                        new ArrayList<>(), Arrays.asList("testing", "random"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice").withTags("testing").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice")
                .withCategory("people human").withTags("others", "more").build()));
        predicate = new FlashcardFilterPredicate(Arrays.asList("Question"), Arrays.asList("Category"),
                Arrays.asList("Mid"), Arrays.asList("Tag"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Question").withAnswer("Answer")
                .withCategory("Category").withPriority("Mid").withTags("Test").build()));

        // No matching keyword
        predicate = new FlashcardFilterPredicate(Arrays.asList("Carol"), Arrays.asList("Child"), new ArrayList<>(),
                Arrays.asList("test"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice").withTags("testing").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice")
                .withCategory("people human").withTags("others", "more").build()));

        // Non-matching bigger keyword
        predicate = new FlashcardFilterPredicate(new ArrayList<>(), Arrays.asList("Peoples", "Humans"),
                new ArrayList<>(), Arrays.asList("testings", "moreMore"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice").withTags("testing").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice")
                .withCategory("people human").withTags("others", "more").build()));
    }
}
