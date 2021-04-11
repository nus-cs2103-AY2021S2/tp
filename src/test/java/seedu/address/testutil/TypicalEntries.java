package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TeachingAssistant;
import seedu.address.model.entry.Entry;

public class TypicalEntries {

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

    private TypicalEntries() {} // prevents instantiation

    public static TeachingAssistant getTypicalEntriesList() {
        TeachingAssistant ab = new TeachingAssistant();
        for (Entry entry : getTypicalEntries()) {
            ab.addEntry(entry);
        }
        return ab;
    }

    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(CONSULTATION, CLASS_MEETING, EXTRA_CLASS, SHORT_QUIZ, DO_STUFF));
    }
}
