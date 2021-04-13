---
layout: page
title: Lee Xuan Wei, Jeremy's Project Portfolio Page
---

## Project: ClientBook

ClientBook is an application for insurance agents to manage client contacts, optimized for use with just a keyboard,
but still offering a graphical interface for ease of viewing of client contacts. The purpose of ClientBook is to help
insurance agents who can type fast to accomplish their client management tasks faster than traditional applications.

Given below are my contributions to the project.

* **New Feature**: Added a new command which allows user to display client's policies in a pop-up window.
    * **What it does**: Allows the user to view all the policies in a separate window, where they can then click a "Copy URL" button to easily retrieve the policy's URL (if any). 
    * **Justification**: This feature improves the product significantly because a user can easily retrieve the URL of a policy document, and enter it into a web browser to view the policy document.
    * **Highlights**: The implementation of this enhancement was exciting, as it involved the use of external libraries like JavaFX to design a graphical interface using FXML. 
      On top of that, the graphical user interface had to be integrated with the main code base which is written in Java. 
      This required in-depth understanding of the Model-View-Controller design principle, due to the interactions between the different components (`Ui`, `Logic` and `Model`) involved.
<br><br>
* **New Feature**: Added a new command which allows user to edit or delete clients' details in batch.
    * **What it does**: Allows user to edit the details (specifically address, phone number, tags and policies) of several clients at once, or delete several client contacts at once.
    * **Justification**: This feature improves the product significantly because a user no longer has to repeat similar commands just to change a particular detail that some clients might have in common. 
      * For example, some clients might co-own an insurance policy, and the policy ID of this policy might have changed. 
      This can be done easily using 1 command now instead of having to key in the same command repeatedly for numerous clients.
    * **Highlights**: This enhancement makes use of existing commands (`EditCommand` and `DeleteCommand`). 
      The implementation was challenging as it required stellar system design to ensure that all involved components are logically related to each other,
      while ensuring that the behaviour of existing system components are minimally affected by this new command. 
<br><br>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=jlxw48&tabRepo=AY2021S2-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
<br><br>
* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub.
<br><br>
* **Enhancements to existing features**:
    * Wrote tests to ensure correctness of implemented `policy` (Pull request [\#51](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/51))
      and `batch` (Pull requests [\#98](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/98), 
      [\#101](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/101)) commands.
    * Corrected existing bugs from the original upstream repository (Pull request [\#183](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/183)).
<br><br>
* **Documentation**:
    * User Guide:
        * Added documentation for 
          `policy` (Pull requests [\#42](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/42), [\#43](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/43)) and
          `batch` (Pull request [\#101](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/101))
          commands.
        * Did cosmetic tweaks to existing documentation: Pull requests
              [\#61](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/61),
              [\#63](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/63), 
              [\#78](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/78)
            * Drafted initial layout of User Guide that is still used in current iterations, through improvements in formatting, whitespaces, organisation and page flow.
            * Increase coherence and readability of User Guide.
            * Crafted welcoming introduction to ClientBook's User Guide. 
    * Developer Guide:
        * Added implementation details [here](https://ay2021s2-cs2103t-w15-2.github.io/tp/DeveloperGuide.html#feature-to-display-the-insurance-policies-associated-with-a-selected-client) 
          for the `policy` feature, to launch a pop-up window to display policies of a selected client (Pull request [\#82](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/82)).
        * Added user stories and use cases for `policy` and `batch` features (Pull requests [\#191](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/191), [\#192](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/192)).
        * Modified Logic component's Class Diagram and abstracted the Parser component into its own Class Diagram (Pull request [\#194](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/194)).
<br><br>
* **Community**:
    * Some PRs reviewed (with non-trivial review comments): 
      [\#28](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/28), 
      [\#44](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/44), 
      [\#173](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/173)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/106#issuecomment-774494112), 
      [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/147#issuecomment-779943553), 
      [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/137#issuecomment-779951075))
    * Some of the bugs reported and suggestions made for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/269), 
      [2](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/260), 
      [3](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/261),
      [4](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/256),
      [5](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/253))
      * Refer [here](https://github.com/jlxw48/ped/issues) if you would like to see more of my contributions to the projects of other teams.
