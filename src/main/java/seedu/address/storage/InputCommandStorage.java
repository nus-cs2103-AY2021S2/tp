package seedu.address.storage;

import java.util.ArrayList;

/**
 * Represents a storage for all user inputs keyed in the {@code CommandBox}.
 */
public class InputCommandStorage {

    private static ArrayList<String> inputCommandList = new ArrayList<String>();
    private static int currentPointer;

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
    public ArrayList<String> getInputCommandList() {
        return inputCommandList;
    }

    /**
     * Appends all new user inputs into inputCommandList.
     * Pointer increments to latest input.
     * @param userInput being parsed
     */
    public void newInput(String userInput) {
        inputCommandList.add(userInput);
        currentPointer = inputCommandList.size() - 1;
    }

    /**
     * Returns previous or next user input according to index of currentPointer.
     * Pointer increments or decrements based on {@code isBefore} boolean value.
     * @param isBefore if user is calling the previous input
     */
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
