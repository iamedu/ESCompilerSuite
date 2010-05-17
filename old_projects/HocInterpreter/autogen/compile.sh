export CLASSPATH=lib/cup.jar:.
jlex hoc.lex
cup -expect 5 hoc.cup
javac *.java
