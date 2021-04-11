---
layout: page
title: Jacob Pang's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense] (https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=jacob-pang)

* **Architecture**: Added appointment-variant features
  * Implemented the defining properties of an appointment: connection between patients and doctors, and non-conflicting.
  * Implemented timeslots with flexibility to be defined by duration or specific date-time.
  * Implemented an appointment schedule storing created appointments sorted by starting time and ensures no conflict between appointments.
  * Implemented appointment-variant storage classes.
  * Modified `Model` and `Logic` to execute appointment-related commands and tasks.
  * Highlights: the `Appointment` implementation sets the foundation for the app and subsequent features, requiring an in-depth analysis of the application workflow and logic.

* **Architecture**: Added polymorphism into person-features
  * Made `Person` an abstract class in order to implemented `Patient` and `Doctor` derived classes.
  * Added generics into person-dependent `Model`, `Storage` and `Logic` implementations to accept extensions of the `Person` class.
  * Justification: abstracts shared attributes between `Patient` and `Doctor`, reducing duplication of code and focused the design of collection-typed classes such as `UniquePersonList` and `Addressbook` to act only on `Person` definitive characteristics.
  * Highlights: eases the implementation of `Doctor` in v1.3

* **New Feature**: Added command for adding appointments.
  * Enables the user to create an appointment based on existing patient and doctor records.
  * Designed to allow the user to specify either the duration or the end time of the appointment for flexibility.

* **Project management**:
  * Helped to fix critical features and bugs in other portions.
    * Fixed errors in the initial implementation of the `Doctor` class. [#73](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/73)
    * Fixed design flaws in `EditAppointmentCommand`. [#147](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/147)

* **Documentation**:
  * User Guide:
    * Added documentation on `add-patient`, `add-doctor` and `add-appt` features.
  * Developer Guide: 
    * Added appointment diagram under architecture overview.
    * Added user stories.
    * Added the use cases on `add-patient`, `add-doctor` and `add-appt` features.

* **Community**:
  * Reviewed a total of 22 PRs: ([#13](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/13), [#14](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/14), [#15](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/15), [#18](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/18), [#22](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/22), [#24](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/24), [#30](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/30), [#43](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/43), [#45](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/45), [#49](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/49), [#54](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/54), [#55](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/55), [#62](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/62), [#66](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/66), [#68](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/68), [#69](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/69), [#73](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/72), [#74](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/74), [#80](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/80), [#82](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/82), [#149](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/149), [#157](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/157)
  * Provided 21 comments on peer PRs.
