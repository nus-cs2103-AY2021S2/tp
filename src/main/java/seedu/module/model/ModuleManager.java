package seedu.module.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.module.model.task.Task;
import seedu.module.model.task.Module;

/**
 * Represents a collection of Modules, and the list of Tasks associated with each.
 */
public class ModuleManager {
    public static HashMap<String, List<Task>> mapping;
    private static List<String> existingModulesInStr;
    private static final String[] arrOfModules = {"CS1101S", "CS1231S", "CS2030", "CS2040S",
            "CS2103T", "CS2105", "CS2106", "CS3230", "CS3243"};

    public ModuleManager() {
        existingModulesInStr = initListOfModulesAccepted();
//        setModulesAccepted();
        clearMapping();
        System.out.println("ModuleManager invoked");
    }

    public static boolean moduleIsValid(String module) {
        return existingModulesInStr.contains(module);
    }

    /**
     * Updates a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, create a new entry in the mappings.
     */
    public static void insertTaskToMapping(Module module, Task task) {
        assert(module != null && task != null);
        if (mapping.containsKey(module.toString())) {
            List<Task> newList = mapping.get(module.toString()); //must ensure Module exists in the listOfValidModules
            newList.add(task);
            mapping.put(module.toString(), newList);
        }
        else {
            List<Task> newList = new ArrayList<>();
            newList.add(task);
            mapping.put(module.toString(), newList);
        }
        System.out.println(mapping);
        System.out.println(mapping.size());
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
