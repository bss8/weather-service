echo "copying classes from API and Client to Server's target directory....."
cp  ../api/target/classes/ ../server/target/classes/ /E
robocopy ../client/target/classes/ ../server/target/classes/ /E
echo "going to Server's target/classes dir....."
cd ../server/target/classes/
echo "attempting to start the server....."
start java -Djava.rmi.server.useCodebaseOnly=false^
 -Djava.rmi.server.codebase=file:../server/target/classes/^
 -Djava.security.policy=../server/src/resources/server.policy^
 edu.txstate.bss64.weatherserver.WeatherServiceServer
echo "going back to scripts dir....."
cd ../../../scripts