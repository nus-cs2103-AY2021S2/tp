---
layout: page
title: User Guide
---

<div align="center">
  <img alt="logo" src="images/tutorbuddy_logo.png">
</div>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 1. **Introduction**
TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students' contacts,
provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation.
TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities
effectively.

--------------------------------------------------------------------------------------------------------------------
## 2. **About**
This user guide provides documentation on the installation and usage of TutorBuddy.
It also provides a comprehensive description of features available to you and
includes a [quick start](#3-quick-start) section that helps you get started.

This guide uses the following features to make it easier for you to navigate around:

* Words that look like [this](#2-about) are hyperlinks, where clicking on it will redirect the user to the related section.
* Words that look like `this` refer to keywords used as part of commands or responses from TutorBuddy.
* Words that look like <kbd>this</kbd> refer to keyboard keys that you can press.

<div markdown="block" class="alert alert-info">

Boxes with the :information_source: icon contain additional useful information.

</div>

<div markdown="block" class="alert alert-primary">

Boxes with the :bulb: icon contain additional tips and tricks to help you get the most out of TutorBuddy.

</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 3. **Quick start**
This section provides information on how to quickly start using TutorBuddy.

### 3.1 Installation
Here are a few steps to get you started on TutorBuddy:

1. Ensure you have **Java 11** or above installed in your computer.

2. Download the latest version of TutorBuddy [here](https://github.com/AY2021S2-CS2103T-T11-1/tp/releases).

3. Move the downloaded TutorBuddy to a folder of your choice. This will be known as the home folder of TutorBuddy.

4. Double-click the file to start the application. An application similar to the one below should appear in a few
   seconds.<br>

![QuickStart](images/QuickStart.png)

### 3.2 Using TutorBuddy

This section offers an overview of the layout in TutorBuddy.

There are three main areas in TutorBuddy:

1. Utility area

2. Main viewing area

3. Result display box and command box

<a name="application-overview"><img alt="application-overview" src="images/ApplicationOverview.png"></a>

1. The utility area consists of 2 tabs: **File**, and **Help**.
    * The **File** tab consists of an exit button. To exit TutorBuddy, click on the exit button. Alternatively, use the `exit` command to exit the application.
    * The **Help** tab consists of a help button. If you require any assistance, click on the help button, copy the link displayed and paste it into any web browser.
    Alternatively, press <kbd>F1</kbd> to bring up the help window.

2. The main viewing area consists of 3 tabs: **Home**, **Tuition** and **Calendar**.
    * The **Home** tab gives tutors a quick overview of important matters. This includes their upcoming tuition lessons, as well as tuition fees receivable for the past 3 months.
    * The **Tuition** tab allow tutors to view and manage their students and lessons.
    * The **Calendar** tab allow tutors to have a weekly view of all sessions.

3. The command box is the area for you to enter your commands. The result of each command will be shown in the result
   display box, which is located directly above the command box. This area is always present in all tabs.
    * Type a command in the command box and press <kbd>Enter</kbd> to execute it.<br>

  Using the [sample data](#sample-data) provided, here are some example commands you can try:
  * **`list`** : Lists all students and sessions.
  * **`add_student`** `n/John Doe p/98765432 e/johnd@example.com a/John street, Block 123, #01-01 l/Sec2 g/95421323 r/Mother` : Adds a student named **John Doe**
    to TutorBuddy.
  * **`delete_student`** `3` : Deletes the 3rd student shown in the Student section.
  * **`add_session`** `n/John Doe d/2021-01-01 t/13:00 k/120 s/Biology f/80`: Adds a single tuition session for John Doe happening on 2021-01-01
  * **`add_rec_session`** `n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80 b/7 e/2021-01-15`: Adds a 7-day recurring session for John Doe happening from 2021-01-01 to 2021-01-15
  * **`exit`** : Exits the application.<br>

Refer to the [Commands](#42-commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 4. **Features**

### 4.1 View

TutorBuddy allows the user to take full control of managing their students and tuition sessions with the following 3 views:
1. Home
2. Tuition
3. Calendar

<div markdown="block" class="alert alert-primary">
:bulb: Clicking and focusing on the tab header allows the user to change tab quickly
using the arrow keys.
</div>

#### 4.1.1 Home
The Home page is split into two views: **Reminder** and **Monthly Fees**.
![home page](images/HomePage.png)

##### Reminder
The Reminder section displays upcoming tuition sessions for 3 consecutive days, starting today.
The upcoming tuition sessions are sorted in chronological order here.
<div markdown="block" class="alert alert-info">
:information_source: If today is a Monday, then the Reminder section will display all sessions on this Monday,
Tuesday and Wednesday.
</div>

##### 3 Months Monthly Fee
The 3 Months Monthly Fees section displays tuition fees for the current month and the past two months.

<div markdown="block" class="alert alert-info">
:information_source:
Note: The current month and past two months are calculated when the user first opens the application. No other months can be added to this section.
</div>

#### 4.1.2 Tuition
The Tuition page is split into two views: **Student** and **Session**.
![tuition page](images/TuitionPage.png)

<div markdown="block" class="alert alert-primary">
:bulb: This is the main tab for managing your students and sessions. Navigating to this tab before using commands such as
<code>add_student</code>, <code>find_student</code>, <code>add_session</code> or <code>delete_session</code> allows the user
to see the reflected changes on the <strong>Student</strong> and <strong>Session</strong> list view.
</div>

##### Student
The Student section displays students in TutorBuddy.

##### Session
The Session section displays sessions corresponding to each student in TutorBuddy.

#### 4.1.3 Calendar
The Calendar section provides a weekly view of all tuition sessions in TutorBuddy.
![calendar](images/CalendarPage.png)
The left and right arrow allows you to view past and future tuition sessions.
Click the 'Today' button to be brought back to this week's view.

### 4.2 Commands

Commands are the main way you interact with TutorBuddy.

This section provides information about all commands that is available in TutorBuddy.

<div markdown="block" class="alert alert-info">
:information_source: All examples from this point onwards are based on the sample data unless otherwise stated. <br>
Each example is run independently of the other examples. <br>
The sample data can be found <a href="#sample-data">[here]</a>.
</div>

#### 4.2.1 Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

This section details the format of the commands available in TutorBuddy. We will adhere to the following:


* Words in `UPPER_CASE` are the parameters / information to be supplied by the user.<br>
  e.g. in `add_student n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `find_student KEYWORD [MORE_KEYWORDS]` can be used as `find_student John Samuel` or as `find_student John`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/STUDENT_PHONE_NUMBER`, `p/STUDENT_PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/98765432 p/99999999`, only `p/99999999` will be taken.

* Extra parameters for commands that do not take in parameters (such as `emails`, `help`, `exit`, `clear` and `list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Names are case-sensitive.<br>
  e.g. `john` is considered different from `John`. <br>
  However, note that in [`find_student`](#locating-student-by-name-find_student), the search is case-insensitive.

* Names cannot be duplicates.<br>
  e.g. There cannot be 2 different students of the same name `John Tan` and `John Tan`.
  
* Indexes must be positive whole numbers 1, 2, 3, …

* The `STUDENT_INDEX` refers to the index number shown in the [displayed student list](#application-overview).

* The `SESSION_INDEX` refers to the index number shown in the [displayed session list](#application-overview).

* Time must be input in the format, `hh:mm`.
  e.g. `13:00` is allowed, but not `1300`.

* Date must be input in the format, `yyyy-mm-dd`.
  e.g. `2021-01-01` is allowed, but not `01-01-2021`.
  
* Year must be between 1970 and 2037, both inclusive, in consideration of the
  [2038 problem](https://en.wikipedia.org/wiki/Year_2038_problem)
  e.g. `1969` and `2038` is not allowed.



</div>

The following subsections will elaborate on the specific details of each TutorBuddy's command.

#### 4.2.2 General

##### Viewing help : `help`

Displays URL to TutorBuddy's User Guide and a list of all commands and their usages.

![help message window](images/helpMessageWindow.png)

Format: `help`

To access the help page, do one of the following:
1. type `help` in the command box 
2. press <kbd>F1</kbd>
3. click the 'Help' button on the top left and click on the dropdown
![help message](images/helpMessage.png)

##### Listing all students and sessions: `list`

Displays list of all students and all sessions in TutorBuddy.

Format: `list` <br>

Example:
1. Type in the command `list` and press <kbd>Enter</kbd>.
2. All students and sessions on their respective list view on the Tuition tab.
![list](images/command_list.png)

##### Clearing all data : `clear`

Clears all student and session data.

<div markdown="span" class="alert alert-primary">:bulb: Tip:
TutorBuddy provides sample data to allow you to try out the application easily. <br>
Run the <code>clear</code> command <b>after</b> you have explored the application and wants to start working with TutorBuddy in a clean slate.
</div>

Format: `clear`

Example:
1. Type in the command `clear` and press <kbd>Enter</kbd>.
2. All data in TutorBuddy will be wiped clean.

##### Exit the program: `exit`

Exits the program

Format: `exit`

To exit the program, do one of the following:
1. Type in the command `exit` and press <kbd>Enter</kbd>
2. Press the 'X' at the top right of the TutorBuddy
3. Click on the 'File' button on the top left and click on the dropdown
![exit command](images/exit_command.png)

#### 4.2.3 Managing Students

##### Adding a student: `add_student`

Adds a student to TutorBuddy with their respective guardian and study information.

Format: `add_student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS l/STUDY_LEVEL g/GUARDIAN_PHONE_NUMBER r/RELATIONSHIP_WITH_GUARDIAN` <br>

<div markdown="block" class="alert alert-info">
:information_source: <code>STUDENT_PHONE_NUMBER</code>, <code>GUARDIAN_PHONE_NUMBER</code> should be in Singapore's phone formatting (i.e. starting with either 6, 8 or 9 and with 8 digits).<br>
:information_source: TutorBuddy automatically shows the full list of students and sessions after a successful <code>add_student</code> command.
</div>
<div markdown="block" class="alert alert-primary">
:bulb: TutorBuddy does not restrict the <code>STUDY_LEVEL</code> due to the vast number of education levels. The user 
is free to input any education level.<br>
Example: Any of the inputs here will be accepted. <code>Sec 2</code>, <code>Secondary 2</code>, <code>JC 1</code>, <code>Poly Y2</code> <br>
:bulb: If you have different students of the same <code>NAME</code> consider changing the name. <br>
Example: Add a <code>John Doe Bespectacled</code> and a <code>John Doe Unadorned</code>
</div>

Example:
1. You would like to add a new student, **John Doe** to TutorBuddy.
2. Type in `add_student n/John Doe p/98765432 e/johnd@example.com a/John street, Block 123, #01-01 l/Sec2 g/95421323 r/Mother` 
   which includes all the essential information the application needs from the student and press <kbd>Enter</kbd>.
  
![add_student_example](images/enhao/add_student%20-%20edited.png)

<ol start="3">
    <li>Scrolling down to the end of the student list, you will see that <b>John Doe</b> has been added to TutorBuddy.</li>
</ol>

![add_student_result_example](images/enhao/add_student_result%20-%20edited.png)

##### Locating student by name: `find_student`

Filters the student and session list to only include student(s), and their respective session(s) whose student name contains any of the given keywords.

Format: `find_student KEYWORD [MORE_KEYWORDS]`
* The search will be case-insensitive. e.g. searching `john` will match `JOHN`
* Only the name of students can be searched
* The order of the keywords does not matter. e.g. `John Doe` will match `Doe John`
* Only full words will be matched e.g. `Do` will not match `Doe`
* Students with name matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `John Doe` will return `John Tan`, `Peter Doe`

Example 1:

\# | Student Name
---- |---------
1 | John Lee
2 | Johnz Tan
3 | Jon Koh
4 | Samuel Lee

*Figure 1: Current State of Student List*

Example command usages from the current student list shown in Figure 1:

* `find_student John` returns "John Lee"
* `find_student Sam` returns nothing
* `find_student Lee` returns "John Lee" and "Samuel Lee"
* `find_student Johnz Lee` returns "John Lee", "Johnz Tan" and "Samuel Lee"

Example 2:
1. You want to find students whose name contains Alex, David and Roy.

2. Type `find_student alex david roy` <i>(Note: keywords are case-insensitive)</i> and press <kbd>Enter</kbd>.

![find_student_example](images/enhao/find_student%20-%20edited.png)

<ol start="3">
    <li>The student and session list will be filtered and only contains students and sessions of students whose name contains the keywords specified.</li>
</ol>

![find_student_result_example](images/enhao/find_student_result%20-%20edited.png)


##### Editing a student: `edit_student`

Edits an existing student's detail(s) in TutorBuddy.

Format: `edit_student STUDENT_INDEX [n/NAME] [p/STUDENT_PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/STUDY_LEVEL] [g/GUARDIAN_PHONE_NUMBER] [r/RELATIONSHIP_WITH_GUARDIAN]` <br>
* `STUDENT_PHONE_NUMBER` and `GUARDIAN_PHONE_NUMBER` should be in Singapore's phone formatting (i.e. starting with either 6, 8 or 9 with 8 digits)

Example:
1. You would like to edit **Alex Yeoh** phone number, email address and address as they are wrong. 
2. Type `edit_student 1 p/98825432 e/alexy@example.com a/Alex street, Block 123, #01-01` and press <kbd>Enter</kbd>. (This edits the student **Alex Yeoh's** `STUDENT_PHONE_NUMBER`, `EMAIL`, and `ADDRESS` which is at index 1)

![edit_student_example](images/enhao/edit_student%20-%20edited.png)

<ol start="3">
    <li>The edited student information will be reflected in the student list.</li>
</ol>

![edit_student_result_example](images/enhao/edit_student_result%20-%20edited.png)

<div markdown="span" class="alert alert-primary">:bulb: Tip:
Use <code>find_student</code> command to filter the list of students you want.<br>
The <code>STUDENT_INDEX</code> will be based on the current / filtered list.<br>
(E.g. If John Doe is the 500th student, instead of <code>edit_student 500 [...]</code>, you can use <code>find_student John Doe</code> and
<code>edit_student 1 [...]</code> if the particular John Doe you wish to edit is the 1st in the list)
</div>


##### Deleting a student: `delete_student`

Deletes the specified student from TutorBuddy.

Format: `delete_student STUDENT_INDEX` <br>

Example:
1. You would like to delete the 2nd student based on the current list of students. 
2. Type `delete_student 2` and press <kbd>Enter</kbd>.

![delete_student_example](images/enhao/delete_student%20-%20edited.png)

<ol start="3">
    <li>The 2nd student in the list (<b>Bernice Yu</b>) and her associated sessions will be deleted.</li>
</ol>

![delete_student_result_example](images/enhao/delete_student_result%20-%20edited.png)

<div markdown="span" class="alert alert-primary">:bulb: Tip:
Similar to <code>edit_student</code>, you can also use <code>find_student</code> command to filter the list of students you want.<br>
The <code>STUDENT_INDEX</code> will be based on the current / filtered list.<br>
(E.g. If John Doe is the 500th student, instead of <code>delete_student 500</code>, you can use <code>find_student John Doe</code> and
<code>delete_student 1</code> if the particular John Doe you wish to delete is the 1st in the list)
</div>

##### Listing students' emails based on current list: `emails`
As a user, you might want to quickly get all the email address of your students and send them a mass email.
This feature displays a concatenated text of students' emails based on current list, separated by `;` which can be copied to their respective email client (E.g. Gmail) for mass sending of emails to students.

Format: `emails`

Example:

1. You want to send a mass email to **all** of your students. 

2. Type `emails` and press <kbd>Enter</kbd>.

![emails_example](images/enhao/email%20-%20edited.png)

<ol start="3">
    <li>The concatenated text of all the students' emails will be displayed.</li>
</ol>

![emails_result_example](images/enhao/email_result%20-%20edited.png)

<ol start="4">
    <li>The user can copy the values in the result display box to the "To" field of any email client that they have (E.g. Gmail).</li>
</ol>

![emails_client_example](images/enhao/email_client-edited.png)

<div markdown="span" class="alert alert-primary">:bulb: Tip:
Similar to <code>edit_student</code>, you can also use <code>find_student</code> command after step 1 to filter the list of students you want.<br>
The email addresses displayed will be based on the current list.<br>
(E.g. If you only want the the email addresses of <code>Alex Yeoh</code>, <code>David Li</code> and <code>Roy Balakrishnan</code> from the sample data,
you can use <code>find_student Alex David Roy</code> and <code>emails</code>
to get the concatenated text of the 3 students' email addresses.)
</div>


#### 4.2.4 Managing Sessions

Please note that the following session commands can only be used after you have at least 1 student in TutorBuddy.
Click [here](#423-managing-students) if you want to find out more on how to add your first student.

<div markdown="block" class="alert alert-info">
:information_source: Note that the examples in this section are done consecutively with an existing student, <b>John Doe</b>, in TutorBuddy.
</div>

##### Adding a single tuition session: `add_session`

Adds a single tuition session to TutorBuddy.

Format: `add_session n/STUDENT_NAME d/DATE t/TIME k/DURATION s/SUBJECT f/FEE`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `DATE` should be the date of the session in `yyyy-mm-dd` format
* `DURATION` should be in minutes
* `FEE` should be the total tuition fee for 1 session of the given duration
    * TutorBuddy only accepts `FEE` within an acceptable range of values between 0 and 999999.99 (both inclusive) as we do not expect a single session to cost more than $999,999.99 or more.
    * `FEE` only allows values up to 2 decimal points only

<div markdown="span" class="alert alert-primary">:bulb: Tip:
TutorBuddy takes care of overlapping session for you by giving a gentle prompt, so you don't have to worry about it.
</div>


<div markdown="block" class="alert alert-info">
:information_source: Note that the [Time given in <code>DATE</code> + <code>DURATION</code>] should not exceed 23:59H of the same day. 
This is done as we do not except tutors to teach lessons beyond the day itself.
</div>


Example:
1. You would like to add a new session for **John Doe** on the date **01 Jan 2021** at **18:00** where you will teach **Biology** for **120** minutes and charge a fee of **$80**.
2. Type in `add_session n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80` and press <kbd>Enter</kbd> to add the session information into TutorBuddy.

![add_session_example](images/choonwei/add_session.png)

<ol start="3">
    <li>You will now see that a new session is added in the session list under <b>John Doe</b> and is marked with a light blue letter <b>"I"</b> to indicate an individual session.</li>
</ol>

![add_session_result_example](images/choonwei/add_session_result.png)


##### Adding a recurring tuition session: `add_rec_session`

Adds a recurring tuition session to TutorBuddy that allows you to specify the number of days between each recurring session.

Format: `add_rec_session n/STUDENT_NAME d/START_DATE e/END_DATE b/INTERVAL t/TIME k/DURATION s/SUBJECT f/FEE `

Arguments similar to `add_session` command except the following:
* `START_DATE` should be the date of the first session
* `END_DATE` should be the date of the last session
* `INTERVAL` is the number of days to the next session <br/>
  e.g. `INTERVAL` = 7 for weekly sessions

Example:
1. You would like to add a new recurring session for **John Doe** on the date **02 Jan 2021** at **18:00** where you will teach
   **Math** for **120** minutes and charge a fee of **$80** for each lesson. The session will occur every **7** days until **29 Jan 2021**.
2. Type in `add_rec_session n/John Doe d/2021-01-02 e/2021-01-30 b/7 t/18:00 k/120 s/Math f/80` and press <kbd>Enter</kbd> to add the session information into TutorBuddy.

![add_recurring_session_example](images/choonwei/add_recurring_session.png)

<ol start="3">
    <li>You will now see that a new recurring session is added in the session list under <b>John Doe</b> and is marked with an orange letter <b>"R"</b> to indicate a recurring session.</li>
</ol>

![add_recurring_session_result_example](images/choonwei/add_recurring_session_result.png)

##### Deleting a tuition session: `delete_session`

Deletes the specified tuition session (Either an individiual session or the entire recurring session) from TutorBuddy.

Format: `delete_session n/STUDENT_NAME i/SESSION_INDEX`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `SESSION_INDEX` refers to the session index for a particular student
* Deletes the tuition session at the specified `SESSION_INDEX`
* The index must be a positive integer 1, 2, 3, …​

<div markdown="block" class="alert alert-info">
:information_source: Note that deleting a student automatically deletes all sessions associated with the student as well.
</div>

Example 1:
1. You would like to delete an existing session for **John Doe** on the date **01 Jan 2021** at **18:00**.
2. Locate the session index to delete on the right of the session card.

![delete_session_example](images/choonwei/delete_session.png)

<ol start="3">
    <li>Using the index from step 2, type in <code>delete_session n/John Doe i/1</code> and press <kbd>Enter</kbd> to deletes <b>John Doe</b>'s first session.</li>
    <li>You will now see that the session has been deleted.</li>
</ol>

![delete_session_result_example](images/choonwei/delete_session_result.png)

Example 2:
1. You would like to delete an existing recurring session for **Alex Yeoh** starting on the date **10 Mar 2021** to **07 Apr 2021**.
2. Locate the session index to delete on the right of the session card.

![delete_session_recurring_example](images/choonwei/delete_session_recurring.png)

<ol start="3">
    <li>Using the index from step 2, type in <code>delete_session n/Alex Yeoh i/1</code> and press <kbd>Enter</kbd> to deletes <b>Alex Yeoh</b>'s first and only session.</li>
    <li>You will now see that the session has been deleted.</li>
</ol>

![delete_session_recurring_ result_example](images/choonwei/delete_session_recurring_result.png)

##### Deleting a recurring tuition session: `delete_rec_session`

Deletes a single session from an existing recurring tuition session of a specified student from TutorBuddy.
Splits the original recurring session into two sessions that span the period exclusively before and after the deleted single session.

Format: `delete_rec_session n/STUDENT_NAME i/SESSION_INDEX d/DATE`

Arguments similar to `delete_session` command except the following:
* `DATE` should be the date of the single session to be deleted
<div markdown="block" class="alert alert-info">
:information_source: Note that the <code>DATE</code> should fall under a valid session date that belongs in the specified recurring session range
</div>

Example:
1. You would like to delete an existing session from a recurring session for **John Doe** on the date **16 Jan 2021** at **18:00**.
2. Locate the recurring session index to delete on the right of the session card.

![delete_recurring_session_example](images/choonwei/delete_recurring_session.png)

<ol start="3">
    <li>Using the index from step 2, type in <code>delete_rec_session n/John Doe i/1 d/2021-01-16</code> and press <kbd>Enter</kbd> to delete a single session from <b>John Doe</b>'s recurring session.</li>
    <li>You will now see that this splits the original recurring session into two recurring session from <b>02 Jan 2021</b> to <b>09 Jan 2021</b> and another from <b>23 Jan 2021</b> to <b>30 Jan 2021</b>.</li>
</ol>

![delete_recurring_session_result_example](images/choonwei/delete_recurring_session_result.png)
  

#### 4.2.5 Fees

##### Calculating monthly session fee for a particular student: `fee`

Calculates and displays the total monthly session fee for a particular student for a particular month and year.

Format: `fee n/STUDENT_NAME m/MONTH y/YEAR`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `MONTH` should be a positive integer between 1 and 12 inclusive
* `YEAR` should be a positive integer between 1970 and 2037 inclusive

Example:
1. You want to get the monthly fee payable by **Alex Yeoh** for March 2021.
2. Type `fee n/Alex Yeoh m/3 y/2021` and press <kbd>Enter</kbd>. 

![fee_example](images/enhao/fee%20-%20Edited.PNG)

<ol start="3">
    <li>The result display box displays <b>Alex Yeoh</b>'s monthly fee for March 2021.</li>
</ol>

![fee_result_example](images/enhao/fee_result%20-%20Edited.PNG)

<div markdown="block" class="alert alert-info">
:information_source: Note that calculation of fee is only guaranteed to be accurate for totaled fees of up to $2,147,483,647. 
We do not expect a person to earn more than $2,147,483,647 a month, hence the fee calculation should be accurate for all actual users.
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 5. **Command summary**

**General**

Action | Format
--------|------------------
**Help** | `help`
**List all students and sessions** | `list`
**Clear all students and sessions** | `clear`
**Exit TutorBuddy** | `exit`


**Students**

Action | Format | Examples (if applicable)
--------|------------------|-------
**Add** | `add_student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS l/STUDY_LEVEL g/GUARDIAN_PHONE_NUMBER r/RELATIONSHIP_WITH_GUARDIAN` | `add_student n/John Doe p/98765432 e/johnd@example.com a/John street, Block 123, #01-01 l/Sec2 g/95421323 r/Mother`
**Find** | `find_student KEYWORD [MORE_KEYWORDS]` | `find_student John Alex`
**Edit** | `edit_student STUDENT_INDEX [n/NAME] [p/STUDENT_PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/STUDY_LEVEL] [g/GUARDIAN_PHONE_NUMBER] [r/RELATIONSHIP_WITH_GUARDIAN]` | `edit_student 1 p/98825432 e/alexy@example.com a/Alex street, Block 123, #01-01`
**Delete** | `delete_student STUDENT_INDEX` | `delete_student 3`
**List students' emails based on current list** | `emails`


**Tuition Sessions**

Action | Format | Examples
--------|------------------|-------
**Add Single Session** | `add_session n/STUDENT_NAME d/DATE t/TIME k/DURATION s/SUBJECT f/FEE` | `add_session n/John Doe d/2021-01-01 t/18:00 k/120 s/Biology f/80`
**Add Recurring Session** | `add_rec_session n/STUDENT_NAME d/DATE e/LASTDATE b/INTERVAL t/TIME k/DURATION s/SUBJECT f/FEE` | `add_rec_session n/John Doe d/2021-01-02 e/2021-01-30 b/7 t/18:00 k/120 s/Math f/80`
**Delete Session** | `delete_session n/STUDENT_NAME i/SESSION_INDEX` | `delete_session n/John Doe i/1`
**Delete Recurring Session** | `delete_rec_session n/STUDENT_NAME i/SESSION_INDEX d/DATE` | `delete_rec_session n/John Doe i/1 d/2021-01-16`

**Fees**

Action | Format | Examples
--------|------------------|-------
**Check fee** | `fee n/STUDENT_NAME m/MONTH y/YEAR` | `fee n/John Doe m/1 y/2021`

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 6. **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorBuddy home folder.

**Q**: May I edit the data file? <br>
**A**: Advanced users are welcome to update data directly by editing that data file. Tutorbuddy data are saved as a JSON file `[JAR file location]/data/tutorbuddy.json`. <br>
:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TutorBuddy may not function as intended.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 7. **Glossary**

This glossary provides definitions for the special terms used in this user guide.

**Single Session:** One time session on a particular date.

**Recurring Session:** Repeating session that occurs every number of days.

**URL:** An address of a web page. e.g. **https://www.google.com/**

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 8. **Appendix**

### Sample Data

#### Sample Data (Students)

![sample_student](images/enhao/sample_student.png)

#### Sample Data (Sessions)
![sample_session](images/enhao/sample_session.png)

--------------------------------------------------------------------------------------------------------------------
