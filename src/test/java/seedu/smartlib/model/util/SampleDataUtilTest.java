package seedu.smartlib.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.model.util.SampleDataUtil.ALEX_RECORD;
import static seedu.smartlib.model.util.SampleDataUtil.ALICE_RECORD;
import static seedu.smartlib.model.util.SampleDataUtil.AVENGERS;
import static seedu.smartlib.model.util.SampleDataUtil.BERNICE_RECORD_1;
import static seedu.smartlib.model.util.SampleDataUtil.BERNICE_RECORD_2;
import static seedu.smartlib.model.util.SampleDataUtil.BOB_RECORD;
import static seedu.smartlib.model.util.SampleDataUtil.CLOUDATLAS;
import static seedu.smartlib.model.util.SampleDataUtil.CLOUDNINE;
import static seedu.smartlib.model.util.SampleDataUtil.HELLO;
import static seedu.smartlib.model.util.SampleDataUtil.HELLOWORLD;
import static seedu.smartlib.model.util.SampleDataUtil.HOBBIT;
import static seedu.smartlib.model.util.SampleDataUtil.LILAC;
import static seedu.smartlib.model.util.SampleDataUtil.OLDMAN;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getTagSet_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SampleDataUtil.getTagSet((String) null));
    }

    @Test
    public void getTagSet_validTag() {
        HashSet<Tag> hashSet = new HashSet<>();

        // empty
        assertEquals(hashSet, SampleDataUtil.getTagSet());

        // empty string -> tags must contain at least a single alphanumeric character
        assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getTagSet(""));
        assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getTagSet(" ", "   "));

        // same single tag
        hashSet.add(new Tag("a"));
        assertEquals(hashSet, SampleDataUtil.getTagSet("a"));

        // same tags
        hashSet.add(new Tag("b"));
        hashSet.add(new Tag("1"));
        hashSet.add(new Tag("2"));
        assertEquals(hashSet, SampleDataUtil.getTagSet("1", "2", "b", "a"));

        // different values
        assertNotEquals(hashSet, SampleDataUtil.getTagSet("1", "2", "3", "4"));
    }

    @Test
    public void getSampleReaders() {
        // first reader
        HashMap<Book, DateBorrowed> alexMap = new HashMap<>();
        alexMap.put(CLOUDATLAS, ALEX_RECORD.getDateBorrowed());
        assertEquals(SampleDataUtil.getSampleReaders()[0],
                new Reader(
                        new Name("Alex Yu"),
                        new Phone("87438807"),
                        new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        SampleDataUtil.getTagSet("VIP"),
                        alexMap
                )
        );

        // second reader
        HashMap<Book, DateBorrowed> berniceMap = new HashMap<>();
        berniceMap.put(HOBBIT, BERNICE_RECORD_1.getDateBorrowed());
        berniceMap.put(OLDMAN, BERNICE_RECORD_2.getDateBorrowed());
        assertEquals(SampleDataUtil.getSampleReaders()[1],
                new Reader(
                        new Name("Bernice Tan"),
                        new Phone("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        SampleDataUtil.getTagSet("VIP", "TopBorrower"),
                        berniceMap
                )
        );

        // third reader
        assertEquals(SampleDataUtil.getSampleReaders()[2],
                new Reader(
                        new Name("Charlotte Chan"),
                        new Phone("93210283"),
                        new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        SampleDataUtil.getTagSet("VIP"),
                        new HashMap<>()
                )
        );

        // fourth reader
        assertEquals(SampleDataUtil.getSampleReaders()[3],
                new Reader(
                        new Name("David Li"),
                        new Phone("91031282"),
                        new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        SampleDataUtil.getTagSet(),
                        new HashMap<>()
                )
        );

        // fifth reader
        assertEquals(SampleDataUtil.getSampleReaders()[4],
                new Reader(
                        new Name("Irfan Ibrahim"),
                        new Phone("92492021"),
                        new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        SampleDataUtil.getTagSet(),
                        new HashMap<>()
                )
        );

        // sixth reader
        assertEquals(SampleDataUtil.getSampleReaders()[5],
                new Reader(
                        new Name("Roy Balakrishnan"),
                        new Phone("92624417"),
                        new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        SampleDataUtil.getTagSet("VIP"),
                        new HashMap<>()
                )
        );

        // seventh reader
        HashMap<Book, DateBorrowed> bobMap = new HashMap<>();
        bobMap.put(HELLO, BOB_RECORD.getDateBorrowed());
        assertEquals(SampleDataUtil.getSampleReaders()[6],
                new Reader(
                        new Name("Bob Tan"),
                        new Phone("98765432"),
                        new Email("bob@hi.com"),
                        new Address("1 Bukit Timah Road"),
                        SampleDataUtil.getTagSet("VIP"),
                        bobMap
                )
        );

        // eighth reader
        HashMap<Book, DateBorrowed> aliceMap = new HashMap<>();
        aliceMap.put(LILAC, ALICE_RECORD.getDateBorrowed());
        assertEquals(SampleDataUtil.getSampleReaders()[7],
                new Reader(
                        new Name("Alice Lee"),
                        new Phone("91234567"),
                        new Email("alice@hello.com"),
                        new Address("2 Bukit Timah Road"),
                        SampleDataUtil.getTagSet(),
                        aliceMap
                )
        );
    }

    @Test
    public void getSampleRecords() {
        // first record
        assertEquals(ALEX_RECORD, SampleDataUtil.getSampleRecords()[0]);

        // second record
        assertEquals(BERNICE_RECORD_1, SampleDataUtil.getSampleRecords()[1]);

        // third record
        assertEquals(BERNICE_RECORD_2, SampleDataUtil.getSampleRecords()[2]);

        // fourth record
        assertEquals(ALICE_RECORD, SampleDataUtil.getSampleRecords()[3]);

        // fifth record
        assertEquals(BOB_RECORD, SampleDataUtil.getSampleRecords()[4]);
    }

    @Test
    public void getSampleBooks() {
        // first book
        assertEquals(OLDMAN, SampleDataUtil.getSampleBooks()[0]);

        // second book
        assertEquals(HOBBIT, SampleDataUtil.getSampleBooks()[1]);

        // third book
        assertEquals(CLOUDATLAS, SampleDataUtil.getSampleBooks()[2]);

        // fourth book
        assertEquals(CLOUDNINE, SampleDataUtil.getSampleBooks()[3]);

        // fifth book
        assertEquals(AVENGERS, SampleDataUtil.getSampleBooks()[4]);

        // sixth book
        assertEquals(LILAC, SampleDataUtil.getSampleBooks()[5]);

        // seventh book
        assertEquals(HELLO, SampleDataUtil.getSampleBooks()[6]);

        // eighth book
        assertEquals(HELLOWORLD, SampleDataUtil.getSampleBooks()[7]);
    }

    @Test
    public void getSampleSmartLib() {
        ReadOnlySmartLib smartLib = SampleDataUtil.getSampleSmartLib();

        // check that smartLib contains sample readers
        // EP: reader without any borrows
        assertTrue(smartLib.getReaderList().contains(new Reader(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                SampleDataUtil.getTagSet(),
                new HashMap<>()
        )));

        // EP: reader with borrows
        HashMap<Book, DateBorrowed> berniceMap = new HashMap<>();
        berniceMap.put(HOBBIT, BERNICE_RECORD_1.getDateBorrowed());
        berniceMap.put(OLDMAN, BERNICE_RECORD_2.getDateBorrowed());
        assertTrue(smartLib.getReaderList().contains(new Reader(
                new Name("Bernice Tan"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                SampleDataUtil.getTagSet("VIP", "TopBorrower"),
                berniceMap
        )));

        // check that smartLib contains sample books
        // EP: book with a single word as name
        assertTrue(smartLib.getBookList().contains(LILAC));

        // EP: book with multiple words as name
        assertTrue(smartLib.getBookList().contains(OLDMAN));

        // check that smartLib contains sample records
        // EP: record by reader with single borrow
        assertTrue(smartLib.getRecordList().contains(BOB_RECORD));

        // EP: record by reader with multiple borrows
        assertTrue(smartLib.getRecordList().contains(BERNICE_RECORD_2));
    }

}
