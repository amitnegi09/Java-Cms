@echo off
SET JAVA_HOME=C:\Program Files\Java\jdk1.7.0_80
    
mvn -Dmaven.test.skip=true  -Dcargo.maven.containerId=tomcat7x -Dcargo.maven.containerUrl=http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.47/bin/apache-tomcat-7.0.47.zip -Drepo.path=C:\Users\admin\Projects\Mootly\cmsrepositories\hippo-solutions\branch_indiantaxonline install