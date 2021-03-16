---
layout: page
title: User Guide
---

Link.me is a **desktop app aimed at insurance agents for managing clients, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Link.me can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `linkme.jar` from [here](https://github.com/AY2021S2-CS2103T-W12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for `Link.me`.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the app.

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
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the app.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GENDER b/BIRTHDATE [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`

* `BIRTH_DATE` and `CONTRACT_START_DATE` should be inputted in the specific datetime format yyyy-mm-dd


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/M b/1995-01-01 t/Investment start/2010-01-01 plan/Pro-Achiever premium/2400 claimed/0`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/91234567 t/Life g/F b/1998-02-03 plan/Guaranteed Protect Plus p$/1500`

### Listing all persons : `list`

Shows a list of all persons in the app.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the app.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* For other optional fields excluding tags, you may type `[FIELD_TYPE]/` to clear the field.


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 t/medical plan/Protecc Life premium/` Edits the insurance tag of the 2nd person to be `medical`, edits the plan name to `Protecc Life` and clears the insurance premium field.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the app.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the app.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the app.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Scheduling/Unscheduling a meetup with a client : `schedule`

#### Scheduling a meetup.
Schedule a date and time with a specified client in the app.

Format: `schedule INDEX m/DESCRIPTION @ DATE_TIME`

* Adds the client at the specified `INDEX`, and the `DATE_TIME` of the meeting, to the schedule list.
* The `INDEX` refers to the index number shown in the displayed person list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* `DATE_TIME` refers to the date and time of the scheduled meeting
* `DATE_TIME` should be inputted in the specific datetime format `yyyy-mm-dd HH:MM`

Example:

* `schedule 24601 m/Insurance Plan @ 2020-02-28 14:30` schedules a Insurance Plan meeting with client no.24601 
  at 2020/2/28 2:30 pm.

#### Unscheduling a meetup

Unscheduling meetups with a certain client.

Format: `schedule INDEX m/remove`

* Removes a scheduled meeting with the client at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed person list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​

Example:

* `schedule 24601 m/remove` removes meetings scheduled with client 24601.


### Filtering by tag : `filter`
Filters the contacts by tag.

Format: `filter TAG`

Lists all contacts that have a matching tag.

Only tags that are exactly the same will be matched.

Examples:

`filter medical` returns every contact with the tag `medical`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GENDER b/BIRTHDATE [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`
**Clear** | `clear`
**Delete** | `delete INDEX`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`
**Find** | `find KEYWORD [MORE_KEYWORDS]`
**Filter** | `filter TAG`
**List** | `list`
**Help** | `help`
**Schedule** | `schedule INDEX DATE_TIME`
