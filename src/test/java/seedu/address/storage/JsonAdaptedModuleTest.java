package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;
import static seedu.address.testutil.TypicalRemindMe.MOD_1_WITH_ASSIGNMENTS;
import static seedu.address.testutil.TypicalRemindMe.MOD_1_WITH_EXAMS;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Title;

public class JsonAdaptedModuleTest {

    @Test
    void toModelType_validModuleDetails_returnModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(MOD_1);
        assertEquals(MOD_1, module.toModelType());
    }

    @Test
    void toModelType_withExams_returnModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(MOD_1_WITH_EXAMS);
        assertEquals(MOD_1_WITH_EXAMS, module.toModelType());
    }

    @Test
    void toModelType_withAssignments_returnModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(MOD_1_WITH_ASSIGNMENTS);
        assertEquals(MOD_1_WITH_ASSIGNMENTS, module.toModelType());
    }

    @Test
    void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, new ArrayList<>(), new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule("", new ArrayList<>(), new ArrayList<>());
        String expectedMessage = String.format(Title.MESSAGE_CONSTRAINTS, "Modules");
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}


