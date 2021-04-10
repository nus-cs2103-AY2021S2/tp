---
layout: page
title: Chiang Kee Woon Jonathan's Project Portfolio Page
---

## Introduction
This page serves to document my contributions 
to the project RemindMe under NUS module CS2103T in AY20/21 semester 2.

## Project: RemindMe
RemindMe is a desktop application for keeping track of user events and deadlines, optimised for use via Command Line
Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).
RemindMe allows students to always be aware of exams and events deadline as there will be reminder pop-ups and calendar
for students to see! It is written in Java, and has about 17k LOC contributed.
<br>

Given below are my contributions to the project

* **New feature**: Added a functional calendar for RemindMe calendar window (GUI) (Pull request [\#91](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/91))
    * Justification: This allows users to be able view events they have in a calendar format.
    * Highlights: Require understanding of referenced code and only implementing necessary lines for current calendar. 
    * Credits: Referred to the codes from https://github.com/AY2021S1-CS2103-W14-1/tp.
     
* **New feature**: Added a storage specifically for calendar. (Pull request [\#119](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/119))
    * Justification: Only event related data are required from the storage for the calendar.
    Therefore, a `calendarStorage` is implemented to retrieve such information from the storage and store it separately.
    
* **New feature**: Added a super class `event` for events (assignments/modules/birthday/general events) (Pull request [\#119](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/119))
    * Justification: The entities mentioned all belong under the idea of an event. Additionally, we wanted to make
    a super class such that `calendarStorage` can store it with more convenience under a `EventList`.

* **New feature**: Added a list view of events for a day in the calendar (GUI) (Pull request [\#236](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/236))
    * Justification: Allows users to click on date box in calendar and see a list of events for that day.
    * Highlights: Require understanding of referenced code and only implementing necessary lines for the list view. 
    * Credits: Referred to the codes from https://github.com/AY2021S1-CS2103T-T12-3/tp for the design.
    
* **Bug Fixing**:
    * Fix bug for calendar window not updated when add/edit/delete of events. 
    (Issue [\#269](https://github.com/AY2021S2-CS2103T-W15-1/tp/issues/269), 
    Pull request [\#298](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/298))
    * Fix bug for out of window texts and truncated names for GUI.
    (Issue [\#249](https://github.com/AY2021S2-CS2103T-W15-1/tp/issues/249), Issue [\#284](https://github.com/AY2021S2-CS2103T-W15-1/tp/issues/284),
     Pull request [\#330](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/330)) 

* **Testing**:
    * Add test files for add commands (Pull request [\#311](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/311))
    * Add test files for parsers for add commands (Pull request[\#349](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/349))

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=banchiang&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=banchiang&tabRepo=AY2021S2-CS2103T-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=)

* **Documentation**:
    * User guide: 
        * Added documentation for `calendar`,`exit`, saving data and FAQ section. (Pull request [\#171](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/171))
    * Developer guide:
        * Added documentation of implementation of `calendar` (Pull request [\#188](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/188)
        * Added documentation of user stories and use cases (Pull request [\#318](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/318)))
        
* **Community**:
    * Highlighted CI build macos-latest crashing in github (Issue [\#281](https://github.com/nus-cs2103-AY2021S2/forum/issues/281))
