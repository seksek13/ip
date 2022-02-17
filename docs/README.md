# Sumikko User Guide

## Introduction

Summikko is a app that aids in managing daily tasks. It is designed for users who enjoys working with Command Line Interface. 

## Quick Start

1. Ensure that Java 11 is installed
2. Download the sumikko.jar file [here] 
3. Move the file to your desired home directory
4. Double click to open the app
5. Key in the commands below to enjoy the app features!


## Features 

### 1. Listing all tasks : `list`

Show a list of all tasks added to the list

Command Format: `list`

Example of usage: 

`list`

Expected outcome:



### 2. Adding Todo Task : `todo`

Adds basic task that does not contain any date/time

Command Format: `todo TASK_DESCRIPTION`

Example of usage: 

`todo math assignment`

Expected outcome:


### 3. Adding Deadline Task : `deadline`

Adds task with deadline date/time

Commmand Format: `deadline TASK_DESCRIPTION /by dd/MM/yyyy HH:mm`

Example of usage: 

`deadline submit assignments /by 10/12/2022 17:00`

Expected outcome:


### 4. Adding Event Task : `event`

Adds an event task 

Command Format: `event TASK_DESCRIPTION /at dd/MM/yyyy HH:mm`

Example of usage: 

`event group meeting /at 02/08/2022 14:30`

Expected outcome:



## Marking task as done : `mark`

Marks a task as done [x]

Command Format: `mark TASK_NUMBER`

Example of usage: 

`mark 1`

Expected outcome:


## Unmarking task as done : `unmark`

Unmark a task as done [x]

Command Format: `unmark TASK_NUMBER`

Example of usage: 

`unmark 1`

Expected outcome:


## Deleting task : `delete`

Removing a task from list

Command Format: `delete TASK_NUMBER`

Example of usage: 

`delete 3`

Expected outcome:


## Finding a task : `find`

Search for a particular task by entering keywords and return all tasks that contains the keyword in its task description

Commnad Format: `find KEYWORD`

Example of usage: 

`find book`

Expected outcome:


## Reminding tasks : `reminder`

Reminders about task that will be due on the date entered by user

Command Format: `reminder dd/MM/yyyy HH:mm`

Example of usage: 

`reminder 13/12/2022 00:00`

Expected outcome:


## Exiting the app : `bye`

Exit and close the app

Command Format: `bye`

Example of usage: 

`bye`



