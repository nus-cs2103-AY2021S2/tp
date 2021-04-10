package seedu.smartlib.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;
import seedu.smartlib.model.util.SampleDataUtil;

/**
 * A utility class to help with building Reader objects.
 */
public class ReaderBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Map<Book, DateBorrowed> borrows;

    /**
     * Creates a {@code ReaderBuilder} with the default details.
     */
    public ReaderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        borrows = new HashMap<>();
    }

    /**
     * Initializes the ReaderBuilder with the data of {@code readerToCopy}.
     *
     * @param readerToCopy a Reader object containing data which we want to copy from.
     */
    public ReaderBuilder(Reader readerToCopy) {
        name = readerToCopy.getName();
        phone = readerToCopy.getPhone();
        email = readerToCopy.getEmail();
        address = readerToCopy.getAddress();
        tags = new HashSet<>(readerToCopy.getTags());
        borrows = new HashMap<>(readerToCopy.getBorrows());
    }

    /**
     * Sets the {@code Name} of the {@code Reader} that we are building.
     *
     * @param name name of the Reader that we are building.
     * @return a ReaderBuilder object with the updated name.
     */
    public ReaderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Reader} that we are building.
     *
     * @param phone phone number of the Reader that we are building.
     * @return a ReaderBuilder object with the updated phone number.
     */
    public ReaderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Reader} that we are building.
     *
     * @param email email of the Reader that we are building.
     * @return a ReaderBuilder object with the updated email.
     */
    public ReaderBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Reader} that we are building.
     *
     * @param address address of the Reader that we are building.
     * @return a ReaderBuilder object with the updated address.
     */
    public ReaderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Parses and sets the {@code tags} into a {@code Set<Tag>} and set it to the {@code Reader} that we are building.
     *
     * @param tags tags of the Reader that we are building.
     * @return a ReaderBuilder object with the updated tags.
     */
    public ReaderBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Borrows} of the {@code Reader} that we are building.
     *
     * @param book book that the Reader that we are building has borrowed.
     * @param dateBorrowed borrow date of the book.
     * @return a ReaderBuilder object with the updated borrowing transaction.
     */
    public ReaderBuilder withBorrows(Book book, DateBorrowed dateBorrowed) {
        this.borrows.put(book, dateBorrowed);
        return this;
    }

    /**
     * Builds a Reader object with the given values for name, phone, email, address, and tags.
     *
     * @return a Reader object with the given parameters.
     */
    public Reader build() {
        return new Reader(name, phone, email, address, tags, borrows);
    }

}
