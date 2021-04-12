package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_ENTITY;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.logic.commands.AddOwnerCommand;
import dog.pawbook.logic.commands.AddProgramCommand;
import dog.pawbook.logic.commands.DeleteDogCommand;
import dog.pawbook.logic.commands.DeleteOwnerCommand;
import dog.pawbook.logic.commands.DeleteProgramCommand;
import dog.pawbook.logic.commands.EditDogCommand;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.logic.commands.EditOwnerCommand;
import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.logic.commands.EditProgramCommand;
import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.logic.commands.ExitCommand;
import dog.pawbook.logic.commands.FindCommand;
import dog.pawbook.logic.commands.HelpCommand;
import dog.pawbook.logic.commands.ListCommand;
import dog.pawbook.logic.commands.ViewCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.NameContainsKeywordsPredicate;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.DogUtil;
import dog.pawbook.testutil.EditDogDescriptorBuilder;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import dog.pawbook.testutil.OwnerUtil;
import dog.pawbook.testutil.ProgramBuilder;
import dog.pawbook.testutil.ProgramUtil;

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
    public void parseCommand_addProgram() throws Exception {
        Program program = new ProgramBuilder().build();
        AddProgramCommand command = (AddProgramCommand) parser.parseCommand(ProgramUtil.getAddCommand(program));
        assertEquals(new AddProgramCommand(program), command);
    }

    @Test
    public void parseCommand_deleteOwner() throws Exception {
        DeleteOwnerCommand command = (DeleteOwnerCommand) parser.parseCommand(
                DeleteOwnerCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD + " " + ID_ONE);
        assertEquals(new DeleteOwnerCommand(ID_ONE), command);
    }

    @Test
    public void parseCommand_deleteDog() throws Exception {
        DeleteDogCommand command = (DeleteDogCommand) parser.parseCommand(
                DeleteDogCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD + " " + ID_ONE);
        assertEquals(new DeleteDogCommand(ID_ONE), command);
    }

    @Test
    public void parseCommand_deleteProgram() throws Exception {
        DeleteProgramCommand command = (DeleteProgramCommand) parser.parseCommand(
                DeleteProgramCommand.COMMAND_WORD + " " + Program.ENTITY_WORD + " " + ID_ONE);
        assertEquals(new DeleteProgramCommand(ID_ONE), command);
    }

    @Test
    public void parseCommand_editOwner() throws Exception {
        Owner owner = new OwnerBuilder().build();
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(owner).build();
        EditOwnerCommand command = (EditOwnerCommand) parser.parseCommand(EditOwnerCommand.COMMAND_WORD + " "
                + Owner.ENTITY_WORD + " " + ID_ONE + " " + OwnerUtil.getEditOwnerDescriptorDetails(descriptor));
        assertEquals(new EditOwnerCommand(ID_ONE, descriptor), command);
    }

    @Test
    public void parseCommand_editDog() throws Exception {
        Dog dog = new DogBuilder().build();
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder(dog).build();
        EditDogCommand command = (EditDogCommand) parser.parseCommand(EditDogCommand.COMMAND_WORD + " "
                + Dog.ENTITY_WORD + " " + ID_ONE + " " + DogUtil.getEditDogDescriptorDetails(descriptor));
        assertEquals(new EditDogCommand(ID_ONE, descriptor), command);
    }

    @Test
    public void parseCommand_editProgram() throws Exception {
        Program program = new ProgramBuilder().build();
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(program).build();
        EditProgramCommand command = (EditProgramCommand) parser.parseCommand(EditProgramCommand.COMMAND_WORD + " "
                + Program.ENTITY_WORD + " " + ID_ONE + " " + ProgramUtil.getEditProgramDescriptorDetails(descriptor));
        assertEquals(new EditProgramCommand(ID_ONE, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_ENTITY, () -> parser.parseCommand(ListCommand.COMMAND_WORD));
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Program.ENTITY_WORD) instanceof ListCommand);

        // plural works too
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD + "s")
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD + "s")
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Program.ENTITY_WORD + "s")
                instanceof ListCommand);

        // and so does many trailing s, it's a hidden feature
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD + "sssss")
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD + "sssss")
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " " + Program.ENTITY_WORD + "sssss")
                instanceof ListCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), (
                ) -> parser.parseCommand(ViewCommand.COMMAND_WORD));

        assertEquals(parser.parseCommand(ViewCommand.COMMAND_WORD + " " + ID_ONE), new ViewCommand(ID_ONE));
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
