---
layout: page
title: Kieron's Project Portfolio Page
---

## Project: Focuris

Focuris is a desktop event management application created with a Kanban board graphical user interface, which aims to help users better keep track of their tasks through an intuitive CLI, with a GUI created in JavaFX. It is written in Java and has about 10 kLoC.

Given below are my contributions to the project:

- **Front-End Component**: Focuris Kanban View 
  - Refactored the different UI Components that made up the Kanban View, including `EventCardPaneKanbanView.java` and 
  `EventCardKanbanView.java`, as well as the various respective `fxml` files.
  - Refined and improved the design of the frontend to make it more component-based and neater.
  - Implemented the `priority` tags and `Identifier` tags into the event cards.
  - Implemented CSS for the Kanban View, including paddings, margins and colours.

- **New Feature**: Added the BackLog, In-Progress Commands.

  - What it does: Allows the user to add the two different kinds of events as stated above into the application.
  - Justification: We require those commands to encapsulate the addition of the different events into Focuris.
  - Highlights: This affects the logic and model components of Focuris in the future.
  - Testing: Added test cases and test factory for the commands written.

- **Code Contributed**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=kieron&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=kieron560&zR=AY2021S2-CS2103T-W15-4%2Ftp%5Bmaster%5D&zACS=105&zS=2021-02-19&zFS=kieron&zU=2021-04-09&zMG=undefined&zFTF=commit&zFGS=groupByRepos&zFR=false)

- **Enhancements to existing features:**

  - Integration of code from JavaFX to Java for the KanBan board

  **Project Management:**
  
  - Managed release of `Focuris v1.3` on GitHub
  - In charge of quality assurance by breaking the application with edge cases

- **Documentation**:

  - User Guide:
    - Refined the User Guide for grammatical errors
    - Edited the User Guide to make the it more user-friendly.
    - Added more content to the `FAQ Section`.
    
  - Developer Guide:
    - Added the `Introduction` section.
    - Added more content to the `Glossary` section.
    - Added diagram for the Architecture Sequence Diagram.
    - Revised and updated the content for the `UI Component ` section.
    - Added content for the `Instructions for Manual Testing` section

- **Community**:
  - PRs Reviewed: 8 (#12， #13， #14， #39，#62， #65， #79， #82)
