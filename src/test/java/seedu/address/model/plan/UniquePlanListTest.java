package seedu.address.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_SOFTWARE_ENGINEERING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_SOFTWARE_ENGINEERING_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPlans.COMPUTER_NETWORKING;
import static seedu.address.testutil.TypicalPlans.SOFTWARE_ENGINEERING;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.plan.exceptions.DuplicatePlanException;
import seedu.address.model.plan.exceptions.PlanNotFoundException;
import seedu.address.testutil.PlanBuilder;

public class UniquePlanListTest {

    private final UniquePlanList uniquePlanList = new UniquePlanList();

    @Test
    public void contains_nullPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList.contains(null));
    }

    @Test
    public void contains_planNotInList_returnsFalse() {
        assertFalse(uniquePlanList.contains(SOFTWARE_ENGINEERING));
    }

    @Test
    public void contains_planInList_returnsTrue() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        assertTrue(uniquePlanList.contains(SOFTWARE_ENGINEERING));
    }

    @Test
    public void contains_planWithSameIdentityFieldsInList_returnsTrue() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        Plan editedSoftwareEngineeringPlan = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withDescription(VALID_PLAN_SOFTWARE_ENGINEERING_DESCRIPTION)
                .withTags(VALID_PLAN_SOFTWARE_ENGINEERING_TAG).build();
        assertTrue(uniquePlanList.contains(editedSoftwareEngineeringPlan));
    }

    @Test
    public void add_nullPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList.add(null));
    }

    @Test
    public void add_duplicatePlan_throwsDuplicatePlanException() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        assertThrows(DuplicatePlanException.class, () -> uniquePlanList.add(SOFTWARE_ENGINEERING));
    }

    @Test
    public void setPlan_nullTargetPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList
                .setPlan(null, SOFTWARE_ENGINEERING));
    }

    @Test
    public void setPlan_nullEditedPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList
                .setPlan(SOFTWARE_ENGINEERING, null));
    }

    @Test
    public void setPlan_targetPlanNotInList_throwsPlanNotFoundException() {
        assertThrows(PlanNotFoundException.class, () -> uniquePlanList
                .setPlan(SOFTWARE_ENGINEERING, SOFTWARE_ENGINEERING));
    }

    @Test
    public void setPlan_editedPlanIsSamePlan_success() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        uniquePlanList.setPlan(SOFTWARE_ENGINEERING, SOFTWARE_ENGINEERING);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        expectedUniquePlanList.add(SOFTWARE_ENGINEERING);
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlan_editedPlanHasSameIdentity_success() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        Plan editedSoftwareEngineeringPlan = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withDescription(VALID_PLAN_SOFTWARE_ENGINEERING_DESCRIPTION)
                .withTags(VALID_PLAN_SOFTWARE_ENGINEERING_TAG).build();
        uniquePlanList.setPlan(SOFTWARE_ENGINEERING, editedSoftwareEngineeringPlan);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        expectedUniquePlanList.add(editedSoftwareEngineeringPlan);
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlan_editedPlanHasDifferentIdentity_success() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        uniquePlanList.setPlan(SOFTWARE_ENGINEERING, COMPUTER_NETWORKING);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        expectedUniquePlanList.add(COMPUTER_NETWORKING);
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlan_editedPlanHasNonUniqueIdentity_throwsDuplicatePlanException() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        uniquePlanList.add(COMPUTER_NETWORKING);
        assertThrows(DuplicatePlanException.class, () -> uniquePlanList
                .setPlan(SOFTWARE_ENGINEERING, COMPUTER_NETWORKING));
    }

    @Test
    public void remove_nullPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList.remove(null));
    }

    @Test
    public void remove_planDoesNotExist_throwsPlanNotFoundException() {
        assertThrows(PlanNotFoundException.class, () -> uniquePlanList.remove(SOFTWARE_ENGINEERING));
    }

    @Test
    public void remove_existingPlan_removesPlan() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        uniquePlanList.remove(SOFTWARE_ENGINEERING);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlans_nullUniquePlanList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList.setPlans((UniquePlanList) null));
    }

    @Test
    public void setPlans_uniquePlanList_replacesOwnListWithProvidedUniquePlanList() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        expectedUniquePlanList.add(COMPUTER_NETWORKING);
        uniquePlanList.setPlans(expectedUniquePlanList);
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlans_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePlanList.setPlans((List<Plan>) null));
    }

    @Test
    public void setPlans_list_replacesOwnListWithProvidedList() {
        uniquePlanList.add(SOFTWARE_ENGINEERING);
        List<Plan> planList = Collections.singletonList(COMPUTER_NETWORKING);
        uniquePlanList.setPlans(planList);
        UniquePlanList expectedUniquePlanList = new UniquePlanList();
        expectedUniquePlanList.add(COMPUTER_NETWORKING);
        assertEquals(expectedUniquePlanList, uniquePlanList);
    }

    @Test
    public void setPlans_listWithDuplicatePlans_throwsDuplicatePlanException() {
        List<Plan> listWithDuplicatePlans = Arrays.asList(SOFTWARE_ENGINEERING, SOFTWARE_ENGINEERING);
        assertThrows(DuplicatePlanException.class, () -> uniquePlanList.setPlans(listWithDuplicatePlans));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniquePlanList
                .asUnmodifiableObservableList().remove(0));
    }
}
