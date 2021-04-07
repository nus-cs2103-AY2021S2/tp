---
layout: page
title: Chen Yuheng's Project Portfolio Page
---

## Project: MeetBuddy

MeetBuddy is your handy desktop app, optimized for NUS students to manage their social connections and daily tasks who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI)! I have added the functionality for tasks scheduling and the connections between tasks and contacts! The GUI of MeetBuddy was created with JavaFX and written in Java, and I have written about 4 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the Meeting Class for tasks (i.e. meetings) scheduling.
    * What it does: allows the user to create a meeting object with various attributes and add them into the GUI.
    * Justification: This feature is an important part in our program.
    * Highlights: This feature affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required to add commands, parsers, models, GUI.

* **New Feature**: Added the PersonMeetingConnection Class for the interactions between contacts and meetings.
    * What it does: allows the user build a connection between the meetings and the contacts. For example, specify the people related to a certain meeting.
    * Highlights: The implementation of the class requires careful design and numerous modification on existing codes as it relates to both contacts and meetings. It's also the very key function in our program, as it brings connections to two major parts in the model.
* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
  
* **Summary of Contributions**:
    * Created over 22 PRs on the team repo. See: https://github.com/AY2021S2-CS2103-T16-2/tp/pulls?q=is%3Apr+author%3Askinnychenpi
    

* _{you can add/remove categories in the list above}_
