package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;



public class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    private final String entryName;
    private final String startDate;
    private final String endDate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    public JsonAdaptedEntry(@JsonProperty("entryName") String entryName,
                            @JsonProperty("startDate") String startDate,
                            @JsonProperty("endDate") String endDate,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.entryName = entryName;
        this.startDate = startDate;
        this.endDate = endDate;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a gievn {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        entryName = source.getEntryName().name;
        startDate = source.getOriginalStartDate().toString();
        endDate = source.getOriginalEndDate().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Convert this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        final List<Tag> entryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            entryTags.add(tag.toModelType());
        }

        if (entryName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EntryName.class.getSimpleName()));
        }
        if (!EntryName.isValidName(entryName)) {
            throw new IllegalValueException(EntryName.NAME_CONSTRAINTS);
        }
        final EntryName modelEntryName = new EntryName(entryName);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EntryDate.class.getSimpleName()));
        }
        if (!EntryDate.isValidDate(startDate)) {
            throw new IllegalValueException(EntryDate.DATE_CONSTRAINTS);
        }
        final EntryDate modelStartDate = new EntryDate(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EntryDate.class.getSimpleName()));
        }
        if (!EntryDate.isValidDate(endDate)) {
            throw new IllegalValueException(EntryDate.DATE_CONSTRAINTS);
        }

        final EntryDate modelEndDate = new EntryDate(endDate);

        if (modelStartDate.isAfter(modelEndDate)) {
            throw new IllegalValueException(MESSAGE_INVALID_DATE_RANGE);
        }

        final Set<Tag> modelTags = new HashSet<>(entryTags);
        return new Entry(modelEntryName, modelStartDate, modelEndDate, modelTags);
    }
}
