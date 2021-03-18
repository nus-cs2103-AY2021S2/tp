---
layout: page
title: User Guide
---
`semester.config` is a **desktop app for managing details, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, `semester.config` can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
1. [Quick start](#quick-start)
2. [Features](#features)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `semesterconfig.jar` from [here](https://github.com/AY2021S2-CS2103-T14-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your `semester.config`.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all deadlines.

   * **`delete`**`3` : Deletes the 3rd deadline shown in the current list.

   * **`clear`** : Deletes all deadlines.

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

* Extraneous parameters for commands that do not take in parameters (such as `instructions`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `instructions 123`, it will be interpreted as `instructions`.

</div>

### See the list of instructions: `instructions`

List down all possible commands in semester.config.

Format: `instructions`

### Add a deadline: `add`

Add a deadline to the list

Format: `add mc/MODULE_CODE mn/MODULE_NAME tn/TASK_NAME d/DATE [t/TIME]`

Example:
* `add mc/CS3243 mn/Introduction to Artificial Intelligence tn/Project 1 d/ Feb 15` will add this deadline to the list

### Clear Application : `clear`

Clears all deadlines from the application

Format: `clear`

### Delete a deadline: `delete`

Deletes the specified deadline from the list

Format: `delete INDEX`

* Index to be inserted must be positive, and
* Index must be available on the list else an error will be thrown

Example:
* `delete 3` will delete the 3rd deadline on the list
* Using `list` to show all deadlines, the user wants to delete the 2nd deadline. User use command `delete 2` to delete the 2nd deadline from the list

### Edit a deadline: `edit`

Edits an existing deadline in the application

Format: `edit INDEX [tn/TASK NAME] [mn/MODULE NAME] [mc/MODULE CODE] [d/DEADLINE DATE]
[t/DEADLINE TIME] [n/NOTES] [pt/PRIORITY]`

* Edits the deadline at the specified index
* The index must be a positive integer 1,2,3,...
* The index must be on the list else an error will be thrown
* At least one of the optional fields must be provided
* Existing values will be updated to the input values

Examples:
* `edit 1 mn/Software Engineering mc/CS2103` edits the module moduleName and module code of the deadline at index 1 to be “Software Engineering” and “CS2103” respectively.
* `edit 2 tn/Finals n/Open Book` edits the moduleName and notes of the deadline at index 2 to be “Finals” and “Open Book” respectively.

### Locating deadlines by moduleName: `find`

Find deadlines whose moduleName contains any of the given keywords.

Format: `find KEYWORD...`
* The search is case-insensitive. e.g “Assignment” will match “assignment”
* The order of the keywords does not matter. e.g. “programming modular” will “match modular programming”
* Only the deadline moduleName is searched.
* Only full words will be matched e.g. “Java” will not match “Javascript”
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. “SQL Python” will return “SQL Quiz, Python Assignment”

Examples:
* `find C++` will return `C++ project` and `C++ graded quiz`
* `find assignment` exam will return `Final Assignment, Midterm exam`

### List all deadlines: `list`

List out all deadlines (might be unsorted)

Format: `list`

### Mark a deadline as done: `done`

Marks deadline(s) in the application to be done.

Format: `done INDEX…`
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​
* Index must be available on the list else an error will be thrown

Example:
* Using `list` to show all deadlines, the user wants to mark the 5th deadline as done. Users use command `done 5` to mark the 5th deadline.

### Sort deadlines: `sort`

Sorts deadlines according to the specified parameter.

Format: sort `[/dt] [/mc] [/pt]`
* Deadlines can be sorted according to these three parameters: date & time `/dt`, module code `/mc` or priority tag `/pt`. If no arguments are given, assumed to be sorted based on date.
* For `date & time`, the deadlines will be sorted in ascending order, with the earliest deadline placed first.
* For `module code`, the deadlines will be sorted according to the lexicographical ordering of the module codes.
* For `priority tag`, the deadlines will be sorted according to this order: HIGH > MEDIUM > LOW. Deadlines with a high priority will then be shown at the top of the list.

### Clear Application: `clear`

Clears all deadlines from the application.

Format: `clear`

### Showing time-based alerts: `show`

Shows alert(s) for incoming deadlines based on the time range (if given).

Format: `show [start/START_DATE] [end/END_DATE]`
* If no time range is given, alert(s) shown will be deadlines next week.
* If only `START_DATE` is given, alert(s) shown will be from **the `START_DATE` until the latest deadline**.
* If only `END_DATE` is given, alert(s) shown will be from **today’s date until the `END_DATE`**.

Example:
* `alert start/10-10-2020` will show deadlines starting from 10 October 2020 onwards.

### Saving the data

`semester.config` data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

`semester.config` data are saved as a JSON file `[JAR file location]/data/semesterconfig.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning"><b>:exclamation: Caution</b> If your changes to the data file makes its format invalid, semester.config will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file
that contains the data of your previous `semester.config` home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**instructions** | `instructions`
**add** | `add mc/MODULE_CODE mn/MODULE_NAME tn/TASK_NAME d/DATE [t/TIME]` <br> e.g, `add mc/CS1010 mn/Programming Methodology tn/Assignment 2 d/ Feb 15`
**delete** | `delete INDEX ...` <br> e.g, `delete 3` `delete 1 2`
**find** | `find KEYWORD ...` <br> e.g, `find Database` `find Software`
**list** | `list`
**done** | `done INDEX ...` <br> e.g, `done 1` `done 3 5`
**sort** | `sort [/dt] [/mc] [/pt]` <br> e.g, `sort /dt` `sort /mc /pt`
**notes** | `notes INDEX n/NOTES` <br> e.g, `notes 4 n/Assignment must be handwritten`
**edit** | `edit INDEX [tn/TASK NAME] [mn/MODULE NAME] [mc/MODULE CODE] [d/ DEADLINE DATE] [t/DEADLINE TIME] [n/NOTES] [pt/PRIORITY]` <br> e.g, `edit 2 tn/Assignment 7`
**clear** |`clear`
**pt** | `pt [/LOW] [/MEDIUM] [/HIGH] INDEX` <br> e.g, `pt /MEDIUM 3`
**show** | `show [start/START_DATE] [end/END_DATE]` <br> e.g, `show`  `show end/10-10-2020` `show start/10-10-2020 end/11-10-2020`



