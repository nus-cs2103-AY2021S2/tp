---
layout: page
title: Chanell Ng 's Project Portfolio Page
---

## Project: EzManage

EzManage - EzManage is a desktop application used for the management of a tuition centre's sessions, students and tutors. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added session ID. 
  * What it does: Allows the user to identify a particular session using a unique ID given to each session when the session is first created and added into EzManage.
  * Justification: This feature improves the product significantly because the user can easily locate a specific session using the unique session ID. This feature is important because all the other session-related features in EzManage will utilize this session ID.
  * Highlights: A session ID of a session is always unique and unmodifiable once given to a session. The uniqueness of the session IDs allows for efficient management of all the sessions. 
  
* **New Feature**: Added the ability to delete a session.
  * What it does: Allows the user to delete an existing session in EzManage using the session's unique session ID.
  * Justification: This feature improves the product significantly because a user can easily manage the list of sessions and allows the app to provide a convenient way to remove sessions when mistakenly added or when a specific session is no longer needed.
  * Highlights: A session can only be deleted if the given session ID is valid and session exists in EzManage.
  * Credits: AddressBook Level 3

* **New Feature**: Added the ability to edit a session.
  * What it does: Allows the user to edit an existing session in EzManage.
  * Justification: This feature improves the product significantly because a user can easily manage the information of sessions and gives users the flexibility to easily alter a session's information after creation should details like the sessions's subject, day, timeslot or tags be outdated.
  * Highlights: To avoid potential day or timeslot clashes of a session's enrolled students and assigned tutor when editing a session, a user can only edit a session's day and timeslot if the session has no students and no tutor assigned. A user will have to unassign all students and tutor before being able to edit the session's time-related information. 
  * Credits: AddressBook Level 3

* **New Feature**: Added the ability to have an individual view of a specific session.
  * What it does: Allows the user to view an existing session in EzManage by displaying all information relevant to a session on two split panels. The left panel displays the session's information and the right panel displays the list of students enrolled in the session , if any.
  * Justification: This feature improves the product significantly because a user can easily view a specific session and allows the app to provide a convenient way to display all information relevant to a specific session. 
  * Highlights: This enhancement allows the user to easily manage a specific session by viewing the session's enrolled students then calling subsequent relevant commands to manage a session's list of enrolled students and tutor. It required an in-depth analysis of design alternatives.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=chanellNg&tabRepo=AY2021S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Wrote additional tests for all added features to increase coverage (Pull requests [\#68](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/68), [\#69](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/69),  [\#70](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/70) , [\#168](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/168) , [\#187](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/187)) :
    * session ID, sessionIdPredicate, delete session, edit session, view session , list 
  * Updated the list command to be able to view all persons, students, tutors and sessions. (Pull Request [\#70](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/70))
  * Updated the clear command to clear the whole list of persons and sessions. (Pull Request [\#164](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/164))
  * Updated the Ui on a consistent basis:
    * Addition of Ui view for the commands such as the split pane view for view commands. (Pull requests [\#70](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/70) , [\#74](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/74) , [\#91](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/91))
    * Resolved Ui issues such as when certain sets of commands are called, switching of panes, truncated fields etc. (Pull requests [\#76](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/76) , [\#161](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/161))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete_session`, `edit_session` and `view_session`. (Pull requests [\#6](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/6), [\#44](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/44) , [\#84](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/84))
  * Developer Guide:
    * Added implementation details of the `delete_session` and `edit_session` features. (Pull request [\#161](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/161))

* **Community**:
  * Reported bugs and suggestions for other teams in the PE Dry Run (Reported 19 bugs).
  
