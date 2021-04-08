package chim.model.util.predicate;

import static chim.testutil.TypicalCustomers.ALICE;
import static chim.testutil.TypicalCustomers.BENSON;
import static chim.testutil.TypicalCustomers.CARL;
import static chim.testutil.TypicalCustomers.DANIEL;
import static chim.testutil.TypicalCustomers.ELLE;
import static chim.testutil.TypicalCustomers.FIONA;
import static chim.testutil.TypicalCustomers.GEORGE;
import static chim.testutil.TypicalCustomers.getTypicalCustomers;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import chim.model.customer.Customer;
import chim.model.customer.predicates.CustomerAddressPredicate;
import chim.model.customer.predicates.CustomerNamePredicate;
import chim.model.customer.predicates.CustomerPhonePredicate;

public class FieldPredicateTest {

    @Test
    public void testDefaultPredicate() {
        FieldPredicate<Customer> customerDefault = FieldPredicate.getDefaultPredicate();

        List<Customer> customerList = getTypicalCustomers();

        List<Customer> expectedList = customerList;
        List<Customer> filteredList = applyFieldPredicateToList(customerList, customerDefault);

        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testEmptyPredicate() {
        FieldPredicate<Customer> customerEmpty = FieldPredicate.getEmptyPredicate();

        List<Customer> customerList = getTypicalCustomers();

        List<Customer> expectedList = Arrays.asList();
        List<Customer> filteredList = applyFieldPredicateToList(customerList, customerEmpty);

        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testCustomerFieldPredicate() {
        List<Customer> customerList = getTypicalCustomers();

        // Compare by name
        FieldPredicate<Customer> nameAlicePredicate = new CustomerNamePredicate(Arrays.asList("Alice"));

        List<Customer> expectedList = Arrays.asList(ALICE);
        List<Customer> filteredList = applyFieldPredicateToList(customerList, nameAlicePredicate);

        assertEquals(expectedList, filteredList);

        // Compare by address
        FieldPredicate<Customer> addressStreetPredicate = new CustomerAddressPredicate(Arrays.asList("street"));

        expectedList = Arrays.asList(CARL, DANIEL, GEORGE);
        filteredList = applyFieldPredicateToList(customerList, addressStreetPredicate);

        assertEquals(expectedList, filteredList);

        // Compare by phone (prefix)
        FieldPredicate<Customer> phonePredicate = new CustomerPhonePredicate(Arrays.asList("9482"));

        expectedList = Arrays.asList(ELLE, FIONA, GEORGE);
        filteredList = applyFieldPredicateToList(customerList, phonePredicate);

        assertEquals(expectedList, filteredList);

        // Compare by name (order relevance
        FieldPredicate<Customer> nameMeDanielPredicate = new CustomerNamePredicate(Arrays.asList("Me", "Daniel"));

        // Must be in exact order (most relevant results on top)
        expectedList = Arrays.asList(DANIEL, BENSON, ELLE);
        filteredList = applyFieldPredicateToList(customerList, nameMeDanielPredicate);

        assertEquals(expectedList, filteredList);
    }

    private static <U> List<U> applyFieldPredicateToList(List<U> list, FieldPredicate<U> predicate) {
        return list.stream().filter(predicate).sorted(predicate).collect(Collectors.toList());
    }

}
