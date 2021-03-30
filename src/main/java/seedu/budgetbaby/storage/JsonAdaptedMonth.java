package seedu.budgetbaby.storage;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.budgetbaby.commons.exceptions.IllegalValueException;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.record.FinancialRecordList;

/**
 * Jackson-friendly version of {@link Month}.
 */
class JsonAdaptedMonth {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Month's %s field is missing!";

    private final List<JsonAdaptedFinancialRecord> records = new ArrayList<>();
    private final String budget;
    private final String month;

    /**
     * Constructs a {@code JsonAdaptedMonth} with the given details.
     */
    @JsonCreator
    public JsonAdaptedMonth(@JsonProperty("records") List<JsonAdaptedFinancialRecord> records,
                            @JsonProperty("budget") String budget,
                            @JsonProperty("month") String month) {
        this.budget = budget;
        this.month = month;
        if (records != null) {
            this.records.addAll(records);
        }
    }

    /**
     * Converts a given {@code Month} into this class for Jackson use.
     */
    public JsonAdaptedMonth(Month source) {
        budget = source.getBudget().getAmount().toString();
        month = source.getMonth().toString();
        records.addAll(source.getFinancialRecords().asUnmodifiableObservableList().stream()
            .map(JsonAdaptedFinancialRecord::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted financial record object into the model's {@code Month} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Month toModelType() throws IllegalValueException {
        final List<FinancialRecord> financialRecords = new ArrayList<>();
        for (JsonAdaptedFinancialRecord record : records) {
            financialRecords.add(record.toModelType());
        }
        FinancialRecordList modelFinancialRecordList = new FinancialRecordList();
        modelFinancialRecordList.setFinancialRecords(financialRecords);

        if (budget == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Budget.class.getSimpleName()));
        }
        if (!Budget.isValidBudget(budget)) {
            throw new IllegalValueException(Budget.MESSAGE_CONSTRAINTS);
        }
        final Budget modelBudget = new Budget(budget);

        if (month == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                YearMonth.class.getSimpleName()));
        }
        final YearMonth modelYearMonth = YearMonth.parse(month);

        return new Month(modelFinancialRecordList, modelBudget, modelYearMonth);
    }

}
