package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage for all user inputs keyed in the {@code CommandBox}.
 */
public class InputCommandStorage {

    private static List<String> inputCommandList;
    private static int currentPointer;

    /**
     * Constructor for InputCommandStorage. No inputs have been keyed in by user,
     * CurrentPointer is set to 0, will traverse as keyCodes in
     * {@code handleToggleQuery(KeyEvent event)} changes.
     */
    public InputCommandStorage() {
        this.inputCommandList = new ArrayList<>();
        currentPointer = 0;
    }

    /**
     * Returns all previous user inputs stored in inputCommandList
     */
    public List<String> getInputCommandList() {
        return inputCommandList;
    }

    /**
     * Appends all new user inputs into inputCommandList.
     * Pointer increments to latest input.
     * @param userInput being parsed
     */
    public void addInput(String userInput) {
        inputCommandList.add(userInput);
        currentPointer = inputCommandList.size();
    }

    /**
     * Returns previous or next user input at index of currentPointer.
     * @param isUpPressed if user is calling the previous input
     */
    public static String retrieveInput(boolean isUpPressed) {
        if (isUpPressed) {
            decrementCurrentPointer();
        } else {
            incrementCurrentPointer();
        }
        return getCurrentPointerInput();
    }

    /**
     * Increments current pointer within inputCommandList
     */
    public static void incrementCurrentPointer() {
        if (currentPointer < inputCommandList.size()) {
            currentPointer++;
        }
    }

    /**
     * Decrements current pointer within inputCommandList
     */
    public static void decrementCurrentPointer() {
        if (currentPointer > 0) {
            currentPointer--;
        }
    }

    /**
     * Returns input at currentPointer index
     */
    public static String getCurrentPointerInput() {
        return (currentPointer == inputCommandList.size())
                ? ""
                : inputCommandList.get(currentPointer);
    }

}
