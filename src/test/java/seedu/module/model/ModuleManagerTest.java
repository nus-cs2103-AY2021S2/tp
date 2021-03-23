package seedu.module.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.QUIZ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.module.model.task.Task;
import seedu.module.testutil.TaskBuilder;

public class ModuleManagerTest {

    private final ModuleManager moduleManager = new ModuleManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyMap(), ModuleManager.getMappingOfModulesToTasks());
    }

    @Test
    public void initSupportedModules_comparedToStub() {
        List<String> stub = new ArrayList<>();
        stub.add("ST3131");
        stub.add("ST4332");
        assertNotEquals(stub, ModuleManager.getListOfExistingModules());
    }

    @Test
    public void moduleIsValid_methodTest() {
        String fakeModule = "CSG21033GG@T";
        assertFalse(ModuleManager.moduleIsValid(fakeModule));
    }

    @Test
    public void insertingNullModuleIntoMappingWithTask_throwsNullPointerException() {
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertThrows(NullPointerException.class, () -> ModuleManager.insertTaskToMapping(null, editedAlice));
        ModuleManager.clearMapping();
    }

    @Test
    public void deletingNullModuleFromMappingWithTask_throwsNullPointerException() {
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertThrows(NullPointerException.class, () -> ModuleManager.insertTaskToMapping(null, editedAlice));
        ModuleManager.clearMapping();
    }

    @Test
    public void clearMapping_test() {
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        ModuleManager.insertTaskToMapping(editedAlice.getModule(), editedAlice);
        ModuleManager.clearMapping();
        assertEquals(ModuleManager.getMappingOfModulesToTasks(), new HashMap<>());
    }
}
