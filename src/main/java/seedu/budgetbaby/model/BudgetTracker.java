package seedu.budgetbaby.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.record.FinancialRecordList;

/**
 * Wraps all data at the budget-tracker level
 */
public class BudgetTracker implements ReadOnlyBudgetTracker {

    private final FinancialRecordList records;

    {
        records = new FinancialRecordList();
    }

    public BudgetTracker() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public BudgetTracker(ReadOnlyBudgetTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the financial record list with {@code records}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFinancialRecords(List<FinancialRecord> records) {
        this.records.setFinancialRecords(records);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBudgetTracker newData) {
        requireNonNull(newData);

        setFinancialRecords(newData.getFinancialRecordList());
    }

    //// financial-record-level operations

    /**
     * Adds a financial record to the budget tracker.
     */
    public void addFinancialRecord(FinancialRecord r) {
        records.add(r);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) {
        requireNonNull(editedRecord);

        records.setFinancialRecord(target, editedRecord);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeFinancialRecord(FinancialRecord key) {
        records.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return records.asUnmodifiableObservableList().size() + " financial records";
        // TODO: refine later
    }

    @Override
    public ObservableList<FinancialRecord> getFinancialRecordList() {
        return records.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof BudgetTracker // instanceof handles nulls
            && records.equals(((BudgetTracker) other).records));
    }

    @Override
    public int hashCode() {
        return records.hashCode();
    }
}

