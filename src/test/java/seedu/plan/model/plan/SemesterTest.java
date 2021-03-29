package seedu.plan.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plan.logic.commands.CommandTestUtil.VALID_PLAN_SOFTWARE_ENGINEERING_TAG;
import static seedu.plan.testutil.Assert.assertThrows;
import static seedu.plan.testutil.TypicalModules.SOFTWARE_ENGINEERING_MODULE;
import static seedu.plan.testutil.TypicalPlans.COMPUTER_NETWORKING;
import static seedu.plan.testutil.TypicalPlans.SOFTWARE_ENGINEERING;
import static seedu.plan.testutil.TypicalSemesters.SEMESTER1;
import static seedu.plan.testutil.TypicalSemesters.SEMESTER2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.plan.model.tag.Tag;

public class SemesterTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Semester(1, null));
    }

    @Test
    public void moduleList_modifyList_throwsUnsupportedOperationException() {
        List<Module> modules = new ArrayList<>();
        Semester semester = new Semester(1, modules);
        assertThrows(UnsupportedOperationException.class, () ->
                semester.getModules().remove(0));
    }

    @Test
    public void addModule_null_throwsNullPointerException() {
        Semester semester = new Semester(1);
        assertThrows(NullPointerException.class, () -> semester.addModule(null));
    }

    @Test
    public void removeModule_null_throwsNullPointerException() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);
        assertThrows(NullPointerException.class, () -> semester.removeModule(null));
    }

    @Test
    public void getModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);
        assertEquals(modules, semester.getModules());

        semester = new Semester(1);
        assertEquals(Collections.emptyList(), semester.getModules());
    }

    @Test
    public void getModule() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);

        assertEquals(SOFTWARE_ENGINEERING_MODULE, semester.getModuleByModuleCode("CS2103"));
        assertNull(semester.getModuleByModuleCode(""));
    }

    @Test
    public void hasModule() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);
        assertTrue(semester.hasModule("CS2103"));
        assertFalse(semester.hasModule("CS2105"));
    }

    @Test
    public void getSemNumber() {
        assertEquals(1,
                SEMESTER1.getSemNumber());
    }

    @Test
    public void getNumModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);
        assertEquals(modules.size(),
                semester.getNumModules());
    }

    @Test
    public void getTotalMCs() {
        List<Module> modules = new ArrayList<>();
        modules.add(SOFTWARE_ENGINEERING_MODULE);
        Semester semester = new Semester(1, modules);
        assertEquals(modules.stream().map(x -> x.getMCs())
                .collect(Collectors.summingInt(Integer::intValue)).intValue(),
                semester.getTotalMCs());
    }

    @Test
    public void addModule() {
        Semester semester = new Semester(1);
        semester.addModule(SOFTWARE_ENGINEERING_MODULE);
        assertEquals(1, semester.getModules().size());
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
        Semester semester1Copy = SEMESTER1;
        assertTrue(SEMESTER1.equals(semester1Copy));

        // same object -> returns true
        assertTrue(SEMESTER1.equals(SEMESTER1));

        // null -> returns false
        assertFalse(SEMESTER1.equals(null));

        // different type -> returns false
        assertFalse(SEMESTER1.equals(5));

        // different plan -> returns false
        assertFalse(SEMESTER1.equals(SEMESTER2));
    }
}
