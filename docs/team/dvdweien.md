---
layout: page
title: David Au Wei En's Project Portfolio Page
---

## Project: PocketEstate

PocketEstate enables easy organisation of mass clientele property information through sorting of information by price,
location and housing type, that may otherwise be difficult to manage.

I have been tasked with adapting the edit commands, creating the update command as well as be the overall in charge of integration.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=dvdweien&tabRepo=AY2021S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Feature**: Implemented `Edit` commands for `appointment` and `property`.
    * Adapted the `Edit` command in AB3 to fit the needs of PocketEstate
    * PocketEstate has many more attributes than AB3, the `Edit` command has to have support for all of them.
* **Enhancement**: Implemented `update` command.
    * What it does: Allows the user to keep track of which stage the property is in the selling process (Option, Sales, Completion).
    * Justification: The property selling process involves multiple stages where different things need to be done at each stage, knowing which stage of the selling process that the property is in can help the user plan for what to do next.
* **Documentation**:
    * User guide: Updated guides for the usage of `edit property`, `edit appointment`, and `update` commands.
    * Developer guide: Added in some user stories, added in a section explaining how the `update` command works and added instructions for manually testing `edit` commands.
* **Testing**:
    * Wrote unit tests for:
        * `edit property` command and parser
* **Team-based tasks**:
    * Reported bugs as issues for better tracking.
    * Created the team repository.
    * Created product demo videos
* **Review/mentoring**:
    * Helped to review and merge pull requests
    * Implemented several bugfixes. 
