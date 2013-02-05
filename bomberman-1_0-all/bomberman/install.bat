@echo off
echo ..... Install .....
set CLASSPATH=.;..;../..
echo compilation du programme
javac Bomberman.java
echo creation de la javadoc
javadoc base element element.bloc element.joueur general niveau niveau.level reseau zone zone.zonejeu -d docs\javadoc -author -version
echo ..... Install End .....