---
layout: page
title: Noel Mathew Isaac's Project Portfolio Page
---

## Project: Taskify

Taskify is a desktop task management application for university students. Using Taskify, students can
manage all their tasks (academics/personal/CCAs) effectively and seamlessly. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLOC.

Given below are my contributions to the project.

* ### Major Contributions:
    * #### Implemented the tag-search feature
        * What it does: Allows the users to search for a task based on one or more tags.
        * Justification: This feature makes it much easier for the user to categorise and filter out tasks belonging to a
          certain category. By tagging tasks with relevant keywords, the user can later retrieve all the tasks from 
          the same
          category really quickly instead of manually looking through all the tasks.
        * Highlights: The implementation involved some changes to existing logic and addition of commands and test 
          cases.
        * Relevant PRs: [\#36](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/36)

   * #### Implemented Due Today Task Panel
       * What it does: Allows the users to view the tasks due on the current day via a panel on the UI.
       * Justification: This feature allows users to easily see the tasks due on the current day without typing in 
         any commands and makes it easy for them to plan their day
       * Relevant PRs: [\#89](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/89)
      
    * #### UI Design and Enhancement
      * What it does: Designed and enhanced the UI to make it more seamless and user-friendly, while also being 
        simple and functional.
      * Justification: A great user interface is essential to the user experience and will help in user retention 
        and overall motivation to use the app.
      * Highlights: The UI design involved getting feedback from multiple user. The color scheme and layout of the 
        UI was designed to be simple and intuitive. The implementation of the tab feature was quite challenging and 
        involved a lot of research, especially on aspects like shape and dynamic resizing.
      * Relevant PRs: [\#56](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/56), 
        [\#86](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/86), 
        [\#93](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/93),
        [\#157](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/157)
        
        
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=noelmathewisaac&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Project management**:
    * Managed releases `v1.2` and `v1.3` (2 releases) on GitHub
    * Managed the issue tracker and milestones v1.1 - v1.4 (4 milestones) on GitHub.
    * Set up branch protection for the `master` branch such that every PR is required to 
      pass all the tests and have 2 approvals before being merged.

* **Enhancements to existing features**:
    * Refactored Person class from AB3 to Task class for Taskify:
      [\#31](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/31)
    * 
    * Wrote tests for the tag-search command to increase coverage: 
      [\#36](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/36)

* **Documentation**:
    * User Guide:
        * Added glossary and user stories, updated FAQ and added details about `tag-search` and `edit`:
          [\#61](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/61),
          [\#171](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/171)
        * Added screenshots of features and did cosmetic tweaks to existing documentation:
          [\#97](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/97),
          [\#200](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/200),
          [\#204](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/204) 
    * Developer Guide:
        * Added implementation details of the `tag-search` and `find` features:
          [\#198](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/198)
        * Updated use cases and added more instructions for manual testing:
          [\#198](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/198)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#37](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/37), 
      [\#40](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/40), [\#54](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/54)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/135#issuecomment-778904556), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/58#issuecomment-768852735))
    * Reported bugs and suggestions for other teams in the class (examples: [11 bugs/suggestions in ped](https://github.com/noelmathewisaac/ped/issues))
 

* **Tools**:
    * Integrated Netlify to the project workflow to generate previews for the website changes for each PR update:
      [\#14](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/14)
