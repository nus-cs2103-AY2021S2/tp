---
layout: page
title: Huang Zhenxin's Project Portfolio Page
---

## Introduction
This page serves to document my contributions to the project RemindMe under NUS module CS2103T in AY20/21 semester 2.

## Project: RemindMe

RemindMe is a desktop application for keeping track of user events and deadlines, optimised for use via Command Line 
Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).
RemindMe allows students to always be aware of exams and events deadline as there will be reminder pop-ups and calendar
for students to see! 
It is written in Java, and has about 17 kLOC contributed.

<br> 

## Summary of Contributions

* **New Feature**: Added the Reminder Window to show deadlines/events happening in the next 3 days
    * What it does: A automatic pop-up reminder window to show the deadlines/events happening in next 3 days.
    * Justification: This feature improves RemindMe as a reminder application and enhance the ability of RemindMe to inform users of the more recent/urgent deadlines/events.
    * Highlights: This feature works closely with storage and analyze the date and time to provide the range of deadlines/events to be selected for display. The UI of the Reminder is designed to be attractive as well.
    
<br>    
    
* **New Feature**: Added the ability to save modules and general events in JSON.
    * What it does: Allows modules and general events to be saved locally to a JSON.
    * Justifications: It is important the modules and assignment that a user adds to RemindMe is properly saved and can be referred next time RemindMe runs. The contents saved should not take too much space of the JSON file. Also, RemindMe should be able to read all the relevant information such as exams and assignments from the saved modules when starting.
    * Highlights: Create Json adapted classes for Assignment and Exam to be placed in Module when storing.

<br>

* **New Feature**: Added the Calendar window and command to open the Calendar window.
    * What it does: A calendar with a summary of deadlines/events on a particular date.
    * Justifications: It is important users get to view their schedules in a more straight forward and clear way with our calendar view.
    * Highlights: Attractive and clear UI of calendar for better visualization. 

<br>
     
* **New Feature**: Added multi-tabs to separate Modules/Contacts/Events and provide list views for these tabs. 
    * What it does: Separation of the module List, contact List and event List navigated by GUI tab or with the respective CLI command.
    * Justification: This feature is important to RemindMe as users might be overwhelmed and get confused by too much information if it is not separated. 
    * Highlights: There are two ways to switch tabs. 1. Manual switching with mouse click. 2. Automatic switching when relevant commands are entered.

<br>

* **Code contributed**: My code contribution to RemindMe can be found via [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=W15-1&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Hzxin&tabRepo=AY2021S2-CS2103T-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

<br>


* **Project management**:
    * Managed releases and milestones `v1.2` - `v1.4` (4 releases/milestones) on GitHub

<br>


* **Enhancements to existing features**:
    * Adding of test cases to the entire Storage package. [\#157](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/157) [\#232](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/232)
    * Improving and customize the UI of the main window and creating the RemindMe logo. [\#58](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/58) [\#110](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/110) [\#127](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/127) [\#148](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/148) [\#219](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/219) 
    * Integrate done status of assignment into the storage. [\#203](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/203)

<br>
<br>

* **Documentation**:
    * User Guide:
        * Added Table of contents, introduction, start-up and documentation about the Add feature. [\#25](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/25) [\#166](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/166/files)
        * Improving the format and readability of UG. [\#47](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/47) [#194](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/194/files) [\#213](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/213)
        * Improving command summary table. [\#212](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/212)
  
    * Developer Guide:
        * Added documentations for Target user profile, value proposition, and user stories. [\#36](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/36)
        * Update implementations of EditFeature with details explanations and UML diagrams. [\#187](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/187)
        * Update and change class diagrams for the UI component and Model component. [\#332](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/332/files)

<br>

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#158](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/158) [\#176](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/176)
