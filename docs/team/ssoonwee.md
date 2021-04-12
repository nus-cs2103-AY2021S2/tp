---
layout: page
title: Song Soon Wee's Project Portfolio Page
---

## Project: DietLAH!

DietLAH! is a desktop diet-tracking application that uses CLI-based inputs to allow for typists to easily record their meals and track their weight-loss/gain journey.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=zoom&zA=ssoonwee&zR=AY2021S2-CS2103T-T12-2%2Ftp%5Bmaster%5D&zACS=223.78225352112676&zS=2021-02-19&zFS=&zU=2021-04-09&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **New Feature**: Implemented Food model
    * What it does: Provides the base model of the Food that allows storage of nutrients' value inside a food item object. 
    * Justification: Allows for OOP-oriented storage of other features such as Food_Intake that needs to store the food item(s) per day and calculate the daily conusmption.
   
* **New Feature**: Implemented parser support for `food_add`, `food_update`, `food_delete`, `food_list` commands
    * What it does: Users are able to use `food_add` command to add food item with their respective nutrients' value into a unique food list. The `food_update` command can be used to update food item with their new nutrients' value into the food list; The `food_delete` command can be used to delete the food item from the list. Subsequently, the `food_list` command can be used to list all food items from the food list.
    * Justification: This feature allow users to reference their food items from the food list storage, managed via the above commands, to aid in their daily food intake tracking.
 
* **New Feature**: Implemented parser support for `food_intake_add`, `food_intake_update`, `food_intake_delete`, `food_intake_query` commands with Jason
    * What it does: Users are able to use `food_intake_add` command to add a food intake with a food item for a specific day. The `food_intake_update` command can be used to update the specific food intake item for the day with a different nutrient value(s). The `food_intake_delete` command can be used to delete a specific food intake item for a specific day.The `food_intake_query` command can be used to query all the food intake within a day or over a period of time.
    * Justification: This feature allows users to track their food intake for the day, which will then help to calculate the nutrients consumption for the day to see how far are they from the planned progress of the specific diet plans daily requirements.

* **New Feature**: Implemented part of TemplateInitializer alongside with Jason
    * What it does: This TemplateInitializer creates a default template data set that can be used during the first initialization. 
    * Justification: This feature allows users to have a sample data preloaded into the application to give them a feel of how the program is like and test the functionalities. 

* **Project management**:
    * Created issues for user stories on GitHub to link related pull requests to the related stories
    * Raised issues for problems found in implementation after certain use cases are being re-considered

* **Documentation**:
    * User Guide:
        * Added documentation for Macronutrients Tracker section and updated table of contents (PRs [\#132](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/132),[\#123](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/123),[\#97](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/97),[\#95](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/95))
    * Developer Guide:
        * Added explanation of design decisions when implementing the operations of the food item model and feature. Editted the UML diagrams (Logic Class Diagram, Architecture Sequence Diagram, UI class Diagram, Delete Sequence Diagram) from the old address book to a new one that fits DietLAH! implementation. (PRs[\#139](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/139),[\#107](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/107))

* **Contributions to team-based tasks**:
  * Set up of issues for user stories
  * Raised issues and bugs related to certain features
  * Reviewing and approving of teammates' pull requests
  * Fixed bugs found during the PE Dry Run

* **Community**:
  * Raised some issues in the forum for open discussions
  * Reported bugs and suggestions for other team in the class: [https://github.com/AY2021S2-CS2103T-W14-3/tp/issues?q=is%3Aissue+in%3Abody+ssoonwee]https://github.com/AY2021S2-CS2103T-W14-3/tp/issues?q=is%3Aissue+in%3Abody+ssoonwee
 
