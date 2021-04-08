---
layout: page
title: Onn Wei Sin's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

<br>

Given below are my contributions to the project.

**Code contributed**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=onnwards)
- Adaptation of code to rename all mentions of App name

<br>

<u>Features Implemented</u>: 
1. Implementation of UUID field in `Person` classes
    - *Problem*:
        - Patient and Doctor were immutable Objects (From `Person` in AB-3, good code practice that we didn't want to change)
        - Edits to Patient and Doctor replaced the object in the model with a completely new object.
        - Since an `Appointment` contains a `Patient` and a `Doctor`, upon an edit to a `Patient` or `Doctor` instance, `Appointment` still held the old `Patient` and `Doctor` objects, and was unable to be updated.
    - *Solution*:
        - **Idea**: Implement a primary key/foreign key system so that appointments are able to refer to patients and doctors with the foreign key.
        - Created a `UUID` field in `Person` classes
        - Instead of holding a reference to a `Patient` or `Doctor` object, `Appointment` holds instead a reference to 2 `UUID` objects, one each for `Patient`'s and `Doctor`'s `UUID`.
        - `AppointmentDisplay` class was created (extending from `Appointment`) to not affect GUI
        - Use of static `java.util.Map` `PATIENT_MAP` and `DOCTOR_MAP` to obtain Person `Name` from `UUID`

2. Closest Command Feature
    - Use of Levenshtein distance algorithm with dynamic programming (reused)
    - to calculate minimum edit distance from an invalid command entered by user to known commands
    - The known command with the lowest minimum edit distance will be displayed to the user as a prompt.

3. List features
    - modified the original `list` to `list-appt`, and added `list-patient` and `list-doctor` to list patients and doctors respectively. Also added tests for these commands.

4. Clear features
    - modified the original `clear` to `clear-appt` and added `clear-patient` and `clear-doctor` to clear patient and doctor records respectively. Also added tests for these commands.

<br>

**Documentation**:
Contributed to team sections of UG/DG:
- Entire Adaptation of UG/DG to remove all mentions of AB-3 (Updated all links, product names in UG/DG, Ui picures, AB-3 features removed in UG)
- Added to DG on behalf of team: Target User Profile, Value Proposition, Constraints, Non-Functional/Technical/Performance/Quality/ Requirements, Glossary

<br>

**Project management and Team Tasks**:
- Set up GitHub Organisation
- Set up GitHub Team Repo
- Set up Grade, Issue Tracker, GitHub Actions, GitHub Pages, Branch Protection Rules
- Created, closed, and managed release for all milestones (v1.1, v1.2, v1.3)
- Authored 35 issues (Out of 36 issues authored by team)
- Recorded all feature demos (v1.2, v1.3)
- Made sure every single item on tP team progress dashboard was done by the the day before tutorial deadline

<br>

**Community**:
- Reviewed **all** PRs to the team repo that were not:
    1. Authored by me.
    2. Individual tP progress (tutorial)
    3. Closed before merging
    - A total of 33 PRs reviewed: (#19, #20, #23, #25, #27, #28, #31, #32, #41, #47, #48 #49 #50 #51 #54 #55 #63 #67 #70 #71 #72 #73 #74 #78 #81 #83 #88 #89 #146 #147 #150, #151, #152)
    - A total of 104 comments on peers' PRs
    - Reviews were given at most within 2 days, usually within a day, from opening of a PR.
- 5 Comments in forums about checkstyle issues and helping with bugs in students outside of my team
- 15 Bugs reported in PE Dry Run

