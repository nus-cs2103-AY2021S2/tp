---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

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
m/MEDICAL_DETAILS r/SCHOOL_RESIDENCE[optional]`

>**NOTE:** The matriculation number of a student is a unique 9-character alphanumeric sequence that begins with A.

Examples:
* `add n/John Doe i/A1234567X p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated m/peanut allergy r/RVRC `
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

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME i/MATRICULATION_NUMBER p/PHONE_NUMBER e/EMAIL a/ADDRESS s/VACCINATION_STATUS r/SCHOOL_RESIDENCE[optional] m/MEDICAL_DETAILS` <br> e.g., ` add n/John Doe i/A1234567X p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/vaccinated r/RVRC m/peanut allergy`
**List Students** | `list students`
**Search** | `search /MATRICULATION_NUMBER ` <br> e.g., `search A1234567X`
**Delete** | `delete /MATRICULATION_NUMBER` <br> e.g., `delete A1234567X`
**Clear** | `clear`

