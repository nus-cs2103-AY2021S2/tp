package seedu.module.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.module.model.task.Module;
import seedu.module.model.task.Task;

/**
 * Represents a collection of Modules, and the list of Tasks associated with each.
 */
public class ModuleManager {

    private static final ObservableList<Module> moduleList = FXCollections.observableArrayList();
    private static final ObservableList<PieChart.Data> modulePieChartData = FXCollections.observableArrayList();
    private static HashMap<Module, List<Task>> mappingOfModulesToTasks;
    private static HashMap<Module, Integer> moduleWorkLoadDistribution;
    private static HashMap<Module, Integer> moduleLowWorkLoadDistribution;
    private static HashMap<Module, Integer> moduleMediumWorkLoadDistribution;
    private static HashMap<Module, Integer> moduleHighWorkLoadDistribution;
    private static List<String> supportedModulesInStr;
    private static final String[] arrOfModules = {"CS1010S", "CS1101S", "CS1231S",
        "CS2030", "CS2040S", "CS2101", "CS2102", "CS2103T", "CS2105", "CS2106", "CS3103",
        "CS3210", "CS3212", "CS3217", "CS3219", "CS3220", "CS3221", "CS3223", "CS3225",
        "CS3230", "CS3231", "CS3233", "CS3243", "CS3244", "IS1103", "ST2131"};
    private static final int LOW_LEVEL = 1;
    private static final int MEDIUM_LEVEL = 2;
    private static final int HIGH_LEVEL = 3;

    /**
     * A ModuleManager which manages the mapping of each module to its
     * associated List of Tasks.
     */
    public ModuleManager() {
        supportedModulesInStr = initListOfModulesAccepted();
        rebuildMapping();
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
     * Inserts a Task into a Module's List of Tasks mapping.
     * If module already exists in the mappings, update the List of Tasks in the mapping.
     * If module does not exist in the mappings, create a new entry in the mappings.
     *
     * @param module Module
     * @param task Task
     */
    public static void insertTaskToMapping(Module module, Task task) {
        if (mappingOfModulesToTasks == null) {
            rebuildMapping();
        }
        insertTaskInternal(module, task);
        increaseCorrectWorkloadDistribution(module, task);
        setExistingModuleList();
        setModulePieChartData();
    }

    private static void insertTaskInternal(Module module, Task task) {
        if (mappingOfModulesToTasks.containsKey(module)) {
            List<Task> newList = mappingOfModulesToTasks.get(module);
            //must ensure Module exists in the listOfValidModules
            newList.add(task);
            mappingOfModulesToTasks.put(module, newList);
        } else {
            List<Task> newList = new ArrayList<>();
            newList.add(task);
            mappingOfModulesToTasks.put(module, newList);
        }
    }

    /**
     * Modifies the workload distribution after adding the task.
     *
     * @param module the corresponding module of the task
     * @param task the task that need to be inserted into mapping book.
     */
    public static void increaseCorrectWorkloadDistribution(Module module, Task task) {
        int workloadLevel = task.getWorkload().getWorkloadLevel();
        moduleWorkLoadDistribution.put(module,
            moduleWorkLoadDistribution.getOrDefault(module, 0) + workloadLevel);
        switch(workloadLevel) {
        case LOW_LEVEL:
            moduleLowWorkLoadDistribution.put(module,
                moduleLowWorkLoadDistribution.getOrDefault(module, 0) + 1);
            break;
        case MEDIUM_LEVEL:
            moduleMediumWorkLoadDistribution.put(module,
                moduleMediumWorkLoadDistribution.getOrDefault(module, 0) + 1);
            break;
        case HIGH_LEVEL:
            moduleHighWorkLoadDistribution.put(module,
                moduleHighWorkLoadDistribution.getOrDefault(module, 0) + 1);
            break;
        default:
            assert false;
        }
    }

    /**
     * Modifies the workload distribution after deleting the task.
     *
     * @param module the corresponding module of the task
     * @param task the task that need to be inserted into mapping book.
     */
    public static void decreaseCorrectWorkloadDistribution(Module module, Task task) {
        int workloadLevel = task.getWorkload().getWorkloadLevel();
        moduleWorkLoadDistribution.put(module,
                moduleWorkLoadDistribution.get(module) - task.getWorkload().getWorkloadLevel());
        switch(workloadLevel) {
        case LOW_LEVEL:
            moduleLowWorkLoadDistribution.put(module,
                moduleLowWorkLoadDistribution.getOrDefault(module, 1) - 1);
            break;
        case MEDIUM_LEVEL:
            moduleMediumWorkLoadDistribution.put(module,
                moduleMediumWorkLoadDistribution.getOrDefault(module, 1) - 1);
            break;
        case HIGH_LEVEL:
            moduleHighWorkLoadDistribution.put(module,
                moduleHighWorkLoadDistribution.getOrDefault(module, 1) - 1);
            break;
        default:
            assert false;
        }
    }

    /**
     * Gets workload information of the {@code module}
     *
     * @param module the module to be checked.
     * @return return the workload information of one module.
     */
    public static String getModuleWorkloadInformation(Module module) {
        String moduleWorkLoadInformation = String.format("low workload tasks: %d\n"
                + "medium workload tasks: %d\n"
                + "high workload tasks: %d\n",
            moduleLowWorkLoadDistribution.getOrDefault(module, 0),
            moduleMediumWorkLoadDistribution.getOrDefault(module, 0),
            moduleHighWorkLoadDistribution.getOrDefault(module, 0));
        return moduleWorkLoadInformation;
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
        assert(mappingOfModulesToTasks.containsKey(module));
        List<Task> newList = mappingOfModulesToTasks.get(module);
        //must ensure Module exists in the listOfValidModules
        newList.remove(task);
        deleteTaskInternal(module, newList);
        decreaseCorrectWorkloadDistribution(module, task);
        setExistingModuleList();
        setModulePieChartData();
    }

    private static void deleteTaskInternal(Module module, List<Task> newList) {
        if (newList.isEmpty()) { //remove the module(key) from mapping if no task is associated with it
            mappingOfModulesToTasks.remove(module);
        } else {
            mappingOfModulesToTasks.put(module, newList);
        }
    }

    /**
     * Returns the mapping of Modules to Tasks.
     */
    public static HashMap<Module, List<Task>> getMappingOfModulesToTasks() {
        return mappingOfModulesToTasks;
    }

    /**
     * Discards all current mappings of Modules to Tasks.
     */
    public static void rebuildMapping() {
        mappingOfModulesToTasks = new HashMap<>();
        moduleWorkLoadDistribution = new HashMap<>();
        moduleLowWorkLoadDistribution = new HashMap<>();
        moduleMediumWorkLoadDistribution = new HashMap<>();
        moduleHighWorkLoadDistribution = new HashMap<>();
        moduleList.clear();
        modulePieChartData.clear();
    }

    /**
     * Returns a list of existing Modules.
     */
    public static List<String> getListOfSupportingModules() {
        return supportedModulesInStr;
    }

    /**
     * Initialize list of valid/accepted modules with basic modules.
     *
     * @return List of String associated with basic modules
     */
    private static List<String> initListOfModulesAccepted() {
        List<String> listOfModules = new ArrayList<>(Arrays.asList(arrOfModules));
        return listOfModules;
    }

    /**
     * Fills the observable list of existingModules with the keySet of the mapping.
     */
    public static void setExistingModuleList() {
        List<Module> existingModules = List.copyOf(mappingOfModulesToTasks.keySet());
        moduleList.setAll(existingModules);
    }

    /**
     * Fills the observable list of existingModules with the keySet of the mapping.
     */
    public static void setModulePieChartData() {
        modulePieChartData.clear();
        for (int i = 0; i < moduleList.size(); i++) {
            Module module = moduleList.get(i);
            modulePieChartData.add(new PieChart.Data(module.toString(), moduleWorkLoadDistribution.get(module)));
        }
    }

    /**
     * @return Observable List of existing modules.
     */
    public static ObservableList<Module> getExistingModuleList() {
        return moduleList;
    }

    /**
     * @return Observable List of existing modules data.
     */
    public static ObservableList<PieChart.Data> getModulePieChartData() {
        return modulePieChartData;
    }
}
