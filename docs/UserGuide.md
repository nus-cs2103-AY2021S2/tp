---
layout: page
title: User Guide
---
_**Tutor Tracker**_ is a **desktop app designed to help secondary school students manage tutors and tuition appointments, optimised for use via a Command Line Interface** (CLI) for a fast and streamlined experience while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tutor Tracker can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutortracker.jar` from [here](https://github.com/AY2021S2-CS2103-T14-3/tp).

1. Copy the file to the folder you want to use as the _home folder_ for your TutorTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`appointment`**`n/Chloe Lim s/English d/2021-4-20 fr/2:00pm to/c l/Bedok` : Adds an appointment with a tutor named `Chloe Lim` to the Tutor Tracker.

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

</div>

### Adding Appointment : `appointment`

Adds an appointment with a specific tutor to the schedule.<br>

Format: `appointment n/NAME s/SUBJECT d/DATE fr/TIME_FROM to/TIME_TO [l/LOCATION]`

* The date format `yyyy-mm-dd` must be strictly followed. e.g. `2021-3-1`and `2021-03-01`.
* The time format `hh:mm a` must be strictly followed. e.g. `9:01 am` and `10:30 pm`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An appointment can only have 0 or 1 location.
</div>

Examples:<br>
* `appointment n/Andrew Ng s/Mathematics d/2021-3-1 fr/10:00am to/12:00am`
* `appointment n/Chloe Lim s/English d/2021-4-20 fr/2:00pm to/c l/Bedok`
  
--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Appointment** | `appointment n/NAME s/SUBJECT d/DATE fr/TIME_FROM to/TIME_TO [l/LOCATION]` <br> e.g., `appointment n/Chloe Lim s/English d/2021-4-20 fr/2:00pm to/c l/Bedok`

