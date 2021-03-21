package seedu.module.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.module.model.task.Module;
import seedu.module.model.task.Task;

/**
 * Represents a collection of Modules, and the list of Tasks associated with each.
 */
public class ModuleManager {
    private static HashMap<String, List<Task>> mapping;
    private static List<String> existingModulesInStr;
    private static final String[] arrOfModules = {"CS1101S", "CS1231S", "CS2030", "CS2040S", "CS2101",
        "CS2103T", "CS2105", "CS2106", "CS3230", "CS3243", "CS3244", "IS1103", "ST2131"};

    /**
     * A ModuleManager which manages the mapping of each module to its
     * associated List of Tasks.
     */
    public ModuleManager() {
        existingModulesInStr = initListOfModulesAccepted();
        clearMapping();
    }

    public static void initExistingModulesInStr() {
        existingModulesInStr = initListOfModulesAccepted();
    }

    public static boolean moduleIsValid(String module) {
        return existingModulesInStr.contains(module);
    }

    /**
     * Insert a Task into a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, create a new entry in the mappings.
     * @param module
     * @param task
     */
    public static void insertTaskToMapping(Module module, Task task) {
        if (mapping == null) {
            clearMapping();
        }
        if (mapping.containsKey(module.toString())) {
            List<Task> newList = mapping.get(module.toString()); //must ensure Module exists in the listOfValidModules
            newList.add(task);
            mapping.put(module.toString(), newList);
        } else {
            List<Task> newList = new ArrayList<>();
            newList.add(task);
            mapping.put(module.toString(), newList);
        }
    }

    /**
     * Deletes a Task from a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, do nothing.
     * @param module
     * @param task
     */
    public static void deleteTaskFromMapping(Module module, Task task) {
        assert(module != null && task != null);
        if (mapping.containsKey(module.toString())) {
            List<Task> newList = mapping.get(module.toString()); //must ensure Module exists in the listOfValidModules
            newList.remove(task);
            if (newList.isEmpty()) { //remove the module(key) from mapping if no task is associated with it
                mapping.remove(module);
            } else {
                mapping.put(module.toString(), newList);
            }
        } else {
        }

    }

    /**
     * Discards all current mappings of Modules to Tasks
     */
    public static void clearMapping() {
        mapping = new HashMap<>();
    }

    public static List<String> getListOfExistingModules() {
        return existingModulesInStr;
    }

    /**
     * Initialize list of valid/accepted modules with basic modules
     * @return List of String associated with basic modules
     */
    static List<String> initListOfModulesAccepted() {
        List<String> listOfModules = new ArrayList<>();
        for (String moduleInStr : arrOfModules) {
            listOfModules.add(moduleInStr);
        }
        return listOfModules;
    }
}
