package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Gets the module with the same module title as {@code module}
     * {@code module} must already exist in the list.
     */
    public Module getModule(Module module) {
        requireNonNull(module);

        Predicate<Module> hasModuleTitle = (x) -> x.isSameModule(module);
        FilteredList<Module> filteredModule = internalList.filtered(hasModuleTitle);
        if (filteredModule.size() != 1) {
            throw new ModuleNotFoundException();
        }
        return filteredModule.get(0);
    }

    /**
     * Gets the module at {@code index}
     * {@code index} must be within the bounds of the list size.
     */
    public Module getModule(int index) {
        if (index - 1 >= 0 && index - 1 < size()) {
            return internalList.get(index - 1);
        } else {
            throw new ModuleNotFoundException();
        }
    }
    /**
     * Replaces the module {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedModule} must not be the same as another existing
     * module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        Module foundModule = getModule(target);
        int index = internalList.indexOf(foundModule);

        boolean hasDuplicate = !foundModule.equals(editedModule) && contains(editedModule);
        if (hasDuplicate) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public void setModules(UniqueModuleList replaceModules) {
        requireNonNull(replaceModules);
        internalList.setAll(replaceModules.internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!areModulesUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return internalList.size();
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueModuleList)
                    && internalList.equals(((UniqueModuleList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean areModulesUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }
}
