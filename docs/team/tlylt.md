---
layout: page
title: Yongliang's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimised for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=tlylt&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

#### New Features & Enhancements

- **New Feature**: Added the ability to send a request without saving the endpoint.

  - What it does: allows the user to run API requests without saving. 
  - Justification: This feature improves the product significantly because a
    user can quickly type in an API URL with the required details, and the app 
    will conveniently trigger a request for the user to view the response.
  - Highlights: This feature involves two variations of [command format](https://ay2021s2-cs2103t-t12-4.github.io/tp/UserGuide.html#429-call-an-api-endpoint-directly-without-saving-run) that can be accepted. It required an 
    in-depth analysis of command parsing order ensuring user inputs are treated correctly. The implementation too was 
    challenging as it required changes to how we verify the validity of URLs.

- **New Feature**: Added a keyboard shortcut that allows the user to retrieve the previous valid command using 
  <kbd>ctrl</kbd> + <kbd>up-arrow</kbd> (Windows) / <kbd>cmd</kbd> + <kbd>up-arrow</kbd> (macOS).

- **New Feature**: Added a help command that allows the user to open a pop up window displaying
   relevant links such as the product's user guide and also a table of command summary for quick reference. 

- **New Feature**: Added a show command that allows the user to view the details of a saved endpoint.

- **Enhancements to existing features**:

  - Updated various custom command feedback messages in the result display.
  - Wrote additional tests for existing features to increase coverage.
  - Refactored code in the request calling logic, and the handling of command result to reduce duplication and improve 
    code quality.
  - Fixed issues related to user input(such as the integer index) involved in various commands to ensure bugs are 
    caught and relevant error messages are provided.

- **Project management**:

  - Contributed to gathering of discussion topics and updating of meeting minutes in weekly team meetings.
  - Managed the `v1.3.1` product release on GitHub.
  
- **Documentation**:

  - User Guide:
    - Added the documentation for the features `send`, `run` and `help`.
    - Added the miscellaneous section to include relevant notes on features such as retrieval of last valid command.
    - Added various sample APIs in command examples and the appendix.

  - Developer Guide:
    - Added the high level overview of product design 
      (Architecture, UI component and Logic component).
    - Added implementation details of the `send` & `run` feature.

- **Community**:

  - PRs reviewed (with non-trivial review comments):
    ([\#79](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/79), 
    [\#146](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/146),
    [\#189](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/189), 
    [\#345](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/345)).
  - Contributed to forum discussions
    ([1](https://github.com/nus-cs2103-AY2021S2/forum/issues/220#issuecomment-797323149), 
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/241#issuecomment-802904375), 
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/236#issuecomment-799401973), 
    [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/235#issuecomment-799058631)).
  - Provided helpful tips and suggestions for other teams in the class 
    ([1](https://github.com/nus-cs2103-AY2021S2/forum/issues/179#issuecomment-785740902),
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/175#issuecomment-784177269), 
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/243#issuecomment-803282899),
    [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/293#issue-850026014)).
  - Raised pertinent issues on the topic of design principles and code refactoring 
    ([1](https://github.com/nus-cs2103-AY2021S2/forum/issues/240), 
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/230)).
  - Followed up on bug reports to other teams to provide detailed information and observation
    ([1](https://github.com/AY2021S2-CS2103T-W14-2/tp/issues/196#issuecomment-812967806),
    [2](https://github.com/AY2021S2-CS2103T-W14-2/tp/issues/196#issuecomment-812968068)).
    
