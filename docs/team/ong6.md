---
layout: page
title: Jun Xiong's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimized for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=ong6&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

#### New Features & Enhancements

- **Enhancements**: Added new models such as `method`, `address`, `data` and `header`

  - What it does: Models the attributes in `endpoint` to display and accept correct information.
  - Justification: The core functionality of our product is heavily dependent on the model. Without the models, there would be no where to store the `endpoints`.
  - Highlights: Ensures users do not enter in invalid fields. Helps to prevent errors from occurring.

- **Enhancements**: Added the ability to add and edit endpoints through the `add` and `edit` commands.

  - What it does: Allows the user to add/edit an endpoint for use in other commands.
  - Justification: This command is needed for a user to include new endpoints in their list.
  - Highlights: Allows for optional fields to be entered as well as prevents the users from entering unrealistic attributes.

- **Enhancements**: Added the ability to find/filter through specific endpoints through the `find` command.

  - What it does: Allows the user to find and filter through endpoints with a greater degree of precision.
  - Justification: Being able to quickly find the endpoint in a large list of endpoints is important as it would save the developer time and reduce confusion.
  - Highlights: Allows users to specify the field to be searched through and allows for combining of the specifications to allow for a more targeted search.

<div style="page-break-after: always;"></div>

- **New Features**:
  - Managed UI aspects of the application.
  - Added the beautified display for Method, response time and other fields after a add, edit, show, remove.
  - Added the ability to show a endpoint by clicking on it in the endpoint list.
  - Added more detailed error messages to commands.
  - Upgraded and redesigned all the themes to fit the UI.

#### Project management

  - Managed releases `v1.4` on GitHub.
  - Maintained [project development board](https://github.com/AY2021S2-CS2103T-T12-4/tp/projects/1)
  - Setup [workflow guide](../WorkflowGuide.md) to ease project workflow.

#### Community

  - Helped with many PRs and provided tips on how to improve code. ([1](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/591), [2](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/601))
  - Provided tips through forum discussions and gave input to forum discussions. ([1](https://github.com/nus-cs2103-AY2021S2/forum/issues/287), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/241))
  
#### Documentation

  - User Guide:
    - Added documentation for `add`, `edit` and `find` command. ([1](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/258), [2](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/275/files))
    - Added documentation on general command usage and detailed find command usage.
    - Redid the all commands summary table. [1](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/378/files)
    - Added styling and formatting.
  - Developer Guide:
    - Added implementation details of the `add` and `find` features. [1](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/367)
    - Added Glossary table [1](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/606)
    - Helped with various aspects of the DG.
