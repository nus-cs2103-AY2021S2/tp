---
layout: page
title: Koh Vinleon's Project Portfolio Page
---

## Project: Tutor Tracker
**Tutor Tracker** is a desktop tuition management application meant for secondary students to track their tuition information, such as upcoming tuition appointments and tutor's contact information.
It focuses on the Command Line Interface (CLI) while providing users with a simple and clean Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Implemented `list_appointments`, `find_appointment` and `view_appointment`.
  * What it does: allows the user to view all the appointments, finding appointments based on tutor's name and viewing appointments on a certain date in the Tutor Tracker.
  * Justification: This feature provides the basic necessity in the application as it allows tutees to track their appointments.
  * Highlights: It mainly supports the GUI in displaying the list of appointments of the tutees. 
  * Credits: Codes are adapted from `ListCommand` and `FindCommand` in AddressBook 3.
  * Links: [#88](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/96) 

* **New Feature**: Implemented Event class.
  * What it does: serves as a superclass of Appointment and Schedule to ease of validation and GUI enhancement.
  * Justification: this feature improves the product significantly because it extends the usability of the product by providing supporting methods in validations and timetable GUI in Tutor Tracker.
  * Highlights: This enhancement affects all event-related commands to be added in the future.
  * Link: [#142](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/142) 

* **New Feature**: Implemented DateTimeValidationUtil.
  * What it does: serves as a thorough validation method to ensure the new Appointment and Schedule does not violate the datetime constraints.
  * Justification: this feature improves the product significantly because it extends the usability of the product by ensuring data integrity in Tutor Tracker.
  * Highlights: This method is used in `AddAppointmentCommand`, `EditAppointmentCommand`, `AddScheduleCommand` and `EditScheduleCommand` to ensure data are tested and validated before it can be added to TutorTracker. 
  * Link: [#304](https://github.com/glatiuden/tp/blob/0443beee162b4fb1e8a6ddc2f4e3dc24ccc1754c/src/main/java/seedu/address/commons/util/DateTimeValidationUtil.java)

* **New Feature**: Implemented Schedule class.
  * What it does: allows the user to create new Schedule objects in Tutor Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing tutees to plan and allocate their time to tuition related task/schedule in Tutor Tracker.
  * Highlights: This enhancement affects all schedule-related commands to be added in the future. 
  * Credits: Codes are adapted from `Person` in AddressBook 3. 
  * Links: [#142](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/142), [#172](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/172) 

* **New Feature**: Implemented Schedule's CRUD Commands.
  * What it does: allows the user to **create, update, retrieve and delete** schedule records from the Tutor Tracker's Schedule Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing tutees to track and manage their schedules.
  * Highlights: This enhancement affects all schedule-related commands to be added in the future.
    The implementation was challenging as it required changes to the existing model, logic, storage and UI.
  * Links: [#142](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/142), [#159](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/159)
  * Credits: Codes are adapted from `AddCommand`, `EditCommand`, `ListCommand` and `DeleteCommand` in AddressBook 3. 

* **New Feature**: Implemented Reminder class.
  * What it does: allows the user to create new Reminder objects in Tutor Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing tutees to store reminders in Tutor Tracker.
  * Highlights: This enhancement affects all reminder-related commands to be added in the future. 
  * Links: [#185](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/185), [#204](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/204)
  * Credits: Codes are adapted from `Person` in AddressBook 3.
    
* **New Feature**: Implemented Reminder's CRUD Commands.
  * What it does: allows the user to **create, update, retrieve and delete** reminder records from the Tutor Tracker's Reminder Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing tutees to track and manage their reminders.
  * Highlights: This enhancement affects all reminder-related commands to be added in the future.
    The implementation was challenging as it required changes to the existing model, logic, storage and UI. 
  * Link: [#185](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/185)
  * Credits: Codes are adapted from `AddCommand`, `EditCommand`, `ListCommand` and `DeleteCommand` in AddressBook 3. 
    
* **GUI Enhancement**: Implemented TimeTable window GUI.
  * What it does: allows the user to view appointment and schedule in a timetable GUI in Tutor Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing tutees to track and manage their appointments and schedules in a graphical representation.
  * Highlights: This feature allows tutees to open a window to view their appointments and schedules just like a typical school timetable.
    The implementation was challenging as it requires a lot of checks before the dynamic population of UI objects. 
  * Link: [#168](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/168)
  * Credits: adapted the solution and reused some codes from senior team [AY2021S1-CS2103T-W13-3](https://github.com/AY2021S1-CS2103T-W13-3/tp/tree/master/src/main/java/seedu/homerce/ui/schedulepanel). 

* **GUI Enhancement**: Added Calendar
  * What it does: allows the user to view `appointment` and `schedule` on a certain date through a calendar interface.
  * Justification: this feature improves the product significantly because it extends the usability of the product by offering an alternative way to view appointment and schedule on a certain date.
  * Highlights: This feature enhances the overall GUI's look and feel as well as provide easability to user.
  * Credits: reused codes from senior team [AY1920S2-CS2103T-T10-3](https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/ui/CalendarView.java). 
  * Link: [#58](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/58)  
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=glatiuden&tabRepo=AY2021S2-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Contributed to the management of the issue tracker and pull requests on GitHub
  * Handles integration 
    
* **Enhancements to existing features**:
    * Update the overall GUI to fit the mockup design [#58](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/58)
    * Update the GUI for schedule feature [#181](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/181)
    * Update the GUI for reminder feature [#201](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/201)
    * Update the GUI for TabPane and auto tabs switching [#290](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/290)

* **Documentation**:
    * User Guide:
      * Added documentation for the feature `list_appointments`, `view_appointment` [\#2](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/2)
      * Added documentation for the feature `add_schedule`, `list_schedules`, `edit_schedule`, `view_schedule` and `delete_schedule` [\#212](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/212)
      * Added documentation for the feature `timetable` [\#212](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/212)
      * Added documentation for the feature `add_reminder`, `list_reminders`, `edit_reminder` and `delete_reminder` [\#212](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/212)
      * Did cosmetic tweaks to existing documentation format [\#232](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/232)

    * Developer Guide:
      * Added introduction: [\#5](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/5)
      * Did cosmetic tweaks to existing documentation of Design Section - `Architecture`, `Design`: [\#5](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/5)
      * Added user cases of the `list_appointments` feature: [\#5](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/5)

* **Community**:
    * Constantly approved PRs of group members despite not being their dedicated reviewer.
    * Reported bugs and suggestions for other teams in the class

* **Tools**:
