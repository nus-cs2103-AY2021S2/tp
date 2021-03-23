package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_SECOND_OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.logic.commands.AddOwnerCommand;
import dog.pawbook.logic.commands.DeleteDogCommand;
import dog.pawbook.logic.commands.DeleteOwnerCommand;
import dog.pawbook.logic.commands.ExitCommand;
import dog.pawbook.logic.commands.FindCommand;
import dog.pawbook.logic.commands.HelpCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.NameContainsKeywordsPredicate
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.DogUtil;

import dog.pawbook.testutil.OwnerBuilder;
import dog.pawbook.testutil.OwnerUtil;

public class PawbookParserTest {

    private final PawbookParser parser = new PawbookParser();

    @Test
    public void parseCommand_addOwner() throws Exception {
        Owner owner = new OwnerBuilder().build();
        AddOwnerCommand command = (AddOwnerCommand) parser.parseCommand(OwnerUtil.getAddCommand(owner));
        assertEquals(new AddOwnerCommand(owner), command);
    }

    @Test
    public void parseCommand_addDog() throws Exception {
        Dog dog = new DogBuilder().build();
        AddDogCommand command = (AddDogCommand) parser.parseCommand(DogUtil.getAddCommand(dog));
        assertEquals(new AddDogCommand(dog), command);
    }

    @Test
    public void parseCommand_deleteOwner() throws Exception {
        DeleteOwnerCommand command = (DeleteOwnerCommand) parser.parseCommand(
                DeleteOwnerCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD + " " + INDEX_FIRST_OWNER
                        .getOneBased());
        assertEquals(new DeleteOwnerCommand(INDEX_SECOND_OWNER), command);
    }

    @Test
    public void parseCommand_deleteDog() throws Exception {
        DeleteDogCommand command = (DeleteDogCommand) parser.parseCommand(
                DeleteDogCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD + " " + INDEX_FIRST_OWNER
                        .getOneBased());
        assertEquals(new DeleteDogCommand(INDEX_SECOND_OWNER), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
