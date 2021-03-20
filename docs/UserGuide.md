---
layout: page
title: imPoster User Guide
nav-text: User Guide
---

<div style="page-break-after: always;"></div>
<br/>

## Table of Contents
{:.no_toc}

* Table of Contents 
{:toc}

<div style="page-break-after: always;"></div>

## 1. Welcome to imPoster

![user_guide_image](images/imPosterUserGuide.png)

Are you an aspiring API developer? Or would you like a peek into the world of how applications communicate with one another? Then **imPoster** is the app just for you! But hold on, what is imPoster?

imPoster is a desktop application for beginners of API development to quickly grasp the basics. Whether you are looking to **explore**, **test**, or **build** your very own APIs, the simple and minimalistic style of imPoster will quickly get you up and going.

This user guide assumes its users to have a **basic understanding** of APIs. If you are wondering [what an API is](#81-what-is-an-api), an appendix has been provided for users who may be unfamiliar with the concept. However, it is highly recommended for users to refer to proper tutorial contents for the basics of APIs prior to using the application.

For fast typists, imPoster is also highly optimised for the command line and can be fully operated through keyboard commands. Users who are familiar with **CURL** will also be happy to know that we share a very similar command line syntax. If you have yet to do so, be sure to download our [latest releases](https://imposter-dev.tk) from our main website and give us a try!

<div style="page-break-after: always;"></div>

## 2. Navigating the User Guide

Before diving into the rest of the contents in our user guide, the following are some important syntaxes to take note of to facilitate your reading:

| Syntax              | Description                                    |
| ------------------- | ---------------------------------------------- |
| **Bold**            | Important words to note                        |
| <kbd>Keyboard</kbd> | Keyboard actions                               |
| <div markdown="span" class="alert alert-warning custom-table-format">:bulb: Tips</div> | Useful tips |
| <div markdown="span" class="alert alert-danger custom-table-format">:exclamation: Caution</div> | Things to watch out for |
| <img class="custom-table-format" src="images/red_box.png" > | Annotations |
| `Markdown`          | Indicates full or part of a user command       |
| UPPER_CASE          | Parameters of a user command                   |
| [Square Brackets]   | Optional parameters of a user command          |
| ...                 | Parameters that may be added multiple times    |


<div style="page-break-after: always;"></div>

## 3. Quickstart

1. Ensure you have **Java 11 or above** installed in your Computer.

1. Download the latest **imposter.jar** from
   [here](https://github.com/AY2021S2-CS2103T-T12-4/tp/releases).

1. Move the file to the folder you wish to use as the **home folder** for
   imPoster.

1. **Double-click** the file to start the application. A graphical user interface (GUI) similar to the one
   below should appear after a few seconds. Note the **4 main components** in the layout of the application:<br>

<p align="center">
  <img width="800px" src="images/startscreen.png" >
</p>

1. Try making your first API call through our application with the first endpoint shown in the **Endpoint List**. Type `send 1` in the **Command Box** and then press <kbd>Enter</kbd>.
2. After which, try scrolling through the responses shown in the **Result Display** as well!
3. For a quick **overview** of all available commands, please refer to our [Command Summary](#6-command-summary).
4. For the **details** of each command, please proceed to the next section on [Commands](#4-commands).

<div style="page-break-after: always;"></div>

## 4. Commands

Commands are classified into 2 categories, namely **general** and **endpoint**. Before diving into the details, let us first look at what makes up a command:

| Component    | Description                                                           |
| ------------ | --------------------------------------------------------------------- |
| Command Word | The keyword representing the action of the command                    |
| Prefix       | The keyword to recognise command parameters                           |
| Parameters   | Follows directly behind a prefix and contains the corresponding value |

As an example, a basic command to add an endpoint could look like the following:

`add -x GET -u https://google.com`

In the above example, `add` is the command word, `-x` and `-u` are the prefixes while `GET` and `https://google.com` are the parameters. Note that the parameters allowed differ for each command and may be optional. For your convenience, a list of all parameters along with their prefixes and descriptions have been included in the following table:

| Parameter    | Prefix | Description                                                           |
| ------------ | ------ | --------------------------------------------------------------------- |
| INDEX        |  None  | The index of the endpoint as shown in the endpoint panel list         |
| THEME        |  None  | The theme for the application                                         |
| METHOD       |   -x   | The [request method](#83-request-methods) to use for an endpoint      |
| ADDRESS      |   -u   | The URL to use for an endpoint                                        |
| HEADER       |   -h   | The header to use for an endpoint **(must be enclosed with "")**      |
| DATA         |   -d   | The data to use for an endpoint **(must be in JSON format)**          |
| TAG          |   -t   | The tag to label an endpoint                 |

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Being familiar with the <a href="#2-navigating-the-user-guide"> syntaxes </a> will allow you to navigate the user guide even more easily
</div>

<div style="page-break-after: always;"></div>

### 4.1 General

General commands are typically basic commands that are unrelated to API endpoints.

#### 4.1.1 View help: `help`

**Description:** Get the link to the user guide to the application in the form of a pop up window.

**Format:** `help`

**Example:** `help`

![help message](images/helpMessage.png)

#### 4.1.2 Toggle theme: `toggle`

**Description:** Toggle the theme for the application.

**Format:** `toggle THEME`

**Example:** `toggle light`

<p align="center">
  <img width="400px" src="images/startscreen.png" >
</p>

#### 4.1.3 Exit program: `exit`

**Description:** Exit the application.

**Format:** `exit`

**Example:** `exit`

### 4.2 Endpoint

#### 4.2.1 Add an API endpoint: `add`

**Description:** Add an API endpoint to the API endpoint list.

**Format:** `add -x METHOD -u URL [-d DATA] [-h HEADER]… [-t TAG]…`

`DATA` must be in **JSON** format and `HEADER` must be enclosed in `" "` else an error message will be shown.

**Examples:**
- `add -x GET -u http://localhost:3000/ -d {"Content-Type": "application/json"} -h "key: value1" -h "key2: value2" -t local -t data`
- `add -x GET -u https://api.data.gov.sg/v1/environment/pm25`

<p align="center">
  <img width="400px" src="images/startscreen.png" >
</p>

**Tip:** An endpoint can have any number of **unique** tags and headers (including 0).

**Tip 2:** An endpoint can only have 0 or 1 data.

#### 4.2.2 Edit an API endpoint: `edit`

**Description:** Edit the API endpoint at the specified index shown in the API endpoint list (at least one optional argument must be provided).

**Format:** `edit INDEX [-x METHOD] [-u URL] [-d DATA] [-h HEADER]… [-t TAG]…`

**Examples:** `edit 1 -x GET -u http://localhost:3000/ -d {"Content-Type": "application/json"}`

**Tip:** Multiple tags must be unique and duplicates will be ignored.

#### 4.2.3 Show an API endpoint: `show`

Description: Show the details of the API endpoint at the specified index shown in the API endpoint list (index must 
be a positive integer).

Format: `show INDEX`

Examples:
- `show 1`
- `show 3`

#### 4.2.4 Remove an API endpoint: `remove`

Description: Remove the API endpoint at the specified index showin in the API endpoint list.

Format: `remove INDEX`

Examples:
- `remove 1`

#### 4.2.5 Find a saved API endpoint: `find`

Description: Find API routes containing the search word in any of its fields. (First Format)

Format: `find KEYWORD [MORE_KEYWORDS]…`

Examples:

- `find pm25`
- `find singapore pm25`


Description: Find API routes containing the search word in a specific field. (Second Format)

Format: `find -x KEYWORD [MORE_KEYWORDS]…`, `find -t KEYWORD [MORE_KEYWORDS]…` (able to use any prefix)

Examples:

- `find -x get`
- `find -t singapore pm25`

#### 4.2.6 List all saved API endpoints: `list`

Description: Show a list of all API endpoints in the API endpoint list.

Format: `list`

#### 4.2.7 Clear all saved API endpoints: `clear`

Description: Clear all API endpoints in the API endpoint list.

Format: `clear`

#### 4.2.8 Call a saved API endpoint: `send`

Description: Call an API endpoint from the API endpoint list.

Format: `send INDEX`

Examples:

- `send 1`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
You may cancel an API call with <kbd>ctrl</kbd> + <kbd>d</kbd>
</div>

#### 4.2.9 Call an API endpoint directly without saving: `run`

**Description:** Call an API endpoint on the fly (without saving). Two command formats are available. The first format performs a standard call to an API endpoint. (First Format)

**Format:** `run -x METHOD -u URL [-d DATA] [-h HEADER]…`

**Examples:** `run -x GET -u https://api.data.gov.sg/v1/environment/pm25`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
You may cancel an API call with <kbd>ctrl</kbd> + <kbd>d</kbd>
</div>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
A shorthand for `GET` requests can be done without specifying `-x` and `-u` (for example: `run https://api.data.gov.sg/v1/environment/pm25`).
</div>

### 4.3 Miscellaneous Information

#### 4.3.1 Autosave

imposter data are saved in the hard disk automatically after any command that
changes the data. There is no need to save manually.

#### 4.3.2 Data file

imPoster data are saved as a JSON file `[JAR file location]/data/imposter.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
imPoster will start with an empty file if a modification to it results causes existing data to be in an invalid format.
</div>

#### 4.3.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## 5. FAQ

**Q**: How do I transfer my data to another Computer?<br> **A**: Install the
application in the other computer and overwrite the empty data file it creates
with the file that contains the data of your previous imPoster home folder.

---

## 6. Command summary

A quick overview of all supported commands, their formats and examples are given below:

### 6.1 General

| Action     | Format                                | Example                                |
| ---------- | ------------------------------------- | -------------------------------------- |
| **Help**   | `help`                                | `help`                                 |
| **Toggle** | `toggle`                              | `toggle light`                         |
| **Exit**   | `exit`                                | `exit`                                 |

### 6.2 Endpoint

| Action     | Format                                | Example                                |
| ---------- | ------------------------------------- | -------------------------------------- |
| **Add**    | `add -x METHOD -u URL [-d DATA] [-h HEADER]… [-t TAG]…` <br>  | `add -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key: value1" -h "key: value2" -t local -t data` |
| **Edit**   | `edit INDEX [-x METHOD] [-u URL] [-d DATA] [-h HEADER]… [-t TAG]…`<br> | `edit 1 -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key: value" -h "key: value" -t local -t data` |
| **Show**   | `show INDEX`<br>                      | `show 1`                             |
| **Remove** | `remove INDEX`<br>                    | `remove 3`                             |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]…`<br> OR <br>`find -x KEYWORD [MORE_KEYWORDS]…`<br> | `find maps`<br> `find -x get`                            |
| **List**   | `list`                                | `list`                                 |
| **Clear**  | `clear`                               | `clear`                                |
| **Send**   | `send INDEX` <br>                     | `send 1`                               |
| **Run**    | `run -x METHOD -u URL [-d DATA] [-h HEADER]…` <br> OR <br>`run URL`  | `run -x GET -u https://api.data.gov.sg/v1/environment/uv-index -d {"some": "data"} -h "key: value"` <br> `run https://api.data.gov.sg/v1/environment/uv-index` |

## 7. Glossary
- **API (Application Programming Interface):** An interface for two systems to interact with each other
- **Endpoint:** The point of entry in a communication channel for two systems to interact with each other

## 8. Appendix

### 8.1 What is an API?

Broadly speaking, an API is an interface for two systems to interact with one another. In a classic analogy, the interaction above is usually likened to a waiter communicating a customer’s order to the restaurant kitchen, where the customer and kitchen represents two separate systems and the waiter represents the API allowing them to interact. The annotated diagrams below capture these interactions and may aid in providing a better understanding:

### 8.2 Why learn about APIs?

You may be surprised to know that APIs are not only widely used in our daily lives, it is also likely that you have been using them frequently without actually noticing them! Just to list a few examples, when you visit a website, an API is responsible for bringing back a response to you in the form of a webpage. When you drop your friend a message, an API is responsible for delivering your message as well as sending you your friend’s reply! The use of APIs is extensive in today’s highly connected world so even if they are completely unrelated to your job, it helps to have some basic understanding of them!

### 8.3 Request Methods

### 8.4 What is JSON format?
