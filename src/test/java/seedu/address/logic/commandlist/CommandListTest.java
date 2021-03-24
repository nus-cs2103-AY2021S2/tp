package seedu.address.logic.commandlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandListTest {

    CommandList commandList;

    @BeforeEach
    public void clear() {
        commandList = new CommandList();
    }

    @Test
    public void addCommand_allArgs_success() {
        commandList.addCommand("abc");
        commandList.addCommand("def");
        commandList.addCommand("");
        commandList.addCommand("ghi");

        assertEquals("[abc, def, , ghi]", commandList.toString());
    }

    @Test
    public void moveUp_nonEmptyList_success() {
        commandList.addCommand("abc");
        commandList.addCommand("def");
        commandList.addCommand("ghi");


        assertEquals("def", commandList.moveUp());
        assertEquals("abc", commandList.moveUp());
        assertEquals("abc", commandList.moveUp());
        assertEquals("abc", commandList.moveUp());
    }

    @Test
    public void moveDown_nonEmptyList_success() {
        commandList.addCommand("abc");
        commandList.addCommand("def");
        commandList.addCommand("ghi");

        for (int i = 0; i < commandList.getSize(); i++) {
            commandList.moveUp();
        }

        assertEquals("def", commandList.moveDown());
        assertEquals("ghi", commandList.moveDown());
        assertEquals("ghi", commandList.moveDown());
        assertEquals("ghi", commandList.moveDown());
    }

    @Test
    public void moveUp_emptyList_success() {
        assertEquals("", commandList.moveUp());
    }

    @Test
    public void moveDown_emptyList_success() {
        assertEquals("", commandList.moveDown());
    }
}
