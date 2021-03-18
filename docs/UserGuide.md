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

### Adding a ToDo event: `todo`

Adds an event with status TODO to Focuris

Format: `todo {n/NAME d/DESCRIPTION}`

Examples:

- `todo n/Household Chores d/Cleaning the kitchen`
- `todo n/CS2030 d/Assignment`
- `todo n/Lunch with John d/At VivoCity`
- `todo n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Adding a Backlog event: `log`

Adds an event with status BACKLOG to Focuris

Format: `log {n/NAME d/DESCRIPTION}`

Examples:

- `log n/Household Chores d/Cleaning the kitchen`
- `log n/CS2030 d/Assignment`
- `log n/Lunch with John d/At VivoCity`
- `log n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Adding an in-progress event: `prog`

Adds an event with status IN_PROGRESS to Focuris

Format: `prog {n/NAME d/DESCRIPTION}`

Examples:

- `prog n/Household Chores d/Cleaning the kitchen`
- `prog n/CS2030 d/Assignment`
- `prog n/Lunch with John d/At VivoCity`
- `prog n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Editing an event : `edit`

Edits an existing event in Focuris.

Format: `edit INDEX [n/NAME] [d/DESCRIPTION] [s/STATUS]`

* Edits the event at the specified `INDEX`. The index refers to the index number shown in the respective displayed event list. The index **must be a positive integer** 1, 2, 3, …​
* The index refers to the index number shown in the respective displayed list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.


Examples:
*  `edit 1 n/CS2030 d/Assignment` Edits the event name and event description of the 1st event to be `CS2030` and `Assignment` respectively.
*  `edit 2 s/log ` Edits the status of the 2nd event to be `BACKLOG`

### Listing all events : `list`
Shows a list of all events in Focuris.

Format: `list`

### Changing the status of an event to Done  : `done`
Changes the specified event status to DONE in Focuris.

Format: `done INDEX`

* Changes the event status at the specified `INDEX` to DONE. 
* The index refers to the index number shown in the displayed event list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `done 2` changes the status of the 2nd event to DONE in Focuris.
* `find CS2100` followed by `done 1` changes the status of the 1st event to DONE in the results of the `find` command.

### Search using a matching keyword : `find`

Finds events whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `cs2040` will match `CS2040`
* The order of the keywords does not matter. e.g. `Household Chores` will match `Chores Household`
* Only the name is searched.
* Only full words will be matched e.g. `Chore` will not match `Chore`
* Events matching at least one keyword will be returned (i.e. OR search). e.g. `Household` will return `Household Tidy`, `Household Clean`

Examples:

* `find CS2103` returns `CS2103` and `CS2103T`
* `find assignment` returns `CS2101 assignment`, `CS2103 assignment`

### Deleting an event : `delete`

Deletes the specified event from Focuris.

Format: `delete INDEX`

* Deletes the event at the specified `INDEX`
* The index refers to the index number shown in the displayed event list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd event in Focuris.
* `find CS2100` followed by `delete 1` deletes the 1st event in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from Focuris.

Format: `clear`

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
| **Todo**   | `todo n/NAME d/DESCRIPTION` <br> e.g., `todo n/CS2040 d/Assignment`                                                                                                                                                              |
| **Log**    | `log n/NAME d/DESCRIPTION` <br> e.g., `log n/CS2030 d/Lab`                                                                                                                                                                       |
| **Prog**   | `prog n/NAME d/DESCRIPTION` <br> e.g., `prog n/CS2100 d/Tutorial`                                                                                                                                                                |
| **Done**   | `done INDEX` <br> e.g., `done 2`                                                                                                                                                                                                 |
| **Clear**  | `clear`                                                                                                                                                                                                                          |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                              |
| **Edit**   | `edit INDEX [n/NAME] [s/STATUS] [d/DESCRIPTION]`<br> e.g.,`edit 2 n/CS2030 d/Assignment`                                                                                                                                         |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                       |
| **List**   | `list`                                                                                                                                                                                                                           |
| **Help**   | `help`                                                                                                                                                                                                                           |
