---
layout: page
title: User Guide
---

Focuris is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Focuris can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
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

   - **`todo`**`n/CS2030 d/Assignment` : Adds an event named `CS2030` to the application with status `TODO`.

   - **`log`**`n/CS2040 d/Tutorial` : Adds an event named `CS2040` to the application with status `BACKLOG`.

   - **`prog`**`n/CS2100 d/Lab` : Adds an event named `CS2100` to the application with status `IN PROGRESS`.
     
   - **`delete`**`3` : Deletes the event with the identifier of 3 shown in the list.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo n/NAME d/DESCRIPTION`, `NAME` is a parameter which can be used as `todo n/CS2030`.

- Items in square brackets are optional.<br>
  e.g `n/NAME d/DESCRIPTION [p/PRIORITY]` can be used as `n/CS2030 d/Assignement p/HIGH` or as `n/CS2030 d/Assignment`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME d/DESCRIPTION`, `d/DESCRIPTION n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/walk d/run`, only `d/run` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessageNew.png)

Format: `help`

### Adding a todo event: `todo`

Adds an event with status TODO to Focuris

Format: `todo n/NAME d/DESCRIPTION [p/PRIORITY]`

Examples:

- `todo n/Household Chores d/Cleaning the kitchen p/HIGH`
- `todo n/CS2030 d/Assignment p/LOW`
- `todo n/Lunch with John d/At VivoCity p/MEDIUM`
- `todo n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Adding a backlog event: `log`

Adds an event with status BACKLOG to Focuris

Format: `log n/NAME d/DESCRIPTION [p/PRIORITY]`

Examples:

- `log n/Household Chores d/Cleaning the kitchen p/HIGH`
- `log n/CS2030 d/Assignment p/MEDIUM`
- `log n/Lunch with John d/At VivoCity`
- `log n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Adding an in-progress event: `prog`

Adds an event with status IN_PROGRESS to Focuris

Format: `prog n/NAME d/DESCRIPTION [p/PRIORITY]`

Examples:

- `prog n/Household Chores d/Cleaning the kitchen`
- `prog n/CS2030 d/Assignment p/LOW`
- `prog n/Lunch with John d/At VivoCity`
- `prog n/Complete Homework d/Complete weekly quiz and group tasks for CS2103T`

### Editing an event : `edit`

Edits an existing event in Focuris.

Format: `edit IDENTIFIER [n/NAME] [d/DESCRIPTION] [s/STATUS] [p/PRIORITY]`

* Edits the event at the specified `IDENTIFIER`.
* The identifier refers to the index number shown in the respective displayed event list.
* The identifier **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 n/CS2030 d/Assignment` Edits the event name and event description of the 1st event to be `CS2030` and `Assignment` respectively.
*  `edit 2 s/log ` Edits the status of the 2nd event to be `BACKLOG`

### Changing the status of an event to Done  : `done`
Changes the specified event status to DONE in Focuris.

Format: `done IDENTIFIER`

* Changes the event status at the specified `IDENTIFIER` to DONE. 
* The identifier refers to the index number shown in the displayed event list.
* The identifier **must be a positive integer** 1, 2, 3, …​

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

Format: `delete IDENTIFIER`

* Deletes the event at the specified `INDEX`
* The identifier refers to the index number shown in the displayed event list.
* The identifier **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd event in Focuris.
* `find CS2100` followed by `delete 1` deletes the 1st event in the results of the `find` command.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Focuris data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Focuris data are saved as a JSON file `[JAR file location]/data/eventbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Focuris will discard all data and start with an empty data file at the next run.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Focuris home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                 |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Todo**   | `todo n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `todo n/CS2040 d/Assignment`                                                                                                                                                              |
| **Log**    | `log n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `log n/CS2030 d/Lab`                                                                                                                                                                       |
| **Prog**   | `prog n/NAME d/DESCRIPTION [p/PRIORITY]` <br> e.g., `prog n/CS2100 d/Tutorial`                                                                                                                                                                |
| **Done**   | `done INDEX` <br> e.g., `done 2`                                                                                                                                                                                                 |
| **Delete** | `delete IDENTIFIER`<br> e.g., `delete 3`                                                                                                                                                                                         |
| **Edit**   | `edit IDENTIFIER [n/NAME] [s/STATUS] [d/DESCRIPTION] [p/PRIORITY]`<br> e.g.,`edit 2 n/CS2030 d/Assignment`                                                                                                                       |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                       |
| **Help**   | `help`                                                                                                                                                                                                                           |
