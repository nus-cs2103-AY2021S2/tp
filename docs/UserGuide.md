---
layout: page
title: User Guide
---

App-Ointment is a desktop app for for managing and scheduling patient appointments, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, App-Ointment can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `App-Ointment.jar` from [here](https://github.com/AY2021S2-CS2103-W17-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your App-Ointment App.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : [Coming Soon]

   * **`add-patient`** `n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a patient.

   * **`delete`** `3` : [Coming Soon]

   * **`clear`** : [Coming Soon]

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### *Patient Commands*:

### Adding a patient: `add-patient`

Adds a patient to the patient records.<br>

Format: `add-patient n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: <b>Tip:</b>

* The patient can have any number of tags (including 0).<br>

</div><br>

Examples:

* `add-patient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Listing all patients : `list-patient`

Changes the displayed patient records to show all patients in the patient records.<br>

Format: `list-patient`


### Editing a patient : `edit-patient`
[Coming Soon]

Edits an existing patient in the schedule.<br>

Format: `edit-patient INDEX [n/PATIENT] [dr/DOCTOR] [d/DATETIME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified INDEX. The index refers to the index number shown in the displayed appointment schedule list. The index must be a <strong>positive integer</strong> 1, 2, 3, …​<br>

* At least one of the optional fields must be provided.<br>

* Existing values will be updated to the input values.<br>

* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.<br>

* You can remove all the patient’s tags by typing t/ without specifying any tags after it.<br>

* Raises an exception if there are conflicts in the new schedule for the patient and the doctor.<br>

Examples:

* `edit-patient 1 dr/Who d/2021-01-01 1200` Edits the assigned doctor and appointment datetime under the 1st appointment to dr.Who and 01 Jan 2021 12pm respectively.

* `edit-patient 2 n/Betsy Crower t/` Edits the name of patient under the 2nd appointment to be Betsy Crower and clears all existing tags.

### Cleaning all entries in patient records: `clear-patient`
Clears all entries from the patient records. <br>

Format: `clear-patient`

### *Appointment Commands*:

### Adding an appointment: `add-appt`

Adds an appointment to the schedule.<br>

Format: `add-appt pt/PATIENT_INDEX dr/DOCTOR at/TIMESLOT_START [to/TIMESLOT_END] [dur/TIMESLOT_DURATION] [t/TAG]…​`

* The `PATIENT_INDEX` corresponds to the patient at the index number in the current displayed patient records.<br>

* The `PATIENT_INDEX` must be a <strong>positive integer</strong> 1, 2, 3, …​<br>

* The `TIMESLOT_START` and `TIMESLOT_END` must be in the format YYYY-MM-DD HH:mm<br>

* Either and only one, `TIMESLOT_END` or `TIMESLOT_DURATION`, must be provided.<br>

* Raises an exception if there are conflicts in schedule for the patient or the doctor.<br> 

<div markdown="span" class="alert alert-primary">:bulb: <b>Tip:</b>

* The appointment can have any number of tags (including 0). It is recommended to use the tags to define the purpose of the appointment.<br>

</div><br>

Examples:

* `add-appt pt/1 dr/Dr. Grey at/2021-01-01 00:00 to/2021-01-01 01:30 t/severe t/brainDamage`

* `add-appt pt/2 dr/Dr. Who at/2021-01-01 00:00 dur/1H 30M t/exhaustion`

### Listing all appointments : `list-appt`

Changes the displayed appointment list to show all appointments in the appointment schedule.<br>

Format: `list-appt`

### Editing an appointment : `edit-appt`
[Coming Soon]

Edits an existing appointment in the appointment schedule.<br>

Format: `edit-appt [pt/PATIENTINDEX] [dr/DOCTOR] [at/TIMESLOT START] [to/TIMESLOT END] [dur/TIMESLOT DURATION] [t/TAG]…​`

* Edits the appointment for the patient specified by the `PATIENTINDEX`.  The `PATIENTINDEX` refers to the index number shown in the displayed patient list. The index must be a <strong>positive integer</strong> 1, 2, 3, …​<br>

* The `PATIENTINDEX` and at least one of the rest optional fields must be provided <br>

* Existing values will be updated to the input values.<br>

* When editing tags, the existing tags of the appointment will be removed i.e adding of tags is not cumulative.<br>

* You can remove all the person’s tags by typing t/ without specifying any tags after it.<br>

* Raises an exception if there are conflicts in the new appointment with the existing appointments.<br>

Examples:

* `edit-appt pt/1 dr/Dr.Chong at/2021-05-08 09:00 dur/1H 00M t/severe t/fever` Edits the assigned doctor, appointment datetime and duration, and tags, for patient with index 1 to Dr.Chong, starting at 2021-05-08 09:00 for 1h 00m, and severe & fever respectively.

* `edit-appt pt/2 dr/Dr.Phon t/` Edits the assigned doctor for patient with index 2 to Dr.Phon, and all the tags for the patient is removed.


### Locating appointments by fields : `find-patient`
[Coming Soon]

Format: `find [n/PATIENT KEYWORDS] [dr/DOCTOR_KEYWORDS] [d/DATETIME] [p/PHONE] [e/EMAIL] [a/ADDRESS_KEYWORDS] [t/TAG KEYWORDS]`

* At least one of the optional fields must be provided.<br>

* The search is case-insensitive. e.g `hans` will match `Hans`<br>

* Only full words will be matched. e.g. `han` will not match `Hans`<br>

* Search fields require at least one keyword to be matched in the field description for the search condition of that field to be satisfied. e.g. `find n/Hans Bo` will match both patients `Hans Gruber` and `Bo Young`.

* Certain fields such as datetime, phone number and email do not support a search by keywords and require a match with the entire field description for the search condition to be satisfied.

* Where multiple search fields are specified, the search is conditioned on the satisfaction of <strong>all</strong> of the search fields' subconditions. e.g. `find n/Hans Bo dr/Grey` will match appointments that satisfy both:
  - Grey in the assigned doctor's name; and
  - Either Hans or Bo in the patient's name.

Examples:

* `find n/john alex` returns appointments with patients `john`, `John`, `John Doe`, `alex`, `Alex` and `Alex Anderson`.

* `find dr/Grey Who t/brain surgery` returns appointments with doctors `grey` or `who` and are tagged as `brain surgery`.

### Cleaning all entries in appointment schedule: `clear-appt`
Clears all entries from the appointment schedule.<br>

Format: `clear-patient`

### Deleting an appointment : `delete`
[Coming Soon]

Deletes the specified appointment from the schedule.

Format: `delete INDEX`

* Deletes the appointment at the specified INDEX.

* The index refers to the index number shown in the displayed appointment list.

* The index must be a <strong>positive integer</strong> 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd appointment in the entire appointment schedule.

* `find Betsy` followed by `delete 1 ` deletes the 1st appointment in the results of the `find` command.



### *Overall Commands*

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Viewing help: `help`
[Coming soon]

Format: `help`

Showing a message containing the url to the User Guide page

### Saving the data

App-Ointment data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

App-Ointment data are saved as a JSON file `[JAR file location]/data/PatientAddressBook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: <b>Caution:</b>
If your changes to the data file makes its format invalid, App-Ointment will discard all data and start with an empty data file at the next run.
</div><br>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous App-Ointment home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
[Coming Soon]
Action | Format, Examples
--------|------------------
**Add** | `add n/PATIENT dr/DOCTOR d/DATETIME [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br>e.g., `add n/John Doe dr/Grey d/2021-01-01 1200 t/brain surgery p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
**Delete** | `delete INDEX`<br>e.g., `delete 2`
**edit-patient** | `edit-patient INDEX [n/PATIENT] [dr/DOCTOR] [d/DATETIME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br>e.g., `edit-patient 1 dr/Who d/2021-01-01 1200`
**edit-appt** | `edit-appt [pt/PATIENTINDEX] [dr/DOCTOR] [at/TIMESLOT START] [at/TIMESLOT END] [at/TIMESLOT DURATION] [t/TAG]…​`<br>e.g., `edit-appt pt/1 dr/Dr.Chong at/2021-05-08 09:00 dur/1H 00M t/severe t/fever`
**List** | `list`
**exit** | [Coming soon]
**Find** | [Coming soon]
**find** | [Coming Soon]
**help** | [Coming Soon]
**clear** | [Coming Soon]
