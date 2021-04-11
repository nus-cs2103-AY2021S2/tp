package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.predicate.TagsMatchKeywordPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ContactBuilder;

public class TagsMatchKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        Set<Tag> firstPredicateTagSet = new HashSet<>(Arrays.asList(new Tag("TagA")));

        Set<Tag> secondPredicateTagSet = new HashSet<>(Arrays.asList(
                new Tag("TagA"), new Tag("TagB")));

        TagsMatchKeywordPredicate firstPredicate = new TagsMatchKeywordPredicate(firstPredicateTagSet);
        TagsMatchKeywordPredicate secondPredicate = new TagsMatchKeywordPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsMatchKeywordPredicate firstPredicateCopy = new TagsMatchKeywordPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagMatchKeywords_returnsTrue() {
        // One tag
        Set<Tag> singleTagSet = new HashSet<>(Arrays.asList(new Tag("Friend")));
        TagsMatchKeywordPredicate predicate = new TagsMatchKeywordPredicate(singleTagSet);
        assertTrue(predicate.test(new ContactBuilder().withTags("Friend", "Facebook").build()));

        // Multiple tags
        Set<Tag> multipleTagSet = new HashSet<>(Arrays.asList(
                new Tag("Colleague"), new Tag("Google")));
        predicate = new TagsMatchKeywordPredicate(multipleTagSet);
        assertTrue(predicate.test(new ContactBuilder().withTags("Colleague", "Google").build()));

        // Only one matching tag
        Set<Tag> diffMultipleTagSet = new HashSet<>(Arrays.asList(
                new Tag("Friend"), new Tag("Facebook")));
        predicate = new TagsMatchKeywordPredicate(diffMultipleTagSet);
        assertTrue(predicate.test(new ContactBuilder().withTags("Friend", "Microsoft").build()));
    }

    @Test
    public void test_tagDoesNotMatchKeywords_returnsFalse() {
        // Zero tags
        Set<Tag> emptyTagSet = new HashSet<>();
        TagsMatchKeywordPredicate predicate = new TagsMatchKeywordPredicate(emptyTagSet);
        assertFalse(predicate.test(new ContactBuilder().withTags("Google").build()));

        // Non-matching keyword
        Set<Tag> nonMatchTagSet = new HashSet<>(Arrays.asList(new Tag("Google")));
        predicate = new TagsMatchKeywordPredicate(nonMatchTagSet);
        assertFalse(predicate.test(new ContactBuilder().withTags("Friend", "Facebook").build()));

        // Mixed-case keywords
        Set<Tag> mixedCaseTagSet = new HashSet<>(Arrays.asList(
                new Tag("fRiEnd"), new Tag("fAcEbOok")));
        predicate = new TagsMatchKeywordPredicate(mixedCaseTagSet);
        assertFalse(predicate.test(new ContactBuilder().withTags("Friend", "Facebook").build()));
    }
}
