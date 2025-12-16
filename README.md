# A Super Good Name
## As part of Programming 1 (420-101-VA) at Vanier College

### Description

This project was executed within a pedagogical context, for the Programming 1 course (420-101-VA) at Vanier College. It served as the final examiniation for this course. This project seeks to model a digital service which allows users to read through and write cookbooks (Referred to by the program as RecipeBooks.) through a computer terminal. It features two pre-written cookbooks, each containing several pre-written recipes. 

This project is able to model two types of recipes; Simple recipes and complex recipes. A recipe is considered to be simple if it uses only unambiguous, singular steps. In contrast, a recipe is considered to be complex if it uses more elaborate, composite steps. This translates to a complex recipe being composed internally of multiple simple recipes. Concretely, a simple recipe may represent a single dish within a meal, whereas a complex recipe may represent an entire meal. Alternatively, a complex recipe may represent a dish with multiple discrete parts, such as a recipe for a whole pasta dish from scratch, whereas a simple recipe may represent a single part of a larger dish, such as the sauce for the afformentioned pasta. 

Additionally, this project features recipe *Tags*, single-word descriptors for a recipe which allow the user to, at a glance, understand important details about a recipe, such as diet or intended meal. Tags may be added by editing the *Tags.java* file. New tags should be formatted in screaming snake case - that is to say fully capatalized, with spaces represented by underscores ( _ ). Please do not remove any tags already present, as this may lead to undesired failures. 

This project also allows for searching through the recipes contained within a cookbook. This is done by navigating to the desired cookbook within the user interface, selecting a search parameter, and entering a search term. When entering a search term, please be sure to follow exact casings, as the search methods are case-sensitive. The currently implemented search parameters are as such: Title, Ingredients, Maximum Estimated Time, and Tags.

### To Run

#### Running the Main Program

In order to facilitate running this project outside of an IDE, a shell script, *easyRun.sh*, has been additionally included. To run this project using said script, first navigate to this project's directory using a bash-based terminal, then run the command `./easyRun.sh`. This script will automatically compile all relevant Java source files, then run the *main()* method, from the *Main* class, which will initialize the program.

#### Running Tests (Within VS Code)

This project also features a full test suite, using JUnit Jupiter. To run these tests from VS Code, the user must first have the *Test Runner for Java by Microsoft* extension installed. This extension is included within the default *Extension Pack for Java by Microsoft* extension. The user can then navigate to the *Testing* tab on the sidebar, this tab is represented by an erlenmeyer containing some liquid. They can then run the tests by clicking the *Run Tests* button at the top of the tab, represented by two "Play" buttons layered over each other. If, for whatever reason, the JUnit package is lost, it may be redownloaded [here](https://junit.org). If redownloading, please be sure to save the jar file(s) under `\lib` in the project directory, and to download the *Jupiter* ( *JUnit 6* ) edition.

### Future Improvements

In the future, this project could be improved on by implementing the following improvements:
- Persistent new cookbooks/recipes through a save file system.
- More accessible cookbook/recipe creation menu.
- More detailed documentation.

### Authors & Acknowledgment

Developped by [Mathieu Bernardin](https://github.com/m-bernardin) and [Madeleine Siwick](https://github.com/maddysiwick).

Special thanks to [Tássia Camões Araùjo](https://github.com/tassia) for teaching us and guiding us this semester!

UML made with the support of [UML Generator for Java by 0SMA0](https://marketplace.visualstudio.com/items?itemName=0SMA0.uml-generator).

Test suite made with the support of [JUnit 6 "Jupiter"](https://junit.org).

### License

This project is licensed under the GNU General Public License v3.0