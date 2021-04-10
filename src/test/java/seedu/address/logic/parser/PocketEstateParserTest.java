package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearPropertyCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListPropertyCommand;
import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.commands.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.logic.commands.SortPropertyCommand;
import seedu.address.logic.commands.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.AppointmentPredicateList;
import seedu.address.model.appointment.AppointmentRemarksPredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.AppointmentUtil;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.PropertyBuilder;
import seedu.address.testutil.PropertyUtil;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;
import seedu.address.testutil.SortPropertyDescriptorBuilder;

public class PocketEstateParserTest {

    private final PocketEstateParser parser = new PocketEstateParser();

    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        AddAppointmentCommand command =
                (AddAppointmentCommand) parser.parseCommand(AppointmentUtil.getAddAppointmentCommand(appointment));
        assertEquals(new AddAppointmentCommand(appointment), command);
    }

    @Test
    public void parseCommand_addProperty() throws Exception {
        Property property = new PropertyBuilder().build();
        AddPropertyCommand command =
                (AddPropertyCommand) parser.parseCommand(PropertyUtil.getAddPropertyCommand(property));
        assertEquals(new AddPropertyCommand(property), command);
    }

    @Test
    public void parseCommand_editAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand command =
                (EditAppointmentCommand) parser.parseCommand(EditAppointmentCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_APPOINTMENT.getOneBased() + " "
                        + AppointmentUtil.getEditAppointmentDescriptorDetails(descriptor));
        assertEquals(new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor), command);
    }

    @Test
    public void parseCommand_editProperty() throws Exception {
        Property property = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(property).build();
        EditPropertyCommand command =
                (EditPropertyCommand) parser.parseCommand(EditPropertyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PROPERTY.getOneBased() + " "
                + PropertyUtil.getEditPropertyDescriptorDetails(descriptor));
        assertEquals(new EditPropertyCommand(INDEX_FIRST_PROPERTY, descriptor), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_APPOINTMENT.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT), command);
    }

    @Test
    public void parseCommand_deleteProperty() throws Exception {
        DeletePropertyCommand command = (DeletePropertyCommand) parser.parseCommand(
                DeletePropertyCommand.COMMAND_WORD + " " + INDEX_FIRST_PROPERTY.getOneBased());
        assertEquals(new DeletePropertyCommand(INDEX_FIRST_PROPERTY), command);
    }

    @Test
    public void parseCommand_clearAppointment() throws Exception {
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD) instanceof ClearAppointmentCommand);
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD + " 3")
                instanceof ClearAppointmentCommand);
    }

    @Test
    public void parseCommand_clearProperty() throws Exception {
        assertTrue(parser.parseCommand(ClearPropertyCommand.COMMAND_WORD) instanceof ClearPropertyCommand);
        assertTrue(parser.parseCommand(ClearPropertyCommand.COMMAND_WORD + " 3")
                instanceof ClearPropertyCommand);
    }

    @Test
    public void parseCommand_clearAll() throws Exception {
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD) instanceof ClearAllCommand);
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD + " 3")
                instanceof ClearAllCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find_appointment() throws Exception {
        List<String> keywords = Arrays.asList("n/foo", "r/bar");
        FindAppointmentCommand command = (FindAppointmentCommand) parser.parseCommand(
                FindAppointmentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        FindAppointmentCommand newCommand = new FindAppointmentCommand(new AppointmentPredicateList(
                Arrays.asList(new AppointmentRemarksPredicate("bar")),
                                Arrays.asList(new AppointmentPredicateList(Collections.singletonList(
                                        new AppointmentContainsKeywordsPredicate(Collections.singletonList("foo")))))));
        assertEquals(newCommand, command);
    }

    @Test
    public void parseCommand_find_property() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPropertyCommand command = (FindPropertyCommand) parser.parseCommand(
                FindPropertyCommand.COMMAND_WORD + " "
                        + "n/"
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPropertyCommand(new PropertyPredicateList(
                Arrays.asList(new PropertyNamePredicate[]{
                    new PropertyNamePredicate(keywords)}))), command);
    }

    @Test
    public void parseCommand_find_client() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindClientCommand command = (FindClientCommand) parser.parseCommand(
                FindClientCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindClientCommand(new PropertyClientNamePredicate(keywords),
                new AppointmentContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_sortAppointment() throws Exception {
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder("asc")
                .withAppointmentSortingKey("datetime")
                .build();
        SortAppointmentCommand command = (SortAppointmentCommand) parser.parseCommand(
                SortAppointmentCommand.COMMAND_WORD + " "
                        + AppointmentUtil.getSortAppointmentDescriptorDetails(descriptor));
        assertEquals(new SortAppointmentCommand(descriptor), command);
    }

    @Test
    public void parseCommand_sortProperty() throws Exception {
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder("asc")
                .withPropertySortingKey("price")
                .build();
        SortPropertyCommand command = (SortPropertyCommand) parser.parseCommand(
                SortPropertyCommand.COMMAND_WORD + " "
                        + PropertyUtil.getSortPropertyDescriptorDetails(descriptor));
        assertEquals(new SortPropertyCommand(descriptor), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listAppointment() throws Exception {
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD + " 3") instanceof ListAppointmentCommand);
    }

    @Test
    public void parseCommand_listProperty() throws Exception {
        assertTrue(parser.parseCommand(ListPropertyCommand.COMMAND_WORD) instanceof ListPropertyCommand);
        assertTrue(parser.parseCommand(ListPropertyCommand.COMMAND_WORD + " 3") instanceof ListPropertyCommand);
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_invalidAdd() {
        assertThrows(ParseException.class,
                Messages.missingPropertyAppointmentError("add"), () -> parser.parseCommand("add"));
    }

    @Test
    public void parseCommand_invalidDelete() {
        assertThrows(ParseException.class,
                Messages.missingPropertyAppointmentError("delete"), () -> parser.parseCommand("delete"));
    }

    @Test
    public void parseCommand_invalidList() {
        assertThrows(ParseException.class,
                Messages.missingAllPropertyAppointmentError("list"), () -> parser.parseCommand("list"));
    }

    @Test
    public void parseCommand_invalidClear() {
        assertThrows(ParseException.class,
                Messages.missingAllPropertyAppointmentError("clear"), () -> parser.parseCommand("clear"));
    }

    @Test
    public void parseCommand_invalidFind() {
        assertThrows(ParseException.class,
                Messages.missingPropertyAppointmentError("find"), () -> parser.parseCommand("find"));
    }

    @Test
    public void parseCommand_invalidEdit() {
        assertThrows(ParseException.class,
                Messages.missingPropertyAppointmentError("edit"), () -> parser.parseCommand("edit"));
    }

    @Test
    public void parseCommand_invalidSort() {
        assertThrows(ParseException.class,
                Messages.missingPropertyAppointmentError("sort"), () -> parser.parseCommand("sort"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
