
---
layout: page
title: User Guide
---

BookCoinToTheMoon is a **desktop app for managing bookings and presents users a structured and detailed information on facility availability via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BookCoinToTheMoon can get your facility management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bookcointothemoon.jar` `[coming in v2.0]`

1. Copy the file to the folder you want to use as the _home folder_ for your BookCoinToTheMoon.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui_Booking.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list_venue`** : Lists all venues.

   * **`add_venue`**`n/Chua Thian Poh Hall max/40` : Adds a venue named `Chua Thian Poh Hall` to the BookCoinToTheMoon.

   * **`bye`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find_venue v/VENUE_NAME`, `VENUE_NAME` is a parameter which can be used as `find_venue v/Victoria Hall`.

* Items in square brackets are optional.<br>
  e.g. `add_venue v/VENUE_NAME [max/MAXIMUM_OCCUPANCY]` can be used as `n/Chua Thian Poh Hall max/40` or as `n/Chua Thian Poh Hall`.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `bye`) will be ignored.<br>
  e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

</div>

### Adding a venue : `create_venue`

Adds a new venue for the booking app.

Format: `create_venue v/VENUE_NAME [max/MAXIMUM_OCCUPANCY]`

Examples:
* `create_venue v/Chua Thian Poh Hall max/40`

### Adding a booking : `add_booking`

Adds a new booking into the booking app. The default booking is set to one hour for the first iteration. The system will
then prompt user to provide details of the booking step by step. During the multi-step prompting, user would only be
allowed to give input that matches the format allowed by prompting question, command inputs would not be accepted during this process.

Format: `add_booking`

Examples:
* `add_booking`

### Listing all venues : `list_venues`

Shows a list of all venues and their corresponding IDs in the booking app.

Format: `list_venues`

### Listing all bookings : `list_bookings`

Shows a list of all bookings and their corresponding IDs in the booking app.

Format: `list_bookings`

### Finding a venue : `find_venue`

Shows information about the venue corresponding to the given venue name.

Format: `find_venue v/VENUE_NAME`

Examples:
* `find_venue v/Victoria Hall`

### Finding a booking : `find_booking`

Shows information about the booking corresponding to the given booking ID.

Format: `find_booking b/BOOKING_ID`

Examples:
* `find_booking b/2321356789`

### Deleting a venue : `delete_venue`

Deletes a venue corresponding to the venue name specified.

Format: `delete_venue v/VENUE_NAME`

Examples:
* `delete_venue v/Court`

### Deleting a booking : `delete_booking`

Deletes booking corresponding to the booking ID specified.

Format: `delete_booking b/BOOKING_ID`

Examples:
* `delete_booking b/2`

### Editing a venue : `edit_venue`

Edits an existing venue in the booking system with the specified venue name.

Format: `edit_venue vo/VENUE_NAME [v/VENUE_NAME] [max/MAXIMUM_OCCUPANCY]`

Examples:
* `edit_venue vo/Lab max/30`

### Editing a person : `edit_person`

Edits an existing person in the booking system with the specified email.

Format: `edit_person eo/EMAIL [e/EMAIL] [p/PHONE_NUMBER] [n/NAME]`

Examples:
* `edit_person eo/amy@example.com p/83984029`

### Filtering bookings by booker : `filter_booking_by_booker`

Shows a list of bookings booked by the booker identified by the booker name given.

Format: `filter_booking_by_booker [n/NAME]`

Examples:
* `filter_booking_by_booker n/John`

### Filtering bookings by date : `filter_booking_by_date`

Shows a list of bookings on the specific date.

Format: `filter_booking_by_date [date/DATE]`

Examples:
* `filter_booking_by_date date/2020-12-12`

### Filtering bookings by venue : `filter_booking_by_venue`

Shows a list of bookings of a specific venue.

Format: `filter_booking_by_venue [v/VENUE]`

Examples:
* `filter_booking_by_venue v/Sports Hall`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Exiting prompting : `exit_prompt`

Exits the multi-step prompting under add_booking or add_venue. After exiting prompting, user would be able to give command 
inputs again.

Format: `exit_prompt`

### Saving the data

BookCoinToTheMoon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file `[coming in v2.0]`

_Details coming soon ..._

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BookCoinToTheMoon home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add_venue n/VENUE_NAME [max/MAXIMUM_OCCUPANCY]` <br> e.g., `add_venue n/Chua Thian Poh Hall max/40`<br><br> `add_booking`e.g., `add_booking`
**Delete** | `delete_venue v/VENUE_ID`<br> e.g., `delete_venue v/1` <br><br> `delete_booking b/BOOKING_ID`<br> e.g., `delete_booking b/2`
**Find** | `find_venue v/VENUE_ID`<br> e.g., `find_venue v/1`<br><br> `find_booking b/BOOKING_ID`<br> e.g., `find_booking b/2`
**List** | `list_venues` <br> `list_bookings`
**Exit** | `bye`
