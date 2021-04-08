package chim.model;

import static chim.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static chim.testutil.Assert.assertThrows;
import static chim.testutil.TypicalCustomers.ALICE;
import static chim.testutil.TypicalCustomers.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import chim.commons.core.GuiSettings;
import chim.commons.core.GuiSettings.PanelToShow;
import chim.model.customer.predicates.CustomerNamePredicate;
import chim.testutil.ChimBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Chim(), new Chim(modelManager.getChim()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setChimFilePath(Paths.get("chim/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(PanelToShow.CUSTOMER_LIST, 1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setChimFilePath(Paths.get("new/chim/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(PanelToShow.CUSTOMER_LIST, 1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setChimFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setChimFilePath(null));
    }

    @Test
    public void setChimFilePath_validPath_setsChimFilePath() {
        Path path = Paths.get("chim/file/path");
        modelManager.setChimFilePath(path);
        assertEquals(path, modelManager.getChimFilePath());
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInChim_returnsFalse() {
        assertFalse(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInChim_returnsTrue() {
        modelManager.addCustomer(ALICE);
        assertTrue(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void getFilteredCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCustomerList().remove(0));
    }

    @Test
    public void equals() {
        Chim chim = new ChimBuilder().withCustomer(ALICE).withCustomer(BENSON).build();
        Chim differentChim = new Chim();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(chim, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(chim, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different CHIM -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentChim, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCustomerList(new CustomerNamePredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(chim, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setChimFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(chim, differentUserPrefs)));
    }
}
