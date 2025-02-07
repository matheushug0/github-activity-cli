# <img src="https://roadmap.sh/images/gifs/rocket.gif" width="25px"> Github Activity CLI

A command-line interface (CLI) application that allows you to quickly and easily track a user's recent activities on Github, directly from the terminal.

### Project suggested by [Roadmap.sh - ](https://roadmap.sh/projects/github-user-activity)[**Backend Developer**](https://roadmap.sh/projects/github-user-activity).

## Features

- **Search** for recent activities of a specific Github user.
- **Display** events such as **Push, Issues, Create, Watch**, and others.
- **Exception handling** and error handling for **requests** to the **Github API**.

---

## How It Works

1. The application makes an `HTTPRequest` to the endpoint `https://api.github.com/users/<USERNAME>/events`.
2. If the **response** is valid, the `response.body()` is processed by the [Gson](https://github.com/google/gson) library, which converts the JSON into an Array. In case of errors, the application displays appropriate messages for issues such as non-existent users, exceeded request limits, or connection failures.
3. The obtained data is processed according to the event `type` and formatted for display in the terminal.

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/matheushug0/github-activity-cli.git
cd github-activity-cli
```

2. Build the Maven project (minimum recommended version: 3.6.3):

```bash
mvn clean package install
```

3. Run the application:

```bash
java -jar target/github-activity-cli-1.0.jar <username>
```

---

## Usage

```bash
java -jar target/github-activity-cli-1.0.jar matheushug0
```

### Example Output:

```
Pushed 3 commits to matheushug0/task-tracker 4 days ago
Created in matheushug0/task-tracker 5 days ago
Opened a new Issue in matheushug0/calculadora99pay 8 days ago
Pushed 1 commit to matheushug0/imagesliteapi 34 days ago
Created in matheushug0/imagesliteapi 34 days ago
```

---

## Project Structure
Below is the organization of the project's directories and files:

```
github-activity
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/
│   │           └── com/
│   │               └── mh/
│   │                   └── Main.java
├── target/   # Maven build output (ignored by .gitignore)
├── pom.xml   # Maven configuration
└── README.md # This file
```

---

## Technologies Used

- **Java**
- **Maven**
- **Github API**
- **Gson**

---

## Contact

- [**LinkedIn**](https://www.linkedin.com/in/matheus-hugo/)

---

## References

- [Github API](https://docs.github.com/en/rest?apiVersion=2022-11-28)
- [Gson](https://github.com/google/gson)
- [lephuocloc1729](https://github.com/lephuocloc1729/github-user-activity-cli)
- [iOmega17](https://github.com/iOmega17/GitHub-activity-CLI)
