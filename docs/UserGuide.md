---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. About BookCoin
BookCoin (to the Moon) is a **desktop app for managing bookings that presents users with a structured and detailed information on facility availability via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). BookCoin is designed for administrative personnels of schools or organisations who need to manage bookings across a large number of facilities. If you can type fast, BookCoin can get your facility management tasks done faster than traditional GUI apps. Enjoy!


## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bookingapp.jar` [here](https://github.com/AY2021S2-CS2103-W17-3/tp/releases).

1. Copy the file to the folder that you want to use as the _home folder_ for BookCoin.

1. Run `java -jar bookingapp.jar via your terminal` (preferred). As a second alternative, double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>

   ![Ui](images/Ui_Booking_1.3.png) <br><br>
   Note how the app contains some sample data. This is for you to test out the commands first, and you may delete them using the `clear` command when you are ready to use the app for your own purposes. <br>


1. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.<br>
   Some example commands you can try:

   * **`list_venue`** : Lists all venues.

   * **`add_venue v/Chua Thian Poh Hall`** : Starts a multi-step command to create a venue named `Chua Thian Poh Hall`. Fill in the prompts as directed.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#3-features) below for details of each command.

<div markdown="block" class="alert alert-info">

**:information_source: Using BookCoin:**<br>

This section gives an overview of BookCoin’s layout so that you can get started quickly.

**There are four main segments in BookCoin:**
* Tabs that allow you to navigate between Persons, Venues and Bookings

* A command box as well as a result display box at the top left of the application

* A bookmark list on the left of the screen

* A main viewing area that would display complete information about a person, venue or booking

</div>

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find_venue v/VENUE_NAME`, `VENUE_NAME` is a parameter which can be used as `find_venue v/Victoria Hall`.

* Items in square brackets are optional.<br>
  e.g. `add_venue v/VENUE_NAME [max/MAXIMUM_OCCUPANCY]` can be used as `v/Chua Thian Poh Hall max/40` or as `v/Chua Thian Poh Hall`.
* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`. 

</div>

### 3.1. General features and commands

#### 3.1.1. Viewing all available commands : `help`

Displays the link to the user guide.

Format: `help`

#### 3.1.2. Deletes all records : `clear`

Deletes all records in the booking system.

Format: `clear`

<div markdown="block" class="alert alert-info">

**:information_source: Warning about clear command:**<br>

* The clear command is especially useful for first time users because the app would first launch with sample data for new users
  to have greater convenience when testing app functionalities. Users can then use the clear command to
  clear the database of sample data after familiarising themselves with the app.

* To prevent indiscriminate or accidental use of the clear command which could be potentially disastrous, we have included an
  inbuilt confirmation message that users would need to affirm.

</div>

#### 3.1.3. Saving the data

BookCoin data is saved in the hard disk automatically after any command which results in changes the data. There is no need to save manually.

#### 3.1.4. Exiting the program : `exit`

Exits the program.

Format: `exit`

### 3.2. Commands specific to multi-step commands

<div markdown="block" class="alert alert-info">

**:information_source: More information about multi-step commands:**<br>

* Some commands require multiple input information which can be very tedious to type in one go. 
Multi-step commands therefore allow such commands to be used with greater ease by users as the system will prompt them for input items one at a time. To skip input for optional fields, you can just press the <kbd>Enter</kbd> key without typing anything when you are prompted to enter an optional field.
* The commands listed in this section are specific to multi-step commands and are only applicable when the user is in the middle of a multi-step command.

</div>

#### 3.2.1. Undo previous input : `undo`

Brings the prompt of the multi-step command back to the previous step if users made a typo and wish to re-enter
their input for the previous field.

Format: `undo`

#### 3.2.2. Exiting prompting : `exit_prompt`

Exits the multi-step prompting under add_booking or add_venue. After exiting prompting, you would be able to give command
inputs again. Other commands would not work if you do not exit the multi-step command.

Format: `exit_prompt`

### 3.3. Person

<div markdown="block" class="alert alert-info">

**:information_source: About persons:**<br>

* BookCoin stores information about venue bookers which is important for scheduling/ contacting purposes. When bookers are stored, their information can be used for multiple bookings in the future which allows you to access their details easily without having to repeat the same booker details multiple times across bookings.
* For your convenience, we use the term "person" in the app instead of "booker" to make a distinction between "booker" and "booking", which can be confusing.
* Persons are identified by their emails, hence all email inputs between persons must be unique. Persons with the same name are allowed.

</div>

#### 3.3.1. Adding a person : `add_person` (Multi step command)

Adds a new person for the booking app. `add_person` is a multi-step command that will prompt you for additional input. Inputting 
`add_person` will start the command and the app will guide you through the command through prompts for each field. As with other multi step commands, you can exit the command by entering `exit_prompt` at any point. Optional fields can be skipped by pressing the <kbd>Enter</kbd> key when you are prompted to input an optional field.

Email and phone number must be unique. Tags are optional.<br>
Format: `add_person`

#### 3.3.2. Editing a person : `edit_person`

Edits an existing person in the booking system, identified by their unique email.

Format: `edit_person eo/EMAIL [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]`

Example:
* `edit_person eo/amy@example.com p/83984029` changes the phone number of the person with email amy@example.com to 83984029.

#### 3.3.3. Deleting a person : `delete_person`

Deletes a person corresponding to the email specified.

Format: `delete_person e/EMAIL`

Example:
* `delete_person e/johndoe@gmail.com`

#### 3.3.4. Listing all persons : `list_person`

Shows a list of all persons in the booking app.

Format: `list_person`

#### 3.3.5. Finding a person : `find_person` (Edit this Vanessa!)

Shows information about the person corresponding to the given email. Partial matching is not accepted to reduce the number of unrelated search results for greater convenience in searching. 

Format: `find_person e/EMAIL`

### 3.4. Venue

#### 3.4.1. Adding a venue : `add_venue` (Multi step command)

Adds a new venue for the booking app. `add_venue` is a multi-step command that will prompt you for additional input. Inputting 
`add_venue v/VENUE_NAME` will start the command and the app will guide you through the command through prompts for each field. As with other multi step commands, you can exit the command by entering `exit_prompt` at any point. Optional fields can be skipped by pressing the `Enter` key when you are prompted to input an optional field.

Capacity, description and tags are optional. Default capacity without an input will be set to 10.

Format: `add_venue v/VENUE_NAME`

Example:
* `add_venue v/Chua Thian Poh Hall max/40` adds a venue with venue name Chua Thian Poh Hall and a maximum capacity of 40.

#### 3.4.2. Editing a venue : `edit_venue`

Edits an existing venue in the booking system with the specified venue name.

Format: `edit_venue vo/VENUE_NAME [v/VENUE_NAME] [max/MAXIMUM_OCCUPANCY] [d/DESCRIPTION] [t/TAG]`

Examples:
* `edit_venue vo/Lab max/30` changes the maximum capacity of the venue named Lab to 30.
* `edit_venue vo/Victoria Hall v/Sports Hall` changes the venue name of the venue named Victoria Hall to Sports Hall.

#### 3.4.3. Deleting a venue : `delete_venue`

Deletes a venue corresponding to the venue name specified.

Format: `delete_venue v/VENUE_NAME`

Example:
* `delete_venue v/Volleyball Court`

#### 3.4.4. Listing all venues : `list_venue`

Shows a list of all venues in the booking app.

Format: `list_venue`

#### 3.4.5. Finding a venue : `find_venue` (rmb to edit this vanessa!!!)

Shows information about the venue corresponding to the given venue name. Partial matching is not accepted to reduce the number of unrelated search results for greater convenience in searching. 

Format: `find_venue v/VENUE_NAME`

Example:
* `find_venue v/Victoria Hall`

### 3.5. Booking

#### 3.5.1. Adding a booking : `add_booking` (Multi step command)

Adds a new booking into the booking app. `add_booking` is a multi-step command that will prompt you for additional input. Inputting 
`add_booking` will start the command and the app will guide you through the command through prompts for each field. As with other multi step commands, you can exit the command by entering `exit_prompt` at any point. Optional fields can be skipped by pressing the <kbd>Enter</kbd> key when you are prompted to input an optional field.

The system will ask for and store the email of the booker, the venue booked, the start and end time of your booking (in the format YYYY-MM-DD HH:MM). You may also choose to add an optional description or tags for your booking.

Format: `add_booking`

#### 3.5.2. Editing a booking : `edit_booking`

Edits an existing booking in the booking system with the specified booking ID.

Format: `edit_booking bid/BOOKING_ID [b/BOOKER_EMAIL] [v/VENUE_NAME] [d/DESCRIPTION] [bs/DATETIME] [be/DATETIME] [t/TAG]`

Examples:
* `edit_booking bid/8937936578 b/janetan@gmail.com` 
* `edit_booking bid/9384720480 v/Field`

#### 3.5.3. Deleting a booking : `delete_booking`

Deletes booking corresponding to the booking ID specified.

Format: `delete_booking bid/BOOKING_ID`

Example:
* `delete_booking bid/232138762134`

#### 3.5.4. Listing all bookings : `list_booking`

Shows a list of all bookings and their corresponding IDs in the booking app.

Format: `list_booking`

#### 3.5.5. Finding a booking : `find_booking` (Edit this Vanessa!)

Shows information about the booking corresponding to the given booking ID. Partial matching is not accepted to reduce the number of unrelated search results for greater convenience in searching. 

Format: `find_booking bid/BOOKING_ID`

Example:
* `find_booking bid/2321837462`

#### 3.5.6. Filtering bookings by date : `filter_booking_by_date`

Shows a list of bookings on the specified date.

Format: `filter_booking_by_date date/DATE`

Example:
* `filter_booking_by_date date/2020-12-12`

#### 3.5.7. Filtering bookings by booker : `filter_booking_by_booker`

Shows a list of bookings booked by the booker identified by the email address given.

Format: `filter_booking_by_booker e/EMAIL`

Example:
* `filter_booking_by_booker e/JohnRose@abc.com`

#### 3.5.8. Filtering bookings by venue : `filter_booking_by_venue`

Shows a list of bookings at the specified venue.

Format: `filter_booking_by_venue v/VENUE`

Example:
* `filter_booking_by_venue v/Sports Hall`

#### 3.5.9. Filtering bookings by tag : `filter_booking_by_tag`

Shows a list of bookings with the specified tag.

Format: `filter_booking_by_tag t/TAG`

Example:
* `filter_booking_by_tag t/student`

### 3.6. Upcoming

#### 3.6.1. Undo command for all actions `[coming in v2.0]`
Undos the effect of a previous command if the command changes the data stored in the system (i.e. adds/ updates/ deletes any data)

Format: `undo`

#### 3.6.2. Generate file of booking schedule `[coming in v2.0]`
Generates an .ics file containing a timetable of the bookings for the particular venue given in a command.

Format `generate v/Victoria Hall`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous BookCoin home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Example
--------|------------------
**add person** | `add_person` <br> (Note: add_person is a multi-step command)
**delete person** | `delete_person e/EMAIL` <br> e.g. `delete_person e/jane@gmail.com`
**edit person** | `edit_person eo/EMAIL [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]` <br> e.g., `edit_person eo/jane@example.com p/94857267`
**find person** | `find_person e/EMAIL` <br> e.g., `find_person e/jane@example.com` 
**list person** | `list_person`
**add venue** | `add_venue v/VENUE_NAME` <br> (Note: add_venue is a multi-step command)
**delete venue** | `delete_venue v/VENUE_NAME` <br> e.g. `delete_venue v/Field`
**edit venue** | `edit_venue vo/VENUE_NAME [v/VENUE_NAME] [max/CAPACITY] [d/DESCRIPTION] [t/TAG]` <br> e.g., `edit_venue vo/Field v/Sports Field`
**find venue** | `find_venue v/VENUE_NAME` <br> e.g., `find_venue v/Field`
**list venue** | `list_venue`
**add booking** | `add_booking` <br> (Note: add_booking is a multi-step command)
**delete booking** | `delete_booking bid/BOOKING_ID` <br> e.g. `delete_booking bid/8756948376`
**edit booking** | `edit_booking bid/BOOKING_ID [b/BOOKER_EMAIL] [v/VENUE_NAME] [d/DESCRIPTION] [bs/DATETIME] [be/DATETIME] [t/TAG]` <br> e.g., `edit_booking bid/3984792837 e/doe@gmail.com`
**find person** | `find_booking bid/BOOKING_ID` <br> e.g., `find_booking bid/8756948376`
**list booking** | `list_booking`
**help** | `help`
**clear** | `clear`
**exit** | `exit`
