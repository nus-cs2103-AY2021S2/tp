---
layout: page
title: User Guide
---

**PlanIT** is a task managing application made specially for NUS computing students like you, from computing students
like us. Forgot to submit your lab worksheet, or too many assignments and you don't know where to get started? PlanIT
gives you the confidence that your busy schedule is organized and accounted for. Quickly and efficiently make and edit
various tasks, mark deadlines, take note of remaining tasks, and more.

PlanIt also includes a calendar and a countdown feature to better manage your deadlines. It is even optimised for all of
you who prefer typing, so that bookkeeping can be done faster. Now you can make progress on the things that are
more important to you.

Objective:
PlanIT's objective is to improve productivity for students with features and tools to help
students manage their workload. These features significantly reduces the trouble of having to keep track of tasks,
especially those that are essential yet repetitive. Features such as recurring schedule and date allows students to
keep track of weekly task and due dates for tutorial homework, projects and much more. More importantly, the functionalities
of PlanIt's simple overview allows students to see upcoming deadlines or events.

Let's dive deeper into these features to see how these features can assist students
in workload management.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `planit.jar` from [here](https://github.com/AY2021S2-CS2103T-T10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your planner.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   

   Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and
   pressing Enter will display a list of commonly used commands for first time users.<br>
   Some example commands you can try:

   * **`ls`** : Lists all tasks.

   * **`mk`**`n/eat dinner` : Makes a task titled `eat dinner` to the todo list.

   * **`rmt`**`3` : Deletes the 3rd task shown in the current list.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. Type in the prefixes along with the 
  parameters.<br>
  e.g. in `mk n/TITLE`, `n/TITLE` is a parameter which can be used as `n/eat dinner`.
  
* Some commands might not require prefixes as written in their formats.<br>
  e.g. in `rmt INDEX`, it can be used as `rmt 4`.

* Items in square brackets are optional.<br>
  e.g `n/TITLE [t/TAG]` can be used as `n/Join Dance t/leisure` or as `n/Join Dance`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/TITLE set/DATE`, `set/DATE n/TITLE` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last
  occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/first task n/second task`, only `n/second task` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### View Commands : `help`

Displays a list of commonly used possible commands along with each of their formats respectively.
  * Only a few main commands will be displayed to avoid information overload for first time users.
  * Users can read the UserGuide for detailed information on all the commands.

Format: `help`


### Making a task: `mk`

Makes a task to the todo list.

Format: `mk n/TITLE [set/DATE] [s/DURATION] [d/DESCRIPTION]
[r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…`

* Only title is compulsory.
* Date should be in the format dd/mm/yyyy like `12/05/2021`.
* Duration should be numeric, contain 2 timings, and should be in 24 hours format with a colon, like `22:30-22:45`.
* Description can have multiple lines by adding a line break using <kbd>shift</kbd>+<kbd>enter</kbd>.
* Recurring schedule should have 3 conditions which consist of:
  * An end date when the task stops recurring, in the same format as a date, which
    cannot be older than the current date or less than a week away from current date.
  * A day of the week that the task recurs on, which can be
    represented in the form of the first 3 letters of the day and is case-insensitive.
  * Frequency of the recurring task, which can be weekly or biweekly and is case-insensitive.
  * E.g. `[23/10/2021][Mon][weekly]`
* Status can only be `done` or `not done`, and is by default `not done`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `mk n/eat dinner`<br>Makes a task titled 'eat dinner'.
* `mk n/do project r/[29/05/2021][thu][Biweekly]` <br>Makes a task titled 'do project' and will recur every
  thursday, biweekly until 29th May 2021.

Example of making task with multiple lines of description:
```
mk n/take a break d/
- do 1
- do 2 t/trying
```

### Listing all tasks : `ls`

Shows a list of all tasks in the planner. Automatically brings your calendar back to the current date.

Format: `ls`

### Adding date to a task : `mk`

Adds a date to an existing task in the list.

Format: `edit INDEX [set/DATE]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* Date field must be provided.
* Existing values will be updated to the input values.
* When editing dateline, the existing dates of the task will be removed i.e adding of dateline is not cumulative.
* You can remove all the task’s dateline by typing `set/` without
  specifying any date after it.

Examples:
*  `edit 1 set/13/05/2021` Adds a date to the 1st task on the list which is to be `13 May 2021`.
*  `edit 2 set/` Clears the existing date of 2nd task on the list.

### Sort task by date: `sort by a` or `sort by d`

Sort tasks in the list either in ascending dates or descending dates so that users would
be able to see the task or event nearest to current date or furthest away from current date.

Format: `sort by a` or `sort by d`

* Shows the list of all task.
* If `sort by a`, task with no deadlines would appear first, 
    subsequently task will be ordered in increasing dates. 
* If `sort by d`, task with no deadlines would appear last, 
    subsequently task will be ordered in decreasing dates.
* If two tasks have the same dates, they will be ordered in equal priority.

### Editing a task : `edit`

Edits an existing task in the planner.

Format: `edit INDEX [n/TITLE] [set/DATE] [s/START TIME] [d/DESCRIPTION]
[r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove the task’s field by typing its prefix (e.g. `t/`) without
    specifying anything after it.

Examples:
*  `edit 1 set/10/10/2021 d/Remember to update User Guide` Edits the date and description of the 1st task to be
   `10/10/2021` and `Remember to update User Guide` respectively.
*  `edit 2 n/Buy textbook t/ set/` Edits the title of the 2nd task to be `Buy textbook` and clears all existing tags
   and the date.

### Searching a task by title: `find`

Find matching tasks based on the title keyword(s) provided by the user.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `project` will match `Project`
* The order of the keywords does not matter. e.g. `CS2103 Project` will match `Project CS2103`
* Only full keywords will be matched e.g. `proj` will not match `projects`
* Tasks matching at least one keyword will be returned e.g. `find proj` will match `find projects`

Examples:
* `find CS2103 team project` returns matching tasks with title of following words `CS2103`, `team`, `project`

### Searching a task by tag: `find t/`

Find matching tasks based on the tag keyword provided by the user.

Format: `find t/KEYWORD`

* The search is case-insensitive. e.g `cs2103t` will match `CS2103T`
* The keyword must be single, alphanumeric and no spacing allowed. e.g. `project CS2103` will not be allowed
  but `projectCS2103` will be acceptable.
* Only full keyword will be matched e.g. `cs2103` will not match `cs2103t`
* Suppose a task with multiple tags of `cs2103` and `cs2105`, it will be returned as a matching task
  if the user inputs falls under the following cases:
  1. `t/cs2103` only
  2. `t/cs2105` only
  3. `t/cs2103` and `t/cs2105`

Examples:
* `find t/CS2103` returns matching tasks with tag of `CS2103` or `cs2103`

### Searching a task by description: `find d/`

Find matching tasks based on the description keywords provided by the user.

Format: `find d/KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `write user guide` will match `write User Guide`
* The order of the keywords does not matter. e.g. `user guide` will match `guide user`
* Only full keywords will be matched e.g. `proj` will not match `projects`
* Tasks matching at least one keyword will be returned e.g. `write guide` will match `write user guide`

Examples:
* `find d/write user guide` returns matching tasks with description of following words `user`, `guide`, `write`

### Removing a task : `rmt`

Removes an existing task from the task list.

Format: `rmt INDEX`

* Removes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `ls` followed by `rmt 2` removes the 2nd task in the task list.
* `find Work` followed by `rmt 1` removes the 1st task in the result of the `find` command.

### Removing a field from a task : `rmf`

Removes an existing field from a task in the task list.

Format: `rmf INDEX FIELD`

* Removes the specified field of task at `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* Fields are specified in the format of `d/` `t/`.
* Exactly one field must be specified.
* Title field cannot be removed.

Examples:
* `ls` followed by `rmf 2 d/` removes the description from the 2nd task in the task list.
* `find Cat` followed by `rmf 1 t/` removes all the tags from the 1st task in the result of the
  `find` command.
  
### Counting down to a task : `count`

Displays the number of days left to a task's date if it exists.

Format: `count INDEX`

* Counts the number of days until the date of the task at `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* The task at index must have a date, otherwise countdown cannot be done.

Examples: 
* `ls` followed by `count 4` displays the number of days left to the 4th task in the task list.
* `find Wool` followed by `count 1` displays the number of days left to the 1st task in the result
of the `find` command.

### Displaying statistics : `stat`

Displays the statistics of the planner. Statistics include:
1) The total number of tasks in the planner.
2) The percentage of tasks completed (marked as done).
3) The number of tasks due in the next 7 days from the system's current time.

Format: `stat`

* Planner must consist of at least one task.

### View tasks on a date : `view`

Displays the tasks happening on a particular date, including those recurring tasks, and brings the calendar to the date
specified.

Format: `view DATE`

* Date should be in the format of dd/mm/yyyy like 12/12/2021.
* The specified date can only be future dates, after the current date.

Examples:
* `view 03/07/2021`<br>Lists all tasks with dates or recurring dates on 03/07/2021, and brings the calendar to July
2021.


### Clearing all entries : `clear`

Clears all entries from the planner. Automatically brings your calendar back to the current date.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PlanIT data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file

PlanIT data are saved as a JSON file `[JAR file location]/data/planit.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PlanIT will discard all data and
start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## Calendar Navigation

You can click on the `prev` and `next` buttons on the calendar to move to the previous and next months respectively,
or you can simply type in the following commands, if you are more inclined to using the command line interface.

### Navigate to the previous month on the calendar : `prev`

### Navigate to the next month on the calendar : `next`

* These 2 commands do not take in parameters. As per the feature commands, extraneous parameters will be ignored.
    
    E.g. if the command specifies `prev 987`, it will be interpreted as `prev`.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file
that contains the data of your previous PlanIT home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Make** | `mk n/TITLE [set/DATE] [s/DURATION] [d/DESCRIPTION] [r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…​`<br> e.g.,`mk n/eat dinner t/important`
**Clear** | `clear`
**Delete Task** | `rmt INDEX`<br> e.g., `rmt 3`
**Delete Field** | `rmf INDEX FIELD`<br> e.g., `rmf 1 d/`
**Edit** | `edit INDEX [n/TITLE] [set/DATE] [s/START TIME] [d/DESCRIPTION] [r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…​`<br>e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`e.g., `find CS2103 team project` <br>`find [t/TAG] `e.g., `find t/CS2103` <br> `find [d/DESCRIPTION] `e.g., `find d/CS2103 milestone postmortem`
**Countdown** | `count INDEX` <br> e.g., `count 2`
**Statistics** | `stat`
**View** | `view DATE`<br> e.g., `view 10/10/2021`
**List** | `ls`
**Help** | `help`
**Calendar Navigation** | `next` `prev`
