TITLE Server
::start rmiregistry
javac server\*.java
javac client\*.java
jar cf ct414.jar server\*.class 
javac -cp ct414.jar server\ExamEngine.java
java -cp C:\Users\Andrew\Documents\College\java\ct414\src;C:\Users\Andrew\Documents\College\java\ct414\src\CT414.jar -Djava.rmi.server.codebase=file:C:\Users\Andrew\Documents\College\java\ct414\src\CT414.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy server.ExamEngine
pause
