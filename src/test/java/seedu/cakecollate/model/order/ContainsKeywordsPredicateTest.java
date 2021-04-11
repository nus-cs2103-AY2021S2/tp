package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DELIVERY_STATUS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.testutil.OrderBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        HashMap<Prefix, List<String>> map1 = new HashMap<>();
        map1.put(PREFIX_NAME, firstPredicateKeywordList);
        HashMap<Prefix, List<String>> map2 = new HashMap<>();
        map1.put(PREFIX_NAME, secondPredicateKeywordList);

        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(map1);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(map2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(map1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different order -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_NAME, Collections.singletonList("Alice"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Sub keyword
        map.clear();
        map.put(PREFIX_NAME, Collections.singletonList("ice"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Alice", "Bob"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Bob", "Carol"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("aLIce", "bOB"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_PHONE, Collections.singletonList("12345678"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withPhone("12345678").build()));

        // Sub keyword
        map.clear();
        map.put(PREFIX_PHONE, Arrays.asList("123"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withPhone("12345678").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_PHONE, Arrays.asList("123", "456"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withPhone("12345678").build()));

        // Only one matching sub keyword
        map.clear();
        map.put(PREFIX_PHONE, Arrays.asList("123", "910"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withPhone("12345678").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_EMAIL, Collections.singletonList("alice@example.com"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));

        // Sub keyword
        map.clear();
        map.put(PREFIX_EMAIL, Arrays.asList("alice"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_EMAIL, Arrays.asList("alice", "@example"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));

        // Only one matching sub keyword
        map.clear();
        map.put(PREFIX_EMAIL, Arrays.asList(".com", ".sg"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));

        // Mixed-case sub keywords
        map.clear();
        map.put(PREFIX_EMAIL, Arrays.asList("aLIce", "eXAMplE"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));

        // Mixed-case keyword
        map.clear();
        map.put(PREFIX_EMAIL, Arrays.asList("aLIce@eXAMplE.Com"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_ADDRESS, Collections.singletonList("Jurong"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withAddress("123, Jurong West Ave 6, #08-111").build()));

        // Multiple keywords
        map.clear();
        map.put(PREFIX_ADDRESS, Arrays.asList("123,", "Jurong"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withAddress("123, Jurong West Ave 6, #08-111").build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_ADDRESS, Arrays.asList("Singapore", "West"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withAddress("123, Jurong West Ave 6, #08-111").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_ADDRESS, Arrays.asList("AVE", "JurONG"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withAddress("123, Jurong West Ave 6, #08-111").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_ADDRESS, Arrays.asList("8-11", "rOng"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withAddress("123, Jurong West Ave 6, #08-111").build()));
    }

    @Test
    public void test_orderDescriptionContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("Strawberry", "Cakes"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withOrderDescriptions("Strawberry Cakes").build()));

        // One keyword, two order descriptions
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("Chocolate", "Cakes"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder()
                .withOrderDescriptions("Strawberry Cakes", "Chocolate Cakes").build()));

        //Multiple keywords
        map.clear();
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("Strawberry"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withOrderDescriptions("Strawberry Cakes").build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("Strawberry", "Chocolate"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withOrderDescriptions("Strawberry Cakes").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("strAwBerRy", "CAKES"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withOrderDescriptions("Strawberry Cakes").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("berry", "ake"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withOrderDescriptions("Strawberry Cakes").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_TAG, Collections.singletonList("friends"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withTags("friends").build()));

        // One keyword
        map.put(PREFIX_TAG, Collections.singletonList("husband"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withTags("friends", "husband").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_TAG, Arrays.asList("friEndS"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withTags("friends").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_TAG, Arrays.asList("ends"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withTags("friends").build()));
    }

    @Test
    public void test_deliveryDateContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // day match
        map.put(PREFIX_DATE, Collections.singletonList("1"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // month match numeral
        map.put(PREFIX_DATE, Collections.singletonList("2"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // month match name
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("February"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // year match
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("2022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // format dd/MM/yyyy
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("01/02/2022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // format dd-MM-yyyy
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("01-02-2022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // format dd.MM.yyyy
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("01.02.2022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // format dd MMM yyyy
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("01", "Feb", "2022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        // sub keyword
        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("ruary"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));

        map.clear();
        map.put(PREFIX_DATE, Arrays.asList("022"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryDate("01/02/2022").build()));
    }

    @Test
    public void test_requestContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_REQUEST, Collections.singletonList("cake"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withRequest("Custom cake decoration, diamond hands").build()));

        // Multiple keywords
        map.clear();
        map.put(PREFIX_REQUEST, Arrays.asList("Custom", "hands"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withRequest("Custom cake decoration, diamond hands").build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_REQUEST, Arrays.asList("paper", "diamond"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withRequest("Custom cake decoration, diamond hands").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_REQUEST, Arrays.asList("CuStOm", "decoRATion"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withRequest("Custom cake decoration, diamond hands").build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_REQUEST, Arrays.asList("ake", "dia"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withRequest("Custom cake decoration, diamond hands").build()));
    }

    @Test
    public void test_deliveryStatusContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_DELIVERY_STATUS, Collections.singletonList("UNDELIVERED"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryStatus().build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_DELIVERY_STATUS, Arrays.asList("UNDELIVERED", "CANCELLED"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryStatus(Status.UNDELIVERED).build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_DELIVERY_STATUS, Arrays.asList("DELivEREd"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryStatus(Status.DELIVERED).build()));

        // Sub keywords
        map.clear();
        map.put(PREFIX_DELIVERY_STATUS, Arrays.asList("can", "lEd"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withDeliveryStatus(Status.CANCELLED).build()));
    }

    @Test
    public void test_orderContainsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.put(PREFIX_ALL, Collections.singletonList("alice"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(ALICE));

        // Multiple keywords
        map.clear();
        map.put(PREFIX_ALL, Arrays.asList("Jurong", "example"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(ALICE));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_ALL, Arrays.asList("1253", "6969"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(ALICE));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_ALL, Arrays.asList("StraWberRy", "friEndS"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(ALICE));

        // Sub keywords
        map.clear();
        map.put(PREFIX_ALL, Arrays.asList("Janua", "paul"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_doesNotContainKeywords_returnsFalse() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // Zero keywords
        map.clear();
        map.put(PREFIX_NAME, Collections.emptyList());
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").build()));

        // Non-matching keyword
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Carol"));
        predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
