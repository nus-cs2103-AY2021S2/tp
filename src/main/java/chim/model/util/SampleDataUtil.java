package chim.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import chim.model.Chim;
import chim.model.ReadOnlyChim;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseId;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;
import chim.model.customer.Address;
import chim.model.customer.Customer;
import chim.model.customer.CustomerId;
import chim.model.customer.Email;
import chim.model.customer.Name;
import chim.model.customer.Phone;
import chim.model.order.CompletedDate;
import chim.model.order.Order;
import chim.model.order.OrderDate;
import chim.model.order.Quantity;
import chim.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Chim} with sample data.
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
            new ExpiryDate("2022-04-10")),
        new Cheese(CheeseType.getCheeseType("Gouda"), new ManufactureDate("2021-03-10"),
            new ExpiryDate("2022-05-10")),
        new Cheese(CheeseType.getCheeseType("Parmesan"), new ManufactureDate("2021-03-10"),
            new ExpiryDate("2022-04-10")),
        new Cheese(CheeseType.getCheeseType("Feta"), new ManufactureDate("2020-03-10"),
            new ExpiryDate("2025-04-10"), CheeseId.getNextId(), true),
        new Cheese(CheeseType.getCheeseType("Feta"), new ManufactureDate("2020-03-10"),
            new ExpiryDate("2025-04-10"), CheeseId.getNextId(), true),
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

    public static ReadOnlyChim getSampleChim() {
        Chim sampleChim = new Chim();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleChim.addCustomer(sampleCustomer);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleChim.addOrder(sampleOrder);
        }
        for (Cheese sampleCheese : getSampleCheeses()) {
            sampleChim.addCheese(sampleCheese);
        }
        sampleChim.checkChim();
        return sampleChim;
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
