package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AbstractId;

public class PredicateUtilTest {

    public class IdTestStub extends AbstractId<IdTestStub> {
        public IdTestStub(int id) {
            super(id);
        }
    }

    @Test
    public void null_containsPrefixWordIgnoreCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            PredicateUtil.containsPrefixWordIgnoreCase(null, Arrays.asList("key1")));
        assertThrows(NullPointerException.class, () ->
            PredicateUtil.containsPrefixWordIgnoreCase("string", null));
    }

    @Test
    public void containsPrefixWordIgnoreCase() {
        // Contains full word
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("abc abc", Arrays.asList("Abc")));
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("marry Ann", Arrays.asList("ann")));

        // Contains prefix
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("MAx lee", Arrays.asList("Ma")));
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("Armstrong Chan", Arrays.asList("chA")));

        // Contains suffix (false)
        assertFalse(PredicateUtil.containsPrefixWordIgnoreCase("Armstrong Chan", Arrays.asList("stronG")));
        assertFalse(PredicateUtil.containsPrefixWordIgnoreCase("Chan Yulee", Arrays.asList("Lee")));

        // More than 1 keywords
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("Yuan Lee Hui", Arrays.asList("Yu", "hui")));
        assertTrue(PredicateUtil.containsPrefixWordIgnoreCase("Boris, Ng Chan", Arrays.asList("alex", "bori")));
        assertFalse(PredicateUtil.containsPrefixWordIgnoreCase("Boris, Ng Chan", Arrays.asList("lee", "hui", "wu")));
    }

    @Test
    public void null_getWordSimilarityScoreIgnoreCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            PredicateUtil.getWordSimilarityScoreIgnoreCase(null, Arrays.asList("key1")));
        assertThrows(NullPointerException.class, () ->
            PredicateUtil.getWordSimilarityScoreIgnoreCase("string", null));
    }

    @Test
    public void getWordSimilarityScoreIgnoreCase() {
        final List<String> keywordList = Arrays.asList("key1", "key2", "key3");

        double allPresentScore = PredicateUtil.getWordSimilarityScoreIgnoreCase("key1 key2 key3", keywordList);
        double somePresentScore = PredicateUtil.getWordSimilarityScoreIgnoreCase("key1 key3", keywordList);
        double somePrefixPresentScore = PredicateUtil.getWordSimilarityScoreIgnoreCase("key138 key3", keywordList);

        assertTrue(allPresentScore > somePresentScore);
        assertTrue(somePresentScore > somePrefixPresentScore);
        assertTrue(somePrefixPresentScore > 0);

        double nonePresent = PredicateUtil.getWordSimilarityScoreIgnoreCase("key key key", keywordList);
        assertTrue(nonePresent == 0);

        final List<String> emptyKeywordList = Arrays.asList();
        assertTrue(PredicateUtil.getWordSimilarityScoreIgnoreCase("key1 key2 key3", emptyKeywordList) == 0);
    }

    @Test
    public void null_matchIntegerId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PredicateUtil.matchIntegerId(new IdTestStub(1), null));
        assertThrows(NullPointerException.class, () -> PredicateUtil.matchIntegerId(null, Arrays.asList("123")));
    }

    @Test
    public void matchIntegerId() {
        final List<String> keywordList = Arrays.asList("1", "2", "3");

        assertTrue(PredicateUtil.matchIntegerId(new IdTestStub(1), keywordList));
        assertTrue(PredicateUtil.matchIntegerId(new IdTestStub(3), keywordList));
        assertFalse(PredicateUtil.matchIntegerId(new IdTestStub(5), keywordList));

        // Match non-numeric
        final List<String> nonNumericKeywordList = Arrays.asList("abc");
        assertFalse(PredicateUtil.matchIntegerId(new IdTestStub(5), nonNumericKeywordList));
    }

}
