package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_NAME_MIXED_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_LAST_NAME_MIXED_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_LOWER_CASE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PassengerBuilder;

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

        // different passenger -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList(
                        VALID_NAME_AMY_LOWER_CASE.split("\\s+")[0]));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_LOWER_CASE.split("\\s")));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_LOWER_CASE.split("\\s")[0],
                VALID_NAME_BOB.split("\\s")[0]));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Mixed-case keywords
        // Keywords changed to lowercase as parser will change all keywords to lowercase
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY_FIRST_NAME_MIXED_CASE.toLowerCase(),
                VALID_NAME_AMY_LAST_NAME_MIXED_CASE.toLowerCase()));
        assertTrue(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_BOB.split("\\s+")));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).build()));

        // Keywords match phone and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_BOB.split("\\s+")));
        assertFalse(predicate.test(new PassengerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_BOB).build()));
    }
}
