TIMEOUT /T 6
call build.bat
TIMEOUT /T 3
call startrmi.bat
TIMEOUT /T 4
call startserver.bat
TIMEOUT /T 2
call startclient.bat