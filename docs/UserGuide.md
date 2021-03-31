---
layout: page title: User Guide
---
`semester.config` is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, `semester.config` can get
your contact management tasks done faster than traditional GUI apps.

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

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/v1.3.1_Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `instructions`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `instructions 123`, it will be interpreted as `instructions`.

</div>

### See the list of instructions: `instructions`

List down all possible commands in semester.config.

Format: `instructions`

### Add a deadline: `add`

Add a deadline to the list

Format: `add mc/MODULE_CODE n/TASK_NAME d/DATE t/TIME w/WEIGHTAGE [r/REMARK] [pt/TAG] [ptag/PRIORITY TAG]`

Example:

* `add mc/CS3243 n/Project 1 d/15-04-2021 t/10:00 w/10%` will add this deadline to the list
* Priority tag of a task will be set to `LOW` by default if there are not inputs 

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
* Using `list` to show all deadlines, the user wants to delete the 2nd deadline. User use command `delete 2` to delete
  the 2nd deadline from the list.

### Edit a deadline: `edit`

Edits an existing deadline in the application

Format: `edit INDEX [n/TASK NAME] [mc/MODULE CODE] [d/DEADLINE DATE]
[t/DEADLINE TIME] [r/REMARK] [pt/TAG] [ptag/PRIORITY TAG]`

* Edits the deadline at the specified index
* The index must be a positive integer 1,2,3,...
* The index must be on the list else an error will be thrown
* At least one of the optional fields must be provided
* Existing values will be updated to the input values

Examples:

* `edit 1 n/Lecture Quiz mc/CS2103` edits the task name and module code of the deadline at index 1 to be “Lecture Quiz”
  and “CS2103” respectively.
* `edit 2 d/15-04-2021 r/Open Book` edits the date and notes of the deadline at index 2 to be “15 April 2021” and “Open
  Book” respectively.
  `edit 3 n/Finals ptag/HIGH` edits the task name and priority tag of the task at index 3 to be "Finals" and "HIGH" respectively

### Locating deadlines by moduleName: `find`

Find deadlines whose taskName contains any of the given keywords.

Format: `find KEYWORD...`

* The search is case-insensitive. e.g “Assignment” will match “assignment”
* The order of the keywords does not matter. e.g. “programming modular” will “match modular programming”
* Only the deadline moduleName is searched.
* Only full words will be matched e.g. “Java” will not match “Javascript”
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. “SQL Python” will return “SQL Quiz,
  Python Assignment”

Examples:

* `find C++` will return `C++ project` and `C++ graded quiz`
* `find assignment` exam will return `Final Assignment, Midterm exam`

### List all deadlines: `list`

List out all deadlines (might be unsorted)

Format: `list`

### Toggle the status of a task: `done`

Toggle the status of a deadline from finished to unfinished or from unfinished to finished.

Format: `done INDEX`

* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​
* Index must be available on the list else an error will be thrown

Example:

* Task 3 has finished. `done 3` will mark task 3 to be unfinished. If the user type `done 3` again, it will mark task 3
  to be finished again.
* Task 1 hasn't finished. `done 1` will mark task 1 to be finished.

### Sort deadlines: `sort`

Sorts deadlines according to the specified parameter.

Format: sort `[dateTime] [taskName] [moduleCode] [priorityTag] [weightage]`

* Deadlines can be sorted according to these three parameters: date & time `dateTime`, module code `moduleCode`,
  priority tag `priorityTag`, weightage `weightage`, and task name `taskName`.
* If no arguments given, an error will be thrown.
* For `date & time`, the deadlines will be sorted in ascending order, with the earliest deadline placed first.
* For `module code`, the deadlines will be sorted according to the lexicographical ordering of the module codes.
* For `priority tag`, the deadlines will be sorted according to this order: HIGH > MEDIUM > LOW. Deadlines with a high
  priority will then be shown at the top of the list.
* For `weightage`, the deadlines will be sorted in ascending order, with the lowest weightage deadline placed first.
* For `taskName`, the deadlines will be sorted according to the lexicographical ordering of the task name.

### PriorityTag: `ptag/`

Every task will be given a default priorityTag of `LOW`

#### Sorting based on priorityTag (See `sorting`)

* User is able to sort tasks based on priority tag: `LOW`, `MEDIUM` and `HIGH`

#### Edit priorityTag (See `edit`)

* User is able to edit status of priorityTag using the `edit` command
* User is able to sort the tasks based on `LOW`, `MEDIUM` and `HIGH`

### Clear Application: `clear`

Clears all deadlines from the application.

Format: `clear`

### Show tasks that due soon: `dueIn`

Show tasks that are due within the days/weeks specified by the user (if any).

Format: `dueIn [start/START_DATE] [end/END_DATE]`

* If no parameters given, tasks shown will be deadlines that will be due by **next week**.
* `NUMBER_OF_DAYS` & `NUMBER_OF_WEEKS` must be a positive integer
* If both `day/` & `week/` given, the `NUMBER_OF_DAYS` will be used.
* Tasks shown are tasks with deadline starting from today's date.

Example:

* Task 1's deadline is 10 March 2020. Task 2's deadline is 11 March 2020. Today is 3 March 2020. `dueIn`, `dueIn day/7`,
  and `dueIn week/1` will lists task 1 on the list.

### Undo last command: `undo`

Undoes the last command that modified the application state

Format: `undo`

* If there are no older states for the application to undo to, an error will be thrown.
* Only commands that modify the application's state can be undone i.e. add, delete, clear etc.

### Redo last command: `redo`

Redoes the last command that modified the application state

Format: `redo`

* If there are no newer states for the application to redo to, an error will be thrown.
* Only commands that modify the application's state can be undone i.e. add, delete, clear etc.

### Saving the data

`semester.config` data are saved in the hard disk automatically after any command that changes the data. There is no
need to save manually.

### Editing the data file

`semester.config` data are saved as a JSON file `[JAR file location]/data/semesterconfig.json`. Advanced users are
welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning"><b>:exclamation: Caution</b> If your changes to the data file makes its format invalid, semester.config will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous `semester.config` home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**instructions** | `instructions`
**add** | `add mc/MODULE_CODE n/TASK_NAME d/DATE t/TIME w/WEIGHTAGE [r/notes]` <br> e.g, `add mc/CS1010 n/Practical Exam d/12-12-2020 t/10:10 w/10`
**delete** | `delete INDEX` <br> e.g, `delete 3` `delete 1`
**find** | `find KEYWORD ...` <br> e.g, `find Database` `find Software`
**list** | `list`
**done** | `done INDEX` <br> e.g, `done 1`
**sort** | `sort [dateTime] [taskName] [moduleCode] [priorityTag] [weightage]` <br> e.g, `sort dateTime` `sort moduleCode`
**notes** | `notes INDEX n/NOTES` <br> e.g, `notes 4 n/Assignment must be handwritten`
**edit** | `edit INDEX [tn/TASK NAME] [mn/MODULE NAME] [mc/MODULE CODE] [d/ DEADLINE DATE] [t/DEADLINE TIME] [n/NOTES] [pt/PRIORITY]` <br> e.g, `edit 2 tn/Assignment 7`
**clear** |`clear`
**ptag** | `edit INDEX ptag[/LOW] [/MEDIUM] [/HIGH]` <br> e.g, `edit 3 ptag/MEDIUM`
**dueIn** | `dueIn [day/NUMBER_OF_DAYS] [week/NUMBER_OF_WEEKS]` <br> e.g, `dueIn`  `dueIn day/10` `dueIn week/2`
**undo** | `undo`
**redo** | `redo`



