package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.CustomerId;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Customer[] SAMPLE_CUSTOMERS = new Customer[] {
        new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends")),
        new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            getTagSet("colleagues", "friends")),
        new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            getTagSet("neighbours")),
        new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            getTagSet("family")),
        new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            getTagSet("classmates")),
        new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
            new Address("Blk 45 Aljunied Street 85, #11-31"),
            getTagSet("colleagues"))
    };

    public static final Cheese[] SAMPLE_CHEESES = new Cheese[]{
        new Cheese(CheeseType.getCheeseType("Brie"), new ManufactureDate("2021-03-10"),
            new MaturityDate("2021-04-10"), new ExpiryDate("2022-04-10")),
        new Cheese(CheeseType.getCheeseType("Gouda"), new ManufactureDate("2021-03-10"),
            new MaturityDate("2021-05-10"), new ExpiryDate("2022-05-10")),
        new Cheese(CheeseType.getCheeseType("Parmesan"), new ManufactureDate("2021-03-10"),
            new MaturityDate("2021-04-10"), new ExpiryDate("2022-04-10")),
        new Cheese(CheeseType.getCheeseType("Feta"), new ManufactureDate("2020-03-10"),
            new MaturityDate("2021-03-05"), new ExpiryDate("2025-04-10"), CheeseId.getNextId(), true),
        new Cheese(CheeseType.getCheeseType("Feta"), new ManufactureDate("2020-03-10"),
            new MaturityDate("2021-03-05"), new ExpiryDate("2025-04-10"), CheeseId.getNextId(), true),
    };

    public static final Order[] SAMPLE_ORDERS = new Order[] {
        new Order(CheeseType.getCheeseType("Brie"), new Quantity(2), new OrderDate("2021-03-24"),
            null, CustomerId.getNextId(3)),
        new Order(CheeseType.getCheeseType("Gouda"), new Quantity(5), new OrderDate("2021-03-25"),
            null, CustomerId.getNextId(4)),
        new Order(CheeseType.getCheeseType("Feta"), new Quantity(2), new OrderDate("2021-02-25"),
            new CompletedDate("2021-03-06"),
            Set.of(SAMPLE_CHEESES[3].getCheeseId(), SAMPLE_CHEESES[4].getCheeseId()),
            CustomerId.getNextId(1)),
    };


    public static Customer[] getSampleCustomers() {
        return SAMPLE_CUSTOMERS;
    }

    public static Order[] getSampleOrders() {
        return SAMPLE_ORDERS;
    }

    public static Cheese[] getSampleCheeses() {
        return SAMPLE_CHEESES;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
        for (Cheese sampleCheese : getSampleCheeses()) {
            sampleAb.addCheese(sampleCheese);
        }
        sampleAb.checkAddressBook();
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
