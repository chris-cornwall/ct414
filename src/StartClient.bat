TITLE Client
javac -cp ct414.jar client\GetAssessment.java
java -cp C:\Users\Andrew\Documents\College\java\ct414\src;C:\Users\Andrew\Documents\College\java\ct414\src\ct414.jar -Djava.rmi.server.codebase=file:C:\Users\Andrew\Documents\College\java\ct414\src\ct414.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=client.policy client.LoginScreen 
pause

