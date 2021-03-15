package seedu.budgetbaby.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.budgetbaby.commons.exceptions.IllegalValueException;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.month.Month;

/**
 * An Immutable BudgetTracker that is serializable to JSON format.
 */
@JsonRootName(value = "budgetbaby")
class JsonSerializableBudgetTracker {

    private final List<JsonAdaptedMonth> months = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBudgetTracker(@JsonProperty("months") List<JsonAdaptedMonth> months) {
        this.months.addAll(months);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableBudgetTracker(ReadOnlyBudgetTracker source) {
        months.addAll(source.getMonthList().stream()
            .map(JsonAdaptedMonth::new).collect(Collectors.toList()));
    }

    /**
     * Converts this budget tracker into the model's {@code BudgetTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BudgetTracker toModelType() throws IllegalValueException {
        BudgetTracker budgetTracker = new BudgetTracker();
        for (JsonAdaptedMonth jsonAdaptedMonth : months) {
            Month month = jsonAdaptedMonth.toModelType();
            budgetTracker.addMonth(month);
        }
        return budgetTracker;
    }

}
