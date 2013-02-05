@echo off
echo ..... Clean .....
echo suppression des fichiers .class .java.bak .java~
del .\*.class
del .\*.java.bak
del .\*.java~
del .\base\*.class
del .\base\*.java.bak
del .\base\*.java~
del .\element\*.class
del .\element\*.java.bak
del .\element\*.java~
del .\element\bloc\*.class
del .\element\bloc\*.java.bak
del .\element\bloc\*.java~
del .\element\joueur\*.class
del .\element\joueur\*.java.bak
del .\element\joueur\*.java~
del .\general\*.class
del .\general\*.java.bak
del .\general\*.java~
del .\niveau\*.class
del .\niveau\*.java.bak
del .\niveau\*.java~
del .\niveau\level\*.class
del .\niveau\level\*.java.bak
del .\niveau\level\*.java~
del .\reseau\*.class
del .\reseau\*.java.bak
del .\reseau\*.java~
del .\zone\*.class
del .\zone\*.java.bak
del .\zone\*.java~
del .\zone\zonejeu\*.class
del .\zone\zonejeu\*.java.bak
del .\zone\zonejeu\*.java~
echo suppression de la javadoc
del .\docs\javadoc\*.*
echo ..... Clean End .....