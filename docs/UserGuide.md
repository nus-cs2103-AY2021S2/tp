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

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list students`** : Lists all contacts.
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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the records.

Format: `add n/NAME i/MATRICULATION_NUMBER p/PHONE_NUMBER e/EMAIL a/ADDRESS s/VACCINATION_STATUS 
r/SCHOOL_RESIDENCE[optional] m/MEDICAL_DETAILS`

>**NOTE:** The matriculation number of a student is a unique 9-character alphanumeric sequence that begins with A.

Examples:
* `add n/John Doe i/A1234567X p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated 
  r/RVRC m/peanut allergy`
* `add n/Betsy Crowe i/A7654321J p/91119222 e/betsycrowe@example.com a/212 Orchard Road, #18-08 s/not vaccinated 
  m/nose lift surgery in 2012`

### Listing all students in the records : `list`

Shows a list of all students in the records.

Format: `list students`

### Searching for a student by matriculation number: `search`

Shows all the details in the records of the student with the matching matriculation number.

Format: `search /MATRICULATION_NUMBER`

>**NOTE:** The matriculation number of a student is a unique 9-character alphanumeric sequence that begins with A.


Examples:
* `search A1234567X` returns `John Doe`

### Deleting a student by their matriculation number: `delete`

Deletes the specified person from the address book.

Format: `delete /MATRICULATION NUMBER`

* If the matriculation number does not exist in the records, a popup message will be displayed to inform users that 
  the matriculation number is not found

>**NOTE:** The matriculation number of a student is a unique 9-character alphanumeric sequence that begins with A.


Examples:
* `delete /A7654321J` deletes Betsy Crowe from the records.

### Clearing all student entries : `clear`

Clears all entries from the records.

Format: `clear`

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

**Action**  | **Format, Examples**
--------|-----------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/VACCINATION_STATUS r/SCHOOL_RESIDENCE[optional] m/MEDICAL_DETAILS ` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated r/RVRC m/peanut allergy`
**List Students** | `list students`
**Search** | `search /MATRICULATION_NUMBER ` <br> e.g., `search A1234567X`
**Delete** | `delete /MATRICULATION_NUMBER` <br> e.g., `delete A1234567X`
