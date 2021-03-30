package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Car;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.DateOfBirth;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

//TODO : change the sample customers carsOwned to something meaningful

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), new DateOfBirth("2000 07 21"),
                        getTagSet("friends"), null, getCarSet("Tesla+sedan", "Honda+Sedan")),

            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DateOfBirth("1999 06 11"),
                        getTagSet("colleagues", "friends"), null,
                        getCarSet("Toyota+Sedan", "Honda+Sedan")),

            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new DateOfBirth("1981 12 01"),
                        getTagSet("neighbours"), null,
                        getCarSet("Toyota+Sedan", "Honda+Sedan", "Tesla+Ev")),

            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new DateOfBirth("1991 07 30"),
                        getTagSet("family"), null, null),

            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), new DateOfBirth("1983 02 13"),
                        getTagSet("classmates"), null, null),

            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), new DateOfBirth("2000 11 11"),
                        getTagSet("colleagues"), null, null)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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

    /**
     * Returns a car set containing the list of strings given.
     */
    public static Set<Car> getCarSet(String... strings) {
        return Arrays.stream(strings)
                .map(x -> parseCar(x))
                .collect(Collectors.toSet());
    }
    /**
     * Parses a {@code String car} into a {@code Car}. Leading and trailing whitespaces will be trimmed.
     */
    public static Car parseCar(String car) {
        assert car != null : "carPreferred should not be null";
        String trimmedCar = car.trim();
        String[] carDetails = trimmedCar.split("\\+");
        String carBrand = carDetails[0].trim();
        String carType = carDetails[1].trim();
        return new Car(carBrand, carType);
    }
}
