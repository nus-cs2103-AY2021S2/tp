package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.record.exception.FinancialRecordNotFoundException;
import seedu.budgetbaby.ui.UiManager;

/**
 * A list of financial records.
 * <p>
 * Supports a minimal set of list operations.
 */
public class FinancialRecordList implements Iterable<FinancialRecord> {

    private final ObservableList<FinancialRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<FinancialRecord> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a financial record to the list.
     */
    public void add(FinancialRecord toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the financial record {@code target} in the list with {@code editedFinancialRecord}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedFinancialRecord) {
        requireAllNonNull(target, editedFinancialRecord);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FinancialRecordNotFoundException();
        }

        internalList.set(index, editedFinancialRecord);
    }

    /**
     * Removes the financial record from the list.
     * The financial record must exist in the list.
     */
    public void remove(FinancialRecord toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FinancialRecordNotFoundException();
        }
    }

    public void setFinancialRecords(FinancialRecordList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code records}.
     */
    public void setFinancialRecords(List<FinancialRecord> records) {
        requireAllNonNull(records);

        internalList.setAll(records);
    }

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);

    /**
     * Filters financial records according to category.
     */
    public void filterByCategory(Category category) {
        requireAllNonNull(category);
        internalList.removeIf(fr -> !fr.getTags().isEmpty());
    }

    /**
     * Returns total expenses incurred by financial records in the internal list.
     */
    public Double getTotalExpenses() {
        return internalList.stream()
                .mapToDouble(fr -> fr.getAmount().getValue())
                .reduce(0.0, Double::sum);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FinancialRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FinancialRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FinancialRecordList// instanceof handles nulls
            && internalList.equals(((FinancialRecordList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
