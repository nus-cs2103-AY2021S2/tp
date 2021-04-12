---
layout: page
title: Yi Qun's Project Portfolio Page
---

## Project: MeetBuddy

MeetBuddy is a desktop application used for managing contacts and meetings. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.
The target users are NUS computing minor students who are busy moving about for their lectures, internship and social life. These students would likely want to manage their school and social life in one app, where they can easily keep track of their contacts and have their family, social and academic commitments well-organised.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find persons by group (findpg command).
  * What it does: Allows the user to find all contacts associated with a group.
  * Justification: This feature improves the product considerably because a user may want to find all his contacts in a particular group. For instance, he may want to quickly access his contacts that are in his class for sending an invitation.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The API of this command is necessary for creating the meeting people connection, which is required for finding all persons in a meeting. A meeting may have groups involved and this API allows us to extract all people in the groups of a meeting.

* **New Feature**: Added the ability to find all persons by meeting (showm command).
  * What it does: Allows the user to find all contacts associated with a meeting. 
  * Justification: This feature improves the product significantly, as it allows the user to filter the contact list to only those in a meeting. The user can now find the contacts he is meeting very conveniently.
  * Highlights: Presently, the team has decided that showm would only show contacts who are directly associated with the meeting, instead of the contacts who are in groups that are in the meeting. For subsequently iterations when the storage is more mature, showm will be updated to show those in the groups in the meeting as well. The implementation is already available in the code base, but is not executed.

* **New Feature**: Added the ability to edit a meeting (editm command).
  * What it does: Allows the user to edit the different fields of an existing meeting.
  * Justification: This feature improves the product significantly because a user can make mistakes in adding a meeting, or the meeting deatials may change over time. This feature offers a convenient way to rectify them the mistake, and for users to change meeting details, without having to delete the meeting and add it back again subsequently.

* **New Feature**: Added the ability for users to add notes. It has already been implemented in the code base, but will only be introduced in subsequently iterations.
  * What it does: Allows the user to add in and delete notes so that he can store a list of personal rmeinders.
  * Justification: This feature improves the product significantly as it allows the user may wish to store personal notes. It makes the application more comprehensive, by allowing users to manage contact list, store meeting information and maintain his personal notes in just one application.
  * Highlights: This feature is termed the NoteBook, like the AddressBook for managing contacts, and MeetingBook for managing meetings
  
* **New Feature**: Added the ability for list meetings (listm) and list both meetings and contacts (list).
  * What it does: Allows the user to find see all available meetings and contacts easily.
  * Justification: This feature improves the product significantly because a user may want to find all his contacts in a particular group. For instance, he may want to quickly access his contacts that are in his class for sending an invitation.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The API of this command is necessary for creating the meeting people connection, which is required for finding all persons in a meeting. A meeting may have groups involved and this API allows us to extract all people in the groups of a meeting.

* **Team-tasks contributions**:
  * Created over 20 Pull Requests to the project
  * Resolved bug reports on GitHub
  * Organised online zoom meetings
  * Included comprehensive unite tests for various classes
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=hengyiqun)

* **Project management**:
  * Managed releases `v1.3.trial` and `v1.3.1` (2 releases) on GitHub
  
* **Documentation**:
  * User Guide:
    * Added documentation for the aforementioned features such as `findpg` and `showm`
  * Developer Guide:
    * Added implementation details of the aforementioned features such as `findpg` and `showm`
