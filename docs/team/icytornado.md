---
layout: page
title: Zhang Peng's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=icytornado)

**Features Implemented**:
1. Implementation of the `edit-appt` command
    - *Description*:
        - `edit-appt` command allow the user to edit an existing appointment by changing the fields for the appointment preceded by the associated prefixes.
   
2. Creation of the  `Doctor` class
    - Added in the `Doctor` class for doctors to appear in the appointments.
   
3. Implementation of `edit-doctor` command
   - *Description*:
      - `edit-doctor` command allow the user to edit an existing doctor by changing the fields for the doctor preceded by the associated prefixes.

4. Implementation of `delete-doctor` command
   - *Description*:
      - `edit-doctor` command allow the user to delete a doctor by specifying the index of the doctor in the doctor list.
   
5. Implementation of `add-doctor` command
   - *Description*:
      - `add-doctor` command allow the user to add a new doctor into the doctor list.

5. Implementation of `clear-doctor` command
   - *Description*:
      - `clear-doctor` command allow the user to add a new doctor into the doctor list.

6. Implementation of `list-doctor` command
   - *Description*:
      - `list-doctor` command allow the user to list all doctors in the doctor list.


3. List features
    - modified the original `list` to `list-appt`, and added `list-patient` and `list-doctor` to list patients and doctors respectively. Also added tests for these commands.

4. Clear features
    - modified the original `clear` to `clear-appt` and added `clear-patient` and `clear-doctor` to clear patient and doctor records respectively. Also added tests for these commands.

* **Documentation**:

* **Community**:

* **Tools**:
