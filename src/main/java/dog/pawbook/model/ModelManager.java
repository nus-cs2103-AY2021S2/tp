package dog.pawbook.model;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_DOG_PREDICATE;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.commons.core.LogsCenter;
import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;

/**
 * Represents the in-memory model of the database data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Database database;
    private final UserPrefs userPrefs;
    private final FilteredList<Pair<Integer, Entity>> filteredEntities;

    /**
     * Initializes a ModelManager with the given database and userPrefs.
     */
    public ModelManager(ReadOnlyDatabase database, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(database, userPrefs);

        logger.fine("Initializing with database: " + database + " and user prefs " + userPrefs);

        this.database = new Database(database);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEntities = new FilteredList<>(this.database.getEntityList());
        filteredEntities.setPredicate(IS_DOG_PREDICATE);
    }

    public ModelManager() {
        this(new Database(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDatabaseFilePath() {
        return userPrefs.getDatabaseFilePath();
    }

    @Override
    public void setDatabaseFilePath(Path databaseFilePath) {
        requireNonNull(databaseFilePath);
        userPrefs.setDatabaseFilePath(databaseFilePath);
    }


    //=========== Database ================================================================================
    @Override
    public void setDatabase(ReadOnlyDatabase database) {
        this.database.resetData(database);
    }

    @Override
    public ReadOnlyDatabase getDatabase() {
        return database;
    }

    @Override
    public boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return database.hasEntity(entity);
    }

    @Override
    public boolean hasEntity(int id) {
        return database.hasEntity(id);
    }

    @Override
    public Entity getEntity(int targetID) {
        return database.getEntity(targetID);
    }

    @Override
    public void deleteEntity(int targetID) {
        database.removeEntity(targetID);
    }

    @Override
    public int addEntity(Entity entity) {
        return database.addEntity(entity);
    }

    @Override
    public void setEntity(int targetId, Entity editedEntity) {
        requireAllNonNull(editedEntity);

        database.setEntity(targetId, editedEntity);
    }

    //=========== Filtered Owner List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Entity} backed by the internal list of
     * {@code versionedDatabase}
     */
    @Override
    public ObservableList<Pair<Integer, Entity>> getFilteredEntityList() {
        return filteredEntities;
    }

    @Override
    public ObservableList<Pair<Integer, Entity>> getUnfilteredEntityList() {
        return database.getEntityList();
    }

    @Override
    public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
        requireNonNull(predicate);
        filteredEntities.setPredicate(predicate);
    }

    @Override
    public void sortEntities(Comparator<Pair<Integer, Entity>> comparator) {
        requireNonNull(comparator);
        database.sortEntities(comparator);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return database.equals(other.database)
                && userPrefs.equals(other.userPrefs)
                && filteredEntities.equals(other.filteredEntities);
    }
}
