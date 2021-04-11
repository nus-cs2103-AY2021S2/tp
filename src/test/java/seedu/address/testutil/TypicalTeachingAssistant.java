package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;

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
