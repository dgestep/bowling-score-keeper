# Bowling Score Keeper
A very simple Java API for scoring and printing one or more bowling games.

### A single game

```Java
Bowler bowler = new Bowler("Wilma", "Flintstone");
GameManager game = new GameManagerImpl(bowler);

game.addFrame(new BowlingFrame(8, 1)); // open
game.addFrame(new BowlingFrame(8, 2)); // spare
game.addFrame(new BowlingFrame(10)); // strike
// simple print
System.out.println(game.getGame().getScore()); // 39

// produces a bowling score card
PrintManager printManager = new PrintManagerImpl();
printManager.printGame(processor.getGame(), System.out);
```

### Multiple games
```Java
GameManagerImpl game1 = new GameManagerImpl(new Bowler("Christene", "Kuhn"));
game1.addFrames(new BowlingFrame[] {
   new BowlingFrame(0, 2), new BowlingFrame(8, 1),
   new BowlingFrame(7, 2), new BowlingFrame(10),
   new BowlingFrame(5, 2), new BowlingFrame(9, 0),
   new BowlingFrame(6, 3), new BowlingFrame(7, 3),
   new BowlingFrame(9, 0), new BowlingFrame(8, 1)
});

GameManagerImpl game2 = new GameManagerImpl(new Bowler("Chris", "Kuhn"));
game2.addFrames(new BowlingFrame[] {
   new BowlingFrame(10), new BowlingFrame(10),
   new BowlingFrame(7, 2), new BowlingFrame(8, 0),
   new BowlingFrame(9, 1), new BowlingFrame(9, 1),
   new BowlingFrame(6, 3), new BowlingFrame(7, 3),
   new BowlingFrame(9, 0), new BowlingFrame(8, 2, true),
   new BowlingFrame(10)
});

GameManagerImpl game3 = new GameManagerImpl(new Bowler("Gwen", "Estep"));
game3.addFrames(new BowlingFrame[] {
   new BowlingFrame(8, 1), new BowlingFrame(9, 0),
   new BowlingFrame(10), new BowlingFrame(6, 2),
   new BowlingFrame(7, 2), new BowlingFrame(7, 3),
   new BowlingFrame(3, 5), new BowlingFrame(0, 7),
   new BowlingFrame(9, 1), new BowlingFrame(10),
   new BowlingFrame(10), new BowlingFrame(6)
});

GameManagerImpl game4 = new GameManagerImpl(new Bowler("Doug", "Estep"));
game4.addFrames(new BowlingFrame[] {
   new BowlingFrame(10), new BowlingFrame(10),
   new BowlingFrame(10), new BowlingFrame(9, 1),
   new BowlingFrame(9, 1), new BowlingFrame(8, 1, true),
   new BowlingFrame(8, 2), new BowlingFrame(9, 1),
   new BowlingFrame(9, 1), new BowlingFrame(10),
   new BowlingFrame(10), new BowlingFrame(9)
});

PrintManager printManager = new PrintManagerImpl();
Game[] games = {
   game1.getGame(), game2.getGame(),
   game3.getGame(), game4.getGame()
};
printManager.printGames(games, System.out);
// produces a score card with 4 games
// Christene Kuhn scores a 99
// Chris Kuhn scores a 155
// Gwen Estep scores a 127
// Doug Estep score a 212.
```

# Intellij Import
To author the code in Intellij, clone this repo and perform the following steps:

1. Open Intellij and close all projects
2. From the Welcome to Intellij IDEA popup window, click the Import Project selection
3. Choose "Import project from external model" choice and click the Gradle model
4. Choose the bowling-score-keeper/bowling.model folder. Choose the "Use gradle wrapper task configuration".  Leave all other choices as their default choice
5. Click Finish

# Eclipse Import
To author the code in Eclipse, clone this repo and perform the following steps:

1. Open Eclipse and select File -> Import...
2. Choose "Existing Gradle Project" under the Gradle wizard
3. If you're presented with a Welcome window, click next to continue
4. Click Browse to select the root directory where you cloned the code. Choose the bowling.model folder. Click Next.
5. Click the "Override workspace settings" and click the "Gradle wrapper" choice.
6. Click Next, then Finish.

# Run Unit Tests
1. Right-click on the src/test/java folder and select the JUnit option.

# Project Structure
The project includes a single model project, called bowler.model.  The intent of the bowler.model project is to serve as an API for a future GUI project which displays and manages bowling games (similar to what you would see in a bowling alley).  

## gradle
The project uses Gradle to build and manage its dependencies. When you first open the project in your favorite IDE, invoke gradle refresh (if it doesn't automatically execute) in order to download all dependencies.  Gradle will do all the work, including downloading itself.

The build.gradle file has built in support for code quality checks, such as checkstyle, PMD, and FindBugs.  It also includes the ability to report on code coverage.

You can run the checks by executing this at the terminal within the bowling-score-keeper/bowling.model folder:

```sh
./gradlew clean check
```

This executes the checkstyle, PMD, FindBugs, and the unit tests.  You should see "BUILD SUCCESSFUL" if all is good.  The reports are generated within the build/reports folder (checkstyle, findbugs, pmd, tests folders).

You can run the code coverage report by executing this at the terminal within the bowling-score-keeper/bowling.model folder:

```sh
./gradlew clean test jacocoTestReport
```

The report is generated within the build/reports/coverage folder.

You can build the project by executing this at the terminal within the bowling-score-keeper/bowling.model folder:

```sh
./gradlew clean build
```

This builds a JAR file in the build/libs folder called, bowling.model-1.0.jar. To change the version number on the JAR, change the "version" property set within the build.gradle file.
