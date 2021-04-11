---
layout: page 
title: User Guide
---

Welcome to the User Guide of _Teaching Assistant_!

Are you a JC/Secondary school teacher, having troubles keeping track of all your consultations, meetings and your
students' contacts? No worries! Our application, _Teaching Assistant_ will provide an all-in-one platform for you to
organise your entries (schedules) and contacts!

We target JC/Secondary school teachers as they are the teachers who have a greater need to contact their students
compared to primary school schools, yet do not have a standardised platform for communication unlike teachers
in tertiary schools.

_Teaching Assistant_ mainly uses a Command Line Interface (CLI). This means that a user can use the application by
typing into a Command Box. For users who type fast, they can use this application more efficiently than other
applications that heavily use Graphical User Interface (GUI), where a user uses the application by interacting with
graphical features such as buttons.

If you are interested, jump to [Quick Start](#quick-start) to learn how to learn how to start using 
_Teaching Assistant_.

An image of our UI is shown below!

![Ui](images/Ui.png)

---

- [Quick Start](#quick-start)
- [Features](#features)
    - [Viewing help: `help`](#viewing-help-help)
    - [Contact](#adding-a-contact-cadd)
        - [Add: `cadd`](#adding-a-contact-cadd)
        - [Find: `cfind`](#finding-a-contact-cfind)
        - [Filter: `cfilter`](#filter-contact-tags-cfilter)
        - [Edit: `cedit`](#editing-a-contact-cedit)
        - [List: `clist`](#listing-contacts-clist)
        - [Delete: `cdelete`](#deleting-a-contact-cdelete)
    - [Entry](#adding-an-entry-eadd)
        - [Add: `eadd`](#adding-an-entry-eadd)
        - [Find: `efind`](#finding-an-entry-efind)
        - [Filter: `efilter`](#filter-entry-tags-efilter)
        - [Edit: `eedit`](#editing-an-entry-eedit)
        - [List: `elist`](#listing-entries-elist)
        - [Free: `free`](#checking-if-time-interval-is-free-free)
        - [Delete: `edelete`](#deleting-an-entry-edelete)
        - [Clear: `eclear`](#clearing-overdue-entries-eclear)
    - [Clear: `clear`](#clearing-all-data-clear)
    - [Exit: `exit`](#exiting-the-program-exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java 11 installed in your computer. You may install it [here](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html).
1. Download the latest `teachingAssistant.jar` [here](https://github.com/AY2021S2-CS2103T-W13-4/tp/releases).
1. Copy the file to the folder you want to use as the *home folder* for your Teaching Assistant.
1. Double-click the file to start the app. The GUI similar to the image above should appear.
1. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
   * **`clist`**: Lists all contacts in Teaching Assistant.
   * **`cadd n/Danny p/98765432 e/danny@email.com`**: Adds a contact named `Danny` to Teaching Assistant.
   * **`efind consultation 1`**: Finds an entry named `consultation 1' in Teaching Assistant.
   * **`exit`**: Exits the app.
1. Refer to the [Features](#features) below for details of each command.

---

## Features

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `cadd n/NAME`, `NAME` is a parameter which
  can be used as `cadd n/John Doe`.
* Items in the square brackets are optional. Users can choose to leave the field empty.
* Items with `...` after them can be used multiple times including zero times. e.g. `[t/TAG]...` can be used as `  ` 
  (i.e. 0 times), t/friend, t/friend t/family etc.
* Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also
  acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken. e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

---

### Viewing help: `help`

Shows a message explaining how to access the help page.

**Format**: `help`

**Breakdown**:
* Command word - `help`

Scenario:
You are using Teaching Assistant but forget the formats of the commands you want to execute. This command is the only
one you need to remember! It will bring you to this document.

---

### Adding a contact: `cadd`

Adds a contact into Teaching Assistant.

**Format**: `cadd n/NAME p/NUMBER e/EMAIL [t/TAG]...`

**Breakdown**:
* Command word - `cadd`
* Prefixes - `n/`, `p/`, `e/`, `t/`
* Parameters - `NAME`, `NUMBER`, `EMAIL`, `TAG`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An entry's name can be tagged to a contact to easily filter for contacts related an entry.
</div>

Example(s):

* `cadd n/Danny p/98765432 e/danny@email.com t/consultation 1`
* `cadd n/Amy p/12345678 e/amy@email.com t/Colleague t/OwesMoney t/meeting 1`

Scenario:
You want to save the contact details of your students so that you can easily obtain their details when you want to
reach out to them.

### Finding a contact: `cfind`

Finds all contacts whose name contain all of the specified keywords and displays them as a list.

**Format**: `cfind KEYWORD [MORE_KEYWORDS]...`

**Breakdown**:
* Command word - `cfind`
* Parameters - `KEYWORD`, `MORE_KEYWORDS`


* Only names are searched.
* The search is case-insensitive e.g. `amy` will match `Amy`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Only contacts matching all keywords will be returned (i.e. AND search). E.g. `Hans Bo` will only return `Hans Bo`.

Example(s):

* `cfind John` returns `john` and `John Doe`
* `cfind alex yeoh` returns only `Alex Yeoh`

Scenario:
Your student had booked a consultation with you and you want to find his/her contact details by name without scrolling 
through the entire contact list so that you can remind him/her of the arrangement as the date of the consultation draws
nearer.

### Filter contact tags: `cfilter`

Filters all contacts that have the tags of the specified keywords and displays them as a list with index numbers.

**Format**: `cfilter KEYWORD [MORE_KEYWORDS]...`

**Breakdown**:
* Command word - `cfilter`
* Parameters - `KEYWORD`, `MORE_KEYWORDS`


* Only tags are searched.
* The filtering is case-insensitive e.g. `CS2100` will match `cs2100`.
* Only full words will be matched e.g. `Friend` will not match `Friends`.
* If more than one keyword is provided, only contacts with all the keywords provided will be displayed.
  E.g. `filter colleagues friends` will only return a contact with both tags `colleagues` and `friends`. Contacts with
  only one of the 2 keywords will not be displayed.
* The order of the keywords does not matter. e.g. `colleagues friends` will match `friends colleagues`

Example(s):

* `cfilter student english`
* `cfilter colleagues`

Scenario:
Your students have decided on the roles and responsibilities they will be taking up in your class (e.g. chairperson, 
english representative). You can add a tag to their contact to identify their roles. Then, you can find their contact 
details easily by filtering contacts via tags.

### Editing a contact: `cedit`

Edits an existing contact with the specified index in Teaching Assistant.

**Format**: `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]...`

**Breakdown**:
* Command word - `cedit`
* Prefixes - `n/`, `p/`, `e/`, `t/`
* Parameters - `INDEX`, `NAME`, `NUMBER`, `EMAIL`, `TAG`


* `INDEX` refers to the index numer shown in the displayed entry list.
* `INDEX` must be a positive integer 1,2,3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e. adding of tags is not cumulative.
* You can remove all the contact’s tags by typing t/ without specifying any tags after it.

Example(s):

* `cedit 1 p/91234567 e/alexyeoh@example.com` Edits the phone number and email address of the contact corresponding to 
  index 1 and to `p/91234567` and `alexyeoh@example.com` respectively.
* `cedit 1 n/Bernice Yu Xiao Ling t/` Edits the name of the contact corresponding to index 1 to be `Bernice Yu Xiao Ling` 
  and clears all existing tags.
  
Scenario:
Your student had recently changed his/her phone number and you want to update his/her contact details without the 
hassle of deleting his/her old contact and then subsequently adding a new updated contact.

### Listing contacts: `clist`

Lists all the contacts in Teaching Assistant.

**Format**: `clist`

**Breakdown**:
* Command word - `clist`

Scenario: You want to look at all the contacts you have saved.

### Deleting a contact: `cdelete`

Deletes an existing contact with the specified index in Teaching Assistant.

**Format**: `cdelete INDEX`

**Breakdown**:
* Command word - `cdelete`
* Parameters - `INDEX`


* `INDEX` refers to the index number shown in the displayed person list.
* `INDEX` must be a positive integer 1, 2, 3, ...

Example(s):

* `cdelete 1`

Scenario: Your students have long graduated and you will not be contacting them anymore. You want to remove their
contacts to make space for the contacts of your new students.

---

### Adding an entry: `eadd`

Adds a new entry into Teaching Assistant.

**Format**: `eadd n/NAME sd/START_DATE ed/END_DATE [t/TAG]...`

**Breakdown**:
* Command word - `eadd`
* Prefixes - `n/`, `sd/`, `ed/`, `t/`
* Parameters - `NAME`, `START_DATE`, `END_DATE`, `TAG`


* `START DATE` and `END DATE` are in the format `yyyy-mm-dd HH:MM`.
* `START DATE` should be before `END DATE`.
* Entries cannot overlap. i.e. Entries with overlapping timings will not be added.

Example(s):

* `eadd n/meeting sd/2021-02-15 21:00 ed/2021-02-15 23:00`
* `eadd n/consultation sd/2021-02-15 22:00 ed/2021-02-15 23:00 t/consultation`

Scenario: You want to add entries into Teaching Assistant to keep track of your schedule.

### Finding an entry: `efind`

Finds all entries whose name contain all of the specified keywords and displays them as a list.

**Format**: `efind KEYWORD [MORE_KEYWORDS]...`

**Breakdown**:
* Command word - `efind`
* Parameters - `KEYWORD`, `MORE_KEYWORDS`


* Only names are searched.
* The search is case-insensitive e.g. `meeting` will match `Meeting`.
* The order of the keywords does not matter. e.g. `teaching assistant` will match `assistant teaching`.
* Only full words will be matched e.g. `meeting` will not match `meetings`.
* Only entries matching all keywords will be returned (i.e. AND search). E.g. `meeting 1` will only return
  `meeting 1`.

Example(s):

* `efind consultation` returns `consultation 1` and `consultation 2`
* `efind consultation 2` returns only `consultation 2`

Scenario: You remember you added a school event entry into Teaching Assistant in the past but forgot its date. 
You can find an entry's details by its name without scrolling through the entire entry list.

### Filter entry tags: `efilter`

Filters all entries that have the tags of the specified keywords and displays them as a list.

**Format**: `efilter KEYWORD [MORE_KEYWORDS]...`

**Breakdown**:
* Command word - `efilter`
* Parameters - `KEYWORD`, `MORE_KEYWORDS`


* Only tags are searched.
* The filtering is case-insensitive e.g. `CS2100` will match `cs2100`.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`.
* If more than one keyword is provided, only entries with all the keywords provided will be displayed.
  E.g. `filter meeting CS2103T` will only return an entry with both tags `meeting` and `CS2103T`. Entries with only one
  of the 2 keywords will not be displayed.
* The order of the keywords does not matter. e.g. `meeting CS2103T` will match `CS2103T meeting`.

Example(s):

* `efilter CS2103T`
* `efilter CS2100 Meeting`

Scenario: You want to filter your entries via the consultation tag you have added to your entries in the past, so that
you can plan ahead and prepare for your upcoming consultations.

### Editing an entry: `eedit`

Edits an existing entry with the specified index in Teaching Assistant.

**Format**: `eedit INDEX [n/NAME] [sd/START_DATE] [ed/END_DATE] [t/TAG]...`

**Breakdown**:
* Command word - `eedit`
* Prefixes - `n/`, `sd/`, `ed/`, `t/`
* Parameters - `INDEX`, `NAME`, `START_DATE`, `END_DATE`, `TAG`


* `INDEX` refers to the index numer shown in the displayed entry list.
* `INDEX` must be a positive integer 1,2,3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the entry will be removed i.e. adding of tags is not cumulative.
* You can remove all the entry’s tags by typing t/ without specifying any tags after it.

Example(s):

* `eedit 1 sd/2021-05-03 13:00 ed/2021-05-03 14:00` Edits the start and end dates of the entry corresponding to index 1 to
  be `2021-05-03 13:00` and `2021-05-03 14:00` respectively.
* `eedit 1 t/` Edits the entry corresponding to index 1 by clearing all existing tags.

Scenario: The Head of Department (HOD) just rescheduled a department meeting. You want to edit an entry's start and end 
dates without first deleting it and then subsequently adding a new entry with the updated dates. 

### Listing entries: `elist`

Lists all entries in Teaching Assistant by displaying them as a list sorted by date. Entries can also be listed by day/week.

**Format**: `elist [FORMAT]`

**Breakdown**:
* Command word - `elist`
* Parameters - `FORMAT`


* No argument: listing all entries
* `FORMAT` is only restricted to the following cases
    * `day`: listing entries for today
    * `week`: listing entries for today as well as the next 6 days

Example(s):

* `elist`
* `elist day`
* `elist week`

Scenario: You want to see what is in store for you today so that you can mentally prepare yourself for the busy day ahead.

### Checking if time interval is free: `free`

Indicates if an interval is free. If free, a message indicating that will be shown. If not, entries occupying that
interval will be shown in the entries list.

**Format**: `free sd/START_DATE ed/END_DATE`

**Breakdown**:
* Command word - `free`
* Prefixes - `sd/`, `ed/`
* Parameters - `START_DATE`, `END_DATE`


* `START DATE` and `END DATE` are in the format `yyyy-mm-dd HH:MM`.
* `START DATE` should be before `END DATE`.

Example(s):

* `free sd/ 2021-12-20 12:00 ed/ 2021-12-20 13:00` if the time interval is free, entries list will be empty and _"You're
  free!"_ message is shown. If not, a message _"Sorry, you're not free. Entries occupying that time interval listed
  below!"_ will be shown, accompanied by occupying entries in the entry list.
  
Scenario: Your student just approached you to book a consultation and asks if you are available at a specific timing.

### Deleting an entry: `edelete`

Deletes an existing entry with the specified index in Teaching Assistant.

**Format**: `edelete INDEX`

**Breakdown**:
* Command word - `edelete`
* Parameters - `INDEX`


* `INDEX` refers to the index number shown in the displayed person list.
* `INDEX` must be a positive integer 1, 2, 3, ...

Example(s):

* `edelete 1`

Scenario: A school event you are involved in has been cancelled because of Covid-19 restrictions. You want to delete
this entry from your schedule.

### Clearing overdue entries: `eclear`

Clears all entries that have dates before today's date.

**Format**: `eclear`

**Breakdown**:
* Command word - `eclear`

Scenario: You still have entries from the past that you no longer need, and want to remove those outdated entries
to not clutter the entries list.

---

### Clearing all data: `clear`

Clears all contacts and entries from Teaching Assistant.

**Format**: `clear`

**Breakdown**:
* Command word - `clear`

Scenario: You opened Teaching Assistant for the first time and want to start using it. You want to clear the sample
data given before you proceed.

### Exiting the program: `exit`

Exits the program.

**Format**: `exit`

**Breakdown**:
* Command word - `exit`

Scenario: You survived yet another hectic day! Use this command to exit the app.

---

### Saving the data
Teaching Assistant data is saved in the hard disc automatically after any command that changes the data is executed.
There is no need to save data manually.

### Editing the data file
Teaching Assistant data is saved as a JSON file `[JAR file location]/data/teachingAssistant.json`. Advanced users are
welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

---

### FAQ
**Q**: How do I transfer my data to another computer?
**A**: Install Teaching Assistant in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Teaching Assistant home folder.

---

## Command summary

### Others

Action | Format
------- | ------------------
**View all commands** | `help`
**Clear** | `clear`
**Exit** | `exit`

### Contacts

Action | Format
-------- | ------------------
**Add** | `cadd n/NAME p/NUMBER e/EMAIL [t/TAG]...`
**Find** | `cfind KEYWORD [MORE_KEYWORDS]...`
**Filter tags** | `cfilter KEYWORD [MORE_KEYWORDS]...`
**Edit** | `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]...`
**List** | `clist`
**Delete** | `cdelete INDEX`

### Entries

Action | Format
-------- | ------------------
**Add** | `eadd n/NAME sd/START_DATE ed/END_DATE [t/TAG]...`
**Find** | `efind KEYWORD [MORE_KEYWORDS]...`
**Filter tags** | `efilter KEYWORD [MORE_KEYWORDS]...`
**Edit** | `eedit INDEX [n/NAME] [sd/START_DATE] [ed/END_DATE] [t/TAG]...`
**List** | `elist [FORMAT]`
**Check if free** | `free sd/START_DATE ed/END_DATE`
**Delete** | `edelete INDEX`
**Clear overdue entries** | `eclear`

