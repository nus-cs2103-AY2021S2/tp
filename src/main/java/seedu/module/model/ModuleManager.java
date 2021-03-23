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
    private static HashMap<String, List<Task>> mappingOfModulesToTasks;
    private static List<String> supportedModulesInStr;
    private static final String[] arrOfModules = {"CS1101S", "CS1231S", "CS2030", "CS2040S", "CS2101",
        "CS2103T", "CS2105", "CS2106", "CS3230", "CS3243", "CS3244", "IS1103", "ST2131"};

    /**
     * A ModuleManager which manages the mapping of each module to its
     * associated List of Tasks.
     */
    public ModuleManager() {
        supportedModulesInStr = initListOfModulesAccepted();
        clearMapping();
    }

    /**
     * Initialize a List of Modules in String format.
     */
    public static void initSupportedModulesInStr() {
        supportedModulesInStr = initListOfModulesAccepted();
    }

    /**
     * Check whether a Module is valid to be used (supported).
     *
     * @param module Module
     * @return True if Module exists in our List of supported Modules.
     */
    public static boolean moduleIsValid(String module) {
        return supportedModulesInStr.contains(module);
    }

    /**
     * Insert a Task into a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, create a new entry in the mappings.
     *
     * @param module Module
     * @param task Task
     */
    public static void insertTaskToMapping(Module module, Task task) {
        if (mappingOfModulesToTasks == null) {
            clearMapping();
        }
        if (mappingOfModulesToTasks.containsKey(module.toString())) {
            List<Task> newList = mappingOfModulesToTasks.get(module.toString());
            //must ensure Module exists in the listOfValidModules
            newList.add(task);
            mappingOfModulesToTasks.put(module.toString(), newList);
        } else {
            List<Task> newList = new ArrayList<>();
            newList.add(task);
            mappingOfModulesToTasks.put(module.toString(), newList);
        }
    }

    /**
     * Deletes a Task from a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, do nothing.
     *
     * @param module Module
     * @param task Task
     */
    public static void deleteTaskFromMapping(Module module, Task task) {
        assert(module != null && task != null);
        assert(mappingOfModulesToTasks.containsKey(module.toString()));
        List<Task> newList = mappingOfModulesToTasks.get(module.toString());
        //must ensure Module exists in the listOfValidModules
        newList.remove(task);
        if (newList.isEmpty()) { //remove the module(key) from mapping if no task is associated with it
            mappingOfModulesToTasks.remove(module.toString());
        } else {
            mappingOfModulesToTasks.put(module.toString(), newList);
        }
    }

    /**
     * Returns the mapping of Modules to Tasks.
     */
    public static HashMap<String, List<Task>> getMappingOfModulesToTasks() {
        return mappingOfModulesToTasks;
    }

    /**
     * Discards all current mappings of Modules to Tasks.
     */
    public static void clearMapping() {
        mappingOfModulesToTasks = new HashMap<>();
    }

    /**
     * Returns a List of supported Modules in String.
     * @return
     */
    public static List<String> getListOfExistingModules() {
        return supportedModulesInStr;
    }

    /**
     * Initialize list of valid/accepted modules with basic modules.
     *
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
