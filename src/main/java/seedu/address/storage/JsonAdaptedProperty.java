package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.property.status.Completion;
import seedu.address.model.property.status.Offer;
import seedu.address.model.property.status.Option;
import seedu.address.model.property.status.SalesAgreement;
import seedu.address.model.property.status.Status;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;

/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";
    public static final String INCORRECT_CLIENT_FIELD_MESSAGE =
            "Different client fields should be delimited with a semicolon "
            + "and each client field should be in their respective formats shown below:\n"
            + Name.MESSAGE_CONSTRAINTS + "\n"
            + Contact.MESSAGE_CONSTRAINTS + "\n"
            + Email.MESSAGE_CONSTRAINTS + "\n"
            + AskingPrice.MESSAGE_CONSTRAINTS;

    public static final String CLIENT_STRING_REGEX = "(Client Name: (?<clientName>" + Name.VALIDATION_REGEX + "))?"
            + "(; )?"
            + "(Client Contact: (?<clientContact>" + Contact.VALIDATION_REGEX + "))?"
            + "(; )?"
            + "(Client Email: (?<clientEmail>" + Email.VALIDATION_REGEX + "))?"
            + "(; )?"
            + "(Client Asking Price: (?<clientAskingPrice>" + AskingPrice.VALIDATION_REGEX + "))?";
    public static final Pattern CLIENT_STRING_FORMAT = Pattern.compile(CLIENT_STRING_REGEX);

    private final String name;
    private final String propertyType;
    private final String address;
    private final String postalCode;
    private final String deadline;
    private final String remark;
    private final String client;
    private final String status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String name, @JsonProperty("propertyType") String propertyType,
                               @JsonProperty("address") String address, @JsonProperty("remark") String remark,
                               @JsonProperty("postalCode") String postalCode,
                               @JsonProperty("deadline") String deadline, @JsonProperty("client") String client,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("status") String status) {
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.remark = remark;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.client = client;
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        name = source.getName().toString();
        propertyType = source.getPropertyType().toString();
        address = source.getAddress().toString();
        postalCode = source.getPostalCode().toString();
        deadline = source.getDeadline().toString();

        if (source.getRemarks() != null) {
            remark = source.getRemarks().toString();
        } else {
            remark = null;
        }
        if (source.getClient() != null) {
            client = source.getClient().toString();
        } else {
            client = null;
        }
        if (source.getStatus() != null) {
            status = source.getStatus().toString();
        } else {
            status = null;
        }
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {
        final List<Tag> propertyTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            propertyTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(propertyTags);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Type.class.getSimpleName()));
        }
        if (!Type.isValidType(propertyType)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(propertyType);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (postalCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PostalCode.class.getSimpleName()));
        }
        if (!PostalCode.isValidPostal(postalCode)) {
            throw new IllegalValueException(PostalCode.MESSAGE_CONSTRAINTS);
        }
        final PostalCode modelPostal = new PostalCode(postalCode);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        final Deadline modelDeadline = new Deadline(LocalDate.parse(deadline, DateTimeFormat.OUTPUT_DATE_FORMAT));

        final Status modelStatus = parseToStatus(status);

        final Remark modelRemark = parseToRemark(remark);

        final Client modelClient = parseToClient(client);

        return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelRemark,
                modelClient, modelTags, modelStatus);
    }

    /**
     * Converts toString of client back to Client object.
     */
    public static Client fromStringToClient(String toString) throws IllegalValueException {
        Matcher matcher = CLIENT_STRING_FORMAT.matcher(toString);
        if (!matcher.matches()) {
            throw new IllegalValueException(INCORRECT_CLIENT_FIELD_MESSAGE);
        }
        String clientName = matcher.group("clientName");
        String clientContact = matcher.group("clientContact");
        String clientEmail = matcher.group("clientEmail");
        String clientAskingPrice = matcher.group("clientAskingPrice");

        Name name = ParserUtil.parseName(clientName);
        Contact contact = ParserUtil.parseClientContact(clientContact);
        Email email = ParserUtil.parseClientEmail(clientEmail);
        AskingPrice askingPrice = ParserUtil.parseClientAskingPrice(clientAskingPrice);

        return new Client(name, contact, email, askingPrice);
    }

    /**
     * Converts toString of status back to Status object.
     */
    public static Status parseToStatus(String toString) {
        if (toString == null) {
            return null;
        }
        Status status = null;
        if (toString.startsWith(Option.TOSTRING_MESSAGE)) {
            Offer amount = new Offer(toString.substring(Option.TOSTRING_MESSAGE.length()));
            status = new Option(amount);
        } else if (toString.startsWith(SalesAgreement.TOSTRING_MESSAGE)) {
            Offer amount = new Offer(toString.substring(SalesAgreement.TOSTRING_MESSAGE.length()));
            status = new Option(amount).next();
        } else if (toString.startsWith(Completion.TOSTRING_MESSAGE)) {
            Offer amount = new Offer(toString.substring(Completion.TOSTRING_MESSAGE.length()));
            status = new Option(amount).next().next();
        }
        return status;
    }

    /**
     * Converts toString of remark back to Remark object.
     */
    public static Remark parseToRemark(String toString) throws IllegalValueException {
        Remark remark = null;
        if (toString != null) {
            if (!Remark.isValidRemark(toString)) {
                throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
            }
            remark = new Remark(toString);
        }
        return remark;
    }

    /**
     * Converts toString of client back to Client object.
     */
    public static Client parseToClient(String toString) throws IllegalValueException {
        Client client = null;
        if (toString != null) {
            //TODO add test to validate client
            client = fromStringToClient(toString);;
        }
        return client;
    }
}

