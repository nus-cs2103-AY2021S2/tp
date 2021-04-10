package seedu.timeforwheels.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.customer.Address;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.Date;
import seedu.timeforwheels.model.customer.Done;
import seedu.timeforwheels.model.customer.Email;
import seedu.timeforwheels.model.customer.Name;
import seedu.timeforwheels.model.customer.Phone;
import seedu.timeforwheels.model.customer.Remark;
import seedu.timeforwheels.model.tag.Tag;



/**
 * Contains utility methods for populating {@code DeliveryList} with sample data.
 */
public class SampleDataUtil {

    public static final Done NOT_DONE = new Done("[X]");
    public static final Done DONE = new Done("[✓]");


    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Remark("Starbucks coffee"), new Date("2021-01-04"),
                getTagSet("liquid", "cold"), DONE),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Remark("Dell laptop"),
                new Date("2021-10-25"), getTagSet("fragile"), NOT_DONE),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Remark("work documents"),
                new Date("2021-02-10"), getTagSet("urgent"), DONE),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Remark("Samsung tv"),
                new Date("2021-11-12"), getTagSet("bulky", "heavy"), NOT_DONE),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Remark("fried wings meal"),
                new Date("2021-03-07"), getTagSet("urgent", "food", "hot"), DONE),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Remark("iPhone 11 case"), new Date("2021-12-22"),
                getTagSet(), NOT_DONE)
        };
    }

    public static ReadOnlyDeliveryList getSampleDeliveryList() {
        DeliveryList sampleAb = new DeliveryList();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
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

}
