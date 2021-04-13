package seedu.plan.model.plan;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.plan.commons.util.CollectionUtil;
import seedu.plan.model.plan.exceptions.DuplicatePlanException;
import seedu.plan.model.plan.exceptions.PlanNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A plan is considered unique by comparing using {@code Plan#isSamePerson(Plan)}. As such, adding and updating of
 * persons uses Plan#isSamePerson(Plan) for equality so as to ensure that the plan being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a plan uses Plan#equals(Object) so
 * as to ensure that the plan with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniquePlanList implements Iterable<Plan> {

    private final ObservableList<Plan> internalList = FXCollections.observableArrayList();
    private final ObservableList<Plan> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent plan as the given argument.
     */
    public boolean contains(Plan toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a plan to the list.
     * The plan must not already exist in the list.
     */
    public void add(Plan toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePlanException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the plan {@code target} in the list with {@code editedPlan}.
     * {@code target} must exist in the list.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the list.
     */
    public void setPlan(Plan target, Plan editedPlan) {
        CollectionUtil.requireAllNonNull(target, editedPlan);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PlanNotFoundException();
        }

        if (!target.equals(editedPlan) && contains(editedPlan)) {
            throw new DuplicatePlanException();
        }

        internalList.set(index, editedPlan);
    }

    /**
     * Removes the equivalent plan from the list.
     * The plan must exist in the list.
     */
    public void remove(Plan toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PlanNotFoundException();
        }
    }

    public void setPlans(UniquePlanList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code plans}.
     * {@code plans} must not contain duplicate plans.
     */
    public void setPlans(List<Plan> plans) {
        CollectionUtil.requireAllNonNull(plans);
        if (!personsAreUnique(plans)) {
            throw new DuplicatePlanException();
        }

        internalList.setAll(plans);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Plan> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Plan> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePlanList // instanceof handles nulls
                        && internalList.equals(((UniquePlanList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code plans} contains only unique plans.
     */
    private boolean personsAreUnique(List<Plan> plans) {
        for (int i = 0; i < plans.size() - 1; i++) {
            for (int j = i + 1; j < plans.size(); j++) {
                if (plans.get(i).equals(plans.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
