package seedu.address.model.entry;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TemporaryEntry {
    private boolean isAnyFieldEdited;
    private Optional<EntryName> entryName;
    private Optional<EntryDate> startDate;
    private Optional<EntryDate> endDate;
    private Optional<Set<Tag>> tags;

    /**
     * Constructs a TemporaryEntry object which is a partial
     * Entry object with optional fields.
     */
    public TemporaryEntry() {
        isAnyFieldEdited = false;
        entryName = Optional.empty();
        startDate = Optional.empty();
        endDate = Optional.empty();
        tags = Optional.empty();
    }

    public boolean isUpdated() {
        return isAnyFieldEdited;
    }

    public Optional<EntryName> getEntryName() {
        return entryName;
    }

    public Optional<EntryDate> getStartDate() {
        return startDate;
    }

    public Optional<EntryDate> getEndDate() {
        return endDate;
    }

    public Optional<Set<Tag>> getTags() {
        return tags;
    }

    public void setEntryName(EntryName name) {
        isAnyFieldEdited = true;
        entryName = Optional.of(name);
    }

    public void setEntryStartDate(EntryDate date) {
        isAnyFieldEdited = true;
        startDate = Optional.of(date);
    }

    public void setEntryEndDate(EntryDate date) {
        isAnyFieldEdited = true;
        endDate = Optional.of(date);
    }

    public void setTags(Set<Tag> tags) {
        isAnyFieldEdited = true;
        this.tags = Optional.of(tags);
    }
}
