package seedu.address.logic.commandhistory;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that contains the history of commands entered by the user.
 */
public class CommandHistory {
    private static CommandHistory commandHistory = null;

    private List<String> history;
    private final Pointer currPointer;
    private String currCommand;

    private CommandHistory() {
        this.history = new ArrayList<>();
        currPointer = new Pointer();
        currCommand = "";
    }

    /**
     * Returns the saved instance of {@code commandHistory}, or creates it if it is null.
     *
     * @return {@code commandHistory}, the command history for the current session.
     */
    public static CommandHistory getCommandHistory() {
        if (commandHistory == null) {
            commandHistory = new CommandHistory();
        }

        return commandHistory;
    }

    /**
     * Adds a command to the history list.
     * Also resets the current pointer to get ready for user to input another command.
     *
     * @param command command to add to the history list.
     */
    public void addCommand(String command) {
        requireNonNull(command);
        history.add(command);
        currPointer.resetPosition();
        currCommand = "";
    }

    /**
     * Returns the number of commands saved.
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * Returns the next string in the command history. Also saves the last command partially typed by the user
     * and handles cases where there is no next string.
     *
     * @param command The last command partially typed by the user.
     * @return The next string in the command history.
     */
    public String getNext(String command) {
        requireNonNull(command);
        setCurrentCommand(command);
        currPointer.next(getHistorySize());
        return getCommandString();
    }

    /**
     * Returns the previous string in the command history. Also saves the last command partially typed by the user
     * and handles cases where there is no previous string.
     *
     * @param command The last command partially typed by the user.
     * @return The previous string in the command history.
     */
    public String getPrevious(String command) {
        requireNonNull(command);
        setCurrentCommand(command);
        currPointer.previous(getHistorySize());
        return getCommandString();
    }

    /**
     * Returns the string in the command history at the current pointer.
     *
     * @return The current string in the command history referenced by the pointer.
     */
    private String getCommandString() {
        if (!currPointer.isPointingToHistory()) {
            return currCommand;
        } else {
            return history.get(currPointer.currPosition);
        }
    }

    private void setCurrentCommand(String command) {
        if (!currPointer.isPointingToHistory()) {
            currCommand = command;
        }
    }

    /**
     * Clears the command history and current command as well as resets the pointer.
     */
    public void clearCommandHistory() {
        history = new ArrayList<>();
        currPointer.resetPosition();
        currCommand = "";
    }

    /**
     * A class to represent the pointer to a {@code CommandHistory} object.
     */
    static class Pointer {
        private int currPosition;
        private boolean isPointingToHistory;

        /**
         * Creates a new {@code Pointer} object.
         */
        public Pointer() {
            currPosition = 0;
            isPointingToHistory = false;
        }

        public void resetPosition() {
            isPointingToHistory = false;
        }

        /**
         * Decrements the pointer, or sets the pointer to the last element of the list if pointer is not pointing to
         * a location in history.
         *
         * @param currSize The current size of the history list.
         */
        public void previous(int currSize) {
            assert currPosition >= 0 : "Current position in history should not be negative";

            if (!isPointingToHistory && currSize != 0) {
                currPosition = currSize - 1;
                isPointingToHistory = true;
            } else if (currPosition != 0) {
                currPosition -= 1;
            }
        }

        /**
         * Increments the pointer, or ignores the command if the pointer is not pointing to a location in history.
         *
         * @param currSize The current size of the history list.
         */
        public void next(int currSize) {
            assert currPosition < currSize : "Current position in history should not be larger than size of list";
            int lastPosition = currSize - 1;

            if (currPosition == lastPosition) {
                resetPosition();
            } else if (isPointingToHistory) {
                currPosition++;
            }
        }

        /**
         * Checks if pointer is pointing to a location in history.
         *
         * @return true if pointer is pointing to a location in history, false otherwise.
         */
        public boolean isPointingToHistory() {
            return isPointingToHistory;
        }
    }
}
