FROM tomcat:7.0.67-jre8

ARG AMP_EXPLODED_WAR
ARG AMP_PULL_REQUEST
ARG AMP_BRANCH
ARG AMP_REGISTRY_PRIVATE_KEY

LABEL "pull-request"=$AMP_PULL_REQUEST
LABEL "branch"=$AMP_BRANCH

ADD docker/wait-for-it.sh bin/
ADD docker/setenv.sh bin/
ADD docker/server.xml conf/

RUN rm -r /usr/local/tomcat/webapps/ROOT && mkdir /usr/local/tomcat/webapps/ROOT
ADD $AMP_EXPLODED_WAR /usr/local/tomcat/webapps/ROOT
ADD META-INF/context.xml /usr/local/tomcat/webapps/ROOT/META-INF/

ENV AMP_REGISTRY_PRIVATE_KEY $AMP_REGISTRY_PRIVATE_KEY
