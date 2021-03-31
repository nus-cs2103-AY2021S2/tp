package seedu.cakecollate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.BUTTERSCOTCH;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE_MUD;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;
import static seedu.cakecollate.testutil.TypicalOrders.BENSON;
import static seedu.cakecollate.testutil.TypicalOrders.ELLE;
import static seedu.cakecollate.testutil.TypicalOrders.GEORGE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.model.order.ContainsKeywordsPredicate;
import seedu.cakecollate.testutil.CakeCollateBuilder;
import seedu.cakecollate.testutil.OrderItemsBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new CakeCollate(), new CakeCollate(modelManager.getCakeCollate()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setCakeCollateFilePath(Paths.get("cakecollate/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setCakeCollateFilePath(Paths.get("new/cakecollate/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setCakeCollateFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCakeCollateFilePath(null));
    }

    @Test
    public void setCakeCollateFilePath_validPath_setsCakeCollateFilePath() {
        Path path = Paths.get("cakecollate/book/file/path");
        modelManager.setCakeCollateFilePath(path);
        assertEquals(path, modelManager.getCakeCollateFilePath());
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasOrder(null));
    }

    @Test
    public void hasOrder_orderNotInCakeCollate_returnsFalse() {
        assertFalse(modelManager.hasOrder(ALICE));
    }

    @Test
    public void hasOrder_orderInCakeCollate_returnsTrue() {
        modelManager.addOrder(ALICE);
        assertTrue(modelManager.hasOrder(ALICE));
    }

    @Test
    public void getFilteredOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredOrderList().remove(0));
    }

    @Test
    public void equals() {
        CakeCollate cakeCollate = new CakeCollateBuilder().withOrder(ALICE).withOrder(BENSON).build();
        CakeCollate differentCakeCollate = new CakeCollate();
        UserPrefs userPrefs = new UserPrefs();
        OrderItems orderItems = new OrderItemsBuilder().withOrderItem(BUTTERSCOTCH).withOrderItem(CHOCOLATE).build();
        OrderItems differentOrderItems = new OrderItemsBuilder().withOrderItem(BUTTERSCOTCH)
                .withOrderItem(CHOCOLATE_MUD).build();
        // same values -> returns true
        modelManager = new ModelManager(cakeCollate, userPrefs, orderItems);
        ModelManager modelManagerCopy = new ModelManager(cakeCollate, userPrefs, orderItems);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different cakeCollate -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentCakeCollate, userPrefs, orderItems)));

        // different orderItems -> return false
        assertFalse(modelManager.equals(new ModelManager(cakeCollate, userPrefs, differentOrderItems)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");

        HashMap<Prefix, List<String>> map = new HashMap<>();
        map.put(PREFIX_NAME, Arrays.asList(keywords));
        modelManager.updateFilteredOrderList(new ContainsKeywordsPredicate(map));
        assertFalse(modelManager.equals(new ModelManager(cakeCollate, userPrefs, orderItems)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setCakeCollateFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(cakeCollate, differentUserPrefs, orderItems)));
    }

    /**
     * Ensure that when an order with an earlier delivery date is added after one with a later delivery date,
     * their orders are swapped in the model, which should always be sorted.
     */
    @Test
    public void getFilteredOrderList_addOrdersNotSorted_modelShouldBeSorted() {
        assert ELLE.getDeliveryDate().compareTo(GEORGE.getDeliveryDate()) > 0
                : "Use different orders for this test case.";

        CakeCollate cakeCollate = new CakeCollateBuilder().withOrder(ELLE).withOrder(GEORGE).build();
        UserPrefs userPrefs = new UserPrefs();
        OrderItems orderItems = new OrderItems();

        modelManager = new ModelManager(cakeCollate, userPrefs, orderItems);
        assertEquals(modelManager.getFilteredOrderList(),
                Arrays.asList(GEORGE, ELLE));
    }
}
