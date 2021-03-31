package seedu.cakecollate.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.ReadOnlyOrderItems;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.order.Request;
import seedu.cakecollate.model.orderitem.Cost;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;
import seedu.cakecollate.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CakeCollate} and {@code OrderItems} with sample data.
 */
public class SampleDataUtil {
    private static LocalDate dateToday = LocalDate.now();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
    private static final Request EMPTY_REQUEST = new Request("");
    private static final Request NON_EMPTY_REQUEST_1 = new Request("No sugar, customer has diabetus.");
    private static final Request NON_EMPTY_REQUEST_2 = new Request("More sugar, spice and everything nice.");

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    getOrderDescriptionSet("2 x Chocolate Cake"), getTagSet("friends"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(3L))), EMPTY_REQUEST
            ),
            new Order(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getOrderDescriptionSet("2 x Vanilla Cake"), getTagSet("colleagues", "friends"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(4L))), EMPTY_REQUEST
            ),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getOrderDescriptionSet("2 x Chocolate Cake", "3 x Kiwi Cake"),
                    getTagSet("neighbours"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(5L))), NON_EMPTY_REQUEST_1
            ),
            new Order(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getOrderDescriptionSet("2 x Chocolate Cake"), getTagSet("family"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(6L))), NON_EMPTY_REQUEST_1
            ),
            new Order(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                    getOrderDescriptionSet("1 x Strawberry Cake", "1 x Chocolate Vanilla Cake", "1 x Berries Cake"),
                    getTagSet("classmates"), new DeliveryDate(dateFormatter.format(dateToday.plusDays(7L))),
                    NON_EMPTY_REQUEST_2
            ),
            new Order(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getOrderDescriptionSet("1 x Black Forest Cake"), getTagSet("colleagues"),
                    new DeliveryDate(dateFormatter.format(dateToday.plusDays(8L))), NON_EMPTY_REQUEST_2
            ),
        };
    }

    public static OrderItem[] getSampleOrderItem() {
        return new OrderItem[] { new OrderItem(new Type("Banana Cake"), new Cost("5.0")),
            new OrderItem(new Type("GME Banana Cake"), new Cost("42690.0")),
            new OrderItem(new Type("Strawberry Cake"), new Cost("5.0")),
            new OrderItem(new Type("Chocolate Cake"), new Cost("5.0"))
        };
    }

    public static ReadOnlyCakeCollate getSampleCakeCollate() {
        CakeCollate sampleAb = new CakeCollate();
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
        return sampleAb;
    }

    public static ReadOnlyOrderItems getSampleOrderItems() {
        OrderItems sampleOi = new OrderItems();
        for (OrderItem sampleItems : getSampleOrderItem()) {
            sampleOi.addOrderItem(sampleItems);
        }
        return sampleOi;
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
