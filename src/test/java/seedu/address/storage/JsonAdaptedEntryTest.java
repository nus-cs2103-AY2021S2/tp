package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeachingAssistant.CONSULTATION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;

public class JsonAdaptedEntryTest {

    public static final String INVALID_ENTRY_NAME = "consultation!!!!!!!!";
    public static final String INVALID_START_DATE = "2021 02 31 18:00";
    public static final String INVALID_END_DATE = "2021-02-31 1800";
    public static final String INVALID_TAG_NOT_SINGLE_WORD = "hello world";

    private static final String VALID_ENTRY_NAME = CONSULTATION.getEntryName().toString();
    private static final String VALID_START_DATE = CONSULTATION.getOriginalStartDate().toString();
    private static final String VALID_END_DATE = CONSULTATION.getOriginalEndDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CONSULTATION.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEntryDetails_returnsEntry() throws Exception {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(CONSULTATION);
        assertEquals(CONSULTATION, entry.toModelType());
    }

    @Test
    public void toModelType_invalidEntryName_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(INVALID_ENTRY_NAME, VALID_START_DATE, VALID_END_DATE, VALID_TAGS);
        String expectedMessage = EntryName.NAME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullEntryName_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(null, VALID_START_DATE, VALID_END_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EntryName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, INVALID_START_DATE, VALID_END_DATE, VALID_TAGS);
        String expectedMessage = EntryDate.DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, null, VALID_END_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EntryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, VALID_START_DATE, INVALID_END_DATE, VALID_TAGS);
        String expectedMessage = EntryDate.DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, VALID_START_DATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EntryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidDateRange_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, VALID_END_DATE, VALID_START_DATE, VALID_TAGS);
        String expectedMessage = Messages.MESSAGE_INVALID_DATE_RANGE;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG_NOT_SINGLE_WORD));
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_ENTRY_NAME, VALID_START_DATE, VALID_END_DATE, invalidTags);
        assertThrows(IllegalValueException.class, entry::toModelType);
    }
}
