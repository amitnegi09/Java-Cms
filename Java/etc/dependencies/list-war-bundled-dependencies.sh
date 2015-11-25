#!/bin/sh
echo "go-green war bundled dependencies " `date`
echo "\ncms.war bundled dependencies"
cd  ../../cms/target/cms/
echo "----------------------------------------------"
ls -x -w1 WEB-INF/lib/*.jar
echo "\nsite.war bundled dependencies"
echo "----------------------------------------------"
cd ../../../site/target/site/
ls -x -w1 WEB-INF/lib/*.jar
echo "\nDone"

