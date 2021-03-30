---
User Guide
---
_**Tutor Tracker**_ is a **desktop app designed to help secondary school students manage tutors and tuition appointments, optimised for use via a Command Line Interface** (CLI) for a fast and streamlined experience while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tutor Tracker can get your tuition contact management tasks done faster than traditional GUI apps.

* Table of Contents
    * Quick Start
    * Features
    * Q&A
--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutortracker.jar` from [here](https://github.com/AY2021S2-CS2103-T14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TutorTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`appointment`**`n/Chloe Lim s/English d/2021-4-20 fr/2:00pm l/Bedok` : Adds an appointment with a tutor named `Chloe Lim` to the Tutor Tracker.
    * **`list_appointments`** : Lists all personal tuition appointments.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `appointment n/NAME`, `NAME` is a parameter which can be used as `appointment n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [l/LOCATION]` can be used as `n/John Doe l/Clementi` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[l/LOCATION]…​` can be used as ` ` (i.e. 0 times), `l/Bedok`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME s/SUBJECT`, `s/SUBJECT n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `l/Bedok l/Clementi`, only `l/Clementi` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Parameters in angle brackets (`<`, `>`) must be supplied together in order as a group. eg. with `<s/SUBJECT_NAME r/RATE>`, both subject name and rate must be supplied in order.

</div>

### Add new tutor details: `add_tutor`

Add a new tutor and enter their basic details.

Details:
* Name
* Phone Number
* Email
* Gender
* Location (multiple allowed)
* Subjects (multiple allowed)
    * Subject Name
    * Hourly Rate
    * Education Level
    * Years of Experience
    * Qualifications

Format:
`add_tutor n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS... <s/SUBJECT_NAME r/RATE l/EDUCATION_LEVEL y/YEARS q/QUALIFICATIONS> notes/NOTES`

Example Input:
`add_tutor n/John Doe p/98765432 e/johnd@example.com g/Male a/John street, block 123, #01-01 s/English r/50 l/Sec 3 y/5 q/A-Level s/Mathematics r/60 l/Sec 4 y/6 q/A-Level notes/cool tutor`

### List all tutors: `list_tutors`

View a list of all tutors known.

Example Output:
```
1) John Doe
2) Jane Doe
3) Peter Ng
```

### Deleting a tutor: `delete_tutor`

Delete a tutor by index.

Format: `delete_tutor INDEX`

Example: `delete_tutor 1`

### Editing a tutor: `edit_tutor`

Edit a tutor's information by index. Only the attributes present are changed in the tutor.

Format: `edit_tutor INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [a/ADDRESS] [<s/SUBJECT_NAME r/RATE l/EDUCATION_LEVEL y/YEARS q/QUALIFICATIONS>] note/NOTES`

Example: `edit_tutor 1 p/99824314 s/English r/50 l/Secondary 5 y/9 q/A-Level note/efficient`

### Viewing a tutor: `view_tutor`

Views a tutor's personal information.

Format: `view_tutor INDEX`

Example: `view_tutor 1`

Example Output:<br>
```
John Doe
98765432
John street, block 123, #01-01
johnd@example.com
Subjects:
1. English
    Level: Sec 3
    Rate: SGD60/hr
    Experience: 6 years
    Qualification: Bacholar of English Literature
```
### Adding a note: `add_note`

Shortcut for adding note to tutor at a particular index

Format: `add_note INDEX NOTE`

Example: `add_note 1 patient tutor`

Example Output:
on the right of ,<br>
```
John Doe 
98765432
John street, block 123, #01-01
johnd@example.com
Subjects:
1. English
    Level: Sec 3                     
    Rate: SGD60/hr
    Experience: 6 years
    Qualification: Bacholar of English Literature
```
```
Notes:
patient tutor
```
### Adding a note: `edit_note`

Shortcut for editing note to tutor at a particular index

Format: `edit_note INDEX NOTE`

Example: `edit_note 1 not patient`

Example Output:
on the right of ,<br>
```
John Doe 
98765432
John street, block 123, #01-01
johnd@example.com
Subjects:
1. English
    Level: Sec 3                     
    Rate: SGD60/hr
    Experience: 6 years
    Qualification: Bacholar of English Literature
```

```
Notes:
not patient
```
### Deleting a note `delete_note`

Deletes solely the note to tutor at a particular index

Format: `delete_note INDEX NOTE`

Example: `delete_note 1`

### List tutors with note `list_note`
Lists all the tutor with note

Format:`list_note`

### List tutors with note `list_note`
Export the tutor details together with the notes into a text file

Format:`list_note`

Example: `export 1`

### Adding a favourite: `favourite`

Label a tutor as a favourite.

Format: `favourite INDEX`

Example: `favourite 1`

Example Output:<br>
```
John Doe *
98765432
John street, block 123, #01-01
johnd@example.com
Subjects:
1. English
    Level: Sec 3
    Rate: SGD60/hr
    Experience: 6 years
    Qualification: Bacholar of English Literature
```

### Removing a favourite: `unfavourite`

Removes the favourite label from a tutor

Format: `unfavourite INDEX`

Example: `unfavourite 1`

### List all favourites: `list_favourites`

View a list of all favourites

Format: `list_favourites`

Example: `list_favourites`

### Adding an appointment : `add_appointment`

Adds an appointment with a specific tutor to the schedule.<br>

Format: `add_appointment e/EMAIL s/SUBJECT d/DATE fr/TIME_FROM l/LOCATION`

* The date format `yyyy-mm-dd` must be strictly followed. e.g. `2021-3-1`and `2021-03-01`.
* The time format `hh:mm a` must be strictly followed. e.g. `9:01 am` and `10:30 pm`.

Examples:<br>
* `appointment n/andew.ng@example.com s/Mathematics d/2021-3-1 fr/10:00am l/Bedok`
* `appointment n/chloe.lim@example.com s/English d/2021-4-20 fr/2:00pm l/Bedok`

### Listing all tuition appointments : `list_appointments`

Shows a list of all upcoming tuition schedules in the personal tuition appointment list.

Format: `list_appointments`

Example outputs:
```
1) john.doe@example.com - 2021-4-20 2:00pm @ Bedok National Library
2) jane.doe@example.com - 2021-4-21 2:00pm @ Bedok National Library
3) peter.ng@example.com - 2021-4-24 2:00pm @ Bedok National Library
```

### View tuition appointment details: `view_appointment`

View details of a tuition appointment.

Format:
`view_appointment DATE`

Example:
`view_appointment 2021-4-20`

Example Output:
```
Appointment Details

Tutor Email: chloe.lim@example.com
Appointment Date: 2021-4-20
Appointment Time: 2:00pm
Location: Bedok National Library
```

### Find tuition appointment details: `find_appointment`

Find list of tuition appointments based on tutor's name.

Format:
`find_appointment NAME...`

Example:
`find_appointment john.doe@example.com`

Example Output:
```
Appointment Details

Tutor Email: john.doe@gmail.com
Appointment Date: 2021-4-21
Appointment Time: 2:00pm
Location: Bedok National Library
```

### Delete a tuition appointment: `delete_appointment`

Format: `delete_appointment INDEX`

Deletes the specific appointment at the specified INDEX.
The index refers to the index number shown in the displayed person list.
The index must be a positive integer e.g. `1, 2, 3, …​`

Referencing to the example output from `list_appointments`,

Example:
`delete_appointment 1`

Example Output:
```
1) jane.doe@example.com - 2021-4-21 2:00pm @ Bedok National Library
2) peter.ng@example.com - 2021-4-24 2:00pm @ Bedok National Library
```

### Editing an appointment : `edit_appointment`

Edits an appointment with a specific index. Only the attributes present are changed in the appointment.

Format: `edit_appointment [e/EMAIL] [s/SUBJECT_NAME] [d/DATE] [fr/TIME_FROM] [l/LOCATION]`

* The date format `yyyy-mm-dd` must be strictly followed. e.g. `2021-3-1`and `2021-03-01`.
* The time format `hh:mm a` must be strictly followed. e.g. `9:01 am` and `10:30 pm`.

Examples: `edit_appointment e/andrewng@example.com l/Clementi`

### Adding Budget : `add_budget`

Adds a budget with an amount specified by user. Stores budget in user system.
Budget must not already exist in user system, otherwise use edit_budget instead.

Format: `add_budget [b/BUDGET]`

Example:
`add_budget b/500`

Example Output:
```
Budget of 500 is sucessfully added
```

* BUDGET must be a positive integer inclusive of zero

### Editing a Budget : `edit_budget`

Edits an already existing budget with an amount specified by user.

Format : `edit_budget [b/BUDGET]`

Example:
`edit_budget b/600`

Example Output
```
Budget of 600 is sucessfully updated.
```


* BUDGET must be a positive integer inclusive of zero

### Deleting a budget : `delete_budget`

Deletes an already existing budget.

Format : `delete_budget`

Example:
`delete_budget`

Example Output:
```
Budget of 600 is sucessfully deleted.
```

### Viewing a budget : `view_budget`

Views an already existing budget.

Format : `view_budget`

Example:
`view_budget`

Example Output:
```
1) Budget does not already exist. Please ensure there is a budget. You can use the 
add_budget function to add a budget.
2) Here is your budget.
Budget: 600
Total Cost of Appointments: 100.
```


### Adding a Grade : `add_grade`

Adds a grade with a subject, a graded item and a grade alphabet specified by user. Stores in user system.

Format: `add_grade s/SUBJECT_NAME gi/GRADED_ITEM gr/GRADE_ALPHABET`

* Valid `GRADE_ALPHABET` recognized by the system only include A to F, S and U.
* `SUBJECT_NAME` is case-insensitive and `GRADE_ALPHABET` must be uppercase.

Example: `add_grade s/Mathematics gi/Final gr/A`

Example Output:
```
New grade added: Mathematics (Final): A
```

### Editing a grade : `edit_grade`

Edits an already existing grade at the specified index. Only the attributes present are changed in the grade.

Format: `edit_grade INDEX [s/SUBJECT_NAME] [gi/GRADED_ITEM] [gr/GRADE_ALPHABET]`

Example: `edit_grade 1 gr/B`

Example Output:
```
Edited Grade: Science (Lab 1): B
```

### Deleting a grade : `delete_grade`

Deletes an already existing grade at the specified index.

Format: `delete_grade INDEX`

Example: `delete_grade 1`

Example Output: 
```
Deleted Grade: Science (Lab 1): B
```

### Listing all grades: `list_grades`

Views a list of all already existing grades in storage.

Format: `list_grades`

Example: `list_grades`

Example Output:
```
Listed all grades
  1. Science
     Lab 1
     A
  2. Mathematics
     Final
     B
  3. English
     Midterm
     C
```

### Exiting `exit`

Closes the app with `bye` message

Q & A
--------------------------------------------------------------------------------------------------------------------
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tutor Tracker home folder.
--------------------------------------------------------------------------------------------------------------------

Action | Format, Examples
--------|------------------
**Add a new tutor** | `add_tutor n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER a/ADDRESS... <s/SUBJECT_NAME r/RATE l/EDUCATION_LEVEL y/YEARS q/QUALIFICATIONS>...` <br> e.g., `add_tutor n/John Doe p/98765432 e/johnd@example.com g/Male a/John street, block 123, #01-01 s/English r/50 l/Sec 3 y/5 q/A-Level s/Mathematics r/60 l/Sec 4 y/6 q/A-Level`
**List tutors** | `list_tutors`
**Delete a tutor** | `delete_tutor INDEX`, <br> e.g. `delete_tutor 1`
**Edit a tutor** | `edit_tutor INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [a/ADDRESS] [<s/SUBJECT_NAME r/RATE l/EDUCATION_LEVEL y/YEARS q/QUALIFICATIONS>]...`, <br> e.g. `edit_tutor 1 p/99824314 s/English r/50 l/Secondary 5 y/9 q/A-Level`
**View a tutor details** | `view_tutor INDEX`, <br> e.g. `view_tutor 1`
**Add note to a tutor** | `add_note INDEX NOTE`, <br> e.g. `add_note 1 patient`
**Edit note of a tutor** | `edit_note INDEX NOTE`, <br> e.g. `edit_note not patient`
**Delete note of a tutor** | `delete_note INDEX`, <br> e.g. `delete_note 1`
**List tutors with note** | `list_note`, <br> e.g. `list_note`
**Export the tutor details**| `export INDEX`, <br> e.g. `export 1`
**Favourite a tutor** | `favourite INDEX`, <br> e.g. `favourite 1`
**Unfavourite a tutor** | `unfavourite INDEX`, <br> e.g. `Unfavourite 1`
**List favourites** | `list_favourites`, <br> e.g. `list_favourites`
**Add a new appointment** | `add_appointment e/EMAIL s/SUBJECT d/DATE fr/TIME_FROM l/LOCATION` <br> e.g., `appointment e/chloe.lim@example.com s/English d/2021-4-20 fr/2:00pm l/Bedok`
**List tuition appointments** | `list_appointments`
**View a tuition appointment details** | `view_appointment` <br> e.g. `view_appointment 2020-03-24`
**Find tuition appointments** | `find_appointment` <br> e.g. `find_appointment John`
**Delete a tuition appointment** | `delete_appointment` <br> e.g. `delete_appointment 1`
**Edit a tuition appointment** | `edit_appointment [e/EMAIL] [s/SUBJECT_NAME] [d/DATE] [fr/TIME_FROM] [l/LOCATION]` <br> e.g. `edit_appointment e/andrewng@example.com l/Clementi`
**Add a budget** | `add_budget` <br> e.g.`add_budget b/500`
**Edit a budget** | `edit_budget` <br> e.g. `edit_budget b/600`
**Deleting a budget** | `delete_budget` <br> e.g. `delete_budget`
**Viewing a budget** | `view_budget` <br> e.g. `view_budget`
**Add a grade** | `add_grade s/SUBJECT_NAME gi/GRADED_ITEM gr/GRADE_ALPHABET`, <br> e.g. `add_grade s/Mathematics gi/Final gr/A` 
**Edit a grade** | `edit_grade INDEX [s/SUBJECT_NAME] [gi/GRADED_ITEM] [gr/GRADE_ALPHABET]`, <br> e.g. `edit_grade 1 gr/B`
**Delete a grade** | `delete_grade INDEX`, <br> e.g. `delete_grade 1`
**List grades** | `list_grades`
**exit** | `bye`
