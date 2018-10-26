#!/bin/bash

. /etc/profile

tomcat_home="/datadisk/server/apache-tomcat-1"
code_home="/datadisk/code/maven-cms-web"

cd ${code_home}
mvn clean
mvn package

ps aux |grep -v grep |grep apache-tomcat-1|awk '{print $2}'|xargs kill -9
cp ${code_home}"/target/maven-cms-web.war" ${tomcat_home}"/webapps/"

cd ${tomcat_home}"/work"
rm -rf *

cd ${tomcat_home}"/webapps/"
jar -xvf maven-cms-web.war
rm -f maven-cms-web.war

sh ${tomcat_home}"/bin/startup.sh"
