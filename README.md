# TopologyAPI
Providing the functionality to access, manage and store device topologies.

## Description
- A topology is a set of electronic components that are connected together.
- The app is about writing an API library which does the following:
    1. Reading and writing topologies to and from disk.
    2. Storeing multiple topologies in memory.
    3. Executing operations on topologies.
- Automated Testing is used to test the work of the existing functionalities.
- Strong Handling of Expected Errors and Different Scenarios on Dialog with the User.
- Take a look on The Working Tree [javadoc Documentaion](javadoc/index.html).

## Used
- Java console application
</br>(Java is used as it uses garbage collector so no need for managed pointers and it also provides a good OOP Structure)
- [*OOP*](https://www.w3schools.com/java/java_oop.asp)
- [*maven*](https://maven.apache.org/)
- [*Javadoc*](https://www.oracle.com/java/technologies/javase/javadoc-tool.html)
- [*JUnit 5*](https://junit.org/junit5/)
- [*IntelliJ*](https://www.jetbrains.com/idea/)

## Working Shots

| InputGuide | DeletingBeforeReading | IncorrectFilePathForReading |
| :--------: | :-------------------: | :-------------------------: |
![](RuntimeScreenshots/1.%20InputGuide.png) | ![](RuntimeScreenshots/2.%20DeletingBeforeReading.png) | ![](RuntimeScreenshots/3.%20IncorrectFilePathForReading.png) |
| CorrectFilePathForReading | DuplicateFilePathForReading | QueryTopologies |
![](RuntimeScreenshots/4.%20CorrectFilePathForReading.png) | ![](RuntimeScreenshots/5.%20DuplicateFilePathForReading.png) | ![](RuntimeScreenshots/6.%20QueryTopologies.png) |
| ValidWriteProcess | WrongTopologyID | QueryingAllComponents |
![](RuntimeScreenshots/7.%20ValidWriteProcess.png) | ![](RuntimeScreenshots/8.%20WrongTopologyID.png) | ![](RuntimeScreenshots/9.%20QueryingAllComponents.png) |
| QueryingWithWrongNodeID | QueryingWithCorrectNodeID | DeletingTopologies |
![](RuntimeScreenshots/10.%20QueryingWithWrongNodeID.png) | ![](RuntimeScreenshots/11.%20QueryingWithCorrectNodeID.png) | ![](RuntimeScreenshots/12.%20DeletingTopologies.png) |
