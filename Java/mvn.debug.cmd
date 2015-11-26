
SET JAVA_HOME=C:\Program Files\Java\jdk1.7.0_80

set MAVEN_OPTS=-Xmx1024M
mvn -P cargo.run -Dcargo.maven.containerId=tomcat7x -Dcargo.maven.containerUrl=http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.40/bin/apache-tomcat-7.0.40.zip -Drepo.path=D:\Java-Cms\Java\repository -Dcargo.debug.address=8003 -Dcargo.debug.suspend=y