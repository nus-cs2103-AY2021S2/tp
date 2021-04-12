package seedu.address.logic.filters;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.customer.Customer;

public class FilterTest {
    @Test
    public void emptyOrNullFilterString() {
        assertThrows(NullPointerException.class, () -> new Filter(null){
            @Override public boolean test(Customer customer) {
                return false;
            }
        });
        assertThrows(IllegalArgumentException.class, () -> new Filter(""){
            @Override public boolean test(Customer customer) {
                return false;
            }
        });
        assertThrows(IllegalArgumentException.class, () -> new Filter("  "){
            @Override public boolean test(Customer customer) {
                return false;
            }
        });
    }

}
