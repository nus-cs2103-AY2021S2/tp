package seedu.address.model.diet;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Collection of {@code DietPlan} instances.
 */
public class DietPlanList implements Iterable<DietPlan> {

    private ObservableList<DietPlan> planList;

    /**
     * Constructor for a list of diet plans.
     */
    public DietPlanList() {
        this.planList = FXCollections.observableArrayList();
    }

    /**
     * Returns an {@code ObservableList} of diet plans.
     *
     * @return List of diet plans.
     */
    public ObservableList<DietPlan> getPlanList() {
        return planList;
    }

    /**
     * Adds a diet plan to the list.
     *
     * @param dietPlan The diet plan to be added.
     * @return Response message.
     */
    public String addDietPlan(DietPlan dietPlan) {
        requireNonNull(dietPlan);

        planList.add(dietPlan);
        return String.format("Successfully added %s to the list!", dietPlan);
    }

    /**
     * Retrieves diet plan at given index.
     *
     * @param index The index of the diet plan.
     * @return The diet plan.
     * @throws IndexOutOfBoundsException If index out of the list.
     */
    public DietPlan getDietPlan(int index) {
        return planList.get(index);
    }

    /**
     * Returns true if diet plan exists in the list.
     *
     * @param dietPlan The diet plan.
     * @return {@code true} if diet plan is found.
     */
    public boolean hasDietPlan(DietPlan dietPlan) {
        return planList.contains(dietPlan);
    }

    /**
     * Updates the diet plan in list, based on the given plan name.
     *
     * @param dietPlan The diet plan to update.
     * @return {@code true} if diet plan is found and updated.
     */
    public boolean updateDietPlan(DietPlan dietPlan) {
        requireNonNull(dietPlan);

        for (int i = 0; i < planList.size(); i++) {
            DietPlan plan = planList.get(i);
            if (plan.getPlanName().equals(dietPlan.getPlanName())) {
                // Override with new diet plan
                planList.set(i, dietPlan);
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes diet plan from list.
     *
     * @param index The index to remove.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    public void deleteDietPlan(int index) {
        planList.remove(index);
    }

    @Override
    public Iterator<DietPlan> iterator() {
        return planList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DietPlanList dietPlans = (DietPlanList) o;
        return Objects.equals(planList, dietPlans.planList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planList);
    }

}
