---
layout: page
title: User Guide
---

RemindMe User Guide v1.3
---
This user guide provides [start-up](#2-quick-start) instructions as well as detailed descriptions and usage of
all the [features](#3-features) in the RemindMe app. You can also access the product website via [link](https://ay2021s2-cs2103t-w15-1.github.io/tp/).

<div style="page-break-after: always;"></div>

## Table of Contents

* **[1. Introduction](#1-introduction)**
* **[2. Quick Start](#2-quick-start)**
* **[3. Features](#3-features)**
    * **[3.1 Viewing Help : `help`](#viewing-help--help)**
    * **[3.2 Adding: `add`](#32-adding-add)**
      * [3.2.1 Adding a person](#321-adding-a-person)
      * [3.2.2 Adding a module](#322-adding-a-module)
      * [3.2.3 Adding an assignment](#323-adding-an-assignment)
      * [3.2.4 Adding an exam](#324-adding-an-exam)
      * [3.2.5 Adding a general event](#325-adding-a-general-event)
    * **[3.3 Editing: `edit`](#33-editing-edit)**
      * [3.3.1 Editing a person](#331-editing-a-person)
      * [3.3.2 Editing a module](#332-editing-a-module)
      * [3.3.3 Editing an assignment](#333-editing-an-assignment)
      * [3.3.4 Editing an exam](#334-editing-an-exam)
      * [3.3.5 Editing a general event](#335-editing-a-general-event)
    * **[3.4 Finding: `find`](#34-finding-find)**
      * [3.4.1 Finding persons](#341-finding-persons)
      * [3.4.2 Finding modules](#342-finding-modules)  
      * [3.4.3 Finding general events](#343-finding-general-events)
    * **[3.5 Marking as done `done`](#35-marking-as-done-done)**
    * **[3.6 Deleting: `delete`](#36-deleting-delete)**
      * [3.6.1 Deleting a person](#361-deleting-a-person)
      * [3.6.2 Deleting a module](#362-deleting-a-module)
      * [3.6.3 Deleting an assignment](#363-deleting-an-assignment)
      * [3.6.4 Deleting an exam](#364-deleting-an-exam)
      * [3.6.5 Deleting a general event](#365-deleting-a-general-event)  
    * **[3.7 Clearing: `clear`](#37-clearing-clear)**
      * [3.7.1 Clearing RemindMe](#371-clearing-remindme)
      * [3.7.2 Clearing all persons](#372-clearing-all-persons)
      * [3.7.3 Clearing all modules](#373-clearing-all-modules)
      * [3.7.4 Clearing all general events](#374-clearing-all-general-events)
      * [3.7.5 Clearing all done assignments](#375-clearing-done-assignments)    
    * **[3.8 Viewing calendar: `calendar`](#38-viewing-calendar-calendar)**
    * **[3.9 Saving the data](#39-saving-the-data)**
    * **[3.10 Reminder window](#310-reminder-window)**
    * **[3.11 Exiting the program: `exit`](#311-exiting-the-program-exit)**
* **[FAQ](#4-faq)**
* **[Command summary](#5-command-summary)**

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction
RemindMe is designed for students from the School of Computing(SOC) in Nation University of Singapore(NUS) to manage school modules and 
events meanwhile keep track of submission deadlines and friends' birthday. To better help the students with scheduling and visualization,
it even provides a calendar view of all the events. RemindMe is optimized for students who prefer to work with
a Command Line Interface(CLI) while still having the benefits of a Graphical User Interface(GUI). We believe the tech-savvy SOC students
can reap more benefits from our RemindMe app as compared to other traditional general scheduling apps. What are you waiting for?
Jump to [Section 2](#2-quick-start) to get started. Hope you enjoy our app.
 


--------------------------------------------------------------------------------------------------------------------


## 2. Quick Start

1. Ensure you have Java `11` or above installed on your computer. You can download Java 11 via this [link.](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html)
   * Set-up tutorial:
        * [Windows user](https://java.tutorials24x7.com/blog/how-to-install-java-11-on-windows)
        * [Mac user](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
        * [Linux user](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)
   
2. Download the latest `remindMe.jar` from our [GitHub release page](https://github.com/AY2021S2-CS2103T-W15-1/tp/releases).
Copy the file to the folder you want to use as the home folder for your RemindMe.
Double click the file to start the app. 
   
3. Alternatively, you can run the command line java -jar RemindMe.jar in your terminal to start the application.
Type the command in the command box and press Enter to execute it.
The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

4. Press start to proceed to the main window.
   ![UiMainWindow](images/UiMainWindow.png)      

5. Type the command in the command box and press enter on the keyboard to execute it. <br> eg. typing `help` and press enter will open the help window.

6. Some example commands you can try:
   * **`add m/CS2103`** : Adds a module name CS2103.
     
   * **`calendar`**: Dsiplays the calendar with the tasks' deadlines and friends' birthdays.
     
   * **`exit`** : Exits the app.
   
7. Refer to [Section 3: Features](#3-features) for details of each command.  


--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add m/MODULE`, `MODULE` is a parameter which can be used as `add m/CS2103`.


* Items in square brackets are optional.<br>
  e.g `add n/Name [t/TAG]` can be used as `add n/Alice t/friends` or as `add n/Alice`.
  

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### 3.1 Viewing Help : `help`

Shows a help page.

![help message](images/helpMessage.png)

Format: `help`

### 3.2 Adding: `add`
This section show features that deals with the adding of entries in the RemindMe app.
<br>
<br>
#### 3.2.1 Adding a person
Add a person with his/her birthday to the RemindMe app.

* Note: Duplicate person(person with the same name) is not allowed. 

Format: `add n/NAME b/BIRTHDAY [t/TAG]`

Examples:
* `add n/Alice b/22/10/1999`
* `add n/Benson b/30/09/1997 t/friends`

Results expected:
* `New person added: Alice` <br> `Birthday: 22/10/1999`


#### 3.2.2 Adding a module
Add a module with the module description.

* Note: Duplicate person(person with the same name) is not allowed.

Format: `add m/MODULE`

Example:
* `add m/CS2101`

Result expected:

* `New module added: CS2101`

#### 3.2.3 Adding an assignment
Add an assignment under a module with the deadline provided.

* **Note:** 
    * **The module related has to be added first before the adding of an assignment.**
    * **Duplicate assignment(assignment under the same `module` with same `description` and `deadline`) is not allowed.**

Format: `add m/MODULE a/ASSIGNMENT by/dd/mm/yyyy HHmm`

Example:
* `add m/CS2101 a/Essay 1 by/01/01/2021 2359`

Result expected:

* `New assignment added: Essay 1 due: 01/01/2021 2359`

#### 3.2.4 Adding an exam
Add an exam under a module with the date time provided.

* **Note:** 
    * **The module related has to be added first before the adding of an assignment.**
    * **Duplicate exam(exam under the same `module` with the same `date time`) is not allow.**

Format: `add m/MODULE e/dd/mm/yyyy HHmm`

Example:

* `add m/CS2101 e/01/01/2021 2359`

Result expected:
* `New exam added: Exam is on: 01/01/2021 2359`

#### 3.2.5 Adding a general event
Add a general event with the description and date time provided.

* **Note:** 
    * **Duplicate event(event with the same `description` and `date time`) is not allow.**
    
Format: `add g/GENERALEVENT on/dd/mm/yyyy HHmm`

Example:

* `add g/School contest on/10/10/2021 1200`

Result expected:
* `New event added: School contest on 10/10/2021 1200`

### 3.3 Editing: `edit`
This section show features that deals with the editing of entries in the RemindMe app.
#### 3.3.1 Editing a person



#### 3.3.2 Editing a module
#### 3.3.3 Editing an assignment
#### 3.3.4 Editing an exam
#### 3.3.5 Editing a general event

### 3.4 Finding: `find`
This section show features that deals with the locating of entries in the RemindMe app.
* **Note:**
    * **All find operations are case-insensitive**
    * **Order of keywords used doesn't matter**
    * **Only whole words are matched**
<br>
<br>
#### 3.4.1 Finding persons
Finds person whose names contain any of the given keywords.  

* **Note:**
    * **Only the names are matched to the keywords**
    * **Persons matching one keyword will be returned**
        * **Example: Alice Lim will return: `Alice Tan` & `Lim Bob`**

Format: `find n/KEYWORD [MORE KEYWORDS]`  

Examples:
* `find n/Alice`  
    returns `alice` and `Alice Lim `
* `find n/Tom Dick Harry`  
    returns anyone with names containing `Tom`, `Dick` or `Harry`
  
#### 3.4.2 Finding modules
Finds modules with titles containing any of the given keywords.

* **Note:**
    * **Only the module titles are matched to the keywords**
    * **Modules' titles matching one keyword will be returned**
    
Format: `find m/KEYWORD [MORE_KEYWORDS]`  

Examples: 
* `find m/CS2101`  
    returns `cs2101`, `CS2101`, `Cs2101` and `cS2101 best mod`  
* `find m/cs2103 cs2101`  
    returns any modules with titles containing `cs2101` or `cs2103`
  
#### 3.4.3 Finding general events
Finds general events with descriptions containing any of the given keywords.  

* **Note:**
    * **Only the descriptions of the general events are matched to the keywords**
    * **General events' descriptions matching one keyword will be returned**

Format: `find g/KEYWORD [KEYWORDS]`  

Examples:
* `find g/doctor`  
  returns `doctor`, `Doctor` and `doctor Appointment`
* `find g/friend meetup`  
  returns any general events with descriptions containing `friend` or `meetup`

### 3.5 Marking as done: `done`

### 3.6 Deleting: `delete`
This section show features that deals with the deleting of entries in the RemindMe app.
#### 3.6.1 Deleting a person
#### 3.6.2 Deleting a module
#### 3.6.3 Deleting an assignment
#### 3.6.4 Deleting an exam
#### 3.6.5 Deleting a general event

### 3.7 Clearing `clear`
This section show features that deals with the clearing of entries in the RemindMe app.
<br>
<br>
#### 3.7.1 Clearing RemindMe  
Clears all entries in RemindMe.  
Format: `clear`

#### 3.7.2 Clearing all persons
Clears all contacts in RemindMe.  
Format: `clear n/`

#### 3.7.3 Clearing all modules
Clears all modules in RemindMe.  
Format: `clear m/`

#### 3.7.4 Clearing all general events
Clears all general events in RemindMe.
Format: `clear g/`

#### 3.7.5 Clearing done assignments [coming in v1.3]
Details coming soon...

### 3.8 Viewing calendar `Calendar`
### 3.9 Saving the data
### 3.10 Reminder window
### 3.11 Exiting the program `exit`

## 4. FAQ
## 5. Command Summary
Action | Format
--------|----------------
Add person | `add n/NAME b/BIRTHDAY [T/TAG]`
Add module | `add m/MODULE`
Add assignment | `add m/MODULE a/ASSIGNMENT by/dd/mm/yyyy HHmm`
Add exam | `add m/MODULE e/dd/mm/yyyy HHmm`
Add event | `add g/GENERALEVENT on/dd/mm/yyyy HHmm`
Find persons | `find n/KEYWORD [MORE KEYWORDS]`
Find modules | `find m/KEYWORD [MORE KEYWORDS]`
Find general events | `find g/KEYWORD [MORE KEYWORDS]`
Delete assignment | `delete (assignment description)`
Clear Remindme | `clear`
Clear persons | `clear n/`
Clear modules | `clear m/`
Clear general events | `clear g/`
View assignments | `view A`
View events | `view E`
Turn on/off reminder | `remind`
See commands available | `help`
View calendar | `calendar`
Save data | `save`
Edit data | `edit`





