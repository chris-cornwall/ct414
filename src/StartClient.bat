TITLE Client
javac -cp ct414.jar client\GetAssessment.java
java -cp C:\Users\cornwall\Desktop\ct414\src;C:\Users\cornwall\Desktop\ct414\src\ct414.jar -Djava.rmi.server.codebase=file:C:\Users\cornwall\Desktop\ct414\src\ct414.jar -Djava.rmi.server.hostname=localhost -Djava.security.policy=client.policy client.ClientControl
pause

