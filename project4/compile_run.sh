#!/bin/sh

cd

cd project3

mvn compile

mvn exec:java -Dexec.mainClass="source_XML_parsing.XmlParser" -Dexec.classpathScope=runtime -Dexec.cleanupDaemonThreads=false

mvn exec:java -Dexec.mainClass="project3.jdbc.AddOoPrice" -Dexec.classpathScope=runtime -Dexec.cleanupDaemonThreads=false

