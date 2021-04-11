---
layout: page
title: Guo Jingxue 's Project Portfolio Page
---

## Project: EZManage

EZManage - EZManage is a desktop application used for the management of a tuition centre's sessions, students and tutors. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view all students
    * What it does: Allows the user to view a list of all students
    * Justification: This feature improves the product significantly because the user can easily 
      view list of students and potentially verify whether an add or delete command has been carried out successfully
    * Highlights: This feature is facilitated by PersonTypePredicate which was created to support the filter function by person type.
    

* **New Feature**: Added the ability to view all tutors
    * What it does: Allows the user to view a list of all tutors
    * Justification: This feature improves the product significantly because the user can easily
    view list of tutors and potentially verify whether an add or delete command has been carried out successfully
    * Highlights: This feature is facilitated by PersonTypePredicate which was created to support the filter function by person type.
      The feature is then refactored as list command by Channel Ng (project teammate)

* **New Feature**: Added the ability to view an individual tutor
    * What it does: Allows the user to view the details of an individual tutor by unique personID
    * Justification: This feature improves the product significantly because a user can easily view the details of a specific tutor
      and verify the information should there be a change in the tutor's details after an edit command or assign command
    * Highlights: This enhancement allows the user to easily track the change to a tutor's details, 
      especially supporting assign and edit functionalities. User can view and verify a tutor's details
      first before the subsequent assign and edit commands.
    * Credits: AddressBook Level 3

* **New Feature**: Added the ability to view an individual student
    * What it does: Allows the user to view the details of an individual student by unique personID
    * Justification: This feature improves the product significantly because a user can easily view the details of a specific student
      and verify the information should there be a change in the student's details after an edit command or assign command
    * Highlights: This enhancement allows the user to easily track the change to a student's details,
      especially supporting assign and edit functionalities. User can view and verify a student's details
      first before the subsequent assign and edit commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=jingxueguo&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=jingxueguo&tabRepo=AY2021S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `view_person`, `list` and initial `add` command. (Pull request [\#195](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/195) [\#78](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/78) [\#15](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/15))
    * Developer Guide:
        * Added manual testing for `view` command (Pull request [\#102](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/102))
        * Added documentation for features `view_person`, `list` and initial `add` command (Pull request [\#166](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/166) [\#78](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/78) [\#15](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/15))
    * ReadMe:
        * Added documentation for `view` command (Pull request [\#94](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/94))

* **Community**:
    * Reported bugs and suggestions for other teams in the PE Dry Run (Reported 7 bugs).
  
