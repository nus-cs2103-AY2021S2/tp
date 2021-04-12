---
layout: page
title: Yeoh Hsin Ying Candice's Project Portfolio Page
---

## Project: PocketEstate

PocketEstate enables easy organisation of mass clientele property information through sorting of information by price,
location and housing type, that may otherwise be difficult to manage.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=candyhy&tabRepo=AY2021S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features & Enhancements

* **Enhancement**: Graphical User Interface (GUI)
    * What it does: Simultaneously displays both property and appointment lists side by side
    * Justification: This feature improves the product significantly because it enables integration of commands on both property and appointment lists, yet enables the user to conduct distinct commands on both lists and view its effects simultaneously without having to call `list property` or `list appointment` prior to each operation
    * Highlights: Greying out of properties and appointments with expired deadlines in the GUI, a newly designed Light theme colour scheme with a organised, minimalist design that highlights important details.
    The implementation was challenging due to limited experience and precise nature of fxml.


* **Enhancement**: Maintains unique `Property` and `Appointment` lists, and all their attributes.
    * What it does: Supports other commands like `add`, `edit`, `sort`, `filter` as all property and appointment data are maintained in a list. The unique property and appointment lists prevents addition/ edit of `Property` / `Appointment` that results in duplicate  `Property` / `Appointment` listings.
    * Justification: The core functionality of our application is heavily reliant on both of these lists, as `Property` and `Appointment` lists are necessary to maintain `Property` and `Appointment` data PocketEstate manages.
    * Highlights: Prevents addition of duplicate `Property` and `Appointment` to minimise redundant data.

<div style="page-break-after: always;"></div>


* **Enhancement**: Implemented storage support for both `Property`, `Appointment` and their attributes.
    * What it does: Performs accurate parsing of `Property` and `Appointment` data from the respective `Property` and `Appointment` lists to data forms that are reader friendly for display to the user, which is then stored in separate json files.
    * Justification: Proper parsing and storage of `Property` and `Appointment` data ensures data integrity and reliability of PocketEstate that aims to manage significant property and appointment data. 
    * Highlights: Maintains 2 separate json files with reader friendly data (Eg. Parsing of date from 01-12-2021 to Dec 12, 2021, time from 1500 to 3:00pm) to be presented to the user in GUI 
  
* **Enhancement**: Added the ability to list properties and appointments through the three different `list` commands - `list all`, `list property`, `list appointment`.
    * What it does: Allows the user to list a property, list an appointment or list both properties and appointments to the application.
    * Justification: These basic core commands are required for users to view information in the application.
    * Highlights: Allows users to retrieve appointments or properties, separately or together after a sort or filter command.


* **Additional Enhancements**:
    * Wrote additional tests ([\#324](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/324), [\#204](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/204), [\#205](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/205), [\#300](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/300), [\#306](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/306), [\#329](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/329), [\#335](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/335))
  

#### Documentation

* **User Guide**:
    * Added documentation for commands `list all`, `list property` and `list appointment` ([\#39](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/39))
    * Added User Interface section ([\#343](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/343))
    * Annotate Ui diagrams ([\#339](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/339))
  
* **Developer Guide**:
    * Added effort section of DG. ([\#322](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/322))
    * Added user stories and use cases for list command. ([\#314](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/314), [\#38](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/38))
    * Updated Storage component and Storage class diagram ([\#199](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/199))
    * Formatted Appendix with numbering for each section ([\#322](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/322))

#### Community

* PRs reviewed (with non-trivial review comments): ([\#162](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/162), [\#126](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/126), [\#298](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/298), [\#111](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/111))
* Provided tips through forum discussions (examples: [Configuring IntelliJ to follow module coding standards for switch statements](https://github.com/nus-cs2103-AY2021S2/forum/issues/42))
* Provided help through forum discussions (examples: [\#23](https://github.com/nus-cs2103-AY2021S2/forum/issues/23))
* Reported a bug in the original AB3 Storage class diagram (issue: [\#273](https://github.com/nus-cs2103-AY2021S2/forum/issues/273))
