package seedu.address.storage;

import java.util.ArrayList;

/**
 * Represents a storage for all user inputs keyed in the {@code CommandBox}.
 */
public class InputCommandStorage {

    private static int currentPointer;
    static ArrayList<String> inputCommandList = new ArrayList<String>();

    /**
     * Constructor for InputCommandStorage. No inputs have been keyed in by user,
     * CurrentPointer is set to 0, will traverse as keyCodes in
     * {@code handleToggleQuery(KeyEvent event)} changes.
     */
    public InputCommandStorage() {
        this.inputCommandList = inputCommandList;
        currentPointer = 0;
    }

    /**
     * Returns all previous user inputs stored in inputCommandList
     */
    public static ArrayList<String> getInputCommandList() {
        return inputCommandList;
    }

    /**
     * Appends all new user inputs into inputCommandList. Pointer increments.
     */
    public void newInput(String args) {
        inputCommandList.add(args);
        currentPointer++;
    }

    public static String retrieveInput(boolean isBefore) {
        int inputCommandListSize = inputCommandList.size() - 1;

        if (isBefore) {
            currentPointer--;
            if (currentPointer < 0) {
                currentPointer = 0;
            }

        } else {
            currentPointer++;

            if (currentPointer > inputCommandListSize) {
                currentPointer = inputCommandListSize;
            }

        }
        return inputCommandList.get(currentPointer);
    }

}
