---
layout: page
title: Kuah Wei Liang's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

## Project: DietLAH!

DietLAH! is a desktop diet-tracking application that uses CLI-based inputs to allow for typists to easily record their meals and track their weight-loss/gain journey.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=WeiLiangLOL&tabRepo=AY2021S2-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented model of `DietPlan` and related-objects (e.g. `DietPlanList`, `MacroNutrientComposition`) that allows corresponding information to be stored and computed.
    * What it does: Used to hold nutritional information with regards to a given diet plan, as well as checks for sanity.
    * Justification: Allows for OOP-oriented storage of further features.

* **New Feature**: Updated Storage to manage storage of `Food` with new attributes
    * What it does: Allows for the saving and loading of food records from the .json file.
    * Justification: This is required for the users to be able to save food items for future reference when adding food intake.

* **New Feature**: Updated Storage to manage storage of `DietPlan` with new attributes
    * What it does: Allows for the saving and loading of diet plan records from the .json file.
    * Justification: This is required for the users to choose/modify a list of available diet plans should they decide to use external diet plans.

* **New Feature**: Wrote diet plans used in `TemplateInitializer`
    * What it does: The TemplateInitializer initialises a set of default sample template data used during the first load or when the reset command is used. The reset command resets the data in the application to blank, or the provided sample data.
    * Justification: When the user loads DietLAH! for the first time or without an existing data, sample data will be loaded to the application to give the user a feel of how it works and test the functionalities.

* **Project management**:
    * Linked and corrected pull requests to issues/milestones
    * Raised issues for bugs reported

* **Documentation**:
    * User Guide:
        * Fixed formatting related issues
        * Added appendix for diet plans
    * Developer Guide:
        * Fixed formatting related issues

* **Contributions to team-based tasks**:
    * Reviewing and approving of teammates' pull requests
    * Contributed to bug fixes and suggestions for architecture implementations
    * Created a logo and researched on food intakes/diet plans

* **Community**:
    * Contributed to forum [discussions](https://github.com/nus-cs2103-AY2021S2/forum/issues?q=is%3Aissue+author%3AWeiLiangLOL)
    * Reported [bugs and suggestions](https://github.com/AY2021S2-CS2103T-T13-4/tp/issues?q=is%3Aissue+WeiLiangLOL) for other teams
