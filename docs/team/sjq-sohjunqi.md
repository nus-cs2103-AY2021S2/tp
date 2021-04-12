---
layout: page
title: Soh Jun Qi's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

## Project: DietLAH!

DietLAH! is a desktop diet-tracking application that uses CLI-based inputs to allow for typists to easily record their meals and track their weight-loss/gain journey.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=T12&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=sjq-sohjunqi&tabRepo=AY2021S2-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented parser support for `bmi`, `update_bmi` and `bmi_query` commands
    * What it does: Users will use the `bmi` command and `bmi_update` commands to enter their Body Mass Index (BMI) information into DietLAH! Subsequently, they can use the `bmi_query` command to view the data they have entered.
    * Justification: This feature allows users to provide the application with the required information in order to calculate the necessary macronutrient requirements for their goals.

* **New Feature**: Implemented parser support for `plan_list`, `plan`,`plan_set` and `plan_current` commands
    * What it does: Users will use the `plan_list` command to view a list of diet plans that they can choose from. The `plan` command provides additional information on the selected plan. The `plan_set` command allows users to set an active diet plan to emabark on. The `plan_current` command allows users to see their active diet plan.
    * Justification: This feature allows users to view and select a diet plan for them to monitor their food intake against.

* **New Feature**: Implemented plan recommendation feature
    * What it does: Users are able to use this feature to get a recommendation of diet plans based on their BMI (whether thye need to lose weight, gain weight or maintain weight)
    * Justification: This helps users who are unsure of what plans are suitable for them to find the appropriate diet plan for their BMI.
<div style="page-break-after: always;"></div>

* **New Feature**: Implemented progress report feature
    * What it does: Users are able to use this feature to generate a progress report based on their food intakes and their current diet plan.
    * Justification: This helps users to track their progress and see how much they have or have not adhered to the diet plan's requirements.

* **Project management**:
    * Created issues for user stories on GitHub to link related pull requests to the related stories

* **Documentation**:
    * User Guide:
        * Added documentation for `progress` command and updated table of contents
    * Developer Guide:
        * Added explanation of design decisions when implementing calculations of macronutrient requirements e.g using Mifflin-St Joer formula instead of others

* **Contributions to team-based tasks**:
    * Set up of issues for user stories
	* Reviewing and approving of teammates' pull requests
	* Removal of existing AB3 code for parser

* **Community**:
    * Reported bugs for other teams such as https://github.com/AY2021S2-CS2103T-W15-4/tp/issues/129 and https://github.com/AY2021S2-CS2103T-W15-4/tp/issues/127
