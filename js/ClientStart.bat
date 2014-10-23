@echo off
set /p WSadrs=Type the websocket address (E.g: ws://localhost (No Port): 
set /p WSport=Type the websocket port (E.g: 8000): 
set /p LOname=Type in a name of the host (Name of service to connect to): 
set /p LOport=Type in the Port of the Local Server (Port for you to connect to): 
echo Starting client on %LOport%
node client %WSadrs% %WSport% %LOname% %LOport%
pause