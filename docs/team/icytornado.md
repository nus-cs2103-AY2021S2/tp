---
layout: page
title: Zhang Peng's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense] (https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=icytornado)

* **Features Implemented**:
  * Implementation of the `edit-appt` feature:
    - *Description*:
        - `edit-appt` command allow the user to edit an existing appointment by changing the fields for the appointment.
        - `edit-appt` + `index` + `field` to edit => edit the specified field of the appointment specified by the appointment index.
        - the `field` to edit is preceded by its related prefix, i.e. `pt/` for patient, `dr/` for doctor, `at/` for appointment start date and time, `to/` for appointment end date and time, `dur/` for appointment duration, `/t` for appointment tags.
    
  * Collaborated with teammates in the implementation of `Doctor` feature and its command features.
    - *Description*:
        - list of `Doctor`commands: `add-doctor`, `delete-doctor`, `list-doctor`,`find-doctor`, `clear-doctor`, `edit-doctor`.
        - Refer to pull request [#73](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/73)
  * Added some relevant test classes for `Doctor`.


* **Documentation**:
  * User Guide:
    - Added documentation on `edit-appt` and `edit-patient` feature.
        - Pull request [#50](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/50)
  * Developer Guide:
    - Updated the UML diagrams and description for the Model component under the Design section:
      * [Model Class Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/ModelClassDiagram.puml)
      * [Better Person Model Class Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/BetterPersonModelClassDiagram.puml)
      * [Better Appointment Model Class Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/BetterAppointmentModelClassDiagram.puml)
        - Pull request [#163](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/163)
    - Added user stories for some command features.
       - Pull request [#154](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/154)
    - Added Use Case documentation on `edit-patient`, `find-patient`, `delete-doctor`, `edit-doctor`, `find-doctor`, `edit-appt` features.
       - Pull request [#162](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/162)
    - Added Instructions for Manual Testing for `edit-patient`, `find-patient`, `edit-doctor`, `find-doctor`, `edit-appt` features.
       - Pull request [#175](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/169)


* **Project management**:
   * Updated my personal PRs with the relevant tags and milestones.
   * Closed and merged a few of my personal PRs.
   

* **Community**:
   * PRs reviewed:
     Reviewed pull-requests
     [#161](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/161)
     [#147](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/147)
     with some comments.
   * Reported 17 Bugs in PE Dry Run

