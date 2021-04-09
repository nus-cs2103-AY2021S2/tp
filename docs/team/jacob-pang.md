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
  * Modified the application model and logic to execute appointment-related commands and tasks.
  * Required an in-depth analysis of the application workflow and logic.

* **Architecture**: Added polymorphism into person-features
  * Made `Person` an abstract class in order to implemented `Patient` and `Doctor` derived classes.
  * Added generics into person-dependent storage and processes to accept extensions of the `Person` class, reducing potential duplication of code.
  * Eased the implementation of `Doctor` class in `v1.3`.

* **New Feature**: Added command for adding appointments.
  * Enables the user to create an appointment based on existing patient and doctor records.
  * Designed to allow the user to specify either the duration or the end time of the appointment for flexibility.

* **Project management**:
  * Reviewed team PRs and provided constructive feedback.
  * Helped to fix critical features and bugs in other portions.
    * Fixed errors in the initial implementation of the `Doctor` class.
    * Fixed design flaws in `EditAppointmentCommand`.

* **Documentation**:
  * User Guide:
    * Added documentation on `add-patient`, `add-doctor` and `add-appt` features.
  * Developer Guide: 
    * Added user stories.
    * Added the use cases on `add-patient`, `add-doctor` and `add-appt` features.

* **Community**:

* **Tools**:
