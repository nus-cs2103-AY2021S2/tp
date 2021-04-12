package seedu.address.testutil;

import java.util.Set;

import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building entry objects.
 */
public class EntryBuilder {

    public static final String DEFAULT_ENTRY_NAME = "Consultation";
    public static final String DEFAULT_START_DATE = "2021-04-05 17:00";
    public static final String DEFAULT_END_DATE = "2021-04-05 19:00";
    public static final String DEFAULT_TAG = "Physics";

    private EntryName entryName;
    private EntryDate startDate;
    private EntryDate endDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code EntryBuilder} with the default details.
     */
    public EntryBuilder() {
        entryName = new EntryName(DEFAULT_ENTRY_NAME);
        startDate = new EntryDate(DEFAULT_START_DATE);
        endDate = new EntryDate(DEFAULT_END_DATE);
        tags = SampleDataUtil.getTagSet(DEFAULT_TAG);
    }

    /**
     * Initializes the EntryBuilder with the data if {@code entryToCopy}.
     */
    public EntryBuilder(Entry entryToCopy) {
        entryName = entryToCopy.getEntryName();
        startDate = entryToCopy.getOriginalStartDate();
        endDate = entryToCopy.getOriginalEndDate();
        tags = entryToCopy.getTags();
    }

    /**
     * Sets the entry's entryName as the given string.
     */
    public EntryBuilder withEntryName(String entryName) {
        this.entryName = new EntryName(entryName);
        return this;
    }

    /**
     * Sets the entry's startDate as the given string.
     */
    public EntryBuilder withStartDate(String startDate) {
        this.startDate = new EntryDate(startDate);
        return this;
    }

    /**
     * Sets the entry's endDate as the given string.
     */
    public EntryBuilder withEndDate(String endDate) {
        this.endDate = new EntryDate(endDate);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tags} and sets it as the entry's tags.
     */
    public EntryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Entry build() {
        return new Entry(entryName, startDate, endDate, tags);
    }
}
