#!bin/sh

cd src
mkdir build
javac -d build */*.java *.java
sleep 2
java -cp build/ Test
