<p align="center">
  <img width="341" height="381" src="https://user-images.githubusercontent.com/48408342/112603571-29845b80-8e50-11eb-8dee-bb88603fffb8.png">
</p>

**DocBob** is a **desktop app for managing patient medical information and appointments, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). DocBob is targeted at Doctors and Nurses like you! If you can type fast, DocBob can help you keep track of your patient's **medical information** and **scheduled appointments** more efficiently than any other patient information management app in the market. With DocBob, you will never have to go through the hassle of manually keeping track of your patients' medical information and appointments ever again.

The **purpose of this User Guide(UG)** is to help new users understand how to get the app running by following the **Quick Start** guide. The **Features** section will help you learn the basic commands, their formatting and usages. You can easily navigate through this guide with the provided **Table of Contents** below.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bob.jar` from [here](https://github.com/AY2021S2-CS2103T-W12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Doc Bob.

1. Double-click the file to start the app. The GUI similar to the mock-up below should appear in a few seconds. Note how the app contains some sample data. To learn how to use the Command Line Interface (CLI), try out the example commands given below with the sample data. Once you are familiar with the CLI, you can use the `clear` command to remove all sample data and start adding your own data!<br>
   ![Ui](images/Ui.png)
   
1. This should work for you regardless of your operating system (OS).

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will list out all available commands.<br>
   Some example commands you can try:

   * **`list`** : List out all patients in DocBob's contact list.

   * **`add n/Shrek p/66666666 e/shrek@swampmail.com a/Swamp h/243cm w/94kg`** : Adds a patient named `Shrek` to DocBob's contact list.

   * **`appt 1 d/010120301200`** : Adds a scheduled upcoming appointment with the patient at index 1 in DocBob's contact list, on 1st January 2030 12pm.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### List out all available commands : `help`

Opens a help window containing a link to this User Guide and a list of all available commands for use in the app, with format example.

Format: `help`

Example: `help`

Output:

DocBob will open up a help window with command information.
![image](https://user-images.githubusercontent.com/48408342/112743708-35952800-8fcc-11eb-9d1a-a7d5b52aac73.png)


### Adding a patients' contact: `add`

Adds a patient to DocBob's contact list.

Format: `add n/Name p/phoneNumber e/Email a/Address h/Height w/Weight [t/TAG]`

Example:
* `add n/Shrek p/66666666 e/shrek@swampmail.com a/Swamp h/243cm w/94kg t/smelly`

Output:

`New patient added: Shrek; Phone: 66666666; Email: shrek@swampmail.com; Address: Swamp; Height: 243cm; Weight: 94kg; Tags: [smelly]`

### Deleting a patients' contact : `delete`

Deletes a patient from DocBob's contact list, identified by the index number shown in the displayed patient list.

Format : `delete INDEX`
where INDEX must be a positive integer (1,2,3,...)

Example:
* `delete 1`

Output:

`Deleted Person: Shrek; Phone: 66666666; Email: shrek@swampmail.com; Address: Swamp; Height: 243cm; Weight: 94kg; Tags: [smelly]`

### Listing out all patients' contacts : `list`

Shows a list of all your saved patients information, with their next scheduled appointment beside their name.

Format: `list`

Example: `list`

Output:

![image](https://user-images.githubusercontent.com/48408342/112432500-f7092e80-8d7b-11eb-85b9-2aaab776d47d.png)

### Adding an appointment to a patient : `appt`

Adds a scheduled upcoming appointment with a patient in DocBob's contact list. Add an appointment and its date and time to a patient identified by the index number shown in the displayed patient list.

Format: `appt INDEX /dDATE`
where INDEX must be a positive integer (1,2,3,...)
and DATE is DDMMYYYYhhmm or DDMMhhmm

Examples:
* `appt 3 d/25120800`

Output:

`Appointment added: Sat, 25 Dec, 08:00`

### Listing out your upcoming appointments : `listappt`

Shows a list of all your upcoming appointments with the patients in DocBob's contact list, sorted by nearest date and time.

Format: `listappt`

Example: `listappt`

Output: 

`Hey Doc, here are your upcoming appointments!`<br> 
`Thu, 25 Mar, 14:00 - Bernice Yu`<br>
`Fri, 11 Jun, 14:00 - David Li`<br>
`Thu, 11 Nov, 12:00 - Alex Yeoh`<br>
`Thu, 11 Nov, 12:00 - Roy Balakrishnan`<br>
`Sun, 12 Dec, 12:00 - Alex Yeoh`<br>
`Sun, 12 Dec, 12:12 - Charlotte Oliveiro`<br>


### Create a new medical record for a patient : `mrec`

Opens an editor for you to write a custom medical report for the patient, identified by the index number shown in the displayed patient list.

Format : `mrec INDEX`
where INDEX must be a positive integer (1,2,3,...)

Examples:
* `mrec 3`

Output:

![image](https://user-images.githubusercontent.com/48408342/112743647-aee04b00-8fcb-11eb-8ac4-9ccf999bde49.png)


### View all information regarding a patient : `view`

Shows an overview of all contact information, tags, appointments and medical records of a patient, identified by the index number shown in the displayed patient list.

Format : `view INDEX`
where INDEX must be a positive integer (1,2,3,...)

Examples:
* `view 1`

Output: 

![image](https://user-images.githubusercontent.com/48408342/112605797-a6183980-8e52-11eb-9694-55d0ff014af4.png)


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Editing a patient information : `edit`

Edits an existing patient in the list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.

### Locating patient by name : `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)


### Clearing all entries[Coming soon] : `clear`

Clears all entries from DocBob.

Format: `clear`

### Saving the data

Client contact data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Client contact data is saved as a JSON file `[JAR file location]/data/docBob.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, docBob will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous DocBob home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**help** | `help`
**add** | `add n/Name p/phoneNumber e/Email a/Address h/Height w/Weight [t/TAGS]` <br> e.g., `add n/Shrek p/66666666 e/shrek@swampmail.com a/Swamp h/243cm w/94kg`
**delete** | `delete INDEX` <br> e.g., `delete 1`
**list** | `list`
**appt** | `appt 1 d/010120211200`
**listappt** | `listappt`
**mrec** | `mrec 3`
**view** | `view 1`
**exit** | `exit`

## Issues
In the event of any issues while using the app and or UG, please contact the team at the emails below <br>
prerthanmunireternam@yahoo.com
