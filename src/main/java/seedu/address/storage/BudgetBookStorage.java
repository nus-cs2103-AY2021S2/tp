package seedu.address.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import seedu.address.model.BudgetBook;
import seedu.address.model.budget.Budget;


/**
 * BudgetStorage class for I/O to budget.txt file.
 */
public class BudgetBookStorage {

    // Source folder
    public static final String SOURCE_FOLDER = "data";
    // Name of text file to store tasks
    public static final String SOURCE_FILE = "/budget.txt";

    /**
     * Creating directory if it does not exist.
     */
    public void createDirectory() {
        File directory = new File(SOURCE_FOLDER);
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success) {
                System.out.println("Directory creation was unsuccessful. Please "
                        + "manually create it.");
            }
        }
    }

    /**
     * @return True is budget file exists.
     */
    public boolean isFilePresent() {
        File budgetFile = new File(SOURCE_FOLDER + SOURCE_FILE);
        return budgetFile.exists();
    }

    /**
     * Overwrites content to save new content to disk.
     */
    public void saveBudget(int budget, int cost) throws IOException {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(
                    SOURCE_FOLDER + SOURCE_FILE, false));
            out.write(String.format("%d | %d", budget, cost));
            out.flush();
        } catch (Exception ignored) {
            System.out.println("Failed to save budget in directory.");
        }
    }

    /**
     * Saves empty file with no budget and cost.
     * @throws IOException
     */
    public void saveBudget() throws IOException {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(
                    SOURCE_FOLDER + SOURCE_FILE, false));
            out.write("");
            out.flush();
        } catch (IOException e) {
            System.out.println("Failed to create source file");
        }

    }

    /**
     * @return A budget book.
     */
    public BudgetBook loadBudgetBook() {
        HashMap<String, Integer> budgetCostMap = loadBudget();
        if (hasBudget()) {
            return new BudgetBook(new Budget(budgetCostMap.get("Budget").toString()));
        } else {
            return new BudgetBook();
        }
    }

    /**
     * Loads budget.txt file
     * @return HashMap representing the budget of user and cost of all appointments
     */
    public HashMap<String, Integer> loadBudget() {
        HashMap<String, Integer> budgetCostMap = new HashMap<>();
        createDirectory();
        try {
            BufferedReader br =
                    new BufferedReader(new FileReader(SOURCE_FOLDER + SOURCE_FILE));
            String line = br.readLine();

            if (line != null && line.contains("|")) {
                String[] budgetAndCost = line.split("[|]");
                int budgetCost = Integer.parseInt(budgetAndCost[0].trim());
                int totalCost = Integer.parseInt(budgetAndCost[1].trim());
                budgetCostMap.put("Budget", budgetCost);
                budgetCostMap.put("Cost", totalCost);
            }
            return budgetCostMap;

        } catch (IOException e) {
            return budgetCostMap;
        }
    }

    /**
     * Checks whether budget text file is empty.
     * @return True is budget is present, has both budget and cost
     * @throws IOException
     */
    public boolean hasBudget() {
        HashMap<String, Integer> budgetCostMap = loadBudget();
        return isFilePresent() && budgetCostMap.size() == 2;
    }


}
