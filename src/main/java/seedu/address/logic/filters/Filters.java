package seedu.address.logic.filters;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_OWNED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_PREFERRED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COE_EXPIRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.customer.Customer;

public class Filters {
    public static Filter getCorrespondingFilter(String info) {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(" " + info + " ", PREFIX_NAME,
            PREFIX_EMAIL,
            PREFIX_PHONE,
            PREFIX_ADDRESS, PREFIX_DOB, PREFIX_TAG, PREFIX_CARS_OWNED, PREFIX_CARS_PREFERRED, PREFIX_COE_EXPIRY);


        if (argumentMultimap.getTotalSize() > 2) { // since there is also a dummy position :(
            throw new NullPointerException(
                "Number of filters between two logical operators should be exactly 1 " + argumentMultimap);
        }

        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new EmailFilter(argumentMultimap.getValue(PREFIX_EMAIL).get());
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new PhoneNumberFilter(argumentMultimap.getValue(PREFIX_PHONE).get());
        }

        if (argumentMultimap.getValue(PREFIX_COE_EXPIRY).isPresent()) {
            return new CoeExpiryFilter(argumentMultimap.getValue(PREFIX_COE_EXPIRY).get());
        }

        if (argumentMultimap.getValue(PREFIX_DOB).isPresent()) {
            return new DobFilter(argumentMultimap.getValue(PREFIX_DOB).get());
        }

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new NameFilter(argumentMultimap.getValue(PREFIX_NAME).get());
        }

        if (argumentMultimap.getValue(PREFIX_CARS_OWNED).isPresent()) {
            return new CarsOwnedFilter(argumentMultimap.getValue(PREFIX_CARS_OWNED).get());
        }

        if (argumentMultimap.getValue(PREFIX_CARS_PREFERRED).isPresent()) {
            return new CarsPreferredFilter(argumentMultimap.getValue(PREFIX_CARS_PREFERRED).get());
        }

        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return new AddressFilter(argumentMultimap.getValue(PREFIX_ADDRESS).get());
        }

        if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new TagFilter(argumentMultimap.getValue(PREFIX_TAG).get());
        }



        /*
         * throw new IllegalArgumentException("No appropriate filter for : " + info);
         */

        return new Filter(info) {

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
