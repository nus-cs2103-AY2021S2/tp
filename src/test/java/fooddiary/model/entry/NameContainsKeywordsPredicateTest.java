package fooddiary.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import fooddiary.testutil.EntryBuilder;


public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different entry -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("KFC"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC Clementi").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("KFC", "Clementi"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC Clementi").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Techno", "KFC"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("teCHnO", "KfC"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Macdonalds"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").build()));

        // Keywords match review, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Great!"));
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withReviews("Food is Great!").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("FastFood"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withTags("FastFood").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Western", "Indian"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen").withTags("Western", "Indian").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("FastFood", "Western"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen").withTags("Western", "Indian").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("wEStErN", "INdIan"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen").withTags("Western", "Indian").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withTags("Western").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withTags("Indian").build()));

        // Keywords match review, but does not match tag
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withTags("FastFood").build()));
    }

    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("4/5"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withRating("4").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("4/5", "5/5"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen").withRating("5").build()));
    }

    @Test
    public void test_ratingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withRating("2").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("1/5"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("3").build()));

        // Keywords match review, but does not match rating
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withRating("2").build()));
    }

    @Test
    public void test_priceContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("$8"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withPrice("8").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("$5", "$9"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen").withPrice("5").build()));
    }

    @Test
    public void test_priceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withPrice("2").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("$2"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("3").build()));

        // Keywords match review, but does not match price
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withPrice("4").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("Science"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Science", "Drive"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Science", "Drive"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("eNGinEerInG", "pArK"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withAddress("27 Prince George's Park, Singapore 118425").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Science"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));

        // Keywords match review, but does not match address
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Great!"));
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withReviews("Food is Great!")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));
    }
}
