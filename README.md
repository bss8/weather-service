# WeatherService
A Java RMI app built using Maven

There are three modules: api, server, and client. 

## Script Automation
#### Build and run
```
cd scripts
start.bat (windows)
start.sh (linux)
```

![Automated launch of RMI Registry, Server, and Client in order](resources/images/rmi_launch.gif)

## Build manually
From project root: 
```
mvn clean package
cp -r api/target/classes/* server/target/classes/
cp -r client/target/classes/* server/target/classes/
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
java -Djava.rmi.server.useCodebaseOnly=false \
 -Djava.rmi.server.codebase=file:/path/to/classes/ \
 -Djava.security.policy=/path/to/server.policy \
 edu.txstate.bss64.weatherserver.WeatherServiceServer
java -Djava.rmi.server.useCodebaseOnly=false \
 -Djava.rmi.server.codebase=file:/path/to/classes/ \
 -Djava.security.policy=/path/to/server.policy \
 edu.txstate.bss64.weatherclient.WeatherServiceClient
```
Ensure there is a final back or forward slash (depenidng on your OS)
at the end of the path to the classfiles for rmi.server.codebase.

### Class Diagram
![WeatherService UML Class Diagram](resources/images/WeatherService.png)

### Helpful Links

1. [Oracle RMI HelloWorld](https://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html)
2. [Oracle RMI Overview](https://docs.oracle.com/javase/tutorial/rmi/overview.html)