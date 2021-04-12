package seedu.address.logic.commands.doctor;

import static seedu.address.commons.core.Messages.MESSAGE_ADD_DOCTOR_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_DUPLICATE_DOCTOR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.DoctorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddDoctorCommand}.
 */
public class AddDoctorCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());
    }

    @Test
    public void execute_newDoctor_success() {
        Doctor validDoctor = new DoctorBuilder().build();

        Model expectedModel = new ModelManager(model.getPatientRecords(), model.getDoctorRecords(),
                model.getAppointmentSchedule(), model.getUserPrefs());

        expectedModel.addDoctor(validDoctor);

        assertCommandSuccess(new AddDoctorCommand(validDoctor), model,
                String.format(MESSAGE_ADD_DOCTOR_SUCCESS, validDoctor), expectedModel);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor doctorInList = model.getDoctorRecords().getPersonList().get(0);

        assertCommandFailure(new AddDoctorCommand(doctorInList), model, MESSAGE_ADD_DUPLICATE_DOCTOR);
    }

}
