---
layout: page
title: Gu Yichen's Project Portfolio Page
---

## Project: TutorsPet

TutorsPet is a **desktop app designed for private tutors in Singapore to manage students’ information** evolved from AddressBook - Level 3.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has altered around 10 kLoC from the original AB3.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=chenzaza&tabRepo=AY2021S2-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

**Enhancements implemented**

* **Add Guardian's name and Guardian's phone for a Student** [\#41](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/41)
  * Justification: The guardian's contacts are essential information for our target users.
  * Highlights: This enhancement affects the formatting of Person's list, and existing Add, Edit command.
    It requires an in-depth analysis of storage design, as well as the command and command parser structures and checks.
    
* **Add a Subject field for a Student** [\#118](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/118)
  * Justification: 
    The subject field restrict the input content that can be tagged on students stored in TutorsPet
    The Tag in AB3 shares a similar design with Subject, but has no content restriction. It did not address any needs of our target users.  
  * Highlights: This enhancement affects the existing Add, Edit and Search command.
    It requires an in-depth analysis of the command and command parser structures and checks.
    
* **Make some personal details of a Student optional** [\#99](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/99)
  * Justification:
    When a user first added in a student's contact, some details may not be available. Thus, TutorsPet allows all fields except name and phone being empty initially, and being added in
    later with Edit command.
  * Highlights: This enhancement affects the constructor of a Student and Student List for storage.
    It requires an in-depth analysis of storage design, as well as the command and command parser structures and checks.

* **Enhancements to existing features**:
  * Allow students with duplicate name to be added, while make the phone number a unique identifier of a Student [\#219](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/219)
  * Updated error messages displayed in the message box of GUI
  * Wrote additional tests for existing features 

**Project management**:
* Managed releases `v1.3` - `v1.4` (2 releases) on GitHub
* Set milestones `v1.1`, `v1.2`, `mid-v1.2`, `1.3` on GitHub

**Documentation**:
* User Guide:
  * Updated Introduction of TutorsPet
  * Added documentations for the features `add` and `edit` 
  * Did cosmetic tweaks to existing documentation of features 
  * Added section Coming soon to introduce the features we plan to implement in the next version 
  
* Developer Guide: 
  * Added implementation details and manual testing instructions of the `add` feature and `edit` feature.
  
**Community**:
  * PRs reviewed (with non-trivial review comments): [\#81](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/81)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/chenzaza/ped/issues/1), [2](https://github.com/chenzaza/ped/issues/4), [3](https://github.com/chenzaza/ped/issues/8))
