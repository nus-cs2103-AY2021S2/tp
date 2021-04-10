package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.DatesBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.date.ImportantDate;

/**
 * An Immutable DatesBook that is serializable to JSON format.
 */
@JsonRootName(value = "datesbook")

public class JsonSerializableDatesBook {

    public static final String MESSAGE_DUPLICATE_IMPORTANT_DATES = "Important dates list contains duplicate important"
            + " date(s).";

    private final List<JsonAdaptedImportantDate> importantDates = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatesBook} with the given important dates.
     */
    @JsonCreator
    public JsonSerializableDatesBook(@JsonProperty("importantDates") List<JsonAdaptedImportantDate> importantDates) {
        this.importantDates.addAll(importantDates);
    }

    /**
     * Converts a given {@code ReadOnlyDatesBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatesBook}.
     */
    public JsonSerializableDatesBook(ReadOnlyDatesBook source) {
        importantDates.addAll(source.getImportantDatesList().stream().map(JsonAdaptedImportantDate::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this dates book into the model's {@code DatesBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DatesBook toModelType() throws IllegalValueException {
        DatesBook datesBook = new DatesBook();
        for (JsonAdaptedImportantDate jsonAdaptedImportantDate : importantDates) {
            ImportantDate importantDate = jsonAdaptedImportantDate.toModelType();
            if (datesBook.hasImportantDate(importantDate)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_IMPORTANT_DATES);
            }
            datesBook.addImportantDate(importantDate);
        }
        return datesBook;
    }
}
