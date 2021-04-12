---
layout: page
title: Kingsley Kuan Jun Hao's Project Portfolio Page
---

Hello I’m Kingsley a Year 2 Computer Science student. I’m an aspiring researcher and content creator.

## Project: Tutor Tracker
_**Tutor Tracker**_ is a **desktop app designed to help secondary school students manage tutors and tuition appointments, optimised for use via a Command Line Interface** (CLI) for a fast and streamlined experience while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tutor Tracker can get your tuition contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented TutorSubject as well as Subject attribute classes.
    * What it does: Represents subjects that a tutor can teach.
    * Justification: Allows user to keep track of subjects that a tutor teaches.
    * Links: [#55](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/55)
    * Credits: Code is adapted from `model` code in AddressBook 3.

* **New Feature**: Implemented parsing for commands to add and edit appointments.
    * What it does: Adds command line parsing for add and edit appointments.
    * Justification: Allows application to recognise commands to add and edit
        appointments.
    * Links: [#94](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/94), [#97](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/97)
    * Credits: Code is adapted from `add` and `edit` parsing code in AddressBook 3.

* **New Feature**: Implemented editing of subject details in tutors.
    * What it does: Allows user to edit the subjects in a tutor.
    * Justification: Allows user to update subject details in a tutor without having
        to delete and re-add the tutor with new subject details.
    * Highlights: Required large addition to parsing and command execution to
        accomodate subject and subject attributes.
    * Links: [#98](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/98)
    * Credits: Code is adapted from `edit` code in AddressBook 3.

* **New Feature**: Implemented filtering of tutors.
    * What it does: Allows users to add and delete filters that filters the tutors
        shown.
    * Justification: Allows users to filter through the list of tutors efficiently
        according to their needs.
    * Highlights: Filters for each tutor attribute were implemented and rules were created to
        determine how filters of the same and different types interacted with each other. The
        GUI was also updated to display the active tutor filters to the user.
    * Links: [#152](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/152), [#158](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/158), [#170](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/170), [#316](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/316), [#318](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/318)

* **New Feature**: Implemented filtering of appointments.
    * What it does: Allows users to add and delete filters that filters the appointments
        shown.
    * Justification: Allows users to filter through the list of appointments efficiently
        according to their needs.
    * Highlights: Filters for each appointment attribute were implemented and rules were created to
        determine how filters of the same and different types interacted with each other. The
        GUI was also updated to display the active appointment filters to the user.
    * Links: [#165](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/165), [#170](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/170), [#316](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/316), [#318](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/318)

* **GUI Enhancement**: Implemented a Filters Pannel in GUI
    * What it does: Displays the active tutor and appointment filters.
    * Justification: Allows users to view their currently used filters easily
    * Highlights: Required careful design due to the limited space available taken up by other GUI components as well as requirement of not conflicting with other GUI components.
    * Links: [#170](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/170)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&tabOpen=true&tabType=authorship&tabAuthor=kingsleykuan&tabRepo=AY2021S2-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
        * Added documentation for add_tutor and list_tutor commands [#13](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/13)
        * Added documentation for Tutor and Appointment Filter features [#200](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/200)
        * Fixed bugs and inconsistencies in user guide [#205](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/205)
    * Developer Guide:
        * Added documentation for Target User Profile, Value Proposition, User Stories, as well as use cases for adding and listing tutors [#14](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/14)
        * Added documentation for Filter Feature User Stories, Use Cases, Implementation Details, Diagrams, as well as manual tests [#347](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/347)

* **Project Management**
    * Set up and manage Github organisation and team repository
    * Manage releases

* **Community**
    * Set up team meetings and goal setting
    * Dedicated PR reviewer for 2 team members as well as adhoc review of other PRs
    * Reported bugs for other teams in the cohort
