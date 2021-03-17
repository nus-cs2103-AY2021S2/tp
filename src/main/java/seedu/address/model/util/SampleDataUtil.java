package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Address;
import seedu.address.model.order.DeliveryDate;
import seedu.address.model.order.Email;
import seedu.address.model.order.Name;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDescription;
import seedu.address.model.order.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CakeCollate} with sample data.
 */
public class SampleDataUtil {
    private static LocalDate dateToday = LocalDate.now();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    getOrderDescriptionSet("2 x Chocolate Cake"), getTagSet("friends"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(3L)))
            ),
            new Order(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getOrderDescriptionSet("2 x Vanilla Cake"), getTagSet("colleagues", "friends"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(4L)))
            ),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getOrderDescriptionSet("2 x Chocolate Cake", "3 x Kiwi Cake"),
                    getTagSet("neighbours"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(5L)))
            ),
            new Order(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getOrderDescriptionSet("2 x Chocolate Cake"), getTagSet("family"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(6L)))
            ),
            new Order(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                    getOrderDescriptionSet("1 x Strawberry Cake", "1 x Chocolate Vanilla Cake", "1 x Berries Cake"),
                    getTagSet("classmates"), new DeliveryDate(dateFormatter.format(dateToday.plusDays(7L)))
            ),
            new Order(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getOrderDescriptionSet("1 x Black Forest Cake"), getTagSet("colleagues"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(8L)))
            ),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
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

    /**
     * Returns a set of order descriptions containing the list of strings given.
     */
    public static Set<OrderDescription> getOrderDescriptionSet(String... strings) {
        return Arrays.stream(strings)
                .map(OrderDescription::new)
                .collect(Collectors.toSet());
    }

}
