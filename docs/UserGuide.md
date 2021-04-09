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

    * **`list`** : Lists all tasks.

    * **`delete`**`3` : Deletes the 3rd task shown in the current list.

    * **`clear`** : Deletes all tasks.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/CS2103 Assignment`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [pt/TAG]` can be used as `n/CS2103 Assignment pt/core` or as `n/CS2103 Assignment`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[pt/TAG]…` can be used as  `(i.e. 0 times)` OR `pt/core`OR `pt/difficult pt/SUable` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/TASK_NAME mc/MODULE_CODE`, `mc/MODULE_CODE n/TASK_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `mc/CS2103 mc/CS2105`, only `mc/CS2105` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* All available inputs of priority tags are `LOW/MEDIUM/HIGH`. The application only accept these inputs as valid inputs and will throw an error if other inputs are entered

* The module code parameter only takes in uppercase CS module codes.
  e.g. `mc/CS2103` will be accepted but `mc/cs2103` will throw an error.
</div>

### Prefixes for `add` and `edit` commands

This table outlines all the available prefixes that can be used for the `add` and `edit` commands.

| Prefix   | Name          | Applicable Commands | Example Usage        |
| -------- | ------------- | ------------------- | -------------------- |
| `n/`     | Task Name     | `add`, `edit`       | `n/Assignment 2`     |
| `mc/`    | Module Code   | `add`, `edit`       | `mc/CS2103`          |
| `d/`     | Deadline Date | `add`, `edit`       | `d/10-10-2021`       |
| `t/`     | Deadline Time | `add`, `edit`       | `t/18:00`            |
| `w/`     | Weightage     | `add`, `edit`       | `w/25%`              |
| `pt/`    | Tags          | `add`, `edit`       | `pt/core mod`        |
| `ptag/`  | Priority Tag  | `add`, `edit`       | `ptag/HIGH`          |
| `notes/` | Notes         | `edit`              | `notes/Hello World!` |

### Add a task: `add`

Add a task to the list.

Format: `add mc/MODULE_CODE n/TASK_NAME d/DEADLINE_DATE t/DEADLINE_TIME w/WEIGHTAGE [ptag/PRIORITY_TAG] [pt/TAGS]...`

Example:

* `add mc/CS3243 n/Project 1 d/15-04-2021 t/10:00 w/10%` will add this task to the list
* Priority tag of a task will be set to `LOW` by default if there are not inputs
* Note that the order of inputs does not matter, for e.g. there is no difference between entering `add mc/CS3243 n/Project 1` and `add n/Project 1 mc/CS3243` .

### Edit a task: `edit`

Edits an existing task in the application

Format: `edit INDEX [n/TASK_NAME] [mc/MODULE_CODE] [d/DEADLINE_DATE]
[t/DEADLINE_TIME] [notes/NOTES] [pt/TAG] [ptag/PRIORITY_TAG]`

* Edits the task at the specified index
* The index must be a positive integer 1,2,3,...
* The index must be on the list else an error will be thrown
* At least one of the optional fields must be provided
* Existing values will be updated to the input values
* Note that the order of inputs does not matter, for e.g. there is no difference between entering `edit 1 mc/CS3243 n/Project 1` and `edit 1 n/Project 1 mc/CS3243` .

Examples:

* `edit 1 n/Lecture Quiz mc/CS2103` edits the task name and module code of the task at index 1 to be “Lecture Quiz”
  and “CS2103” respectively.
* `edit 2 d/15-04-2021 notes/Open Book` edits the date and notes of the task at index 2 to be “15 April 2021” and “Open
  Book” respectively.
* `edit 3 n/Finals ptag/HIGH` edits the task name and priority tag of the task at index 3 to be "Finals" and "HIGH" respectively.

### Delete a task: `delete`

Deletes the task at the specified index under All Tasks

Format: `delete INDEX`

* Index to be inserted must be positive, and
* Index must be available on the All Tasks list else an error will be thrown

Example:

* `delete 3` will delete the 3rd task on the list of All Tasks
* Using `list` to show all tasks, the user wants to delete the 2nd task. User use command `delete 2` to delete
  the 2nd task from the list.

### Locating tasks by taskName: `find`

Find tasks whose taskName contains any of the given keywords.

Format: `find KEYWORD...`

* The search is case-insensitive. e.g “Assignment” will match “assignment”
* The order of the keywords does not matter. e.g. “programming modular” will match "modular programming”
* Only the task's taskName is searched.
* Only full words will be matched e.g. “Java” will not match “Javascript”
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. “SQL Python” will return “SQL Quiz,
  Python Assignment”
* Searching allows multiple keywords e.g: "Final Assignment" will match with "Final Assignment part 1" and "Final Assignment part 2". Use of multiple search keys must be delimited by space. Using example above. "FinalAssignment" will not match "Final Assignment part 1".

Examples:

* `find C++` will return `C++ project` and `C++ graded quiz`
* `find assignment` exam will return `Final Assignment, Midterm exam`

### List all tasks: `list`

List out all tasks (might be unsorted)

Format: `list`

### Toggle the status of a task: `done`

Toggle the status of a task from finished to unfinished or from unfinished to finished.

Format: `done INDEX`

* The index refers to the index number shown in the displayed All Tasks list.
* The index must be a positive integer 1, 2, 3, …​
* Index must be available on the list else an error will be thrown

Example:

* Task 3 has finished. `done 3` will mark task 3 to be unfinished. If the user type `done 3` again, it will mark task 3
  to be finished again.
* Task 1 hasn't finished. `done 1` will mark task 1 to be finished.

### Sort tasks: `sort`

Sorts tasks according to the specified parameter.

Format: `sort [dateTime OR taskName OR moduleCode OR priorityTag OR weightage]`

* Tasks can be sorted according to these five parameters: date & time `dateTime`, module code `moduleCode`,
  priority tag `priorityTag`, weightage `weightage`, and task name `taskName`.
* If no arguments are given, an error will be thrown.
* For date & time, the tasks will be sorted in chronological order, with the earliest deadline placed first.
* For module code, the tasks will be sorted according to the lexicographical ordering of the module codes.
* For priority tag, the tasks will be sorted according to this order: HIGH > MEDIUM > LOW. Tasks with higher priority will then be shown at the top of the list.
* For weightage, the tasks will be sorted in a descending order, starting from the heaviest weightage.
* For task name, the tasks will be sorted according to the lexicographical order of the task names.

### Clear Application: `clear`

Clears all tasks from the application.

Format: `clear`

### Exit Application `exit`

Exit from semester.config, changes will be saved

Format: `exit`

### Show tasks that due soon: `dueIn`

Show tasks that are due within the days/weeks specified by the user (if any).

Format: `dueIn [day/NUMBER_OF_DAYS OR week/NUMBER_OF_WEEKS]`

* If no parameters given, tasks shown will be tasks that will be due by **next week**.
* `NUMBER_OF_DAYS` & `NUMBER_OF_WEEKS` must be a positive integer
* If both `day/` & `week/` are given, an error will be thrown.
* Tasks shown are tasks with deadlines starting from today's date.
* The limit for dueIn is until 31-12-2099.

Example:

* Task 1's task is 10 March 2020. Task 2's task is 11 March 2020. Today is 3 March 2020. `dueIn`, `dueIn day/7`,
  and `dueIn week/1` will lists task 1 on the list.

### Add a task to the daily task list: `doToday`

Adds the specified task from the regular task list to the daily task list.

Format: `doToday [-a OR -r] INDEX`

- Flag to add or remove must be specified: `-a` to add a daily task, `-r` to remove a daily task.
- For add flag `-a`: Index must be available on the task list else an error will be thrown.
- For remove flag `-r`: Index must be available on the daily task list else an error will be thrown.

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
**add** | `add mc/MODULE_CODE n/TASK_NAME d/DEADLINE_DATE t/DEADLINE_TIME w/WEIGHTAGE [pt/TAGS] [ptag/PRIORITY_TAG]` <br> e.g, `add mc/CS1010 n/Practical Exam d/12-12-2020 t/10:10 w/10%`
 **delete**  | `delete INDEX` <br> e.g, `delete 3` `delete 1`
**find** | `find KEYWORD ...` <br> e.g, `find Database` `find Software`
**list** | `list`
**done** | `done INDEX` <br> e.g, `done 1`
**sort** | `sort [dateTime OR taskName OR moduleCode OR priorityTag OR weightage]` <br> e.g, `sort dateTime` `sort moduleCode`
**notes** | `notes INDEX notes/NOTES` <br> e.g, `notes 4 notes/Assignment must be handwritten`
**edit** | `edit INDEX [n/TASK NAME] [mc/MODULE CODE] [d/DEADLINE_DATE] [t/DEADLINE_TIME] [notes/NOTES] [ptag/PRIORITY_TAG]` <br> e.g, `edit 2 n/Assignment 7`
**clear** |`clear`
**dueIn** | `dueIn [day/NUMBER_OF_DAYS OR week/NUMBER_OF_WEEKS]` <br> e.g, `dueIn`  `dueIn day/10` `dueIn week/2`
**doToday** | `doToday [-a OR -r] INDEX` <br>e.g, `doToday -a 2` `doToday -r 2`
**undo** | `undo`
**redo** | `redo`
**exit** | `exit`



