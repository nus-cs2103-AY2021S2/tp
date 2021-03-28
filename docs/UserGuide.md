---
layout: page
title: User Guide
---

Link.me is a **desktop app aimed at insurance agents for managing clients, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Link.me can get your contact management tasks done faster than traditional GUI apps.

---

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
## Overview of Features
The features of Link.me mainly revolve around adding and editing clients as Link.me is first and foremost a client
managing app. 

Features of Link.me including adding, editing and deleting clients, as well as methods to quickly find
clients through finding and filtering. Link.me also supports peripheral features such as meeting scheduling, client
remarking and notifying the user to important upcoming events. 

See [Features](#features) below for details on how to use each command.

### View help

You can view a message explaining how to access the help page.

### List all clients
You can list all the clients currently stored in Link.me.

### Add a client

You can add a client to Link.me, by specifying each of the fields below:
* Name
* Phone number
* Email
* Address
* Gender
* Birthdate
* Tags (optional)

### Edit a client

You can edit the information of existing clients.

### Search for clients by name

You can find clients whose name matches the given keywords.

### Delete clients

You can delete clients from Link.me.

### Schedule and remove meetings

You can schedule and remove meetings with clients.

### Filter clients

You can filter clients by their address, gender, age, tags or insurance plan name.

### Display notifications

You can open a notification window which informs you of upcoming meetings and client birthdays.

### Record, clear and view notes

You can record, clear and view notes for each client in Link.me.

### Clear all clients

You can clear all clients from Link.me. 

### Exit program

You can exit Link.me.



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

* The commands of Link.me generally follow the following format:
  `COMMAND [PERSON_INDEX] [PREFIX/] [DESCRIPTION]`

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Listing all persons : `list`

Shows a list of all persons in the app.

Format: `list`


### Adding a person: `add`

Adds a person to the app.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GENDER b/BIRTHDATE [t/TAG]...`

* `BIRTHDATE` should be given in the format yyyy-mm-dd


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/M b/1995-01-01 t/investment`
* `add n/Betsy Crowe t/medical e/betsycrowe@example.com a/Newgate Prison p/91234567 t/life g/F b/1998-02-03`



### Editing a person : `edit`

Edits an existing person in the app.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [t/TAG]...`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 5 t/medical b/1990-01-01` edits the insurance tag of the 5th person to be `medical` and edits the birthdate to be `1990-01-01`.

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


### Scheduling/Unscheduling a meetup with a client : `schedule`

#### Scheduling a meetup.
Schedule a date and time with a specified client in the app.

New schedule commands overwrite the original meeting scheduled with a client.

Format: `schedule INDEX m/DESCRIPTION @ DATE_TIME`

* Adds the client at the specified `INDEX`, and the `DATE_TIME` of the meeting, to the schedule list.
* The `INDEX` refers to the index number shown in the displayed person list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* `DATE_TIME` refers to the date and time of the scheduled meeting
* `DATE_TIME` should be inputted in the specific datetime format `yyyy-mm-dd HH:MM`

Example:

* `schedule 2 m/Insurance Plan @ 2020-02-28 14:30` schedules a Insurance Plan meeting with the client indexed 2 on the 
  display at 2020/2/28 2:30 pm.

#### Unscheduling a meetup

Unscheduling meetups with a certain client.

Format: `schedule INDEX m/remove`

* Removes a scheduled meeting with the client at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed person list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​

Example:

* `schedule 2 m/remove` removes meeting scheduled with the client indexed 2 on the display.


### Filtering by tag : `filter`
Filters the contacts by tag.

Format: `filter TAG`

Lists all contacts that have a matching tag.

Only tags that are exactly the same will be matched.

Examples:

`filter medical` returns every contact with the tag `medical`

### Calling notifications of recent events : `notif`

Generates a list of notifications.

Lists all client birthdays within the next two weeks then lists all meetings occurring today in order of time.

The notification window is also shown on startup of the application.

![notif message](images/notifMessage.png)

Format: `notif`

### Recording, clearing and viewing notes : `note`

#### Recording a note

Records a note for a specified client.

Format: `note INDEX r/NOTE`

* Adds the provided `NOTE` to the client specified at `INDEX`.

Example:

* `note 3 r/Wants to upgrade insurance coverage` adds the note "Wants to upgrade insurance coverage" to the 3rd client.

#### Clearing notes

Clears all existing notes from a specified client.

Format: `note INDEX c/`

* Clears all notes from the client specified at `INDEX`.

Example:

* `note 4 c/` clears all notes from the 4th client.

#### Viewing notes

View all existing notes from a specified client. Notes will be displayed in a popup box, with each note listed as a bullet point.

Format: `note INDEX v/`

* View notes from the client specified at `INDEX`.

Example:

* `note 4 v/` generates a popup box displaying the notes taken for the 4th client.


### Clearing all entries : `clear`

Clears all entries from the app.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`


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

Action      | Format, Examples
------------|------------------
**Add**     | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GENDER b/BIRTHDATE [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`
**Clear**   | `clear`
**Delete**  | `delete INDEX`
**Edit**    | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GENDER] [b/BIRTHDATE] [t/TAG] [start/CONTRACT_START_DATE] [plan/INSURANCE_PLAN] [premium/INSURANCE_PREMIUM] [claimed/AMOUNT_CLAIMED_TO_DATE]`
**Find**    | `find KEYWORD [MORE_KEYWORDS]`
**Filter**  | `filter TAG`
**List**    | `list`
**Help**    | `help`
**Notif**   | `notif`
**Schedule**| `schedule INDEX DATE_TIME`
**Note**    | `note INDEX r/NOTE', `note INDEX c/', `note INDEX v/'
