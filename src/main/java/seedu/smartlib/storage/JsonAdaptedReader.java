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
import seedu.smartlib.model.book.Book;
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
    private final List<JsonAdaptedBookDateBorrowedPair> borrows = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedReader} with the given reader details.
     *
     * @param name name of the reader.
     * @param phone phone number of the reader.
     * @param email email of the reader.
     * @param address address of the reader.
     * @param tagged tags of the reader.
     * @param borrows books which the reader has borrowed.
     */
    @JsonCreator
    public JsonAdaptedReader(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("borrows") List<JsonAdaptedBookDateBorrowedPair> borrows) {
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
     *
     * @param source reader to be converted.
     */
    public JsonAdaptedReader(Reader source) {
        name = source.getName().toString();
        phone = source.getPhone().toString();
        email = source.getEmail().toString();
        address = source.getAddress().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        borrows.addAll(source.getBorrows().entrySet().stream()
                .map(JsonAdaptedBookDateBorrowedPair::new)
                .collect(Collectors.toList()));
    }

    /**
     * Verifies validity of the reader name.
     *
     * @throws IllegalValueException if the reader name is null or invalid.
     */
    private void verifyReaderName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the reader phone.
     *
     * @throws IllegalValueException if the reader phone is null or invalid.
     */
    private void verifyReaderPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the reader email.
     *
     * @throws IllegalValueException if the reader email is null or invalid.
     */
    private void verifyReaderEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Verifies validity of the reader address.
     *
     * @throws IllegalValueException if the reader address is null or invalid.
     */
    private void verifyReaderAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Converts this Jackson-friendly adapted reader object into the model's {@code Reader} object.
     *
     * @return Reader object converted from the storage file.
     * @throws IllegalValueException if there were any data constraints violated in the adapted reader.
     */
    public Reader toModelType() throws IllegalValueException {
        final List<Tag> readerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            readerTags.add(tag.toModelType());
        }

        final Map<Book, DateBorrowed> readerBorrows = new HashMap<>();
        for (JsonAdaptedBookDateBorrowedPair pair : borrows) {
            readerBorrows.put(pair.toModelType().getKey(), pair.toModelType().getValue());
        }

        verifyReaderName();
        final Name modelName = new Name(name);

        verifyReaderPhone();
        final Phone modelPhone = new Phone(phone);

        verifyReaderEmail();
        final Email modelEmail = new Email(email);

        verifyReaderAddress();
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(readerTags);

        final Map<Book, DateBorrowed> modelBorrows = new HashMap<>(readerBorrows);

        return new Reader(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelBorrows);
    }

}
