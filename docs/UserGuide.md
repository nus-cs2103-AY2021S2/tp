---
layout: page
title: User Guide
---
<div align="center">
  <img alt="logo" src="images/tutorbuddy_logo.png">
</div>

TutorBuddy is an application made for independent tutors as a management tool to cut down on admin overheads,
by graphically managing their student’s information with a Graphical User Interface (GUI).
It allows for faster and more effective student management.

**Table of Contents**
* [About](#about)
* [Quick start](#quick-start)
* [Features](#features)
  * [Listing all students: `list_student`](#listing-all-students-list_student)
  * [Locating student profile by name: `find_student`](#locating-student-profile-by-name-find_student)
  * [Adding a student: `add_student`](#adding-a-student-add_student)
  * [Deleting a student: `delete_student`](#deleting-a-student-delete_student)
  * [Listing students' emails based on current list: `emails`](#listing-students-emails-based-on-current-list-emails)
  * [Listing all tuition sessions: `list_session`](#listing-all-tuition-sessions-list_session)
  <!--* [Locating tuition session by student name / date: `find_session`](#locating-tuition-session-by-student-name--date-find_session)-->
  * [Adding a tuition session: `add_session`](#adding-a-tuition-session-add_session)
  * [Deleting a tuition session: `delete_session`](#deleting-a-tuition-session-delete_session)
  * [Getting monthly fee for a particular student: `fee`](#getting-monthly-fee-for-a-particular-student-fee)
  * [Exit the program: `exit`](#exit-the-program-exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## About
This user guide provides documentation on the installation and usage of TutorBuddy.
It also provides a comprehensive description of features available to you and
includes a [quick-start](#quick-start) section that helps you get started.

This guide uses the following features to make it easier for you to navigate around:

* Words that look like [this](#about) can be clicked to jump to the related section.
* Words that look like `this` refer to keywords used as part of commands or responses from TutorBuddy.
* Words that look like <kbd>this</kbd> refer to keyboard keys that you can press.

<div markdown="block" class="alert alert-info">

:information_source: Boxes with the :information_source: icon contain additional useful information.

</div>

<div markdown="block" class="alert alert-primary">

:bulb: Boxes with the :bulb: icon contain additional tips and tricks to help you get the most out of TutorBuddy.

</div>

--------------------------------------------------------------------------------------------------------------------
## Quick start
This section provides information on how to quickly start using TutorBuddy.

### Installation
Here are a few steps to get you started on Tutor's Pet:

1. Ensure you have **Java 11** or above installed in your computer.

1. Download the latest version of TutorBuddy [here](https://github.com/AY2021S1-CS2103T-T10-4/tp/releases).

1. Move the downloaded TutorBuddy to a folder of your choice. This will be known as the home folder of TutorBuddy.

1. Double-click the file to start the application. An application similar to the one below should appear in a few
   seconds.<br>

![Ui](images/Ui.png)

### Using TutorBuddy

This section offers an overview of the layout in TutorBuddy.

There are three main areas in TutorBuddy:

1. the utility area,

1. the main viewing area,

1. the command box and result display box.

![ApplicationOverview](images/ApplicationOverview.png)

1. The utility area consists of 2 tabs: **File**, and **Help**.
* The **File** tab consists of an exit button. To exit TutorBuddy, click on the exit button. Alternatively, use the `exit` command to exit the application.
* The **Help** tab consists of a help button. If you require any assistance, click on the help button, copy the link
  displayed and paste it into any web browser. Alternatively, press <kbd>F1</kbd> to bring up the help window.
  
2. The main viewing area consists of 3 tabs: **Home**, **Tuition** and **Calendar**.
* The **Home** tab contains a reminder section where a user can view their upcoming tuition lessons. There is also
  a fee section where the user can view their tuition fees for the past 3 months.
* The **Tuition** tab contains a Student and Session section where a user can view and manage their students and lessons.
* The **Calendar** tab contains a weekly view of all sessions.

3. The command box is the area for you to enter your commands. The result of each command will be shown in the result
   display box, which is located directly above the command box.

* Type a command in the command box and press <kbd>Enter</kbd> to execute it.<br>

  Here are some example commands you can try:

  * **`list`** : Lists all students and sessions.

  * **`add_student`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/Sec2 g/95421323 r/Mother` : Adds a student named `John Doe`
    to TutorBuddy.

  * **`delete_student`**`3` : Deletes the 3rd student shown in the Student section.

  * **`add_session`**`n/John Doe d/2021-01-01 t/13:00 k/120 s/Biology f/80` : Adds a tuition session for John Doe happening on 2021-01-01.

  * **`exit`** : Exits the application.

* Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Listing all students: `list_student`

Shows a list of all students in the TutorBuddy

Format: `list_student` <br>

### Locating student profile by name: `find_student`

Finds students whose names contain any of the given keywords.

Format: `find_student KEYWORD [MORE_KEYWORDS]`
* The search will be case-insensitive. e.g. searching `stonks` will match `STONKS`
* Only the student’s name will be searched
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

\# | Student Name
---- |---------
1 | John Lee
2 | Johnz Tan
3 | Jon Koh
4 | Samuel Lee

* `find_student John` returns John Lee
* `find_student Sam` returns nothing
* `find_student Lee` returns "John Lee" and "Samuel Lee"
* `find_student Johnz Lee` returns "Johnz Tan", "John Lee" and "Samuel Lee"

### Adding a student: `add_student`

Adds a student to the TutorBuddy

Format: `add_student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS l/STUDY_LEVEL g/GUARDIAN_PHONE_NUMBER r/RELATIONSHIP_WITH_GUARDIAN` <br>
* `STUDENT_PHONE_NUMBER`, `GUARDIAN_PHONE_NUMBER` should be in Singapore's phone formatting (i.e. starting with either 6, 8 or 9 and 8 digits)

Examples:
* `add_student n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/Sec2 g/95421323 r/Mother`

### Deleting a student: `delete_student`

Deletes the specified student from TutorBuddy

Format: `delete_student INDEX` <br>

Examples:
* `delete_student 2` deletes the 2nd student in the address book

### Listing students' emails based on current list: `emails`
Displays concatenated string of students' emails based on current list, separated by `;`. Useful for sending mass emails to students.

Format: `emails`

Examples:

\# | Student Name | Email
---- |---------|------|
1 | John Lee | johnlee@gmail.com
2 | Johnz Tan | johnztan@gmail.com
3 | Jon Koh | jonkoh@gmail.com
4 | Samuel Lee | sam@gmail.com

* To get emails of all students: `list_student` followed by `emails` returns `johnlee@gmail.com;johnztan@gmail.com;jonkoh@gmail.com;sam@gmail.com;`
* To get emails of specific students: `find_student john jon` followed by  `emails` returns `johnlee@gmail.com;jonkoh@gmail.com;`


### Listing all tuition sessions: `list_session`

Shows a list of all tuition sessions in TutorBuddy

Format: `list_session`

<!-- COMMENT OUT FOR FIND SESSION -->
<!--### Locating tuition session by student name and session index: `find_session`

Find tuition sessions that match the keyword given

Format: `find_session s/STUDENT_NAME i/SESSION_INDEX`
* The search will be case-insensitive. e.g. searching “stonks” will match “STONKS”
* For student names:
  * Any word that a student’s name contains will be matched. For example, if a session student’s name is “moon”, searching “moo” will match it

Examples:<br>
The command `list_session` will show the following:

\# | Sessions
---- |---------
1 | John Lee<br>15/2/2021 13:00<br>2h $60/h<br>Computer Science
2 | Johnz Lee<br>16/2/2021 14:00<br>1.5h $30/h<br>Math
3 | John Dam<br>18/2/2021 15:00<br>2h $0.10/h<br>Software Engineering
4 | Sammuel Bruce Lee<br>19/2/2021 18:00<br>2h $30/h<br>Wing Chun

* `find_session John` returns all John in TutorBuddy
* `find_session Jo` returns all John in TutorBuddy
* `find_session John Lee` returns all John Lee in TutorBuddy
* `find_session Zach` returns nothing-->
<!-- END OF COMMENT OUT FOR FIND SESSION -->

### Adding a tuition session: `add_session`

Adds a tuition session to the TutorBuddy

Format: `add_session n/STUDENT_NAME d/DATE t/TIME k/DURATION s/SUBJECT f/FEE`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `DATE` should be in YYYY-MM-DD format
* `TIME` should be in HH:MM 24-hr format
* `DURATION` should be in minutes
* `FEE` should be the total tuition fee for the total duration

Examples:
* `add_session n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80`

### Deleting a tuition session: `delete_session`

Deletes the specified tuition session from TutorBuddy

Format: `delete_session n/STUDENT_NAME i/SESSION_INDEX`

* Deletes the tuition session at the specified `SESSION_INDEX`
* `SESSION_INDEX` refers to the session index respective to the student specified under `STUDENT_NAME`
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `delete_session n/John Lee i/1` deletes John Lee's **first** session

### Getting monthly fee for a particular student: `fee`

Get the monthly fee for a particular student for a particular month and year

Format: `fee n/STUDENT_NAME m/MONTH y/YEAR`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `MONTH` should be a positive integer between 1 and 12
* `YEAR` should be a positive integer between 1970 and 2037

Examples:
* `fee n/John Lee m/1 y/2021` get John Lee monthly fee for January 2021


### Exit the program: `exit`

Exits the program

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorBuddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

**Students**

Action | Format, Examples
--------|------------------
**List** | `list_student`
**Find** | `find_student find KEYWORD [MORE_KEYWORDS]`<br><br>e.g. `find_student John Alex`
**Add** | `add_student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS l/STUDY_LEVEL g/GUARDIAN_PHONE_NUMBER r/RELATIONSHIP_WITH_GUARDIAN`<br><br> e.g., `add_student n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/Sec2 g/95421323 r/Mother`
**Delete** | `delete_student INDEX`<br><br>e.g. `delete_student 3`
**List students' emails based on current list** | `emails`

**Tuition Session**

Action | Format, Examples
--------|------------------
**List** | `list_session`
**Find** | `find_session KEYWORD`<br><br>e.g. `find_session John`
**Add** | `add_session n/STUDENT_NAME d/DATE t/TIME k/DURATION s/SUBJECT f/FEE`<br><br> e.g. `add_session n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80`
**Delete** | `delete_session n/STUDENT_NAME i/SESSION_INDEX`<br><br>e.g. `delete_session n/John Lee i/1`

**Fees**

Action | Format, Examples
--------|------------------
**Student fee for a particular month and year** | `fee n/STUDENT_NAME m/MONTH y/YEAR`<br><br>e.g. `fee n/John Lee m/1 y/2021`
