package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Car;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

//TODO: need to change this, but not needed immediately since it is more for testing.
// need to include the CarsOwned tests as well. need to do in v1.2b

/**
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Customer's %s field is missing!";
    public static final String CAR_COE_EXPIRY_LENGTH_MISMATCH = "The number of cars and coeexpiries are not same";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedCar> cars = new ArrayList<>();
    private final List<JsonAdaptedCoeExpiry> coeExpiries = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("cars") List<JsonAdaptedCar> cars,
                               @JsonProperty("coeExpiries") List<JsonAdaptedCoeExpiry> coeExpiries) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (cars != null) {
            this.cars.addAll(cars);
        }
        if (coeExpiries != null) {
            this.coeExpiries.addAll(coeExpiries);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        cars.addAll(source.getCarsOwned().keySet().stream().map(JsonAdaptedCar::new).collect(Collectors.toList()));
        coeExpiries.addAll(
            source.getCarsOwned().values().stream().map(JsonAdaptedCoeExpiry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Customer toModelType() throws IllegalValueException {
        final List<Tag> customerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            customerTags.add(tag.toModelType());
        }

        final Map<Car, CoeExpiry> allCars = new HashMap<>();
        if (cars.size() != coeExpiries.size()) {
            throw new IllegalValueException(CAR_COE_EXPIRY_LENGTH_MISMATCH);
        }

        var iter1 = cars.iterator();
        var iter2 = coeExpiries.iterator();

        for (; iter1.hasNext() && iter2.hasNext(); ) {
            var car = iter1.next().toModelType();
            var coeExpiry = iter2.next().toModelType();
            allCars.put(car, coeExpiry);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(customerTags);
        return new Customer(modelName, modelPhone, modelEmail, modelAddress, modelTags, allCars);
    }

}
