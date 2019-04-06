#!/bin/sh
[ -d Javadoc ] || mkdir Javadoc
javadoc -d Javadoc src/*/*.java src/*.java src/genetics/*/*.java src/genetics/*.java
