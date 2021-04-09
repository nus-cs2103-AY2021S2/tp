---
layout: page
title: Tomashiwa's Project Portfolio Page
---

## Project: insurance Sure Can Arrange Meeting (iScam)

iScam is a desktop application used for insurance agents to keep track of their clients and the meetings that they have. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 18k
LoC.

Given below are my contributions to the project.

* **New Feature**: Meetings
  * What it does: Allows the insurance agent to record the meetings they going to have with their clients and track which meeting is coming soon, all through CLI commands and view through the GUI. 
  * Justification: Even though insurance policies can be purchased online, many clients still prefers the personal touch of having an agent to handle the matters. The interaction between clients and agents often comes in the form of physical or digital meetings. So, agents may need to juggle all these meetings and ensure not to dissapoint any clients and lose a policy. This feature will help to minimize that effort and help them to juggle those meetings.
  * Highlights:
    * A new suite of commands that is similar to the Person commands from AddressBook-3. 
        * Basic commands are all enhanced to fit the needs of a meeting.
        * Additional commands like `donemeet`, `reschedule`, `relocate` etc are also added to allow ease of access for the agents.
    * The agent's meetings are all displayed in the GUI's list with incomplete and complete meetings grouped together and each sorted in chronological order.
  * Credits: *Structure of the command suite is adopted from the Person commands from AddressBook-3*
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=tomashiwa&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=Tomashiwa&tabRepo=AY2021S2-CS2103-W17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Set up, managed the team repository and resolve any GitHub related problems tha the team has.
  * Set up the [github-page](https://ay2021s2-cs2103-w17-4.github.io/tp/) for the team.
  * Integrated and released `v1.1` - `v1.4` (4 releases) on GitHub.
  * Read through and tag every project issues from the PE dry run to allow easier prioritization for the team.


* **Enhancements to existing features**:
  * Extended the Model classes to accommodate multiple books. ([\#53](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/53))
  * Created sub-packages and restructured classes in the Model package to make it more intuitive for development. ([\#56](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/55))
  * Wrote tests for Meeting classes, Parameter classes and Model classes to increase test coverage on the Meetings feature, increasing coverage from 51% to 56.5%. ([\#206](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/206))
  

* **Documentation**:
  * Modified and maintained the [index page](https://ay2021s2-cs2103-w17-4.github.io/tp/) to reflect the product's changes from the original AddressBook-3 product. ([\#81](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/81))
  * User Guide:
    * Restructured various sections for the team. ([\#207](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/207))
    * Wrote and maintained the Meeting features under the `Features` section. ([\#73](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/73), [\#207](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/207))
    * Kept the command summary to be in sync with both client and meeting commands. ([\#207](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/207))
  * Developer Guide:
    * Added uses-cases, non-functional requirements, and glossary. ([\#10](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/10), [\#55](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/55))
    * Corrected and maintained the Model component section and diagram class diagram to reflect the changes iScam made. ([\#55](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/55), [\#91](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/91))
    * Added implementation details, sequence and activity diagrams of the `Meetings` feature. ([\#216](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/216))
    * Corrected deprecated diagrams. ([\#216](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/216))
  

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#222](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/222), [\#195](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/195)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/224), [2](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/220), [3](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/219), [4](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/217))
  

* **Tools**:
  * Integrated a third party library (plantUML) to the project. ([\#200](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/200))
  

* **Diagram extracts from UG**
  * Model Class Diagram
    ![Model class diagram](../images/ModelClassDiagram.png)


* **Diagram extracts from DG**
  * AddMeeting's Activity Diagram
    ![AddMeeting activity diagram](../images/AddMeetingActivityDiagram.png)
  * AddMeeting's Sequence Diagram
    ![AddMeeting sequence diagram](../images/AddMeetingSequenceDiagram.png)
  * EditMeeting's Activity Diagram
    ![EditMeeting activity diagram](../images/EditMeetingActivityDiagram.png)
  * EditMeeting's Sequence Diagram
    ![EditMeeting sequence diagram](../images/EditMeetingSequenceDiagram.png)
  * FindMeeting's Activity Diagram
    ![FindMeeting activity diagram](../images/FindMeetingActivityDiagram.png)
  * FindMeeting's Sequence Diagram
    ![FindMeeting sequence diagram](../images/FindMeetingSequenceDiagram.png)
    
-