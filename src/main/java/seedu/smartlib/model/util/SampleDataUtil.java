package seedu.smartlib.model.util;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.HashMap;
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

    // books

    public static final Book OLDMAN = new Book(
            new Name("The Old Man And The Sea"),
            new Author(new Name("Harmingway")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890123"),
            new Barcode(Barcode.MIN_VALUE),
            new Genre(new Name("Novel")),
            new Name("Bernice Tan"),
            new DateBorrowed("2021-04-02T13:45:00")
    );

    public static final Book HOBBIT = new Book(
            new Name("The Hobbit"),
            new Author(new Name("Tolkien")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890124"), new Barcode(Barcode.MIN_VALUE + 1),
            new Genre(new Name("Fantasy")),
            new Name("Bernice Tan"),
            new DateBorrowed("2021-04-12T23:30:00")
    );

    public static final Book CLOUDATLAS = new Book(
            new Name("Cloud Atlas"),
            new Author(new Name("David Mitchell")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890125"),
            new Barcode(Barcode.MAX_VALUE),
            new Genre(new Name("SciFi")),
            new Name("Alex Yu"),
            new DateBorrowed("2021-04-03T08:30:00")
    );

    public static final Book CLOUDNINE = new Book(
            new Name("Cloud Nine"),
            new Author(new Name("Tom Hanks")),
            new Publisher(new Name("Scientific")),
            new Isbn("1234567890120"),
            new Barcode(Barcode.MAX_VALUE - 1),
            new Genre(new Name("SciFi"))
    );

    public static final Book AVENGERS = new Book(
            new Name("The Avengers"),
            new Author(new Name("Marvel")),
            new Publisher(new Name("Pearson")),
            new Isbn("1234567890129"),
            new Barcode(Barcode.MAX_VALUE - 2),
            new Genre(new Name("Comic"))
    );

    public static final Book LILAC = new Book(
            new Name("Lilac"),
            new Author(new Name("IU")),
            new Publisher(new Name("Scientific")),
            new Isbn("1234567890130"),
            new Barcode(Barcode.MAX_VALUE - 3),
            new Genre(new Name("Lyrics")),
            new Name("Alice Lee"),
            new DateBorrowed("2021-03-30T08:30:00")
    );

    public static final Book HELLO = new Book(
            new Name("Hello"),
            new Author(new Name("Me")),
            new Publisher(new Name("NUS")),
            new Isbn("1234567890987"),
            new Barcode(Barcode.MAX_VALUE - 4),
            new Genre(new Name("Greetings")),
            new Name("Bob Tan"),
            new DateBorrowed("2021-03-29T08:30:00")
    );

    public static final Book HELLOWORLD = new Book(
            new Name("Helloworld"),
            new Author(new Name("You")),
            new Publisher(new Name("NUS")),
            new Isbn("1234567890986"),
            new Barcode(Barcode.MAX_VALUE - 5),
            new Genre(new Name("Greetings"))
    );

    // records

    public static final Record ALEX_RECORD = new Record(
            CLOUDATLAS.getName(),
            CLOUDATLAS.getBarcode(),
            CLOUDATLAS.getBorrowerName(),
            CLOUDATLAS.getDateBorrowed()
    );

    public static final Record BERNICE_RECORD_1 = new Record(
            HOBBIT.getName(),
            HOBBIT.getBarcode(),
            HOBBIT.getBorrowerName(),
            HOBBIT.getDateBorrowed()
    );

    public static final Record BERNICE_RECORD_2 = new Record(
            OLDMAN.getName(),
            OLDMAN.getBarcode(),
            OLDMAN.getBorrowerName(),
            OLDMAN.getDateBorrowed()
    );

    public static final Record ALICE_RECORD = new Record(
            LILAC.getName(),
            LILAC.getBarcode(),
            LILAC.getBorrowerName(),
            LILAC.getDateBorrowed()
    );

    public static final Record BOB_RECORD = new Record(
            HELLO.getName(),
            HELLO.getBarcode(),
            HELLO.getBorrowerName(),
            HELLO.getDateBorrowed()
    );

    // init

    @SafeVarargs
    private static Reader[] getReaderList(HashMap<Book, DateBorrowed> ... maps) {
        requireAllNonNull((Object) maps);

        return new Reader[] {
            new Reader(new Name("Alex Yu"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("VIP"), maps[0]),
            new Reader(new Name("Bernice Tan"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("VIP", "TopBorrower"), maps[2]),
            new Reader(new Name("Charlotte Chan"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("VIP"), new HashMap<>()),
            new Reader(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet(), new HashMap<>()),
            new Reader(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet(), new HashMap<>()),
            new Reader(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("VIP"), new HashMap<>()),
            new Reader(new Name("Bob Tan"), new Phone("98765432"), new Email("bob@hi.com"),
                    new Address("1 Bukit Timah Road"),
                    getTagSet("VIP"), maps[3]),
            new Reader(new Name("Alice Lee"), new Phone("91234567"), new Email("alice@hello.com"),
                    new Address("2 Bukit Timah Road"),
                    getTagSet(), maps[1])
        };
    }

    /**
     * Gets an array of sample readers.
     *
     * @return an array of sample readers.
     */
    public static Reader[] getSampleReaders() {
        HashMap<Book, DateBorrowed> alexMap = new HashMap<>();
        alexMap.put(CLOUDATLAS, ALEX_RECORD.getDateBorrowed());

        HashMap<Book, DateBorrowed> aliceMap = new HashMap<>();
        aliceMap.put(LILAC, ALICE_RECORD.getDateBorrowed());

        HashMap<Book, DateBorrowed> berniceMap = new HashMap<>();
        berniceMap.put(HOBBIT, BERNICE_RECORD_1.getDateBorrowed());
        berniceMap.put(OLDMAN, BERNICE_RECORD_2.getDateBorrowed());

        HashMap<Book, DateBorrowed> bobMap = new HashMap<>();
        bobMap.put(HELLO, BOB_RECORD.getDateBorrowed());

        return getReaderList(alexMap, aliceMap, berniceMap, bobMap);
    }

    /**
     * Gets an array of sample records.
     *
     * @return an array of sample records.
     */
    public static Record[] getSampleRecords() {
        return new Record[] {
            ALEX_RECORD, BERNICE_RECORD_1, BERNICE_RECORD_2, ALICE_RECORD, BOB_RECORD
        };
    }

    /**
     * Gets an array of sample books.
     *
     * @return an array of sample books.
     */
    public static Book[] getSampleBooks() {
        return new Book[] {
            OLDMAN, HOBBIT, CLOUDATLAS, CLOUDNINE, AVENGERS, LILAC, HELLO, HELLOWORLD
        };
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

}
