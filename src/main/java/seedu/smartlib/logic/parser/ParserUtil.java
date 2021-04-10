package seedu.smartlib.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.util.StringUtil;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Genre;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex the given index to be parsed.
     * @return an Index object made up of the given one-based index.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name the given name to be parsed.
     * @return a Name object made up of the given name.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phone the given phone (number) to be parsed.
     * @return a Phone object made up of the given phone (number).
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address the given address to be parsed.
     * @return an Address object made up of the given address.
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email the given email to be parsed.
     * @return an Email object made up of the given email.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     *
     * @param tag the given tag to be parsed.
     * @return a Tag object made up of the given tag.
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @param tags the given tags to be parsed.
     * @return a set of Tag objects made up of the given tags.
     * @throws ParseException if the given {@code tags} is invalid.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String author} into a {@code Author}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param author the given author to be parsed.
     * @return an Author object made up of the given author.
     * @throws ParseException if the given {@code author} is invalid.
     */
    public static Author parseAuthor(String author) throws ParseException {
        requireNonNull(author);
        String trimmedAuthor = author.trim();
        if (!Author.isValidAuthor(trimmedAuthor)) {
            throw new ParseException(Author.MESSAGE_CONSTRAINTS);
        }
        return new Author(new Name(trimmedAuthor));
    }

    /**
     * Parses a {@code String Isbn} into a {@code Isbn}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param isbn the given ISBN to be parsed.
     * @return an Isbn object made up of the given ISBN.
     * @throws ParseException if the given {@code isbn} is invalid.
     */
    public static Isbn parseIsbn(String isbn) throws ParseException {
        requireNonNull(isbn);
        String trimmedIsbn = isbn.trim();
        if (!Isbn.isValidIsbn(trimmedIsbn)) {
            throw new ParseException(Isbn.MESSAGE_CONSTRAINTS);
        }
        return new Isbn(trimmedIsbn);
    }

    /**
     * Parses a {@code String Publisher} into a {@code Publisher}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param publisher the given publisher to be parsed.
     * @return a Publisher object made up of the given publisher.
     * @throws ParseException if the given {@code publisher} is invalid.
     */
    public static Publisher parsePublisher(String publisher) throws ParseException {
        requireNonNull(publisher);
        String trimmedPublisher = publisher.trim();
        if (!Publisher.isValidPublisher(trimmedPublisher)) {
            throw new ParseException(Publisher.MESSAGE_CONSTRAINTS);
        }
        return new Publisher(new Name(trimmedPublisher));
    }

    /**
     * Parses a {@code String Genre} into a {@code Genre}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param genre the given genre to be parsed.
     * @return a Genre object made up of the given genre.
     * @throws ParseException if the given {@code genre} is invalid.
     */
    public static Genre parseGenre(String genre) throws ParseException {
        requireNonNull(genre);
        String trimmedGenre = genre.trim();
        if (!Genre.isValidGenre(trimmedGenre)) {
            throw new ParseException(Genre.MESSAGE_CONSTRAINTS);
        }
        return new Genre(new Name(trimmedGenre));
    }

    /**
     * Parses a {@code String Barcode} into a {@code Barcode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param barcode the given BARCODE to be parsed.
     * @return an Barcode object made up of the given BARCODE.
     * @throws ParseException if the given {@code barcode} is invalid.
     */
    public static Barcode parseBarcode(String barcode) throws ParseException {
        requireNonNull(barcode);
        String trimmedBarcode = barcode.trim();

        try {
            int barcodeAsInt = Integer.parseInt(trimmedBarcode);

            if (!Barcode.isValidBarcode(barcodeAsInt)) {
                throw new ParseException(Barcode.MESSAGE_CONSTRAINTS);
            }

            return new Barcode(Integer.parseInt(trimmedBarcode));
        } catch (NumberFormatException e) { // barcode given by user may exceed integer length
            throw new ParseException(Barcode.MESSAGE_CONSTRAINTS);
        }
    }

}
