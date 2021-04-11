---
layout: page
title: Tan Jun Wei's Project Portfolio Page
---

### Project: PocketEstate

PocketEstate enables easy organisation of mass clientele property information through sorting of information by price, location and housing type, that may otherwise be difficult to manage.

Given below are my contributions to the project:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=w2vgd&tabRepo=AY2021S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### New Features & Enhancements

* **New Feature**: Added new models `Property` and `Appointment`, together with all of their attributes.
    * What it does: Each `Property` models a property listing, storing several attributes including the address, postal code, deadline to sell and client information of the property. Each `Appointment` models a scheduled meeting, storing several attributes including meeting date and time.
    * Justification: The core functionality of our application is heavily reliant on both of these models, as our application is about organizing both property and appointment data.
    * Highlights: Each `Property` may contain several optional attributes, such as remarks, tags and client's information. Each attribute also has nicely formatted output to be shown to the user in the GUI.
  
<br>

* **Enhancement**: Added the functionality to add properties and appointments through the two different `add` commands.
  * What it does: Allows the user to add a property or appointment to the application.
  * Justification: These basic core commands are required for users to add new information to the application.  
  * Highlights: Highly optimized and provides increased convenience for fast typists by allowing for a great range of attributes (both mandatory and optional) to be entered in one shot.

<div style="page-break-after: always;"></div>

* **Enhancement**: Implemented parsing support for all the attributes of `Property` and `Appointment`.
    * What it does: Performs strict input validation on user input arguments for each of the attributes of both `Property` and `Appointment`. Ensure that user input for each field is correctly validated. Upon invalid inputs, descriptive error messages are shown to user.
    * Justification: The creation and storage of every `Property` and `Appointment` is heavily dependent on the correct parsing of user input. Without ensuring correct parsing of user input commands, the application will not be able to store the data of the user accurately.
    * Highlights: Strict but yet flexible regex validation on user input for client's asking price, allowing for optional dollar sign and optional commas separating the numbers for greater ease of inputting large prices. Strict validation is also performed on user input date and time arguments, with error messages tailored to incorrect input format and invalid dates and times. The implementation was challenging as it required knowledge of regular expressions to construct all the validation regex.


* **Additional Enhancements**:
    * Added check to prevent addition of properties with passed deadlines and appointments with passed meeting date and time
    * Remove all traces of original addressbook that are not needed ([\#133](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/133))
    * Added icons to application GUI for easier differentiation between property types
    * Added application icon
    * Wrote additional tests ([\#299](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/299), [\#62](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/62), [\#66](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/66))


#### **Project management**:

* Managed releases `v1.2`, `v1.2.1`, `v1.3`, `v1.3.1`, `v1.4` (5 releases) on GitHub

#### **Documentation**:

* User Guide:
  * Added documentation for the commands `add property` and `add appointment` ([\#25](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/25), [\#290](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/290))
  * Added introduction and trying out sections ([\#185](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/185))
  * Added storage and appendix sections ([\#185](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/185))
  * Formatted table of contents with numbering for each section ([\#187](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/187))

* Developer Guide:
  * Added implementation details of the `Property` component. ([\#182](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/182), [\#215](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/215), [\#219](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/219))
  * Added implementation details of the `Appointment` component. ([\#182](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/182))
  * Updated Non-Functional Requirements section ([\#28](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/28))
  * Updated Architecture sequence diagram, Ui class diagram, Logic class diagram, Model class diagram ([\#142](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/142), [\#151](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/151), [\#156](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/156), [\#182](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/182))
  * Formatted table of contents with numbering for each section ([\#323](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/323))

#### **Community**:

* PRs reviewed (with non-trivial review comments): ([\#132](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/132), [\#202](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/202), [\#191](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/191), [\#195](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/195), [\#84](https://github.com/AY2021S2-CS2103T-T13-4/tp/pull/84))
* Initiated forum discussions tagged with `must-see` labels (discussion examples: [\#224](https://github.com/nus-cs2103-AY2021S2/forum/issues/224), [\#257](https://github.com/nus-cs2103-AY2021S2/forum/issues/257), [\#276](https://github.com/nus-cs2103-AY2021S2/forum/issues/276))
* Provided tips through forum discussions (discussion examples: [Moving tags to different commits](https://github.com/nus-cs2103-AY2021S2/forum/issues/36), [Enabling soft wraps in IntelliJ](https://github.com/nus-cs2103-AY2021S2/forum/issues/188))
* Provided help through forum discussions (discussion examples: [\#5](https://github.com/nus-cs2103-AY2021S2/forum/issues/5), [\#30](https://github.com/nus-cs2103-AY2021S2/forum/issues/30), [\#53](https://github.com/nus-cs2103-AY2021S2/forum/issues/53))
* Helped fix a bug in the AB3 Logic class diagram that is adopted by other classmates (issue: [\#257](https://github.com/nus-cs2103-AY2021S2/forum/issues/257))
