---
layout: page
title: User Guide
---

Vax@NUS is a **one stop management app to efficiently track and and schedule COVID-19 vaccinations for NUS students.** It is a desktop app **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Vax@NUS can get your appointment management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `VaxAtNUS.jar` from [here](https://github.com/AY2021S2-CS2103T-W10-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Vax@NUS application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list students`** : Lists all students.
   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/SCHOOL RESIDENCE]` can be used as `n/John Doe r/RC4` or as `n/John Doe`.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
**NOTE: The following parameter formats must be followed:**
> The `MATRICULATION_NUMBER` of a student is a unique 9-character alphanumeric sequence that begins with A, followed by 
  > 7 numbers and ends with an alphabet. 

> The `FACULTY` of a student must be one of the following:
  > * FASS (for Faculty of Arts and Social Sciences)
  > * BIZ (for NUS Business School) 
  > * COM (for School of Computing)
  > * SCALE (for School of Continuing and Lifelong Education)
  > * DEN (for Faculty of Dentistry)
  > * SDE (for School of Design and Environment)
  > * DNUS (for Duke-NUS Medical School)
  > * ENG (for Faculty of Engineering)
  > * ISEP (for Integrative Sciences and Engineering)
  > * LAW (for Faculty of Law)
  > * MED (for Yong Loo Lin School of Medicine)
  > * MUSIC (for Yong Siew Toh Conservatory of Music)
  > * SPH (for Saw Swee Hock School of Public Health)
  > * SPP (for Lee Kuan Yew School of Public Policy)
  > * SCI (for Faculty of Science)
  > * USP (for University Scholars Programme)
  > * YNC (for Yale-NUS College)
  
> The `VACCINATION_STATUS` of a student must only be `vaccinated` or `not vaccinated`

> The `[SCHOOL_RESIDENCE]` of a student must be one of the following:
  > * PGPH (for Prince George's Park House)
  > * PGPR (for Prince George's Park Residences)
  > * KE7H (for King Edward VII Hall)
  > * SH (for Sheares Hall)
  > * KRH (for Kent Ridge Hall)
  > * TH (for Temasek Hall)
  > * EH (for Eusoff Hall)
  > * RH (for Raffles Hall)
  > * RVRC (for Ridge View Residential College)
  > * YNC (for Yale-NUS College)
  > * TC (for Tembusu College)
  > * CAPT (for College of Alice and Peter Tan)
  > * RC4 (for Residential College 4)
  > * USP (for University Scholars Programme)
  > * UTR (for Utown Residences)

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student entry: `add`

Adds a student to the records.

Format: `add n/NAME i/MATRICULATION_NUMBER f/FACULTY p/PHONE_NUMBER e/EMAIL a/ADDRESS s/VACCINATION_STATUS m/MEDICAL_DETAILS r/SCHOOL_RESIDENCE[optional]`


Examples:
* `add n/John Doe i/A1234567X f/COM p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated m/peanut allergy r/RVRC`
* `add n/Betsy Crowe f/ENG i/A7654321J p/91119222 e/betsycrowe@example.com a/212 Orchard Road, #18-08 s/not vaccinated m/nose lift surgery in 2012`

### Editing a student entry: `edit`

Edits a student in the records.

Format: `edit INDEX [n/NAME] [i/MATRICULATION_NUMBER] [f/FACULTY] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/VACCINATION_STATUS] [m/MEDICAL_DETAILS] [r/SCHOOL_RESIDENCE]`

* Edits the student at the specified INDEX. The index refers to the index number shown in the displayed student list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:

* `edit 1 p/91234567 f/MED` Edits the phone number and faculty of the first student to be 91234567 and MED respectively.

* `edit 2 r/KRH`  Edits the school residence of the second student to be KRH.


### Listing all students in the records : `list`

Shows a list of all students in the records.

Format: `list students`

### Finding a student by matriculation number: `find`

Shows only the details of the student that matches the specified matriculation number.

Format: `find MATRICULATION_NUMBER`


Examples:
* `Find A1234567X` returns `John Doe` from the record.

### Filtering the student entries by vaccination status, faculty or school residence : `filter`

Shows only the details of the student entries that matches the specified filter condition.

Format: <br>
`filter VACCINATION_STATUS`
`filter FACULTY`
`filter SCHOOL_RESIDENCE`

Examples:
* `filter VACCINATED` 
* `filter COM` 
* `filter RVRC` 


### Deleting a student by their matriculation number: `deleteStud`

Deletes the specified student from Vax@NUS' records.

Format: `deleteStud MATRICULATION NUMBER`

* If the matriculation number does not exist in the records, an error message will be displayed to inform users that 
  the matriculation number is not found.
  

Examples:
* `deleteStud A7654321J` deletes Betsy Crowe from the records.


### Adding an appointment: `addAppt`

Adds an appointment to Vax@NUS' records. 

Format: `addAppt i/MATRICULATION_NUMBER d/DATE_YYYY-MM-DD ts/START_TIME_HH:MM te/END_TIME_HH:MM`

* The student that the appointment is for must exist in the records before the appointment can be created.


Examples:
* `addAppt i/A1234567X d/2021-12-13 ts/13:00 te/14:00`
* `addAppt i/A7654321J d/2021-12-13 ts/14:00 te/14:30`


### Viewing statistics: `stats`

Displays the statistics of the requested Faculty/School Residence, the whole of NUS or statistics of all Faculties
and School Residences. 

Format: <br>
`stats FACULTY`
`stats SCHOOL_RESIDENCE`
`stats NUS`
`stats all`

* If there is no available data for the requested Faculty or School Residence, a message will be displayed to inform
  users that the requested Faculty or School Residence has no available data.
  

Examples:
* `stats COM` displays the percentage of vaccinated students in School of Computing.
* `stats RC4` displays the percentage of vaccinated students in RC4.
* `stats DOES_NOT_LIVE_ON_CAMPUS` displays the percentage of vaccinated students not living on campus.
* `stats NUS` displays the percentage of vaccinated students in NUS.
* `stats all` displays the list of percentages of vaccinated students in every Faculty and School Residence.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Vax@NUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Adding vaccination appointments for students `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What do I do if I accidentally deleted a student? 
<br>
**A**: We regret to inform you that deletion is permanent. You will have to manually re-add the student.

**Q**: What can be included in medical details?
<br>
**A**: Allergies and past medical history. 

**Q**: Will I be able to amend a student’s details?
<br>
**A**: Unfortunately, this feature is not available at the moment, but plans are in the works to make this a reality.

**Q** : Will I be able to add other types of appointments besides vaccination appointments?
<br>
**A** : No, the current version only allows you to add vaccination appointments and not any other type of appointments. 
 
--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME i/MATRICULATION_NUMBER f/FACULTY p/PHONE_NUMBER e/EMAIL a/ADDRESS s/VACCINATION_STATUS r/SCHOOL_RESIDENCE[optional] m/MEDICAL_DETAILS` <br> e.g., `add n/John Doe i/A1234567X f/COM p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated r/RVRC m/peanut allergy`
**Edit** | `edit INDEX [n/NAME] [i/MATRICULATION_NUMBER] [f/FACULTY] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/VACCINATION_STATUS] [m/MEDICAL_DETAILS] [r/SCHOOL_RESIDENCE]` <br> e.g., `edit 1 p/91234567 f/MED`
**List Students** | `list students`
**Find** | `find MATRICULATION_NUMBER` e.g., `find A1234567X`
**Filter** | `filter VACCINATION_STATUS`  e.g., `filter VACCINATED` <br> `filter FACULTY ` e.g., `filter COM` <br> `filter SCHOOL_RESIDENCE` e.g., `filter RVRC` 
**Delete Student** | `deleteStud MATRICULATION_NUMBER` e.g., `deleteStud A1234567X`
**View Statistics** | `stats FACULTY` e.g., `stats COM` <br> `stats SCHOOL_RESIDENCE` e.g., `stats RC4` <br> `stats NUS` <br> `stats all` 
