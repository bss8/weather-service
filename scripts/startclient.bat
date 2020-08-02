cd ..\server\target\classes
start java -Djava.rmi.server.useCodebaseOnly=false^
 -Djava.rmi.server.codebase=file:C:\Users\boris\Downloads\WeatherService\server\target\classes\^
 -Djava.security.policy=C:\Users\boris\Downloads\WeatherService\server\src\resources\server.policy^
 edu.txstate.bss64.weatherclient.WeatherServiceClient
cd ..\..\..\scripts