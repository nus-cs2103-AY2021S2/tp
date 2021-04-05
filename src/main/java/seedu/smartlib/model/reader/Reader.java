package seedu.smartlib.model.reader;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;

/**
 * Represents a reader in SmartLib.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reader {

    public static final Name TEMP_READERNAME = new Name("reader");

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Map<Book, DateBorrowed> borrows = new HashMap<>();

    /**
     * Constructor for the Reader class.
     * Every field must be present and not null.
     *
     * @param name name of the reader.
     * @param phone phone number of the reader.
     * @param email email of the reader.
     * @param address address of the reader.
     * @param tags tags which the reader is associated with.
     * @param borrows list of books borrowed by the reader.
     */
    public Reader(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Map<Book, DateBorrowed> borrows) {
        requireAllNonNull(name, phone, email, address, tags, borrows);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.borrows.putAll(borrows);
    }

    /**
     * Retrieves the list of books borrowed by the reader.
     *
     * @return the list of books borrowed by the reader.
     */
    public Map<Book, DateBorrowed> getBorrows() {
        return borrows;
    }

    /**
     * Retrieves the name of the reader.
     *
     * @return the name of the reader.
     */
    public Name getName() {
        return name;
    }

    /**
     * Retrieves the phone number of the reader.
     *
     * @return the phone number of the reader.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Retrieves the email of the reader.
     *
     * @return the email of the reader.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Retrieves the address of the reader.
     *
     * @return the address of the reader.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return an immutable tag set.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks if a reader has overdue books.
     *
     * @return true if the reader has overdue books, and false otherwise.
     */
    public boolean hasOverdueBooks() {
        for (DateBorrowed dateBorrowed : borrows.values()) {
            if (dateBorrowed.isOverdue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a reader has borrowed any book
     * @return true if the reader has any book
     */
    public boolean hasBorrowedBooks() {
        return this.getBorrows().size() > 0;
    }

    /**
     * Returns true if both readers have the same name.
     * This defines a weaker notion of equality between two readers.
     *
     * @param otherReader the reader to be compared with this reader.
     * @return true if both readers have the same name, and false otherwise.
     */
    public boolean isSameReader(Reader otherReader) {
        if (otherReader == this) {
            return true;
        }

        return otherReader != null
                && otherReader.getName().equals(getName());
    }

    /**
     * Returns true if both readers have the same identity and data fields.
     * This defines a stronger notion of equality between two readers.
     *
     * @param other the reader to be compared with this reader.
     * @return true if both readers have the same identity and data fields, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reader)) {
            return false;
        }

        Reader otherReader = (Reader) other;
        return otherReader.getName().equals(getName())
                && otherReader.getPhone().equals(getPhone())
                && otherReader.getEmail().equals(getEmail())
                && otherReader.getAddress().equals(getAddress())
                && otherReader.getTags().equals(getTags());
    }

    /**
     * Generates a hashcode for this Reader.
     *
     * @return the hashcode for this Reader.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    /**
     * Returns this Reader in String format.
     *
     * @return this Reader in String format.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
