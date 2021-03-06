---
layout: page
title: User Guide
---

Focuris is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Focuris can get your contact management tasks done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `focuris.jar` from [link coming soon].

1. Copy the file to the folder you want to use as the _home folder_ for your Focuris.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all contacts.

   - **`add`**`-p n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the application.

   - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   - **`clear`** : Deletes all contacts.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -p n/NAME`, `NAME` is a parameter which can be used as `add -p n/John Doe`.

- Items in curly brackets are either-or, meaning you must include at least one of the options within the curly brackets.<br>
  e.g. in `add {-p n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ | -e n/NAME s/TIME_START e/TIME_END [d/DESCRIPTION]}`

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>

  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessageNew.png)

Format: `help`

### Adding a person or event to Focuris: `add`

Adds a new event or person to Focuris

Format: `add {-p n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ | -e n/NAME s/TIME_START e/TIME_END [d/DESCRIPTION]}`

- Adds a person if `-p` is used, otherwise, an event if `-e` is used
- For events, time start and time end should be specified in `DD-MM-YYYY HHMM` format

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

- `add -p n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add -p n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
- `add -e n/NUS Hackathon s/12-03-2021 1000 e/13-03-2021 1800`
- `add -e n/Complete Homework s/02-03-2021 1000 e/02-03-2021 1400 d/Complete weekly quiz and group tasks for CS2103T`

### Listing all persons : `list`

Shows a list of all persons in the Focuris Person List.

Format: `list`

### Editing a person : `edit`

Edits multiple items in Focuris.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the item at the specified `INDEX`. The index refers to the type of item, followed by index number shown in the respective displayed item list. The index **must be a positive integer** 1, 2, 3, …​
* The format of `INDEX` is `{P | E}[NUMBER]`, where Persons are prefixed with `P` and Events are prefixed with `E`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit P1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit P2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit E1 n/CS2030S` Edits the name of the 1st Event to `CS2030S`.

### Search using a matching keyword : `find`

Finds a class using a matching keyword in Focuris. Uses flags such as  -e, -p, -l, -t, -lab, -r

Format: `find {-e | -p | -l | -t | -lab | -r} KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* -e is the event flag. Attach it to the end of the command to search for an event

Examples:

* `find -e CS2101` returns the CS2101 event
* `find -p John` returns John's profile
* `find -l CS2101` returns the CS2101 lecture
* `find -t CS2101` returns the CS2101 tutorial
* `find -lab lsm1301` returns the CS2101 lab
* `find -r CS2030` returns the CS2030 recitation

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

### Deleting a person : `delete`

Deletes the specified person from the Focuris.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX` of person list (prefixed with `P`) or event list (prefixed with `E`).
* The index refers to the index number shown in the respective displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete P2` deletes the 2nd person in Focuris.
* `find CS2100` followed by `delete E1` deletes the 1st Event in the results of the `find` command.

### Changing the week : `week`

Changes the week shown and all events to the corresponding week.

Format: `week {WEEK_NUMBER | first | next | prev | last}`

- Shows events of the week at the specified `WEEK_NUMBER`.
- The `WEEK_NUMBER` **must be a positive integer** from 1, 2, 3, …​

Examples:

- `week 2` shows events of the second week of the year.
- `week next` shows the next week of events.
- `week prev` shows the previous week of events.
- `week first` shows the first week of events.
- `week last` shows the last week of events.

### Changing the year : `year`

Changes the year shown and all events to the corresponding year.

Format: `year {YEAR_NUMBER | next | prev}`

- The year entered **must be a 4 digit positive integer** ranging from 2021 onwards.

Examples:

- `year 2022` shows events of the year 2022.
- `year next` shows events of the next year.
- `year prev` shows events of the previous year.

### Clearing all entries : `clear`

Clears all entries from Focuris.

Format: `clear`

### Shortcut back to current week's task list: `today`

Changes your current view for task list back to current week and year.

Format: `today`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Focuris data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Focuris data are saved as a JSON file `[JAR file location]/data/focuris.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Focuris will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Focuris home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                 |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**    | `add {-p n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ \| -e n/NAME s/TIME_START e/TIME_END [d/DESCRIPTION]}`<br> e.g.,`add -p n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                                                                                          |
| **Delete** | `delete INDEX`<br> e.g., `delete E3`                                                                                                                                                                                              |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit P2 n/James Lee e/jameslee@example.com`                                                                                                      |
| **Find**   | `find {-e \| -p \| -l \| -t \| -lab \| -r} KEYWORD [MORE_KEYWORDS]`<br> e.g., `find -p James Jake`                                                                                                                                                                       |
| **List**   | `list`                                                                                                                                                                                                                           |
| **Help**   | `help`                                                                                                                                                                                                                           |
| **Today**  | `today`                                                                                                                                                                                                                          |
| **Week**   | `week {WEEK_NUMBER \| first \| next \| prev \| last}` <br> e.g., `week 2`                                                                                                                                                        |
| **Year**   | `year {YEAR_NUMBER \| next \| prev}` <br> e.g., `year 2022`                                                                                                                                                                      |
