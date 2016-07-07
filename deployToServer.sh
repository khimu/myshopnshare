mvn clean install -DskipTests=true


ssh root@107.170.234.144 'service tomcat7 stop'

ssh root@107.170.234.144 'rm -rf /var/lib/tomcat7/webapps/ROOT'

scp mss-war/target/mss-web-1.0.war root@107.170.234.144:/var/lib/tomcat7/webapps/ROOT.war

ssh root@107.170.234.144 'service tomcat7 start'

ssh root@107.170.234.144 'ps aux | grep "tomcat"'
