---
layout: page
title: imPoster User Guide
nav-text: User Guide
---

<p align="center">
  <img width="300px" src="images/imPosterUserGuide.png" >
</p>

<h1 class="post-title">{{ page.title | escape }}</h1>
<h3 class="post-subtitle">v1.3.1</h3>

<div style="page-break-after: always;"></div>
<br/>

## Table of Contents
{:.no_toc}

* Table of Contents 
{:toc}

<div style="page-break-after: always;"></div>

## 1. Welcome to imPoster

Are you an aspiring [**Application Programming Interface (API)**](#glossary-api) developer? Or would you like a peek into the world of how applications communicate with one another? Then **imPoster** is the app just for you! But hold on, what is imPoster?

imPoster is a desktop application for beginners of API development to quickly go hands-on with the basics. Whether you are looking to **explore**, **test**, or **build** your very own APIs, the simple and minimalistic style of imPoster will quickly get you up and going.

This user guide assumes that users have a **basic understanding** of APIs. If you are wondering [what an API is](#81-what-is-an-api), an appendix has been provided for users who may be unfamiliar with the concept. However, it is highly recommended for users to refer to proper tutorial contents for the basics of APIs prior to using the application.

For fast typists, imPoster is also highly optimised for the command line and can be fully operated through keyboard commands. Users who are familiar with [**CURL**](#glossary-curl) will also be happy to know that we share a very similar command line syntax. If you have yet to do so, be sure to download our [latest releases](https://imposter-dev.tk) from our main website and give us a try!

<div style="page-break-after: always;"></div>

## 2. Navigating the User Guide

Before diving into the rest of the contents in our user guide, the following are some important syntax to take note of to facilitate your reading:

| Syntax              | Description                                    |
| ------------------- | ---------------------------------------------- |
| **Bold**            | Important words to note                        |
| `Markdown`          | Important examples                             |
| <kbd>Keyboard</kbd> | Keyboard actions                               |
| <div markdown="span" class="alert alert-warning custom-table-format">:bulb: Tips</div> | Useful tips |
| <div markdown="span" class="alert alert-danger custom-table-format">:exclamation: Caution</div> | Things to watch out for |
| <span class="main-command">Main Command</span> | Indicates the keyword describing the main action of a command |
| <span class="compulsory-param">Compulsory Parameter</span> | Indicates the compulsory parameters/prefixes of a command |
| <span class="optional-param">Optional Parameter</span> | Indicates the optional parameters/prefixes of a command |
| [Repeated Parameters]   | Indicates the parameters/prefixes that may be repeated multiple times, including zero times    |


<div style="page-break-after: always;"></div>

## 3. Quickstart

1. Ensure you have **Java 11 or above** installed on your Computer.

2. Download the latest **imPoster.jar** from
   [here](https://imposter-dev.tk) and move the file to the folder you wish to use as the **home folder** for imPoster.

3. **Double-click** the file to start the application. A graphical user interface (GUI) containing the annotated **3 main components** should show up as below:<br>

    <p align="center">
      <img width="900px" src="images/startscreen.png" >
    </p>

4. Try making your first API call through our application with the first [**endpoint**](#glossary-endpoint) shown in the default **Endpoint List**. Type <span class="main-command">send</span> <span class="compulsory-param">1</span> in the **Command Box** and then press <kbd>Enter</kbd>.

5. After which, try scrolling through the responses shown in the **Result Display** as well!

6. For a quick **overview** of all available commands, please refer to our [Command Summary](#6-command-summary).

7. For the **details** of each command, please proceed to the next section on [Commands](#4-commands).

8. When you are ready to start testing your APIs, clear the default **Endpoint List** by typing <span 
   class="main-command">clear</span> in the **Command Box** and then pressing <kbd>Enter</kbd>.

9. If you are new and unsure of where to find an API endpoint to start, fret not! We have prepared an extensive list of [sample endpoints](#86-sample-endpoints) for you.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
imPoster will start with an empty file if a modification to the [data file](#data-file) causes the data to be invalid.
</div>


<div style="page-break-after: always;"></div>

## 4. Commands

Commands are classified into 2 categories, namely **general** and **endpoint**. Before diving into the details, let us first look at what makes up a command:

| Component    | Description                                                           |
| ------------ | --------------------------------------------------------------------- |
| Command Word | The keyword representing the action of the command                    |
| Prefix       | The keyword to recognise command parameters                           |
| Parameters   | Follows directly behind a prefix and contains the corresponding value |

As an example, a basic command to add an endpoint could look like the following:

<span class="main-command">add</span> <span class="compulsory-param">-x GET</span> <span class="compulsory-param">-u https://google.com</span>

In the example above, <span class="main-command">add</span> is the command word while <span class="compulsory-param">-x</span> and <span class="compulsory-param">-u</span> are the prefixes of the <span class="compulsory-param">GET</span> and <span class="compulsory-param">https://google.com</span> parameters respectively. For your convenience, a list of all parameters along with their prefixes and descriptions have been included in the table below. An example usage of all the parameters are also demonstrated in the [add command](#421-add-an-api-endpoint-add).

<a name="prefix-table"></a>

| Parameter    | Prefix | Description                                                           |
| ------------ | ------ | --------------------------------------------------------------------- |
| INDEX        |  None  | The index of the endpoint as shown in the endpoint panel list         |
| THEME        |  None  | The theme for the application                                         |
| METHOD       |   -x   | The [request method](#84-request-methods) to use for an endpoint      |
| URL          |   -u   | The URL to use for an endpoint                                        |
| HEADER       |   -h   | The header to use for an endpoint **(must be enclosed with \"\")**      |
| DATA         |   -d   | The data to use for an endpoint **(must be in [JSON](#85-json-format) format)**          |
| TAG          |   -t   | The tag to label an endpoint                 |

Note that the fields **METHOD**, **URL**, **HEADER**, **DATA** and **TAG** are used to uniquely identify an endpoint.

<div style="page-break-after: always;"></div>

<a name="general-rules"></a>
Here are some general rules to follow when entering prefixes and parameters:

* A **whitespace** must be included before **every prefix**.<br>
  e.g. `-x METHOD -u URL` is acceptable but `-x METHOD-u URL` is not.<br>
  
* Parameters may be entered in **any order**.<br>
  e.g. Both `-x METHOD -u URL` and `-u URL -x METHOD` are acceptable.<br>
  
* If a parameter is expected only once in the command but you specified it multiple times, only the **last occurrence** of the parameter will be taken.<br>
  e.g. If you specify `-u https://github.com/ -u https://google.com/`, only `-u https://google.com/` will be taken.<br>

* **Extraneous parameters** for commands that do not take in parameters (such as **help**, **list**, **exit** and **clear**) will be ignored.<br>
  e.g. If the command specifies `help 123`, it will be interpreted as `help`.<br>
  
* For **add**, **edit** and **run** commands, to add multiple parameters of the same prefix, add the prefix multiple times before each parameter.<br>
  e.g. To add two tags, enter `-t tagOne -t tagTwo`.<br>
  e.g. To add three headers, enter `-h "header: one" -h "header: two" -h "header: three"`.<br>
  
* Multiple **headers/tags must be unique** and duplicates will be ignored.<br>
e.g. `edit 1 -t tagA -t tagA` will only create one `tagA`.

* For the **URL** parameter, as our application is focused on API testing, we have no plans to direct all our efforts in
  verifying every technically valid or invalid [**URL**](https://en.wikipedia.org/wiki/URL) against the official [URL standard](https://url.spec.whatwg.org/#url-parsing). Hence, **minimal checks** are performed for cases of **invalid URL** for which we will still display error messages.
  e.g.`abc.com\go` is an invalid URL as `\` cannot exist in a valid URL.

* If no website [protocol](#glossary-protocol) is specified for the **URL**, we enforce a **HTTP protocol** as a protocol needs 
  to be specified for an API request to be carried out.<br>
  e.g. if a user enters `google.com` as a URL, we will prepend the URL with `http://`, making it `http://google.com`.

* The **index** parameter provided should be a **non-zero unsigned integer** within the allowed range of Java's `int` data type:
  * the maximum value an `int` can have is (2^31)-1.
  * the minimum value an `int` can have is -(2^31).

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Every command explanation comes with a screenshot that shows the expected message in the application's **Result Display** (screenshot may be partial to save space!)
</div>

<div style="page-break-after: always;"></div>

### 4.1 General

#### 4.1.1 View help: <span class="main-command">help</span>

**Description:** New or stuck with one of the commands? Get the relevant helpful information through a quick pop up window!

**Format:** <span class="main-command">help</span>

**Example & Output:** <span class="main-command">help</span>

<p align="center">
  <img width="450px" src="images/helpMessage.png" >
</p>

#### 4.1.2 Toggle theme: <span class="main-command">toggle</span>

**Description:** Seeking a more personal visual design? Choose from our themes consisting of **light, dark, material or imposter**!

**Format:** <span class="main-command">toggle</span> <span class="compulsory-param">THEME</span>

**Example & Output:** <span class="main-command">toggle</span> <span class="compulsory-param">light</span>

<p align="center">
  <img width="450px" src="images/commands/toggle.png" >
</p>

<div style="page-break-after: always;"></div>

#### 4.1.3 Exit program: <span class="main-command">exit</span>

**Description:** Looking to exit the application? A simple command does the job but do come back soon!

**Format:** <span class="main-command">exit</span>

**Example:** <span class="main-command">exit</span>

### 4.2 Endpoint

#### 4.2.1 Add an API endpoint: <span class="main-command">add</span>

**Description:** Add an API endpoint to the API endpoint list.

**Format:** <span class="main-command">add</span> <span class="compulsory-param">-x METHOD</span> <span class="compulsory-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span> <span class="optional-param">[-t TAG]</span>

**Example & Output:** <span class="main-command">add</span> <span class="compulsory-param">-x POST</span> <span class="compulsory-param">-u https://reqres.in/api/users</span> <span class="optional-param">-d {\"name\": \"tarzan\", \"job\": \"the jungle man\"}</span> <span class="optional-param">-h \"Content-Type: application/json\"</span> <span class="optional-param">-t nature</span> <span class="optional-param">-t important</span>

<p align="center">
  <img width="450px" src="images/commands/add.png" >
</p>


#### 4.2.2 Edit an API endpoint: <span class="main-command">edit</span>

**Description:** Edit the API endpoint at the specified index shown in the API endpoint list **(at least one optional argument must be provided)**.

**Format:** <span class="main-command">edit</span> <span class="compulsory-param">INDEX</span> <span class="optional-param">-x METHOD</span> <span class="optional-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span> <span class="optional-param">[-t TAG]</span>

**Example & Output:** <span class="main-command">edit</span> <span class="compulsory-param">1</span> <span class="optional-param">-x POST</span> <span class="optional-param">-u https://reqres.in/api/users</span> <span class="optional-param">-d {\"name\": \"john doe\", \"job\": \"developer\"}</span> <span class="optional-param">-t common</span> <span class="optional-param">-t important</span>

<p align="center">
  <img width="450px" src="images/commands/edit.png" >
</p>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
When editing tags/headers, the existing tags/headers of the endpoint will be removed. <br>
i.e adding of tags/headers is not cumulative.<br>
You may remove all the endpoint’s tags by typing ` -t` without specifying any tags after it. Similarly, you may remove
all the endpoint's headers by typing ` -h` without specifying any headers after it.
For example: `edit 1 -t` will remove all existing tags for the first endpoint in the saved endpoint list.
</div>

#### 4.2.3 Show an API endpoint: <span class="main-command">show</span>

**Description:** Show the details of the API endpoint at the specified index shown in the API endpoint list (index must 
be a positive integer).

**Format:** <span class="main-command">show</span> <span class="compulsory-param">INDEX</span>

**Example & Output:** <span class="main-command">show</span> <span class="compulsory-param">1</span>

<p align="center">
  <img width="450px" src="images/commands/show.png" >
</p>

<div style="page-break-after: always;"></div>

#### 4.2.4 Remove an API endpoint: <span class="main-command">remove</span>

**Description:** Remove the API endpoint at the specified index shown in the API endpoint list.

**Format:** <span class="main-command">remove</span> <span class="compulsory-param">INDEX</span>

**Example & Output:** <span class="main-command">remove</span> <span class="compulsory-param">1</span>

<p align="center">
  <img width="450px" src="images/commands/remove.png" >
</p>

#### 4.2.5 Find a saved API endpoint: <span class="main-command">find</span>

**Description (General Search):** Find endpoints containing the search word/s through all fields **(requires at least one keyword)**.

**Format (General Search):** <span class="main-command">find</span> <span class="optional-param">[KEYWORD]</span>

**Example & Output:** <span class="main-command">find</span> <span class="optional-param">github</span> <span class="optional-param">transport</span>

<p align="center">
  <img width="450px" src="images/commands/find.png" >
</p>

**Description (Precise Search):** Find endpoints containing the search word/s based on the [prefix](#prefix-table) **(requires at least one keyword)**

**Format (Precise Search):** <span class="main-command">find</span> <span class="optional-param">-x [METHOD]</span> <span class="optional-param">-u [URL]</span> <span class="optional-param">-d [DATA]</span> <span class="optional-param">-h [HEADER]</span> <span class="optional-param">-t [TAG]</span>

**Example & Output:** <span class="main-command">find</span> <span class="optional-param">-x GET</span> <span 
class="optional-param">-u google</span>

to-do tanjin update pic here (will match `GET` from the Method field **and** `google` from the URL field)
<p align="center">
  <img width="450px" src="images/commands/find.png" >
</p>

**Example & Output:** <span class="main-command">find</span> <span class="optional-param">-x GET POST</span> <span 
class="optional-param">-u google</span>

to-do tanjin update pic here (will match `GET OR POST` from the Method field **and** `google` from the URL field)
<p align="center">
  <img width="450px" src="images/commands/find.png" >
</p>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:** The search is case-insensitive and the order of the keywords do not matter.<br>
Partial Words **will** be matched. e.g. `appl` will match `Apple`<br>
Searches with no none or a single [prefix](#prefix-table) will preform an **OR** search and all Endpoints matching either keywords will be returned.<br>
Searches across multiple [prefixes](#prefix-table) will preform an **AND** search and only endpoints matching all keywords will be returned.
</div>

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
`find -x GET -x POST` is not the same as `find -x GET POST`. <br>
The first command will only search for items matching `POST` (as stated [here](#general-rules)), while the second command 
will search for all items matching `GET` and `POST`.
</div>

<div style="page-break-after: always;"></div>

#### 4.2.6 List all saved API endpoints: <span class="main-command">list</span>

**Description:** Show a list of all API endpoints in the API endpoint list. If there are no endpoints in the API endpoint list, nothing will show up on the Endpoint List bar.

**Format:** <span class="main-command">list</span>

**Example & Output:** <span class="main-command">list</span>

<p align="center">
  <img width="450px" src="images/commands/list.png" >
</p>

#### 4.2.7 Clear all saved API endpoints: <span class="main-command">clear</span>

**Description:** Clear all API endpoints in the API endpoint list.

**Format:** <span class="main-command">clear</span>

**Example & Output:** <span class="main-command">clear</span>

<p align="center">
  <img width="450px" src="images/commands/clear.png" >
</p>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
If you wish to generate a set of sample endpoints, you may delete the **imposter.json** file inside the **data** folder!
</div>

<div style="page-break-after: always;"></div>

#### 4.2.8 Call a saved API endpoint: <span class="main-command">send</span>

**Description:** [Call](#glossary-call) an API endpoint from the API endpoint list **(an ongoing call can be cancelled with <kbd>ctrl</kbd> + <kbd>d</kbd>)**.

**Format:** <span class="main-command">send</span> <span class="compulsory-param">INDEX</span>

**Example & Output:** <span class="main-command">send</span> <span class="compulsory-param">1</span>

<p align="center">
  <img width="450px" src="images/commands/send.png" >
</p>

#### 4.2.9 Call an API endpoint directly without saving: <span class="main-command">run</span>

**Description:** [Call](#glossary-call) an API endpoint directly (without saving) **(an ongoing call can be cancelled with <kbd>ctrl</kbd> + <kbd>d</kbd>)**.

**Format:** <span class="main-command">run</span> <span class="compulsory-param">-x METHOD</span> <span class="compulsory-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span>

**Example & Output:** <span class="main-command">run</span> <span class="compulsory-param">-x GET</span> <span class="compulsory-param">-u https://api.data.gov.sg/v1/environment/pm25</span>

<p align="center">
  <img width="450px" src="images/commands/run.png" >
</p>

**Description (Shortcut):** [Call](#glossary-call) an API endpoint directly (without saving) to send a GET 
request that does not specify any data or header **(an ongoing call can be cancelled with <kbd>ctrl</kbd> + 
<kbd>d</kbd>)**.

**Format (Shortcut):** <span class="main-command">run</span> <span class="compulsory-param">URL</span>

**Example & Output:** <span class="main-command">run</span> <span class="compulsory-param">https://api.data.gov.sg/v1/environment/pm25</span>

<p align="center">
  <img width="450px" src="images/commands/run.png" >
</p>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
The above shortcut for run command is designed for users to easily verify outputs for common API endpoints that 
do not require other input data or header. Note that this feature is only meant for sending GET requests.
</div>

<div style="page-break-after: always;"></div>

### 4.3 Miscellaneous

#### 4.3.1 Retrieve the last valid command

**Description:** Given that the last valid command from a user is most likely to be repeated during the API development
& verification process, a special key combination <kbd>ctrl</kbd> + <kbd>up-arrow</kbd> (Windows) / <kbd>cmd</kbd> + 
<kbd>up-arrow</kbd> (macOS) is available to set the 
command box with the last command.

**Format:** <kbd>ctrl</kbd> + <kbd>up-arrow</kbd> (Windows) / <kbd>cmd</kbd> + <kbd>up-arrow</kbd> (macOS)

#### 4.3.2 Switch focused component

**Description:** As users may wish to switch the focused component (e.g. to type commands in the command box or to scroll responses in the result display), the <kbd>tab</kbd> key is available as an option to toggle the focused component (highlighted with an orange outline).

**Format:** <kbd>tab</kbd>

#### 4.3.3 Scroll endpoint list or result display

**Description:** As users may wish to scroll the endpoints in the endpoint list or the responses in result display, the <kbd>Page Up</kbd> and <kbd>Page Down</kbd> keys (<kbd>fn</kbd> + <kbd>up</kbd> and <kbd>fn</kbd> + <kbd>down</kbd> on **macOS**) are available as options to scroll the 2 components above.

**Format:** <kbd>Page Up</kbd> and <kbd>Page Down</kbd> (<kbd>fn</kbd> + <kbd>up</kbd> and <kbd>fn</kbd> + <kbd>down</kbd> on **macOS**)

#### 4.3.4 Error Messages

**Description:** Do not worry if you input any commands wrongly when using our app! Our app gives helpful error messages depending on the command you entered and you can always enter the [**help command**](#411-view-help-help) if you need to refer back to the various commands available!

<div style="page-break-after: always;"></div>

## 5. Frequently Asked Questions (FAQ)

**Q**: I am completely new to APIs, how do I get an [endpoint](#glossary-endpoint)?
<br/><br/>
**A**: While the use of APIs is commonplace, not all applications provide their API endpoints publicly. **Google** is your best friend for searching of public APIs but if you are new and just looking to try things out, fear not! We have prepared an extensive list of [sample endpoints](#86-sample-endpoints) to get you started!

**Q**: How do I transfer my data to another Computer?
<br/><br/> 
**A**: Install the application in the other computer and place your current data folder in the same directory as the newly installed application (overwrite the data folder of the new application if applicable).

**Q**: How can I send non-JSON data in the request body?
<br/><br/>
**A**: This current version of imPoster only supports the sending of [JSON](#85-json-format) data in the request body which is the format used by an estimated over 70% of APIs worldwide. We apologise for the inconvenience caused but we are happy to share that plans are in place to include support for other data formats in future versions!

<a name="data-file"></a>
**Q**: Where are the data of imPoster saved?
<br/><br/>
**A**: imPoster data are saved automatically after every command into a JSON file named `imposter.json`. This file is stored within the `data` folder in the same location as the application `JAR` file and is created after the initial launch (and a command execution) of a fresh installation.

<div style="page-break-after: always;"></div>

## 6. Command summary

A quick overview of all supported commands, their formats and examples are given below:

### 6.1 General

| Command    | Format                                 |
| ---------- | -------------------------------------  |
| **Help**   | <span class="main-command">help</span> |
| **Toggle** | <span class="main-command">toggle</span> <span class="compulsory-param">THEME</span> |
| **Exit**   | <span class="main-command">exit</span> |

### 6.2 Endpoint

| Command    | Format                                | 
| ---------- | ------------------------------------- |
| **Add**    | <span class="main-command">add</span> <span class="compulsory-param">-x METHOD</span> <span class="compulsory-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span> <span class="optional-param">[-t TAG]</span><br>|
| **Edit**   | <span class="main-command">edit</span> <span class="compulsory-param">INDEX</span> <span class="optional-param">-x METHOD</span> <span class="optional-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span> <span class="optional-param">[-t TAG]</span><br> |
| **Show**   | <span class="main-command">show</span> <span class="compulsory-param">INDEX</span><br>                   |
| **Remove** | <span class="main-command">remove</span> <span class="compulsory-param">INDEX</span>                     |
| **Find**   | <span class="main-command">find</span> <span class="optional-param">[KEYWORD]</span><br>                 |
| **List**   | <span class="main-command">list</span><br>                                                               |
| **Clear**  | <span class="main-command">clear</span><br>                                                              |
| **Send**   | <span class="main-command">send</span> <span class="compulsory-param">INDEX</span><br>                   |
| **Run**    | <span class="main-command">run</span> <span class="compulsory-param">-x METHOD</span> <span class="compulsory-param">-u URL</span> <span class="optional-param">-d DATA</span> <span class="optional-param">[-h HEADER]</span><br>|

<div style="page-break-after: always;"></div>

## 7. Glossary

| Term                                         | Description                                               |
| -------------------------------------------- | --------------------------------------------------------- |
| **API** | <a name="glossary-api"></a> API is short for **Application Programming Interface** and allows two systems to interact with each other  |
| **Call** | <a name="glossary-call"></a> A call to an API endpoint refers to the process of sending a [request to the server and then receiving a response](#83-what-are-requests-and-responses).          |
| **Endpoint** | <a name="glossary-endpoint"></a> The communication point of a system that allows it to interact with another system, commonly accessed through a URL |
| **Request** | A process in which information is sent out to an endpoint through one of the [request methods](#84-request-methods) (a more detailed explanation can be found [here](#83-what-are-requests-and-responses)) |
| **Response** | The information obtained from an endpoint after a request is sent to it (a more detailed explanation can be found [here](#83-what-are-requests-and-responses)) |
| **Parameter**   | Information passed in as part of a command with its type identified by a prefix (e.g. <span class="compulsory-param">METHOD</span>) |
| **Prefix**   | Characters used to identify the following parameter (e.g. <span class="compulsory-param">-x</span> is the prefix for the parameter <span class="compulsory-param">METHOD</span>) |
| **JSON** | JSON is short for **JavaScript Object Notation** which is a lightweight format for data storage (a more detailed explanation can be found [here](#85-json-format)) |
| **CURL** | <a name="glossary-curl"></a> CURL is short for **Client URL** and is a command-line tool used in the transfer of data via different network protocols |
| **Index** | Index in this guide refers to the position of the endpoint in the endpoint list (represented by the number beside the endpoint) |
| **Protocol** | <a name="glossary-protocol"></a> A protocol is a system of rules that define how data is exchanged within or between systems |

<div style="page-break-after: always;"></div>

## 8. Appendix

### 8.1 What is an API?

Broadly speaking, an **API** is an interface that enables and defines how **two systems** interact with one another. In a classic analogy, the interaction above is usually likened to a **waiter** communicating a **customer** order to the restaurant **kitchen**. In this analogy, the **customer** and **kitchen** represents the **two systems**, and the **waiter** represents the **API** allowing them to communicate. The **order** and **food** delivered then corresponds to the terms **request** and **response** associated with an API call. The annotated diagram below captures these interactions and may aid in providing a better understanding:

<p align="center">
  <img width="700px" src="images/ApiExplanation.png" >
</p>

Note that for the **waiter** to pass the order to the **kitchen**, a **window/door** is required to allow communication and this is represented by the term [**endpoint**](#glossary-endpoint) which is frequently used in relation to an API.

<div style="page-break-after: always;"></div>

### 8.2 Why learn about APIs?

You may be surprised to know that APIs are not only widely used in our daily lives, it is also likely that you have been using them frequently without actually noticing them! For example, the simple act of visiting a website involves an API request which is responsible for bringing back a response to you in the form of a webpage. Even a simple text message to your friend relies on an API to reliably deliver your message! APIs are used extensively in our world today so even if they are unrelated to your job, it helps to have some basic understanding of them!

### 8.3 What are requests and responses?

The terms **requests** and **responses** both refer to messages used in the exchange of information between two systems (sender and receiver). Typically, the sender will send out a request containing information to be sent to the receiver. Then, when the receiver receives the request from the sender, it will process the request and send back a response. This response is then received and processed by the original sender, thus completing a single API call.

### 8.4 Request Methods
As of the latest version of our application, we support 7 commonly used request methods. We recommend individuals who are interested to learn more about request types to refer to [official documentation](https://webconcepts.info/concepts/http-method/). A brief explanation for the 7 supported requests have been provided below:

| Method      | Description                                                                                                 |
| ----------- | ----------------------------------------------------------------------------------------------------------- |
| **GET**     | Retrieves information from a server through a specified URL                  |
| **POST**    | Sends data to a server, commonly in JSON/html form format           |
| **PUT**     | Sends data to a server, commonly in JSON/html form format      |
| **DELETE**  | Removes information from a server through a specified URL             |
| **HEAD**    | Similar to GET, but returns only the header section of the response                                         |
| **PATCH**   | Sends data to a server, commonly in JSON/html form format |
| **OPTIONS** | Retrieves the allowed communication options (methods) for a specified URL                                   |

### 8.5 JSON Format
JSON is short for JavaScript Object Notation and is a common lightweight format for data storage (in the form of key/value pairs). In an API call, the JSON format is also commonly used to send data between two systems. For the current version of our application, JSON is the only format supported for sending data. The following are some examples of data in JSON format:
- `{}` - represents an empty JSON data
- `{"name": "john doe"}` - represents a single level JSON data 
- `{"persons": {"name": "john doe"}}` - represents a nested JSON data

### 8.6 Sample Endpoints

An extensive list of sample endpoints have been provided below for new users to test and try out. Note that the **header** field has been omitted in the samples to keep them beginner friendly but rest assured that the following **sample endpoints will work without the header** field. That said, for those more familiar with APIs, some examples of headers have been included in the initial list of default endpoints that came with the JAR file. For the even more adventurous, you may refer to and explore the [full list of HTTP headers](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields). 

Data fields on the other hand have been included in a standalone column (if applicable). They are all in the [**JSON format**](#85-json-format) and only the keys are provided (you may play around with your own values). The sample endpoints below are organised according to their [request method types](#84-request-methods) for your convenience. Go ahead and try out the sample endpoints below!

<span class="pseudo-header">GET</span>

| URL                                                            |
| -------------------------------------------------------------- |
| http://imposter-dev.tk:6000/api/v1/resources/books/all         |
| http://imposter-dev.tk:6000/api/v1/resources/books?id=1        |
| https://project-billboard.herokuapp.com/laugh                  |
| https://api.data.gov.sg/v1/environment/psi                     |
| https://api.data.gov.sg/v1/environment/4-day-weather-forecast  |

<div style="page-break-after: always;"></div>

<span class="pseudo-header">POST</span>

| URL                                                           | Data (JSON)                                       |
| ------------------------------------------------------------- |                                                   |
| https://jsonplaceholder.typicode.com/posts                    | title, body, userId                               |
| https://reqbin.com/echo/post/json                             | Id, Customer, Quantity, Price                     |
| https://reqres.in/api/register                                  | email, password                   |
| https://reqres.in/api/login                                | email, password                     |
| https://reqres.in/api/users                             | name, job                     |

<span class="pseudo-header">PUT</span>

| URL                                                           | Data (JSON)                                       |
| ------------------------------------------------------------- |                                                   |
| https://jsonplaceholder.typicode.com/posts/1                    | id, title, body, userId                               |
| https://reqres.in/api/users/2                             | name, job                     |

<span class="pseudo-header">DELETE</span>

| URL                                                           |
| ------------------------------------------------------------- |
| https://jsonplaceholder.typicode.com/posts/1                  |
| https://reqres.in/api/users/2                             |

<span class="pseudo-header">HEAD</span>

| URL                                                           |
| ------------------------------------------------------------- |  
| https://jsonplaceholder.typicode.com/posts/1/comments        |
| https://api.data.gov.sg/v1/environment/2-hour-weather-forecast |
| https://cat-fact.herokuapp.com/facts                         |
| https://dog-facts-api.herokuapp.com/api/v1/resources/dogs?number=1 |

<span class="pseudo-header">PATCH</span>

| URL                                                           | Data (JSON)                                       |
| ------------------------------------------------------------- |                                                   |
| https://reqres.in/api/users/2                             | name, job                     |
| https://jsonplaceholder.typicode.com/posts/1                    | title, body, userId                               |

<span class="pseudo-header">OPTIONS</span>

| URL                                                           |
| ------------------------------------------------------------- |  
| http://imposter-dev.tk:6000/api/v1/options       |
| https://gorest.co.in/public-api/users |
