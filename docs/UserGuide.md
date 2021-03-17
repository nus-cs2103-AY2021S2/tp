---
layout: page
title: User Guide
---
# TutorBuddy
TutorBuddy is an application made for independent tutors as a management tool to cut down on admin overheads,
by graphically managing their student’s information with a Graphical User Interface (GUI).
It allows for faster and more effective student management.

**Table of Contents**
* [Quick start](#quick-start)
* [Features](#features)
  * [Listing all students: `list_student`](#listing-all-students-list_student)
  * [Locating student profile by name: `find_student`](#locating-student-profile-by-name-find_student)
  * [Adding a student: `add_student`](#adding-a-student-add_student)
  * [Listing all tuition sessions: `list_session`](#listing-all-tuition-sessions-list_session)
  <!--* [Locating tuition session by student name / date: `find_session`](#locating-tuition-session-by-student-name--date-find_session)-->
  * [Adding a tuition session: `add_session`](#adding-a-tuition-session-add_session)
  * [Deleting a tuition session: `delete_session`](#deleting-a-tuition-session-delete_session)
  * [Exit the program: `exit`](#exit-the-program-exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TutorBuddy.jar` from [here](#).

3. Copy the file to the folder you want to use as the home folder for your TutorBuddy application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`list_student`** and pressing Enter will open the list of all students.<br>

   Some example commands you can try:

**Students**
  * `list_student`: List all students
  * `find_student James`: Finds and lists all students that have the keyword **James** in the student's name
  * `add_student n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/Sec2 g/95421323 r/Mother`: Adds a student John Doe to the Tutor Buddy application
  * `delete_student 3`: Deletes the 3rd student in the student list

**Tuition Session**
  * `list_session`: List all tuition sessions
  * `find_session James`: Finds and lists all tuition sessions that James have
  * `add_session n/John Doe d/2021-01-01 t/13:00 k/120 s/Biology f/80`: Adds a tuition session for John Doe happening on 14-02-2021
  * `delete_session 1`: Deletes the 1st tuition session in the tuition session list

**General**
  * `exit`: Exits the application

6. Refer to the [Features](#features) below for details of each command.

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

Example:

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

### Listing all students' emails: `emails`
Displays concatenated string of students' emails, separated by `;`. Useful for sending mass emails to students.

Format: `emails`

Example:

\# | Student Name | Email
---- |---------|------|
1 | John Lee | johnlee@gmail.com
2 | Johnz Tan | johnztan@gmail.com
3 | Jon Koh | jonkoh@gmail.com
4 | Samuel Lee | sam@gmail.com

* `emails` returns `johnlee@gmail.com;johnztan@gmail.com;jonkoh@gmail.com;sam@gmail.com;`

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

Example:<br>
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
**List all emails** | `emails`

**Tuition Session**

Action | Format, Examples
--------|------------------
**List** | `list_session`
**Find** | `find_session KEYWORD`<br><br>e.g. `find_session John`
**Add** | `add_session n/STUDENT_NAME d/DATE t/TIME k/DURATION s/SUBJECT f/FEE`<br><br> e.g. `add_session n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80`
**Delete** | `delete_session n/STUDENT_NAME i/SESSION_INDEX`<br><br>e.g. `delete_session n/John Lee i/1`
