package seedu.address.model.entry;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TemporaryEntry {
    public boolean isAnyFieldEdited;
    public Optional<EntryName> entryName;
    public Optional<EntryDate> startDate;
    public Optional<EntryDate> endDate;
    public Optional<Set<Tag>> tags;

    public TemporaryEntry() {
        isAnyFieldEdited = false;
        entryName = Optional.empty();
        startDate = Optional.empty();
        endDate = Optional.empty();
        tags = Optional.empty();
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
