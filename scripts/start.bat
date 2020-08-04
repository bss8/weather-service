rem Author: Borislav S. Sabotinov
rem script to build and launch all components of the RMI app

call build.bat
TIMEOUT /T 3
call startrmi.bat
TIMEOUT /T 4
call startserver.bat
TIMEOUT /T 2
call startclient.bat