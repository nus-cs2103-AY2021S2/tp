package dog.pawbook.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Pair<Integer, Entity>> PREDICATE_SHOW_ALL_ENTITIES = unused -> true;
    Comparator<Pair<Integer, Entity>> COMPARATOR_ID_ASCENDING_ORDER = Comparator.comparingInt(Pair::getKey);

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' database file path.
     */
    Path getDatabaseFilePath();

    /**
     * Sets the user prefs' database file path.
     */
    void setDatabaseFilePath(Path databaseFilePath);

    /**
     * Replaces database data with the data in {@code database}.
     */
    void setDatabase(ReadOnlyDatabase database);

    /**
     * Returns the Database.
     */
    ReadOnlyDatabase getDatabase();

    /**
     * Returns true if an entity with the same identity as {@code entity} exists in Pawbook.
     */
    boolean hasEntity(Entity entity);
    boolean hasEntity(int id);

    /**
     * Deletes the given entity.
     * The entity must exist in Pawbook.
     */
    void deleteEntity(int targetID);

    /**
     * Adds the given entity.
     * {@code entity} must not already exist in Pawbook.
     * @return int The id of the entity.
     */
    int addEntity(Entity entity);

    /**
     * Replaces the given entity {@code targetId} with {@code editedEntity}.
     * {@code targetId} must exist in Pawbook.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in the database.
     */
    void setEntity(int targetId, Entity editedEntity);

    /**
     * Get the entity with the given ID.
     * {@code targetId} must exist in Pawbook.
     */
    Entity getEntity(int targetID);

    /**
     * Returns an unmodifiable view of the filtered entity list
     */
    ObservableList<Pair<Integer, Entity>> getFilteredEntityList();

    /**
     * Updates the filter of the filtered entity list to filter by the given {@code predicate}.
     * Returns an unmodifiable view of the unfiltered owner list
     */
    ObservableList<Pair<Integer, Entity>> getUnfilteredEntityList();

    /**
     * Updates the filter of the filtered entity list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate);

    /**
     * Sorts the filtered entity list by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortEntities(Comparator<Pair<Integer, Entity>> comparator);
}
