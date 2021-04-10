package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;
import seedu.address.model.garment.Garment;
import seedu.address.testutil.EditGarmentDescriptorBuilder;
import seedu.address.testutil.GarmentBuilder;
import seedu.address.testutil.GarmentUtil;

public class WardrobeParserTest {

    private final WardrobeParser parser = new WardrobeParser();

    @Test
    public void parseCommand_add() throws Exception {
        Garment garment = new GarmentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(GarmentUtil.getAddCommand(garment));
        assertEquals(new AddCommand(garment), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_GARMENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_GARMENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Garment garment = new GarmentBuilder().build();
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder(garment).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_GARMENT.getOneBased() + " " + GarmentUtil.getEditGarmentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_GARMENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findSize() throws Exception {
        String args = " s/23 14 53";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_findColour() throws Exception {
        String args = " c/black blue red";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_findDressCode() throws Exception {
        String args = " r/active casual formal";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_findDescription() throws Exception {
        String args = " d/foo bar baz";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_findType() throws Exception {
        String args = " t/upper lower footwear";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_findMultipleAttributes() throws Exception {
        String args = " s/23 14 c/red blue d/foo t/upper";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + args);
        assertEquals(new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
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
