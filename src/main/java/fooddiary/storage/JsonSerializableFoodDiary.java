package fooddiary.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.entry.Entry;

/**
 * An Immutable FoodDiary that is serializable to JSON format.
 */
@JsonRootName(value = "foodDiary")
class JsonSerializableFoodDiary {

    public static final String MESSAGE_DUPLICATE_FOOD_DIARY = "Entries list contains duplicate person(s).";

    private final List<JsonAdaptedEntry> entries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given entries.
     */
    @JsonCreator
    public JsonSerializableFoodDiary(@JsonProperty("entries") List<JsonAdaptedEntry> entries) {
        this.entries.addAll(entries);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableFoodDiary(ReadOnlyFoodDiary source) {
        entries.addAll(source.getEntryList().stream().map(JsonAdaptedEntry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this food diary into the model's {@code FoodDiary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodDiary toModelType() throws IllegalValueException {
        FoodDiary foodDiary = new FoodDiary();
        for (JsonAdaptedEntry jsonAdaptedEntry : entries) {
            Entry entry = jsonAdaptedEntry.toModelType();
            if (foodDiary.hasEntry(entry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD_DIARY);
            }
            foodDiary.addEntry(entry);
        }
        return foodDiary;
    }

}
