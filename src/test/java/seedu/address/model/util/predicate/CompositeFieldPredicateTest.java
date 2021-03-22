package seedu.address.model.util.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCustomers.DANIEL;
import static seedu.address.testutil.TypicalCustomers.getTypicalCustomers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.customer.Customer;
import seedu.address.model.customer.predicates.CustomerAddressPredicate;
import seedu.address.model.customer.predicates.CustomerEmailPredicate;
import seedu.address.model.customer.predicates.CustomerNamePredicate;

public class CompositeFieldPredicateTest {

    @Test
    public void equals() {
        // Check that composites are still equal after performing compose
        final List<String> keywords = Arrays.asList("key1", "key2");

        CompositeFieldPredicateBuilder<Customer> composite = new CompositeFieldPredicateBuilder<>();

        composite.compose(new CustomerEmailPredicate(keywords));

        CompositeFieldPredicate<Customer> actualPredicate = composite.build();
        CompositeFieldPredicate<Customer> expectedPredicate =
            new CompositeFieldPredicate<>(Set.of(new CustomerEmailPredicate(keywords)));

        assertEquals(expectedPredicate, actualPredicate);

        composite.compose(new CustomerAddressPredicate(keywords));

        actualPredicate = composite.build();
        expectedPredicate =
            new CompositeFieldPredicate<>(Set.of(
                new CustomerEmailPredicate(keywords),
                new CustomerAddressPredicate(keywords)
            ));

        assertEquals(expectedPredicate, actualPredicate);

        composite.compose(new CustomerNamePredicate(keywords));

        actualPredicate = composite.build();
        expectedPredicate = new CompositeFieldPredicate<>(Set.of(
            new CustomerEmailPredicate(keywords),
            new CustomerAddressPredicate(keywords),
            new CustomerNamePredicate(keywords)
        ));

        assertEquals(expectedPredicate, actualPredicate);
    }

    public void testCustomerCompositeFieldPredicate() {
        List<Customer> customerList = getTypicalCustomers();

        FieldPredicate<Customer> emailExamplePredicate = new CustomerEmailPredicate(Arrays.asList("example"));
        FieldPredicate<Customer> addressStreetPredicate = new CustomerAddressPredicate(Arrays.asList("street"));
        FieldPredicate<Customer> nameMePredicate = new CustomerNamePredicate(Arrays.asList("me"));

        CompositeFieldPredicate<Customer> compositeCustomerPredicate =
            new CompositeFieldPredicate<>(Set.of(
                emailExamplePredicate,
                addressStreetPredicate,
                nameMePredicate
            ));

        List<Customer> expectedList = Arrays.asList(DANIEL);
        List<Customer> filteredList = applyFieldPredicateToList(customerList, compositeCustomerPredicate);

        assertEquals(expectedList, filteredList);
    }

    private static <U> List<U> applyFieldPredicateToList(List<U> list, FieldPredicate<U> predicate) {
        return list.stream().filter(predicate).sorted(predicate).collect(Collectors.toList());
    }
}
