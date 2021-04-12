package seedu.us.among.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.model.Model.PREDICATE_SHOW_ALL_ENDPOINTS;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
import static seedu.us.among.testutil.TypicalEndpoints.POST;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;
import seedu.us.among.testutil.EndpointListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        Assertions.assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EndpointList(), new EndpointList(modelManager.getEndpointList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setEndpointListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4, "imposter"));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setEndpointListFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4, "imposter");
        modelManager.setGuiSettings(guiSettings);
        Assertions.assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setEndpointListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEndpointListFilePath(null));
    }

    @Test
    public void setEndpointListFilePath_validPath_setsEndpointListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setEndpointListFilePath(path);
        assertEquals(path, modelManager.getEndpointListFilePath());
    }

    @Test
    public void hasEndpoint_nullEndpoint_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEndpoint(null));
    }

    @Test
    public void hasEndpoint_endpointNotInEndpointList_returnsFalse() {
        assertFalse(modelManager.hasEndpoint(GET));
    }

    @Test
    public void hasEndpoint_endpointInEndpointList_returnsTrue() {
        modelManager.addEndpoint(GET);
        assertTrue(modelManager.hasEndpoint(GET));
    }

    @Test
    public void getFilteredEndpointList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEndpointList().remove(0));
    }

    @Test
    public void equals() {
        EndpointList endpointList = new EndpointListBuilder().withEndpoint(GET).withEndpoint(POST).build();
        EndpointList differentEndpointList = new EndpointList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(endpointList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(endpointList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different endpointList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentEndpointList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = GET.getMethod().methodName.split("\\s+");
        modelManager.updateFilteredEndpointList(new EndPointContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(endpointList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEndpointList(PREDICATE_SHOW_ALL_ENDPOINTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEndpointListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(endpointList, differentUserPrefs)));
    }
}
