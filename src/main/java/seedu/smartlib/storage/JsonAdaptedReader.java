package seedu.smartlib.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Reader}.
 */
class JsonAdaptedReader {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reader's %s field is missing!";
    public static final String NOT_BORROWED = "Not Borrowed";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedNameDateBorrowedPair> borrows = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedReader} with the given reader details.
     */
    @JsonCreator
    public JsonAdaptedReader(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("borrows") List<JsonAdaptedNameDateBorrowedPair> borrows) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (borrows != null) {
            this.borrows.addAll(borrows);
        }
    }

    /**
     * Converts a given {@code Reader} into this class for Jackson use.
     */
    public JsonAdaptedReader(Reader source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        borrows.addAll(source.getBorrows().entrySet().stream()
                .map(JsonAdaptedNameDateBorrowedPair::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted reader object into the model's {@code Reader} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reader.
     */
    public Reader toModelType() throws IllegalValueException {
        final List<Tag> readerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            readerTags.add(tag.toModelType());
        }
        final Map<Name, DateBorrowed> readerBorrows = new HashMap<>();
        for (JsonAdaptedNameDateBorrowedPair pair: borrows) {
            readerBorrows.put(pair.toModelType().getKey(), pair.toModelType().getValue());
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

        final Set<Tag> modelTags = new HashSet<>(readerTags);

        final Map<Name, DateBorrowed> modelBorrows = new HashMap<>(readerBorrows);

        return new Reader(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelBorrows);

    }

}
