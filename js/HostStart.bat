@echo off
set /p WSadrs=Type the websocket address (E.g: ws://localhost (No Port): 
set /p WSport=Type the websocket port (E.g: 8000): 
set /p WSpass=Type in a Password (Default: "password"): 
set /p LOname=Type in a name for host (Name clients connect to): 
set /p LOport=Type in the Port of the Local Server (E.g Minecraft 25565): 
echo Starting host
node host %WSadrs% %WSport% %WSpass% %LOname% %LOport%
pause