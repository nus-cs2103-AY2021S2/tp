package dog.pawbook.model;

import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.ALICE;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class DatabaseTest {

    private final Database database = new Database();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), database.getEntityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDatabase_replacesData() {
        Database newData = getTypicalDatabase();
        database.resetData(newData);
        assertEquals(newData, database);
    }

    @Test
    public void hasOwner_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasEntity(null));
    }

    @Test
    public void hasOwner_ownerNotInDatabase_returnsFalse() {
        assertFalse(database.hasEntity(ALICE));
    }

    @Test
    public void hasOwner_ownerInDatabase_returnsTrue() {
        database.addEntity(ALICE);
        assertTrue(database.hasEntity(ALICE));
    }

    @Test
    public void getOwnerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getEntityList().remove(0));
    }

    /**
     * A stub ReadOnlyDatabase whose entities list can violate interface constraints.
     */
    private static class DatabaseStub implements ReadOnlyDatabase {
        private final ObservableList<Pair<Integer, Entity>> entitiesWithId = FXCollections.observableArrayList();

        DatabaseStub(Collection<Pair<Integer, Entity>> owners) {
            this.entitiesWithId.setAll(owners);
        }

        @Override
        public ObservableList<Pair<Integer, Entity>> getEntityList() {
            return entitiesWithId;
        }
    }

}
