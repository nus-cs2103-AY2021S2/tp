// package seedu.us.among.model.endpoint;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// import seedu.us.among.testutil.EndpointBuilder;

// to-do not sure what this class exists for

// public class NameContainsKeywordsPredicateTest {

// @Test
// public void equals() {
// List<String> firstPredicateKeywordList = Collections.singletonList("first");
// List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

// NameContainsKeywordsPredicate firstPredicate = new
// NameContainsKeywordsPredicate(firstPredicateKeywordList);
// NameContainsKeywordsPredicate secondPredicate = new
// NameContainsKeywordsPredicate(secondPredicateKeywordList);

// // same object -> returns true
// assertTrue(firstPredicate.equals(firstPredicate));

// // same values -> returns true
// NameContainsKeywordsPredicate firstPredicateCopy = new
// NameContainsKeywordsPredicate(firstPredicateKeywordList);
// assertTrue(firstPredicate.equals(firstPredicateCopy));

// // different types -> returns false
// assertFalse(firstPredicate.equals(1));

// // null -> returns false
// assertFalse(firstPredicate.equals(null));

// // different endpoint -> returns false
// assertFalse(firstPredicate.equals(secondPredicate));
// }

// @Test
// public void test_nameContainsKeywords_returnsTrue() {
// // One keyword
// NameContainsKeywordsPredicate predicate = new
// NameContainsKeywordsPredicate(Collections.singletonList("GET"));
// assertTrue(predicate.test(new EndpointBuilder().withMethod("GET
// POST").build()));

// // Multiple keywords
// predicate = new NameContainsKeywordsPredicate(Arrays.asList("GET", "POST"));
// assertTrue(predicate.test(new EndpointBuilder().withMethod("GET
// POST").build()));

// // Only one matching keyword
// predicate = new NameContainsKeywordsPredicate(Arrays.asList("POST",
// "Carol"));
// assertTrue(predicate.test(new EndpointBuilder().withMethod("GET
// Carol").build()));

// // Mixed-case keywords
// predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
// assertTrue(predicate.test(new EndpointBuilder().withMethod("GET
// POST").build()));
// }

// @Test
// public void test_nameDoesNotContainKeywords_returnsFalse() {
// // Zero keywords
// NameContainsKeywordsPredicate predicate = new
// NameContainsKeywordsPredicate(Collections.emptyList());
// assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));

// // Non-matching keyword
// predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
// assertFalse(predicate.test(new EndpointBuilder().withMethod("GET
// POST").build()));

// // Keywords match phone, email and address, but does not match name
// predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345",
// "alice@email.com", "Main", "Street"));
// assertFalse(predicate.test(new
// EndpointBuilder().withMethod("GET").withAddress("Main Street").build()));
// }
//
