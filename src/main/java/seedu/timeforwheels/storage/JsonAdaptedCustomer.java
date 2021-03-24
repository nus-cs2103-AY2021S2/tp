package seedu.timeforwheels.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.timeforwheels.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Customer's %s field is missing!";
    private final String done;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String date;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String remark;
    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("remark") String remark,
                               @JsonProperty("date") String date,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("done") String done) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.date = date;
        this.done = done;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Customer} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        date = source.getDate().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        done = source.getDone().value;
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {
        final List<Tag> customerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            customerTags.add(tag.toModelType());
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

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }

        if (date == null) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }

        final Remark modelRemark = new Remark(remark);

        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(customerTags);

        final Done modelDone = new Done(done);

        final Date modelDate = new Date(date);

        return new Customer(modelName, modelPhone, modelEmail, modelAddress, modelRemark,
                modelDate, modelTags, modelDone);
    }

}
