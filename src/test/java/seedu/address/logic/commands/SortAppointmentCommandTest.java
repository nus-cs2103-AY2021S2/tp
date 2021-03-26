package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ASC_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_APPOINTMENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_ASC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortAppointmentCommand;
import seedu.address.logic.commands.sort.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;


public class SortAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void execute_sortByDatetimeInAscendingOrder_success() {
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME)
                .build();
        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand(descriptor);

        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

        Comparator<Appointment> comparator = (o1, o2) -> {
            if (o1.getDate().compareTo(o2.getDate()) == 0) {
                return o1.getTime().compareTo(o2.getTime());
            } else {
                return o1.getDate().compareTo(o2.getDate());
            }
        };

        expectedModel.sortAppointmentList(comparator);

        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByDatetimeInDescendingOrder_success() {
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME)
                .build();
        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand(descriptor);

        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

        Comparator<Appointment> comparator = (o1, o2) -> {
            if (o1.getDate().compareTo(o2.getDate()) == 0) {
                return -1 * o1.getTime().compareTo(o2.getTime());
            } else {
                return -1 * o1.getDate().compareTo(o2.getDate());
            }
        };

        expectedModel.sortAppointmentList(comparator);

        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByNameInAscendingOrder_success() {
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_NAME)
                .build();
        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand(descriptor);

        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

        Comparator<Appointment> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

        expectedModel.sortAppointmentList(comparator);

        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByNameInDescendingOrder_success() {
        SortAppointmentDescriptor descriptor = new SortAppointmentDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_NAME)
                .build();
        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand(descriptor);

        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

        Comparator<Appointment> comparator = (o1, o2) -> -1 * o1.getName().compareTo(o2.getName());

        expectedModel.sortAppointmentList(comparator);

        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same values -> returns true
        SortAppointmentDescriptor descriptorWithSameValues = new SortAppointmentDescriptor(ASC_DATETIME);
        assertTrue(ASC_DATETIME.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ASC_DATETIME.equals(ASC_DATETIME));

        // null -> returns false
        assertFalse(ASC_DATETIME.equals(null));

        // different types -> returns false
        assertFalse(ASC_DATETIME.equals(5));

        // different values -> returns false
        assertFalse(ASC_DATETIME.equals(DESC_APPOINTMENT_NAME));

        // different sorting orders -> returns false
        SortAppointmentDescriptor editedDescriptor = new SortAppointmentDescriptorBuilder(ASC_DATETIME)
                .withSortingOrder(VALID_SORTING_ORDER_DESC).build();
        assertFalse(ASC_DATETIME.equals(editedDescriptor));

        // different sorting keys -> returns false
        editedDescriptor = new SortAppointmentDescriptorBuilder(ASC_DATETIME)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_NAME).build();
        assertFalse(ASC_DATETIME.equals(editedDescriptor));
    }
}
