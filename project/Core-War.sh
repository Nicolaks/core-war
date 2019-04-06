#!bin/sh

[ -d build ] || mkdir build
javac -d build src/*/*.java src/*.java src/genetics/*/*.java src/genetics/*.java
sleep 2
java -cp build/ Demo
sleep 1000
