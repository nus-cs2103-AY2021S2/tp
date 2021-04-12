package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_NAME_MIXED_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_LAST_NAME_MIXED_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FEMALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FEMALE_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_BOB_LOWER_CASE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.testutil.PassengerBuilder;

/*
Keywords changed to lowercase as parser will change all keywords to lowercase for the Predicate.
 */
public class AttributeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AttributeContainsKeywordsPredicate firstPredicate =
                new AttributeContainsKeywordsPredicate(firstPredicateKeywordList);
        AttributeContainsKeywordsPredicate secondPredicate =
                new AttributeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttributeContainsKeywordsPredicate firstPredicateCopy =
                new AttributeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_attributeContainsKeywordsInName_returnsTrue() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList(
                        VALID_NAME_AMY_LOWER_CASE.split("\\s+")[0]));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_LOWER_CASE.split("\\s")));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Multiple words as one keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_LOWER_CASE));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Only one matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_LOWER_CASE.split("\\s")[0],
                VALID_NAME_BOB_LOWER_CASE.split("\\s")[0]));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_BOB).build()));

        // Mixed-case keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_FIRST_NAME_MIXED_CASE.toLowerCase(),
                VALID_NAME_AMY_LAST_NAME_MIXED_CASE.toLowerCase()));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));
    }

    @Test
    public void test_attributeContainsKeywordsInPhoneNumber_returnsTrue() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_PHONE_AMY));
        assertTrue(predicate.test(new PassengerBuilder().withPhone(VALID_PHONE_AMY).build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_PHONE_AMY, VALID_PHONE_BOB));
        assertTrue(predicate.test(new PassengerBuilder().withPhone(VALID_PHONE_AMY).build()));
        assertTrue(predicate.test(new PassengerBuilder().withPhone(VALID_PHONE_BOB).build()));
    }

    @Test
    public void test_attributeContainsKeywordsInTag_returnsTrue() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_TAG_FEMALE_LOWER_CASE));
        assertTrue(predicate.test(new PassengerBuilder().withTags(VALID_TAG_FEMALE).build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_TAG_IT_LOWER_CASE, VALID_TAG_FEMALE_LOWER_CASE));
        assertTrue(predicate.test(new PassengerBuilder().withTags(VALID_TAG_IT).build()));
        assertTrue(predicate.test(new PassengerBuilder().withTags(VALID_TAG_FEMALE).build()));
        assertTrue(predicate.test(new PassengerBuilder().withTags(VALID_TAG_FEMALE, VALID_TAG_IT).build()));
    }

    @Test
    public void test_attributeContainsKeywordsInTag_returnsFalse() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList(VALID_TAG_FEMALE_LOWER_CASE));
        assertFalse(predicate.test(new PassengerBuilder().withTags(VALID_TAG_IT).build()));

        // No keywords
        predicate = new AttributeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_BOB).withTags(VALID_TAG_IT).build()));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FEMALE).build()));
        // Passenger with no tags shouldn't show up
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_BOB).build()));
    }

    @Test
    public void test_attributeDoesNotContainKeywordsInName_returnsFalse() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Non-matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_PHONE_AMY));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_BOB).build()));

        // Keywords match name and address, but does not match tripday
        // all search does not search by tripday
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList(VALID_TRIPDAY_BOB_LOWER_CASE));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTripDay(VALID_TRIPDAY_BOB).build()));
    }
}
