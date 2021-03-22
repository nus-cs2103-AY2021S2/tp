package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2040;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleName;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final Integer INVALID_WEIGHTAGE = 100000;
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CODE = "CT2103";

    private static final String VALID_NAME = CS2040.getModuleName().toString();
    private static final String VALID_CODE = CS2040.getModuleCode().toString();
    private static final String VALID_DATE = CS2040.getDeadlineDate().toString();
    private static final String VALID_TIME = CS2040.getDeadlineTime().toString();
    private static final String VALID_STATUS = CS2040.getStatus().toString();
    private static final Integer VALID_WEIGHTAGE = CS2040.getWeightage().weightage;
    private static final String VALID_REMARK = CS2040.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2040.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CS2040);
        assertEquals(CS2040, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_CODE, VALID_DATE,
                        VALID_TIME, VALID_STATUS, VALID_WEIGHTAGE, VALID_REMARK,
                        VALID_TAGS);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_CODE, VALID_DATE,
                VALID_TIME, VALID_STATUS, VALID_WEIGHTAGE,
            VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedTask task =
            new JsonAdaptedTask(VALID_NAME, INVALID_CODE, VALID_DATE,
                    VALID_TIME, VALID_STATUS, VALID_WEIGHTAGE, VALID_REMARK,
                    VALID_TAGS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_DATE,
                VALID_TIME, VALID_STATUS, VALID_WEIGHTAGE,
            VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_CODE, VALID_DATE,
                        VALID_TIME, VALID_STATUS, VALID_WEIGHTAGE, VALID_REMARK,
                        invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
