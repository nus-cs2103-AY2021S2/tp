package seedu.address.logic.commandlist;

/**
 * Class to hold the commands inputted by users. It is modelled as a linked list
 * because users will primarily use arrow keys to traverse through the commands
 * they have inputted.
 */
public class CommandList {
    private CommandNode headPointer = new CommandNode("");
    private CommandNode tailPointer = new CommandNode("");
    private CommandNode cursor = tailPointer;

    private int size = 0;

    /**
     * Adds a command into the list.
     * @param command Command to be added.
     */
    public void addCommand(String command) {
        CommandNode tempNode = new CommandNode(command);
        if (size == 0) {
            headPointer = tempNode;
            tempNode.nextNode = tailPointer;
            tailPointer.prevNode = tempNode;
        } else {
            CommandNode lastCommand = tailPointer.prevNode;
            lastCommand.nextNode = tempNode;
            tempNode.prevNode = lastCommand;

            tailPointer.prevNode = tempNode;
            tempNode.nextNode = tailPointer;
        }
        cursor = tailPointer;
        size++;
    }

    /**
     * Moves the pointer to previous node in the list and returns the command stored.
     * If the pointer is at the previous node at the time of calling, command stored
     * in the first node is returned.
     * @return Command stored in the previous node, or command stored in the head node
     * if there is no previous node.
     */
    public String moveDown() {
        if (cursor.nextNode != tailPointer && cursor.nextNode != null) {
            cursor = cursor.nextNode;
        }
        return cursor.command;
    }

    /**
     * Moves the pointer to next node in the list and returns the command stored.
     * If the pointer is at the last node at the time of calling, the command
     * stored in the last node is returned.
     * @return Command stored in the next node, or command stored in the tail node if the
     * pointer is at the last node.
     */
    public String moveUp() {
        if (cursor.prevNode != null) {
            cursor = cursor.prevNode;
        }
        return cursor.command;
    }

    // The following methods are solely for testing purposes.

    /**
     * Returns a string representation of the list.
     * @return A string representation of the list.
     */
    public String toString() {
        CommandNode printingCursor = headPointer;
        StringBuilder result = new StringBuilder("[");
        while (printingCursor != tailPointer) {
            result.append(printingCursor.command);
            result.append(", ");
            printingCursor = printingCursor.nextNode;
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        result.append("]");
        return result.toString();
    }

    /**
     * Returns the size of the list.
     * @return The size of the list.
     */
    public int getSize() {
        return this.size;
    }


    private static class CommandNode {
        private final String command;
        private CommandNode prevNode;
        private CommandNode nextNode;

        public CommandNode(String command) {
            this.command = command;
            this.prevNode = null;
            this.nextNode = null;
        }
    }

}
