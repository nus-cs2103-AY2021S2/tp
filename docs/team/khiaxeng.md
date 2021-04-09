---
layout: page
title: Wong Khia Xeng's Project Portfolio Page
---

## Project: Taskify

Taskify is a desktop task management application for university students. Using Taskify, students can
manage all their tasks (academics/personal/CCAs) effectively and seamlessly. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLOC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to track and modify the Status of a Task 
    * What it does: Allows the user to view the Status of a Task. A Status comes in the form of `expired`, `completed` and `uncompleted`.
    * Justification: This is a fundamental feature as users need to be able to track and mark the statuses of their tasks as `completed` or `uncompleted`.

* **New Feature**: Added the ability to view Tasks based on the Task's Deadline.
    * What it does: Allows the user to show Tasks that have the same Deadline as the specified Deadline.
    * Justification: This is feature is important as users can get a better idea of which Tasks to perform first by viewing the Tasks based on their Deadline.
    * Highlights: This feature also introduced shortcut keywords such as `today` and `tomorrow` for the convenience of the user.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=khiaxeng&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Enhancement**: Adding a Task without specifying the Date defaults to the current Date.
    * What it does: If an `add` command does not specify the Date, the newly added Task will have a default Date of today's date.
    * Justification: This feature acts as a shortcut for the user as they can quickly specify a Task that is due today without typing out the entire Date.
  
* **Other Enhancements**:
  * Refactored elements of `addressbook` to `taskify` [\#176](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/176) 
  * Refactored original `Email` field to `Status` field [\#34](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/34)
  * Updated default values of previous addressbook to sample tasks for Taskify [\#95](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/95)
  * Add multiple exceptions to handle unique invalid Date inputs such as Feb 29 on non-leap years [\#153](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/153)
  * Wrote tests for the multiple classes ([\#177](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/177, [\#169](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/169)))
  
  
* **Project Management**:
  * Team Lead - Responsible for communication between Team and teaching staff.
  * Scheduling - Responsible for allocating deadlines and ensuring the team meets deadlines
  * Renamed majority of the product to Taskify.
  
* **Documentation**:
  * User Guide:
  * Developer Guide:
  
* **Community**:
  * Reported bugs and provided suggestions for other teams in the cohort. (Examples: [1](https://github.com/khiaxeng/ped/issues/2), [2](https://github.com/AY2021S2-CS2103-W16-3/tp/issues/259), [3](https://github.com/AY2021S2-CS2103-W16-3/tp/issues/261))
  
