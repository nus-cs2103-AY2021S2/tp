---
layout: page
title: Phoe Chuan Yi, Benny Project Portfolio Page
---


## Project: EzManage

EzManage - EzManage is a desktop address book application used for the management of a tuition centre's sessions, students and tutors. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature** : Add Student/Tutor Feature [\#5](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/5)
  * What it does: Allow the user to add a student or tutor depending on the person type
  * Justification: Student and Tutor are the crucial entities in EzManage, without proper segregation of student and tutor, administrative tasks become difficult and tedious. This also allow filtering of personnel easier as each person is given a person type.
  * Highlights: This is modified using the AddressBook Level 3's original structure when adding a person into AddressBook. When a student or tutor is added, the UI is able to easily differentiate between student and tutor. The students and tutors are also saved into EzManage's person.JSON with a PersonType attribute for easy future references.
  * Credits: AddressBook Level 3

* **New Feature** : Assign Feature [\#66](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/66)
  )    * What it does: Allow the user to assign student(s) and tutor to a session
    * Justification: Instead of using pen and paper to indicate which student/tutor belongs to which session, this feature allow user to swifty assign student and tutor to a class. The application will also warn the user if there are any schedule clashes or if the personnel is already assigned to a class to prevent duplicate.
    * Highlights: Each student/tutor has a list of sessions that he/she is assigned to. Likewise, sessions also have a list of students and a tutor assigned to it. This makes retrieval of information much easier.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Bennyphoe&tabRepo=AY2021S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Add User Interface to view all sessions. Implemented Storage functionality to store sessions in JSON file for retrieval. Create the necessary classes to convert Sessions to JSON Objects [\#42](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/42)
  
* **Documentation**:
    * User Guide:
        * Add documentation for the features `add_person` and `assign` [\#92](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/92) [\#165](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/165) [\#10](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/10)
    * Developer Guide:
        * Add implementation details of the `add_person` and `assign` features. [\#175](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/175)

* **Community**:
    * Reported bugs and suggestions for other teams in the PE Dry Run.
