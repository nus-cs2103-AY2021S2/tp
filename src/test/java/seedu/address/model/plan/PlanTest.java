package seedu.address.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_COMPUTER_NETWORKING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_COMPUTER_NETWORKING_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_SOFTWARE_ENGINEERING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_SOFTWARE_ENGINEERING_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPlans.COMPUTER_NETWORKING;
import static seedu.address.testutil.TypicalPlans.SOFTWARE_ENGINEERING;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PlanBuilder;

public class PlanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PlanBuilder()
                .withDescription(null)
                .withTags((String[]) null));

        assertThrows(NullPointerException.class, () -> new PlanBuilder()
                .withDescription(null)
                .withTags((String[]) null)
                .withSemesters(null));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Plan plan = new PlanBuilder().build();
        assertThrows(UnsupportedOperationException.class, () ->
                plan.getTags().remove(0));
    }

    @Test
    public void addSemester_null_throwsNullPointerException() {
        Plan plan = new PlanBuilder().build();
        assertThrows(NullPointerException.class, () -> plan.addSemester(null));
    }

    @Test
    public void removeSemester_null_throwsNullPointerException() {
        List<Semester> semesters = new ArrayList<>();
        semesters.add(new Semester(1));
        Plan plan = new PlanBuilder()
                .withSemesters(semesters).build();
        assertThrows(NullPointerException.class, () -> plan.removeSemester(null));
    }

    @Test
    public void getSemesters() {
        List<Semester> semesters = new ArrayList<>();
        semesters.add(new Semester(1));
        Plan plan = new PlanBuilder()
                .withSemesters(semesters).build();
        assertEquals(semesters, plan.getSemesters());

        plan = new PlanBuilder().build();
        assertEquals(Collections.emptyList(), plan.getSemesters());
    }

    @Test
    public void getSemester() {
        List<Semester> semesters = new ArrayList<>();
        Semester semester = new Semester(1);
        semesters.add(semester);
        Plan plan = new PlanBuilder()
                .withSemesters(semesters).build();
        assertEquals(semester, plan.getSemester(1));
        assertNull(plan.getSemester(2));
    }

    @Test
    public void hasSemester() {
        List<Semester> semesters = new ArrayList<>();
        semesters.add(new Semester(1));
        Plan plan = new PlanBuilder()
                .withSemesters(semesters).build();
        assertTrue(plan.hasSemester(1));
        assertFalse(plan.hasSemester(2));
    }

    @Test
    public void getDescription() {
        assertEquals(new Description(VALID_PLAN_SOFTWARE_ENGINEERING_DESCRIPTION),
                SOFTWARE_ENGINEERING.getDescription());
    }

    @Test
    public void getTags() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_PLAN_SOFTWARE_ENGINEERING_TAG));
        assertEquals(tags, SOFTWARE_ENGINEERING.getTags());
        assertNotEquals(tags, COMPUTER_NETWORKING.getTags());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Plan softwareEngineeringCopy = new PlanBuilder(SOFTWARE_ENGINEERING).build();
        assertTrue(SOFTWARE_ENGINEERING.equals(softwareEngineeringCopy));

        // same object -> returns true
        assertTrue(SOFTWARE_ENGINEERING.equals(SOFTWARE_ENGINEERING));

        // null -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(null));

        // different type -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(5));

        // different plan -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(COMPUTER_NETWORKING));

        // different description -> returns false
        Plan editedSoftwareEngineering = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withDescription(VALID_PLAN_COMPUTER_NETWORKING_DESCRIPTION).build();
        assertFalse(SOFTWARE_ENGINEERING.equals(editedSoftwareEngineering));

        // different tags -> returns false
        editedSoftwareEngineering = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withTags(VALID_PLAN_COMPUTER_NETWORKING_TAG).build();
        assertFalse(SOFTWARE_ENGINEERING.equals(editedSoftwareEngineering));
    }
}
