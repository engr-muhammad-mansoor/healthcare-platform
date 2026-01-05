#!/bin/sh

echo "Starting"

date

set -a

date

exec java -javaagent:/dd-java-agent.jar -Ddd.trace.analytics.enabled=true -Ddd.profiling.enabled=true -Dspring.profiles.active=$ENV -Dspring.config.location=/var/run/vault/application-$ENV.properties -Ddd.logs.injection=true -jar $JAVA_OPTS -Djavax.net.ssl.trustStore="/cacerts" checkout-control-back.jar