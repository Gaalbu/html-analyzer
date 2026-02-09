EASTER_EGG_URLS

This is an study project, for understanding of how java native libraries handle HTML

This project indentifies the deepest text in a HTML structure gotten by the URL

## The Project Logic
An Stack structure was used to track the HTML tags hierarchy:

- **Opening Tags:** Pushed onto the Stack to increase the current depth level.
- **Closing Tags:** Popped from the Stack after validating if they match the most recent opening tag.
- **Text Content:** When a line of text is encountered, its depth is compared against the current maximum depth.
- **Validation:** The system monitors the stack's integrity. If a closing tag doesn't match the opening tag or if the stack is not empty at the end of the process, it returns `malformed HTML`.

## Constraints and Rules
To comply with the project requirements, the solution follows these premises:
- Uses only **Pure Java** (JDK 17) with no external libraries.
- Handles HTML line by line (as per the requirement that each line contains only one type of content).
- Identifies the **first** occurrence of a text segment in case of a depth tie.

## How to Run
1. **Compile the program:**
   ```bash
   javac HtmlAnalyzer.java