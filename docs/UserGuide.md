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

# Quick start

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

   * **`mk`**`n/eat dinner` : Makes a task titled `eat dinner` to the planner.

   * **`rmt`**`3` : Removes the 3rd task shown in the current planner.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# How to use this guide

Here are some symbols you might need to know:
 * :information_source: : Helpful information you should take note of.
 * :bulb: : Useful tips that might help you.
 * :exclamation: : Important information that might affect your usage of PlanIT.
 
--------------------------------------------------------------------------------------------------------------------

# Features
## Tasks
PlanIT contains a list of tasks.
Tasks can have the following attributes:

### Title: `n/`
A short description or name for the task. Titles can only contain alphanumeric values.

:information_source:  Every task must have a title.

### Date: `set/`
A date to represent the deadline of a Task or to represent the day that the task will be carried out.

Dates should be of the format dd/mm/yyyy e.g 02/06/2021

### Duration: `s/`
The start and end time of a task. You should specify start time and end time in the 24-hour clock format.

Duration should be of the format hh:mm-hh:mm e.g 12:30-13:30

### Recurring Schedule: `r/`
Represents a task that might repeat weekly or biweekly.

:bulb: You can use this to quickly add weekly tutorials or biweekly lab session for the entire semester.

Recurring Schedule should be of the format [dd/mm/yyyy][DAY][FREQUENCY] e.g [23/10/2021][mon][weekly]

DAY should be either: mon, tue, wed, thu, fri, sat, sun. Days are case-insensitive.

FREQUENCY should be either: weekly or biweekly and is also case-insensitive.

### Description: `d/`
A text description of the task. Your description should only contain alphanumeric values.

### Tag: `t/`
A label attached to a task for easy grouping and searching of tasks. Your tag should only contain alphanumeric values.

:bulb: You can use this to group tasks by modules e.g adding a `CS2103` tag to a task.

### Status: `st/`
Reflects the current status of your task. Status can only be either 'done' or 'not done'.

:information_source:  Your task's status will be set to 'not done' by default.
:information_source:  Every task must have a status.


## Constraints
In order to maximise the efficiency of adding tasks and ensuring that there are no unnecessary attributes, there are
two constraints to the attributes that can exist on the tasks that you create or edit.
1. Tasks cannot have Date and Recurring Schedule at the same time.
2. Tasks cannot have Duration on its own without a Date or Reclcurring Schedule.

When it comes to Dates and Recurring Schedules, the main purpose of a Date attribute is to give a task a deadline or
a single day to carry out the task itself. This should not co-exist with a Recurring Schedule, which can also indicate
a task's deadline or day to carry out the event, except it is being repeated on a weekly or biweekly basis.

As for the Duration of a task, it will be confusing to you as a user when you have multiple tasks with durations but no
date specified. As such, this might cause you to miss your task or lower the efficiency of utilizing PlanIT when 
searching for tasks.

## List of Commands

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

Displays a list of commonly used possible commands along with each of their formats respectively 
so that you can refer to commands conveniently whenever you forget about them.
  * Only a few main commands will be displayed to avoid information overload for first-time and forgetful users.
  * Users can read the User Guide for detailed information on all the commands.

Format: `help`

### Making a task: `mk`

Makes a task and adds it into the planner. <br>
Tasks with the same title cannot be added to the planner
so that you do not have to worry about adding duplicate tasks by accident.

Format: `mk n/TITLE [set/DATE] [s/DURATION] [d/DESCRIPTION]
[r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…`

* Only title is compulsory.
* Date should be in the format dd/mm/yyyy. For example, 1 December 2021
  should be expressed as `01/12/2021`, not 1/12/2021. Furthermore, Date should be
  a day that is after the current day.
* Duration should be numeric, contain 2 timings, and should be in 24 hours format with a colon, like `22:30-22:45`.
  Duration can only exists when there is date or recurring schedule. 
* Description can have multiple lines by adding a line break using <kbd>shift</kbd>+<kbd>enter</kbd>.
* Recurring schedule (can be optional) should have 3 conditions which consist of:
    * An end date when the task stops recurring.
    * A day of the week that the task recurs on.
    * Frequency of the recurring task.
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

### Editing a task : `edit`

Edits an existing task in the planner
so that you can have the flexibility to make changes to a certain task
if there is an input error when adding the task to the planner or there is a change in task requirements.

Format: `edit INDEX [n/TITLE] [set/DATE] [s/DURATION] [d/DESCRIPTION]
[r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.

Examples:
*  `edit 1 set/10/10/2021 d/Remember to update User Guide` Edits the date and description of the 1st task to be
   `10/10/2021` and `Remember to update User Guide` respectively.
*  `edit 2 n/Buy textbook t/ set/` Edits the title of the 2nd task to be `Buy textbook` and clears all existing tags
   and the date.


### Adding / Editing recurring schedule in a task : `mk n/TITLE r/RECURRING SCHEDULE` or `edit INDEX r/RECURRING SCHEDULE`

Adds / Edits a recurring schedule to a new or existing task in the planner
so that you can schedule the task in a weekly/biweekly basis
on a particular day of week for the future all at once.
<br>**Note: Existing recurring dates that has passed the current system date will be removed
automatically from the existing task upon application startup.**

Format: `mk n/TITLE r/[END DATE][DAY OF WEEK][WEEK FREQUENCY]` or `edit INDEX r/[END DATE][DAY OF WEEK][WEEK FREQUENCY]`

* The `RECURRING SCHEDULE` is an **optional field** by default when adding a task so it can be excluded
  and will be blank if it is just `r/` too.
* Similarly, the recurring schedule can be set to blank in editing field for task by typing `r/` only.
* All three fields `END DATE`, `DAY OF WEEK` and `WEEK FREQUENCY` must be present when there is input after `r/`.
* You can add/edit recurring schedule with duration but not with a deadline date.
* `END DATE` format is in `dd/mm/yyyy` and it cannot come before the current system date. <br>
  **Any number greater than 31 is invalid for day and any number greater than 12 is invalid for month.**
* `DAY OF WEEK` is **case-insensitive** and can be represented in the
  form of the first 3 letters of the day from Monday to Sunday.
* `WEEK FREQUENCY` is **case-insensitive** and can be **weekly or biweekly**.
* Recurring dates up till the `END DATE` will be generated for the task.
* An example of `RECURRING SCHEDULE`: `[23/10/2021][Mon][weekly]`

Examples:
*  `mk n/CS2103 team meeting r/[31/05/2021][mon][weekly]` Adds the task with the title `CS2103 team meeting` to the
   planner and generate recurring dates that is on `mon` `weekly` up to `31/05/2021`.
*  `edit 1 r/[23/12/2021][mon][biweekly]` modifies the first task in the planner and generate recurring dates that
   is on `mon` `biweekly` up to `23/12/2021`.

### Adding date to a task : `edit`

Adds a date to an existing task in the planner
so that you can have the option to set a deadline to the task or use it for a single day event task.

Format: `edit INDEX [set/DATE]…​`
Date should only be in the format of dd/mm/yyyy as specified above. 

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed list. 
The index **must be a positive integer** 1, 2, 3, …​
* Date field must be provided.
* Existing values will be updated to the input values.
* When editing date, the existing dates of the task will be removed i.e adding of date is not cumulative.
* You can remove all the task’s date by typing `set/` without
  specifying any date after it.

Examples:
*  `edit 1 set/13/05/2021` Adds a date to the 1st task on the list which is to be `13 May 2021`.
*  `edit 2 set/` Clears the existing date of 2nd task on the list.

### Postpone a task's date : `snooze`

Postpones your task's date by a specified number of days.

Format: `snooze INDEX [DAYS]`
* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed list.
 The index **must be a positive integer** e.g 1, 2, 3, …​
* The DAYS is optional and it's default value will be 1 if no number is specified in your command.
* The snooze command will only successfully update the date of the task if the task contains a date.

Examples:
* `snooze 2` Postpones the date of the task at index 2 in the list by 1 day.
* `snooze 3 4 ` Postpones the date of the task at index 3 in the list by 4 days.

### Listing all tasks : `ls`

Shows a list of all tasks in the planner
so that you can view all the tasks easily in one place. <br>
Automatically brings your calendar back to the current date.

Format: `ls`

### Listing all tasks : `ls not done`

Shows a list of all uncompleted tasks in the planner
so that you can view all the uncompleted tasks easily.

Format: `ls not done`

**Note: The keyword `not done` is case-insensitive.**

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


### Searching a task by title or description: `find` or `find d/`

Find matching tasks based on the title keyword(s) provided 
so that you can find matching tasks quickly when only certain words from the title of the task can be remembered.

Find matching tasks based on the description keywords provided
so that you can find matching tasks quickly when only certain words from the multi-line description can be remembered.

Format: `find KEYWORD [MORE_KEYWORDS]` or `find d/KEYWORD [MORE_KEYWORDS]`

* The search is **case-insensitive**. e.g `project` will match `Project`
* The order of the keywords does not matter. e.g. `CS2103 Project` will match `Project CS2103`
* Only full keywords will be matched e.g. `proj` will not match `projects`
* Tasks matching at least one keyword will be returned e.g. `find proj` will match `find projects`
* No `t/` or `d/` should be in the search by title query
* No `t/` should be in the search by description query

Examples:
* `find CS2103 team project` returns matching tasks with title of following words `CS2103`, `team`, `project`
* `find d/write user guide` returns matching tasks with description of following words `user`, `guide`, `write`

### Searching a task by tag: `find t/`

Find matching tasks based on the tag keyword provided 
so that you can find matching tasks from the same category quickly when only the tag(s) can be remembered.

Format: `find t/KEYWORD`

* The search is **case-insensitive**. e.g `cs2103t` will match `CS2103T`
* The keyword must be **single, alphanumeric and no spacing** allowed. e.g. `project CS2103` will not be allowed
  but `projectCS2103` will be acceptable.
* Only full keyword will be matched e.g. `cs2103` will not match `cs2103t`
* No `d/` should be in the search by tag query
* Suppose a task with **multiple tags** of `cs2103` and `cs2105`, it will be returned as a matching task
  if the user inputs falls under the following cases:
  1. `t/cs2103` only
  2. `t/cs2105` only
  3. `t/cs2103` and `t/cs2105`

Examples:
* `find t/CS2103` returns matching tasks with tag of `CS2103` or `cs2103`

### Removing a task : `rmt`

Removes an existing task from the planner
so that you can reduce clutter from the planner or remove a mistakenly added task easily.

Format: `rmt INDEX`

* Removes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed planner.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `ls` followed by `rmt 2` removes the 2nd task in the planner.
* `find Work` followed by `rmt 1` removes the 1st task in the result of the `find` command.

### Removing a field from a task : `rmf`

Removes an existing field from a task in the planner
so that you can remove certain details from the task directly
without having to go through the hassle of removing the task and adding the same task with lesser fields again.

Format: `rmf INDEX FIELD`

* Removes the specified field of task at `INDEX`.
* The index refers to the index number shown in the displayed planner.
* The index **must be a positive integer** 1, 2, 3, …​
* Fields are specified in the format their respective attributes: `set/`, `s/`, `r/`, `d/`, `t/`.
* Exactly one field must be specified.
* Title and status fields cannot be removed.

Examples:
* `ls` followed by `rmf 2 d/` removes the description from the 2nd task in the planner.
* `find Cat` followed by `rmf 1 t/` removes all the tags from the 1st task in the result of the
  `find` command.
  
### Counting down to a task : `count`

Displays the number of days left to a task's date if it exists
so that you know how much time you have left to work on the task.

Format: `count INDEX`

* Counts the number of days until the date of the task at `INDEX`.
* The index refers to the index number shown in the displayed planner.
* The index **must be a positive integer** 1, 2, 3, …​
* The task at the index must have a date, otherwise countdown cannot be done.

Examples: 
* `ls` followed by `count 4` displays the number of days left to the 4th task in the planner.
* `find Wool` followed by `count 1` displays the number of days left to the 1st task in the result
of the `find` command.

### Displaying statistics : `stat`

Displays the statistics of the planner 
so that you can check the current task progression and determine your own work efficiency conveniently. 

Statistics include:
1) The total number of tasks in the planner.
   
2) The percentage of tasks completed (marked as done).
   
3) The number of tasks due in the next 7 days from the system's current time.

Format: `stat`

* Planner must consist of at least one task.

### View tasks on a date : `view`

Displays the tasks happening on a particular date, including those recurring tasks
and brings the calendar to the date specified
so that you can schedule new activities during the free time on the same day.

Format: `view DATE`

* Date should be in the format of dd/mm/yyyy like 12/12/2021.
* The specified date can only be future dates, after the current date.

Examples:
* `view 03/07/2021`<br>Lists all tasks with dates or recurring dates on 03/07/2021, and brings the calendar to July
2021.


### Clearing all entries : `clear`

Clears all entries from the planner so that you can remove all tasks within the planner at once. 
<br> Automatically brings your calendar back to the current date.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Saving the data

PlanIT data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

## Editing the data file

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
**Remove Task** | `rmt INDEX`<br> e.g., `rmt 3`
**Remove Field** | `rmf INDEX FIELD`<br> e.g., `rmf 1 d/`
**Edit** | `edit INDEX [n/TITLE] [set/DATE] [s/DURATION] [d/DESCRIPTION] [r/RECURRING SCHEDULE] [st/STATUS] [t/TAG]…​`<br>e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br>e.g., `find CS2103 team project` <br><br>`find [t/TAG] `<br>  e.g., `find t/CS2103` <br><br> `find [d/DESCRIPTION] ` <br> e.g., `find d/CS2103 milestone postmortem`
**Countdown** | `count INDEX` <br> e.g., `count 2`
**Statistics** | `stat`
**View** | `view DATE`<br> e.g., `view 10/10/2021`
**List** | `ls`<br> `ls not done`
**Help** | `help`
**Calendar Navigation** | `next` `prev`
