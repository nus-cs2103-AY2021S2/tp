package seedu.smartlib.testutil;

import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;

/**
 * A utility class containing a list of {@code Reader} objects to be used in tests.
 */
public class TypicalReaders {

    public static final Reader ALICE = new ReaderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Reader BENSON = new ReaderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Reader CARL = new ReaderBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Reader DANIEL = new ReaderBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Reader ELLE = new ReaderBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Reader FIONA = new ReaderBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Reader GEORGE = new ReaderBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Reader HOON = new ReaderBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Reader IDA = new ReaderBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Reader's details found in {@code CommandTestUtil}
    public static final Reader AMY = new ReaderBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Reader BOB = new ReaderBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final Record RECORDA = new Record(
            new Name("Cloud Atlas"), new Name("Alex Yeoh"), new DateBorrowed("2020-11-23"));
    public static final Record RECORDB = new Record(
            new Name("The Avengers"), new Name("Bernice Yu"), new DateBorrowed("2021-01-22"));

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalReaders() {} // prevents instantiation

    /**
     * Returns an {@code SmartLib} with all the typical readers.
     */
    public static SmartLib getTypicalSmartLib() {
        SmartLib ab = new SmartLib();
        for (Reader reader : getTypicalReaders()) {
            ab.addReader(reader);
        }
        for (Record record : getTypicalRecords()) {
            ab.addRecord(record);
        }
        return ab;
    }

    private static List<Record> getTypicalRecords() {
        return new ArrayList<>(Arrays.asList(RECORDA, RECORDB));
    }

    public static List<Reader> getTypicalReaders() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
