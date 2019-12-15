Steps of deploy:
1. if jar version has changed in build.gradle, developer needs to modify jar name in RunFtpService.sh file 
2. command: gradle myPackpage
3. after deploy to target computer, developer needs to change the following path:
   java path and application directory in RunFtpService.sh file
   log configuration file location in config/application.properties
   logging files location in config/logback.xml


Run Service:
1. ./RunFtpService.sh start
2. ./RunFtpService.sh stop
3. ./RunFtpService.sh restart
4. ./RunFtpService.sh  ==> in this case, the service would be started if not yet started. If the service is already started, shall file would do nothing.

Run Jar:
jaa -jar FtpService-1.0.jar

Notice:
In the program I keep the function of running the app as Linux Daemon, but in the end I do not run the app as Linux Daemon.
Linux related funtion/library:
LinuxDaemonMainClass.java
lib/commons-daemon-1.2.2.jar
linuxInstall/CMTFtpService.sh
linuxInstall/set_auto_start.sh