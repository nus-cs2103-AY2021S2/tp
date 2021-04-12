---
layout: page
title: Chia Jia-Xi, Kymie's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=kwmiw&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **New Feature**: Added command for locating appointments.
    * Enables the user to locate an appointment based on existing patient and doctor records.
    * Designed to allow the user to specify start time and tags of the appointment for more succint appointment searches.
    
* **New Feature**: Added Timeslot Parser for App-Ointment to read more date and time formats.
    * Enables the user to key in various date and time formats to his/her preference for easier usage.
    * Justification: This feature improves the product because a user can be accustomed to certain date and time formats.
      Hence instead of referring to the standard few accepted formats, which can be prone to error if the user is not accustomed to it, the user is able to use App-Ointment in a familiar way.
      Users may not have a calendar or a date at the back of their head, thus the ability for App-Ointment to help users identify common date patterns such as `next year` for annual check-ups is helpful when booking/editing appointments swiftly and accurately.
    * Highlights: Designed to allow for an easy reconfiguration, should future maintainers of App-Ointment which to add on new date and time formats

* **New Feature**: Added a toggle ability that allows the user to navigate to previous commands using up/down keys.
    * What it does: allows the user to navigate all previous commands one at a time. Preceding undo commands can be reversed in order by toggling `up` arrow keys.
    * Justification: This feature improves the product because a user is prone to making mistakes in commands and the app should provide a convenient way to rectify them. Else, a long string of commands would need to be re-input, placing a considerable frustration in the user experience.
    * Credits: Toggle suggestion from teammate @onnwards
    
* **Documentation**:
    * User Guide:
        * Revised documentation on `find-appt`, `edit-appt` features.
        * Added documentation on `toggle user inputs`

