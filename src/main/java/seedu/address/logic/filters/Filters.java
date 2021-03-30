package seedu.address.logic.filters;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.customer.Customer;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.*;

public class Filters {
    public static AbstractFilter getCorrespondingFilter(String info) {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(info, PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE,
                PREFIX_ADDRESS, PREFIX_DOB, PREFIX_TAG, PREFIX_CARS);

        if(argumentMultimap.getTotalSize() != 1) {
            throw new NullPointerException("Number of filters between two logical operators should be exactly 1");
        }



        return new AbstractFilter(info) {

            @Override
            public boolean test(Customer customer) {
                return false;
            }

            @Override
            public List<Customer> filterAllCustomers(List<Customer> customer) {
                return null;
            }
        };
    }
}
