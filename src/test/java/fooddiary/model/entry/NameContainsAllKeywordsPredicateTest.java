package fooddiary.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import fooddiary.testutil.EntryBuilder;

public class NameContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsAllKeywordsPredicate firstPredicate =
                new NameContainsAllKeywordsPredicate(firstPredicateKeywordList);
        NameContainsAllKeywordsPredicate secondPredicate =
                new NameContainsAllKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsAllKeywordsPredicate firstPredicateCopy =
                new NameContainsAllKeywordsPredicate(firstPredicateKeywordList);
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
        NameContainsAllKeywordsPredicate predicate =
                new NameContainsAllKeywordsPredicate(Collections.singletonList("KFC"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC Clementi").build()));

        // Multiple keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("KFC", "Clementi"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC Clementi").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("teCHnO"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").build()));

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Macdonalds"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "KFC"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").build()));

        // Keywords match review, but does not match name
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Great!"));
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withReviews("Food is Great!").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("FastFood"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withTagCategories("FastFood").build()));

        // Multiple keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Western", "Indian"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withTagCategories("Western", "Indian").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("wEStErN", "INdIan"));
        assertTrue(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withTagCategories("Western", "Indian").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier")
                .withTagCategories("Western").build()));

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withTagCategories("Indian").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("FastFood", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withTagCategories("Western", "Indian").build()));

        // Keywords match review, but does not match tag
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withTagCategories("FastFood").build()));
    }

    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("4/5"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withRating("4").build()));
    }

    @Test
    public void test_ratingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withRating("2").build()));

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("1/5"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("3").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("4/5", "5/5"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").withRating("5").build()));

        // Keywords match review, but does not match rating
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withRating("2").build()));
    }

    @Test
    public void test_priceContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("$7"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withPrice("7").build()));
    }

    @Test
    public void test_priceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withPrice("6").build()));

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$3"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$5", "$6"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").withPrice("6").build()));

        // Keywords match review, but does not match price
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withPrice("7").build()));
    }

    @Test
    public void test_priceRangeContainsKeywords_returnsTrue() {
        // One keyword - keyword single price, entry price range
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("$7"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withPrice("7-12").build()));

        // One keyword - keyword price range, entry single price
        predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("$7-12"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withPrice("11").build()));

        // One keyword - keyword price range, entry price range
        predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("$6-15"));
        assertTrue(predicate.test(new EntryBuilder().withName("KFC").withPrice("7-12").build()));
    }

    @Test
    public void test_priceRangeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withPrice("5-9").build()));

        // Keywords match review, but does not match price
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Spicy"));
        assertFalse(predicate.test(new EntryBuilder().withName("Macdonalds").withReviews("Mcspicy not very spicy")
                .withPrice("7-11").build()));

        // Non-matching keyword - keyword single price, entry price range (higher)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$3"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4-7").build()));

        // Non-matching keyword - keyword single price, entry price range (lower)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$10"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4-9").build()));

        // Only one matching keyword - keyword single price, entry price range
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$8", "$6"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").withPrice("3-7").build()));

        // Non-matching keyword - keyword price range, entry single price (higher)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$2-3"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4").build()));

        // Non-matching keyword - keyword price range, entry single price (lower)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$10-15"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("9").build()));

        // Only one matching keyword - keyword price range, entry single price
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$8-10", "$4-6"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").withPrice("3-7").build()));

        // Non-matching keyword - keyword price range, entry price range (higher)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$2-3"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4-7").build()));

        // Non-matching keyword - keyword price range, entry price range (lower)
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$10-15"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withPrice("4-9").build()));

        // Only one matching keyword - keyword price range, entry price range
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("$8-10", "$4-6"));
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen").withPrice("3-7").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Collections.singletonList("Science"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));

        // Multiple keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Science", "Drive"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("eNGinEerInG"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withAddress("27 Prince George's Park, Singapore 118425").build()));

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Science"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Science", "Drive"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));

        // Keywords match review, but does not match address
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Great!"));
        assertFalse(predicate.test(new EntryBuilder().withName("Frontier").withReviews("Food is Great!")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));
    }

    @Test
    public void test_foodEntryContainsAllKeywords_returnsTrue() {
        // Name & rating keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(
                Arrays.asList("Frontier", "4/5"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier").withRating("4").build()));

        // Name & address keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Frontier", "Science"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withAddress("12 Science Drive 2, Singapore 117549").build()));

        // Name & tag keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Frontier", "Western"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier")
                .withTagCategories("Western")
                .withTagSchools("SCIENCE")
                .build()));

        // Name & price keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Frontier", "$5-9"));
        assertTrue(predicate.test(new EntryBuilder().withName("Frontier").withPrice("4-7").build()));

        // Rating & address keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("2/5", "Drive"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withRating("2").withAddress("2 Engineering Drive 4, Singapore 117584").build()));

        // Rating & tag keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("2/5", "Indian"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withRating("2").withTagCategories("Indian").build()));

        // Rating & price keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("2/5", "$4-9"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("2").withPrice("6").build()));

        // Address & tag keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Engineering", "Indian"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Indian").build()));

        // Address & price keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Engineering", "$4-6"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withPrice("6-8").build()));

        // Tag & price keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Indian", "$3-5"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge")
                .withTagCategories("Indian").withPrice("3-5").build()));

        // Name, rating, address, tag & price keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "4/5", "$5-9", "Drive", "Western"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno").withRating("4").withPrice("5")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("eNGinEerInG", "1/5", "edGE"));
        assertTrue(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("1")
                .withAddress("2 Engineering Drive 4, Singapore 117584").build()));
    }

    @Test
    public void test_foodEntryDoesNotContainAllKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EntryBuilder().withName("PGP Canteen")
                .withAddress("27 Prince George's Park, Singapore 118425").build()));

        // Non-matching name keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Frontier", "5/5", "$4", "Drive", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("5").withPrice("4")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Non-matching rating keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "2/5", "$4", "Drive", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("5").withPrice("4")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Non-matching price keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "2/5", "$4-6", "Drive", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("2").withPrice("7")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Non-matching address keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "5/5", "$4", "Science", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("5").withPrice("4")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Non-matching tag keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Techno", "5/5", "$4", "Drive", "Indian"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("5").withPrice("4")
                .withAddress("2 Engineering Drive 4, Singapore 117584").withTagCategories("Western").build()));

        // Keywords match review, and also name, rating, price, address & tag
        predicate = new NameContainsAllKeywordsPredicate(
                Arrays.asList("Techno", "5/5", "$4-5", "Drive", "Cheap", "Western"));
        assertFalse(predicate.test(new EntryBuilder().withName("Techno Edge").withRating("5").withPrice("3-6")
                .withAddress("2 Engineering Drive 4, Singapore 117584")
                .withReviews("Cheap food!").withTagCategories("Western").build()));
    }
}
