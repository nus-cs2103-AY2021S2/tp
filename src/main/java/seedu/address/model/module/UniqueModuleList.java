package seedu.address.model.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    //todo I think this is for editCommand. Delete if not used.
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        boolean hasDuplicate = !target.isSameModule(editedModule) && contains(editedModule);
        if (hasDuplicate) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

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

    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!areModulesUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }

    // todo not sure if we need this
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
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

    private boolean areModulesUnique(List<Module> modules) {
        // todo trying something different, hope it works.
        sort(modules);
        for (int i = 0; i < modules.size(); i++) {
            Module currModule = modules.get(i);
            Module nextModule = modules.get(i + 1);
            if (currModule.isSameModule(nextModule)) {
                return false;
            }
        }
        return true;
    }

    private void sort(List<Module> modules) {
        Collections.sort(modules);
    }
}
