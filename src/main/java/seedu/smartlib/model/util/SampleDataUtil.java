package seedu.smartlib.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Author;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.Genre;
import seedu.smartlib.model.book.Isbn;
import seedu.smartlib.model.book.Publisher;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SmartLib} with sample data.
 */
public class SampleDataUtil {

    private static final Book OLDMAN = new Book(
            new Name("The Old Man And The Sea"),
            new Author(new Name("Harmingway")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890123"),
            new Barcode(Barcode.MIN_VALUE),
            new Genre(new Name("Novel")),
            new Name("Bernice Yu")
    );

    private static final Book HOBBIT = new Book(
            new Name("The Hobbit"),
            new Author(new Name("Tolkien")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890124"), new Barcode(Barcode.MIN_VALUE + 1),
            new Genre(new Name("Fantasy")),
            new Name("Bernice Yu")
    );

    private static final Book CLOUDATLAS = new Book(
            new Name("Cloud Atlas"),
            new Author(new Name("David Mitchell")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890125"),
            new Barcode(Barcode.MAX_VALUE),
            new Genre(new Name("SciFi")),
            new Name("Alex Yeoh")
    );

    private static final Book CLOUDNINE = new Book(
            new Name("Cloud Nine"),
            new Author(new Name("Tom Hanks")),
            new Publisher(new Name("Scientific")),
            new Isbn("1234567890120"),
            new Barcode(Barcode.MAX_VALUE - 1),
            new Genre(new Name("SciFi"))
    );

    private static final Book AVENGERS = new Book(
            new Name("The Avengers"),
            new Author(new Name("Marvel")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890129"),
            new Barcode(Barcode.MAX_VALUE - 2),
            new Genre(new Name("Comic"))
    );

    /**
     * Gets an array of sample readers.
     *
     * @return an array of sample readers.
     */
    public static Reader[] getSampleReaders() {
        HashMap<Book, DateBorrowed> berniceMap = new HashMap<>();
        berniceMap.put(HOBBIT, new DateBorrowed("2021-01-22T23:30:00"));
        berniceMap.put(OLDMAN, new DateBorrowed("2021-03-02T13:45:00"));

        HashMap<Book, DateBorrowed> alexMap = new HashMap<>();
        alexMap.put(CLOUDATLAS, new DateBorrowed("2020-11-23T08:30:00"));

        return new Reader[] {
            new Reader(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("VIP"), alexMap),
            new Reader(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("VIP", "TopBorrower"), berniceMap),
            new Reader(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("VIP"), getBorrowMap(new HashMap<>())),
            new Reader(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet(), getBorrowMap(new HashMap<>())),
            new Reader(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet(), getBorrowMap(new HashMap<>())),
            new Reader(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("VIP"), getBorrowMap(new HashMap<>()))
        };
    }

    /**
     * Gets an array of sample records.
     *
     * @return an array of sample records.
     */
    public static Record[] getSampleRecords() {
        return new Record[] {
            new Record(CLOUDATLAS.getBarcode(), CLOUDATLAS.getBorrowerName(),
                    new DateBorrowed("2020-11-23T08:30:00")),
            new Record(HOBBIT.getBarcode(), HOBBIT.getBorrowerName(), new DateBorrowed("2021-01-22T23:30:00")),
            new Record(OLDMAN.getBarcode(), OLDMAN.getBorrowerName(), new DateBorrowed("2021-03-02T13:45:00"))
        };
    }

    /**
     * Gets an array of sample books.
     *
     * @return an array of sample books.
     */
    public static Book[] getSampleBooks() {
        return new Book[] { OLDMAN, HOBBIT, CLOUDATLAS, CLOUDNINE, AVENGERS };
    }

    /**
     * Gets a Sample of SmartLib.
     *
     * @return SmartLib Sample.
     */
    public static ReadOnlySmartLib getSampleSmartLib() {
        SmartLib sampleAb = new SmartLib();
        for (Reader sampleReader : getSampleReaders()) {
            sampleAb.addReader(sampleReader);
        }
        for (Record sampleRecord : getSampleRecords()) {
            sampleAb.addRecord(sampleRecord);
        }
        for (Book sampleBook : getSampleBooks()) {
            sampleAb.addBook(sampleBook);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @param strings given list of strings.
     * @return tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a borrow map containing the list of borrow records given.
     *
     * @param map given list of borrow records.
     * @return borrow map containing the list of borrow records given.
     */
    private static Map<Book, DateBorrowed> getBorrowMap(HashMap<Book, DateBorrowed> map) {
        return map;
    }

}
