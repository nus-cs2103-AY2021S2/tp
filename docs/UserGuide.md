---
layout: page
title: User Guide
---

ModuleBook3.5 is the go-to tool for busy students/professionals who are confident that typing can save them time.

* Table of Contents
{:toc}
* Modules supported: CS1101S, CS1231S, CS2030, CS2040S, CS2101,
  CS2103T, CS2105, CS2106, CS3230, CS3243, CS3244, IS1103, ST2131

--------------------------------------------------------------------------------------------------------------------

## Quick start

{more to be added}

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the compulsory parameters to be supplied by the user.<br>
  e.g. in `add d/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add d/DESCRIPTION`.

* Items in square brackets are optional.<br>
  e.g `d/DESCRIPTION [t/TAG]` can be used as `d/CS3243 Assignment4 t/Minimax` or as `d/CS3243 Assignment4`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Minimax`, `t/Minimax t/CSP` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `d/DESCRIPTION t/TAG`, `t/TAG d/DESCRIPTION` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/CS2103T team project for week7 d/CS3243 Assignment 4`, only `d/CS2103T team project for week7` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as`list`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

--------------------------------------------------------------------------------------------------------------------

## Commands

   * **`list`** : List out tasks

   * **`add`**` : Add a task

   * **`delete`** : Delete a task

   * **`done`** : Mark a task as done

   * **`notdone`** : Mark a task as not done

   * **`tag`** : Add a tag to a task

   * **`find`** : Search for tasks with name

   * **`findTag`** : Search for tasks with tag
     
   * **`deleteTag`** : Deletes a tag of a task

   * **`mod`** : Search for tasks of a specific module

   * **`edit`** : Edit task

   * **`sort`** : Sort tasks
   
   * **`recur`** :  Recur tasks



--------------------------------------------------------------------------------------------------------------------

### Listing out tasks : `list`

Shows a list of all tasks you have added.

Format: `list`


### Adding a task: `add`

Adds a task to ModuleBook3.5.

Format: `add n/TASK NAME m/MODULE d/DESCRIPTION b/DEADLINE w/WORKLOAD [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Enter the time you wish to complete your task in the format: yyyy-MM-dd HH:mm or yyyy-MM-dd (HH:mm taken as current time)
</div>


![add message](images/addTaskUiV1.2.png)

Examples:
* `add n/v1.2 TP m/CS2103T d/implement basic features b/2021-03-13 23:59 w/3 t/urgent`
* `add n/practice set 1 m/CS3230 d/practice master's theorem b/2021-03-15 00:00 w/1 t/optional`

### Deleting a task : `delete`

Deletes the specified task from the module book.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the ModuleBook 3.5.
* `find CS2103T` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Mark a task as done : `done`

Mark an existing task as done.

Format: `done INDEX`

* Mark the task as done at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `done 2` marks the 2nd task as done in the ModuleBook 3.5.
* `find CS2103T` followed by `done 1` marks the 1st task in the results of the `find` command as done.

### Mark a task as not done : `notdone`

Mark an existing task as not done.

Format: `notdone INDEX`

* Mark the task as not done at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `notdone 2` marks the 2nd task as not done in the ModuleBook 3.5.
* `find CS2103T` followed by `notdone 1` marks the 1st task in the results of the `find` command as not done.


### Adding a tag: `tag`

Adds a tag to a task.

Format: `tag INDEX t/TAG [t/MORETAGS]` 

* Attaches one or more tags to the task associated with INDEX. Tags can be used to identify related tasks.

Examples:

* `tag 1 t/SoftwareEng`

### Searching tasks with name of task: `find`

Searches for tasks with a name of task  provided.

Format: `find KEYWORD`

* Searches through ModuleBook 3.5 for tasks whose names contain `KEYWORD`.

Examples:

* `find revise`

### Searching tasks with tag: `findTag`

Searches for tasks with an associated tag.

Format: `findTag KEYWORD`

* Searches through ModuleBook 3.5 for tasks whose has a tag named `KEYWORD`.

Examples:

* `findTag homework`

### Searching tasks associated to a Module: `mod`

Searches for tasks of a specific Module.

Format: `mod MODULE`

* Searches through ModuleBook 3.5 for tasks which belong to `MODULE`.

Examples:

* `mod CS3243`


### Deleting tag of Task: `deleteTag`

Deletes a tag from its associated task.

Format: `deleteTag INDEX [t/TAG]`

* Deletes the task at the specified `INDEX`. 
  The index refers to the index number shown in the displayed task list. 
  The index must be a positive integer 1, 2, 3, …​

Examples:

* `deleteTag 1 t/homework`

### Editing a task: `edit`

Edits an existing task in the module book.

Format: `edit INDEX [n/NAME] [m/MODULE] [d/DESCRIPTION] [b/DEADLINE] [w/WORKLOAD] [t/TAG] …​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 d/Eat Biscuits` Edits the description of the 1st task to `Eat Biscuits`.
*  `edit 2 d/Eat Biscuits b/2021-03-21 10:10` Edits the description of the 2nd task to be `Eat Biscuits` and its deadline to date `2021-03-21 10:10`.
*  `edit 2 b/2021-03-21` Edits the deadline of the 3rd task to `2021-03-25`


### Sorting tasks : `sort`

Sorts the list of all tasks added by deadline.

Format: `sort`

### Recurring tasks: `recur`

Recurs a task either daily, monthly or weekly in the module book.

Format: `recur INDEX r/RECURRENCE`

* Recurs the task at the specified `INDEX`.
* `INDEX` refers to the index number displayed in ModuleBook3.5.
   It must be a positive integer.
* `RECURRENCE` refers to the regularity of the task that recurs periodically.
* `RECURRENCE` can only be `daily`, `weekly` or `monthly`. It is case-sensitive.

Examples:
* `recur 1 r/monthly` Recurs the 1st task in ModuleBook3.5 every month.
* `recur 2 r/weekly` Recurs the 4th task in ModuleBook3.5 every week.
* `recur 3 r/daily` Recurs the 3rd task in ModuleBook3.5 every day.


--------------------------------------------------------------------------------------------------------------------

### Saving the data

ModuleBook3.5 data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

### Editing the data file

ModuleBook3.5 data is saved as a JSON file `[JAR file location]/data/modulebook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ModuleBook3.5 will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ModuleBook3.5 home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**list** | `list`
**add** | `add d/DESCRIPTION b/TIME [t/TAG]…​` <br> e.g., `add n/TP m/CS2103T d/Team tasks b/2021-01-20 20:00 w/3 t/tagname`
**delete** | `delete INDEX`<br> e.g., `delete 3`
**done** | `done INDEX`<br> e.g., `done 1`
**notdone** | `notdone INDEX`<br> e.g., `notdone 1`
**tag** | `tag INDEX [t/TAG NAME]`<br> e.g., `tag 1 [t/SoftwareEng]`
**find** | `find KEYWORD`<br> e.g., `find CS3230`
**edit** | `edit INDEX [d/DESCRIPTION] [b/DEADLINE]…​`<br> e.g., `edit 2 d/Eat Biscuits b/2021-03-21 10:10`
**sort** | `sort`
**recur** | `recur INDEX r/RECURRENCE`<br> e.g., `recur 1 r/monthly`
