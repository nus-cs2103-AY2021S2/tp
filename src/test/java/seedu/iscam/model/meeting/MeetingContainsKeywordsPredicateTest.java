package seedu.iscam.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.iscam.testutil.MeetingBuilder;

public class MeetingContainsKeywordsPredicateTest {

    @Test
    public void equals() {

    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // 1 keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "Fiona"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Fiona Gunther").build()));

        // Partial keyword
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("ion"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Fiona Gunther").build()));

        // Multiple keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("ion", "unth"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Fiona Gunther").build()));

        // Mixed-case keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("iOn", "UnTH"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Fiona Gunther").build()));
    }

    @Test
    public void test_nameDoesNotContainsKeywords_returnsFalse() {
        // No keywords
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withName("Fiona").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Alice", "Bob", "Johnson"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Fiona Gunther").build()));
    }

    @Test
    public void test_dateTimeContainsDigits_returnsTrue() {
        // Day and month
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "31-12"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Year only
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("2030"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Full date
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("31-12-2030"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Full time
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("10:00"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Full date-time
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("31-12-2030", "10:00"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));
    }

    @Test
    public void test_dateTimeDoesNotContainsDigits_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // No matching keyword
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keyword"));
        assertFalse(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));
    }

    @Test
    public void test_dateTimeContainsDayOfWeek_returnsTrue() {
        // 3-letter representation e.g. Tue
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "Tue"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Full representation e.g. Tuesday
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("Tuesday"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // Mixed case
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("tuESdAy"));
        assertTrue(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));
    }

    @Test
    public void test_dateTimeDoesNotContainsDayOfWeek_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withDateTime("31-12-2030 10:00").build()));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // 1 keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "Starbucks"));
        assertTrue(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));

        // Partial keyword
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("arbu"));
        assertTrue(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));

        // Multiple keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Starbucks", "Bedok"));
        assertTrue(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));

        // Mixed-case keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("StARBUckS", "bEDOk"));
        assertTrue(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));
    }

    @Test
    public void test_locationDoesNotContainsKeywords_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withLocation("Starbucks, Bedok Mall").build()));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // 1 keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "Discuss"));
        assertTrue(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));

        // Partial keyword
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("cuss"));
        assertTrue(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));

        // Multiple keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("policy", "insur"));
        assertTrue(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));

        // Mixed-case keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("inSuRANCE", "DISCUss"));
        assertTrue(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));
    }

    @Test
    public void test_descriptionDoesNotContainsKeywords_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withDescription("Discuss insurance policy").build()));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // 1 keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "VIP"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("VIP").build()));

        // Partial keyword
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("IP"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("VIP").build()));

        // Multiple keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("VIP", "Friend"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("VIP", "Friend", "Premium").build()));

        // Mixed-case keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("vip", "friEnD"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("VIP", "Friend", "Premium").build()));
    }

    @Test
    public void test_tagsDoesNotContainsKeywords_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withTags("VIP", "Friend", "Premium").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("VIP", "Friend", "Premium").build()));
    }

    @Test
    public void test_statusContainsComplete_returnsTrue() {
        // Matching
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "complete"));
        assertTrue(predicate.test(new MeetingBuilder().withStatus("complete").build()));

        // Mix-case matching
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("coMPleTe"));
        assertTrue(predicate.test(new MeetingBuilder().withStatus("complete").build()));
    }

    @Test
    public void test_statusDoesNotContainsComplete_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withStatus("complete").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withStatus("complete").build()));
    }

    @Test
    public void test_statusContainsIncomplete_returnsTrue() {
        // Matching
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList(
                "incomplete"));
        assertTrue(predicate.test(new MeetingBuilder().withStatus("incomplete").build()));

        // Mix-case matching
        predicate = new MeetingContainsKeywordsPredicate(Collections.singletonList("iNcoMPleTe"));
        assertTrue(predicate.test(new MeetingBuilder().withStatus("incomplete").build()));
    }

    @Test
    public void test_statusDoesNotContainsIncomplete_returnsFalse() {
        // No keyword
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withStatus("incomplete").build()));

        // No matching keywords
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Just", "Some", "Keywords"));
        assertFalse(predicate.test(new MeetingBuilder().withStatus("incomplete").build()));
    }
}
