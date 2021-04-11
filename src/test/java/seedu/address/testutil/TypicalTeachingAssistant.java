package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;
import seedu.address.model.person.Person;

public class TypicalTeachingAssistant {

    public static final Entry CONSULTATION = new EntryBuilder().withEntryName("Consultation")
            .withStartDate("2022-02-01 13:00").withEndDate("2022-02-01 14:30").withTags("History").build();

    public static final Entry CLASS_MEETING = new EntryBuilder().withEntryName("Class Meeting")
            .withStartDate("2022-02-01 15:00").withEndDate("2022-02-01 16:30").withTags("21S07").build();

    public static final Entry EXTRA_CLASS = new EntryBuilder().withEntryName("Extra class")
            .withStartDate("2022-02-02 17:00").withEndDate("2022-02-02 18:30").withTags("Math").build();

    public static final Entry SHORT_QUIZ = new EntryBuilder().withEntryName("Short Geo Quiz")
            .withStartDate("2022-02-02 14:30").withEndDate("2022-02-02 15:15").withTags("Geography", "21A01").build();

    public static final Entry DO_STUFF = new EntryBuilder().withEntryName("Go do something important")
            .withStartDate("2022-02-04 09:00").withEndDate("2022-02-04 09:30").withTags("Important", "Stuff").build();

    //Manually Added
    public static final Entry REMEDIAL = new EntryBuilder().withEntryName("Remedial").withStartDate("2022-02-05 10:00")
            .withEndDate("2022-02-05 12:00").withTags("Geography", "21A07").build();

    public static final Entry CONSULTATION_MATH = new EntryBuilder().withEntryName("Consultation")
            .withStartDate("2022-02-05 17:00").withEndDate("2022-02-05 18:00").withTags("Math", "Alice").build();

    public static final Entry NON_OVERDUE_ENTRY = new EntryBuilder().withEntryName("This entry will not be overdue")
            .withStartDate("2030-02-04 09:00").withEndDate("2030-02-04 09:30").withTags("Important", "Stuff").build();

    public static final Entry OVERDUE_ENTRY = new EntryBuilder().withEntryName("This entry is overdue")
            .withStartDate("2010-02-04 09:00").withEndDate("2010-02-04 09:30").withTags("Important", "Stuff").build();
    //===Person===
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();
    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    //===Contact===
    public static final Contact AVA = new ContactBuilder().withName("Ava Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final Contact BEN = new ContactBuilder().withName("Ben Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();

    public static final Contact CLAIRE = new ContactBuilder().withName("Claire Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withTags("colleagues").build();

    public static final Contact DAVID = new ContactBuilder().withName("David Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();

    public static final Contact ELLIE = new ContactBuilder().withName("Ellie Meyer").withPhone("94822244")
            .withEmail("werner@example.com").build();

    public static final Contact FINN = new ContactBuilder().withName("Finn Kunz").withPhone("94824277")
            .withEmail("lydia@example.com").build();

    public static final Contact GABRIEL = new ContactBuilder().withName("Gabriel Best").withPhone("94824422")
            .withEmail("anna@example.com").build();
    // Manually added
    public static final Contact HANNAH = new ContactBuilder().withName("Hannah Meier").withPhone("84824244")
            .withEmail("stefan@example.com").build();
    public static final Contact IVAN = new ContactBuilder().withName("Ivan Mueller").withPhone("84821311")
            .withEmail("hans@example.com").build();

    private TypicalTeachingAssistant() {} // prevents instantiation

    public static AddressBook getTypicalTeachingAssistant() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }

        for (Entry entry : getTypicalEntries()) {
            ab.addEntry(entry);
        }
        return ab;
    }

    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(CONSULTATION, CLASS_MEETING, EXTRA_CLASS, SHORT_QUIZ, DO_STUFF));
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(AVA, BEN, CLAIRE, DAVID, ELLIE, FINN, GABRIEL));
    }
}
