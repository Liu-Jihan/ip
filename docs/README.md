Here's a well-structured and polished version of your user guide:

---

# OscarL User Guide

![OscarL Interface](docs/Ui.png)

## Introduction

Welcome to **OscarL**, your intelligent task management assistant! OscarL helps you manage your tasks, deadlines, and important places efficiently using simple commands.

## Features

### Adding Deadlines

You can add a task with a deadline using the `deadline` command.

#### Usage:
```plaintext
deadline <task description> /by <date>
```

#### Example:
```plaintext
deadline Finish project report /by 2025-02-25
```

#### Expected Output:
```plaintext
Got it! I've added this task:
[D] [ ] Finish project report (by: 2025-02-25)
```

---

### Managing Tasks

OscarL supports various commands to help you manage your tasks:

- **List Tasks:** `list` – Displays all tasks.
- **Mark Task as Done:** `mark <task index>` – Marks a task as completed.
- **Unmark Task:** `unmark <task index>` – Reverts a task to incomplete.
- **Delete Task:** `delete <task index>` – Removes a task from the list.
- **Add To-Do Task:** `todo <task description>` – Adds a simple task without a deadline.
- **Add Event:** `event <event description> /at <date/time>` – Adds an event with a specific date/time.

---

### Managing Places

OscarL also allows you to store and manage important locations.

#### Available Commands:
- **Add a Place:** `addplace <place name>` – Saves a location.
- **List Places:** `listplaces` – Displays all saved places.
- **Remove a Place:** `removeplace <place name>` – Deletes a saved location.

#### Example:
```plaintext
addplace NUS Library
```

#### Expected Output:
```plaintext
Got it! I've added this place: NUS Library
```

---

### Exiting the Program

When you're done, you can exit the program using:
```plaintext
bye
```

#### Expected Output:
```plaintext
Goodbye! See you next time.
```

---

