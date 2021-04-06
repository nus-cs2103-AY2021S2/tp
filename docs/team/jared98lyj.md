---
layout: page
title: Jared Lim's Project Portfolio Page
---

## Project: Tutor Tracker

Tutor Tracker is a desktop tutor finder application that links a user with multiple
tutors in the database. The user interacts with it using a CLI, and it
has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added an Appointment class to represent the relation between 
  tutor and tutee. 
  * Rationale: Appointment class served as a holder for creating new Appointments 
  based on tutor.
  * Status: Complete as it has been fully integrated into the app, with CRUD function, 
    with individual storage utility, and respective test methods as well.
  * Link: [#38, #79] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/38,
    https://github.com/AY2021S2-CS2103-T14-3/tp/pull/79)

* **New Feature**: Added add_appointment, delete_appointment function. 
  * Rationale: Helped provide a basic way to add and delete appointments into user 
    system. Also created AppointmentBook storage to facilitate saving and storage for appointments.
  * Status: Complete as it has been integrated well, and take noted of special 
    conditions like clash of datetime and duplicate appointments.
  * Link: [#85] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/85)

* **New Feature**: Added Budget feature to track each person's individual budget and 
  total cost of appointments. 
  * Rationale: User might have a personal budget and it is inconvenient for him to 
    keep looking at the appointment list to see the outstanding cost. Budget feature 
    provides a convenient summary of user's personal budget alongside the 
    outstanding cost of all appointments
  * Status: Complete as it has been integrated with the app, with CRUD functions, 
    and GUI.
  * Link: [#138] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/138)

* **New Feature**: Added CRUD functions for Budget feature. This includes add_budget, 
  edit_budget, delete_budget, and view_budget. Also created BudgetBook storage utility.
  * Rationale: Basic utility functions for user.
  * Status: Complete as it has been integrated with the app, with Budget Book 
    storage utility to facilitate saving and loading.
  * Link: [#138] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/138)
  
  
* **Code contributed**:
  * TP Dashboard summary link:
  https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=jared98lyj&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jared98lyj&tabRepo=AY2021S2-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false

* **Project management**:
  * Refactored tutor and favourite into packages. [#171] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/171)

* **Enhancements to existing features**:
  * Fixed existing bugs with appointment and tutor [#171] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/171)
  * Added numerous Appointment Test classes [#230] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/230)
  * Improved code logic for association between Appointment and Tutor [#169] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/169)
  * Added Budget GUI. [#293] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/293)
  
* **Documentation**:
  * User Guide:
    * Added documentation (format and example usage) for the command view_tutor [#36]
      (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/36)
    * Added documentation for Budget features. (format and example usages) for
      add_budget, delete_budget, edit_budget, and view_budget. [#190] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/190)
    * Updated documentation for Appointment features. [#294] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/294)

  * Developer Guide:
    * Added Non-Functional Requirements [#37] (https://github.
      com/AY2021S2-CS2103-T14-3/tp/pull/37)
    * Updated DG for Budget feature. [#139] (https://github.com/AY2021S2-CS2103-T14-3/tp/pull/139)

* **Community**:
  * Help review Team PRs.
